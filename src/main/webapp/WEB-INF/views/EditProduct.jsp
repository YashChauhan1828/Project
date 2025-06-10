<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
    <div class="product-card bg-white">
        <img class="product-image" src="${product.productImagePath}" alt="${product.productName}">
        <div class="product-details">
            <div class="product-title">${product.productName}</div>
            <div class="product-price">₹ ${product.price}</div>
            <div class="product-category">Category: ${product.category}</div>
            
            <a href="products" class="btn btn-outline-dark mt-4">← Back to Products</a>
        </div>
    </div>
    <h2>New Product</h2>
<form action="updateproduct" method="post" enctype="multipart/form-data">
<input type="hidden" name="productId" value="${product.productId }"/>
Product Name: <input type="text" name="productName" value="${product.productName}"/><br>
Category: <input type="text" name="category" value="${product.category}"/><br>
Price: <input type="text" name="price" value="${product.price}"/><br>
Quantity: <input type="text" name="qty" value="${product.qty}"/><br>
MasterImage: <input type="file" name="productImage" value="${product.productImagePath}"/><br><br>
<input type="submit" value="Update Product"/>
</form>
</div>
</body>
</html>