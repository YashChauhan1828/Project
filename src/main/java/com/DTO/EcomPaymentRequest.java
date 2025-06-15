package com.DTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EcomPaymentRequest 
{
	String creditcardnumber;
	String date;
	String cvv;
	Float price;
	String email;
}
