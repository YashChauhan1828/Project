<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>New Product</h2>
<form action="saveproduct" method="post" enctype="multipart/form-data">
Product Name: <input type="text" name="productName"/><br>
Category: <input type="text" name="category"/><br>
Price: <input type="text" name="price"/><br>
Quantity: <input type="text" name="qty"/><br>
MasterImage: <input type="file" name="productImage"/><br><br>
<input type="submit" value="Add Product"/>
</form>
</body>
</html>