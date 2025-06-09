<%@page import="com.entity.EcomProductEntity"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>List products</h1>
	<a href="newproduct">New Product </a>&nbsp
	<a href="deleteproductbyname">Delete Product by Name</a><br><br>
	<a href="ehome">home</a><br><br>
	
	<% List<EcomProductEntity> products = (List<EcomProductEntity>) request.getAttribute("products"); %>
<table border="1">
	<tr>
		<th>ProductId</th>
		<th>ProductName</th>
		<th colspan="2">Action</th>
	</tr>
	<% 
		for(EcomProductEntity p : products)
		{
			out.print("<tr>");
			out.print("<td>"+p.getProductId()+"</td><td>"+p.getProductName() + "</td><td><a href='deleteproducts?productId="+p.getProductId()+"'>Delete</a></td><td><a href='viewproduct?productId="+p.getProductId()+"'>View</a></td>");
			out.print("</tr>");
		}
	
	%>
</table>
</body>
</html>