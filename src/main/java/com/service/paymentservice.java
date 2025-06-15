package com.service;


import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DTO.EcomPaymentRequest;
import com.entity.EcomShippingEntity;

import jakarta.servlet.http.HttpSession;
import net.authorize.Environment;
import net.authorize.api.contract.v1.*;
import net.authorize.api.controller.CreateTransactionController;
import net.authorize.api.controller.base.ApiOperationBase;

@Service
public class Paymentservice {
    
	@Autowired
	HttpSession session;
	
    public ANetApiResponse run(EcomPaymentRequest paymentbean , String email  ) {
    	
    	String apiLoginId = "7b6SwXH2J";
    	String transactionKey = "44Cn4CY2Q5tb3vCM";
    	
        // Set the request to operate in either the sandbox or production environment
        ApiOperationBase.setEnvironment(Environment.SANDBOX);

        // Create object with merchant authentication details
        MerchantAuthenticationType merchantAuthenticationType  = new MerchantAuthenticationType() ;
        merchantAuthenticationType.setName(apiLoginId);
        merchantAuthenticationType.setTransactionKey(transactionKey);

        // Populate the payment data
        PaymentType paymentType = new PaymentType();
        CreditCardType creditCard = new CreditCardType();
        creditCard.setCardNumber(paymentbean.getCreditcardnumber());
        creditCard.setExpirationDate(paymentbean.getDate());
        paymentType.setCreditCard(creditCard);

        // Set email address (optional)
        CustomerDataType customer = new CustomerDataType();
        customer.setEmail(email);
        EcomShippingEntity shippingbean = (EcomShippingEntity) session.getAttribute("ship");
		System.out.println("Name : "+shippingbean.getRecipientName());
        
//        customer.setId(userbean.getUserId());

        // Create the payment transaction object
        TransactionRequestType txnRequest = new TransactionRequestType();
        txnRequest.setTransactionType(TransactionTypeEnum.AUTH_CAPTURE_TRANSACTION.value());
        txnRequest.setPayment(paymentType);
        txnRequest.setCustomer(customer);
        
        // Calculate base price and tax
        
        BigDecimal basePrice = new BigDecimal(paymentbean.getPrice());
        BigDecimal taxAmount = basePrice.multiply(new BigDecimal("0.18")).setScale(2, RoundingMode.CEILING);
        BigDecimal totalAmount = basePrice.add(taxAmount).setScale(2, RoundingMode.CEILING);

        // Set tax
        ExtendedAmountType tax = new ExtendedAmountType();
        tax.setAmount(taxAmount);
        tax.setName("GST");
        tax.setDescription("Government Tax");
        txnRequest.setTax(tax);

        // Set total amount (base price + tax)
        txnRequest.setAmount(totalAmount);

        
        NameAndAddressType customeraddress = new NameAndAddressType();
        customeraddress.setFirstName(shippingbean.getRecipientName());
        customeraddress.setAddress(shippingbean.getAddress());
        customeraddress.setCity(shippingbean.getCity());
        customeraddress.setCountry(shippingbean.getCountry());
        customeraddress.setZip(shippingbean.getZipCode());
     // After customeraddress is populated
        txnRequest.setShipTo(customeraddress);
       
        // Create the API request and set the parameters for this specific request
        CreateTransactionRequest apiRequest = new CreateTransactionRequest();
        apiRequest.setMerchantAuthentication(merchantAuthenticationType);
        apiRequest.setTransactionRequest(txnRequest);

        // Call the controller
        CreateTransactionController controller = new CreateTransactionController(apiRequest);
        controller.execute();

        // Get the response
        CreateTransactionResponse response = new CreateTransactionResponse();
        response = controller.getApiResponse();
//        
        

        // Parse the response to determine results
        if (response!=null) {
            // If API Response is OK, go ahead and check the transaction response
            if (response.getMessages().getResultCode() == MessageTypeEnum.OK) {
                TransactionResponse result = response.getTransactionResponse();                
                if (result.getMessages() != null) {
                    System.out.println("Successfully created transaction with Transaction ID: " + result.getTransId());
                    System.out.println("Response Code: " + result.getResponseCode());
                    System.out.println("Message Code: " + result.getMessages().getMessage().get(0).getCode());
                    System.out.println("Description: " + result.getMessages().getMessage().get(0).getDescription());
                    System.out.println("Auth Code: " + result.getAuthCode());             
                    System.out.println("Email : "+customer.getEmail());
                    System.out.println("Name : "+customeraddress.getFirstName());
                    System.out.println("Address : "+customeraddress.getAddress());
                    System.out.println("City : "+customeraddress.getCity());
                    System.out.println("Country : "+customeraddress.getCountry());
                    System.out.println("zip : "+customeraddress.getZip());
                } else {
                    System.out.println("Failed Transaction.");
                    if (response.getTransactionResponse().getErrors() != null) {
                        System.out.println("Error Code: " + response.getTransactionResponse().getErrors().getError().get(0).getErrorCode());
                        System.out.println("Error message: " + response.getTransactionResponse().getErrors().getError().get(0).getErrorText());
                    }
                }
            } else {
                System.out.println("Failed Transaction.");
                if (response.getTransactionResponse() != null && response.getTransactionResponse().getErrors() != null) {
                    System.out.println("Error Code: " + response.getTransactionResponse().getErrors().getError().get(0).getErrorCode());
                    System.out.println("Error message: " + response.getTransactionResponse().getErrors().getError().get(0).getErrorText());
                } else {
                    System.out.println("Error Code: " + response.getMessages().getMessage().get(0).getCode());
                    System.out.println("Error message: " + response.getMessages().getMessage().get(0).getText());
                }
            }
        } else {
            // Display the error code and message when response is null 
            ANetApiResponse errorResponse = controller.getErrorResponse();
            System.out.println("Failed to get response");
            if (!errorResponse.getMessages().getMessage().isEmpty()) {
                System.out.println("Error: "+errorResponse.getMessages().getMessage().get(0).getCode()+" \n"+ errorResponse.getMessages().getMessage().get(0).getText());
            }
        }
        
        return response;
    }
}
