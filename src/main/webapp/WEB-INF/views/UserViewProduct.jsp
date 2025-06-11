
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Product</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <style>
        body {
            background-color: #121212;
            color: #fff;
            font-family: 'Segoe UI', sans-serif;
        }
        .product-card {
            background-color: #1e1e1e;
            border-radius: 12px;
            max-width: 700px;
            margin: 60px auto;
            box-shadow: 0 0 20px rgba(255, 255, 255, 0.05);
            padding: 30px;
        }
        .product-image {
            width: 200px;
            height: auto;
            border-radius: 10px;
            box-shadow: 0 0 8px rgba(255, 255, 255, 0.1);
        }
        .product-title {
            font-size: 1.8rem;
            font-weight: bold;
            margin-top: 20px;
        }
        .product-price {
            font-size: 1.4rem;
            color: #4caf50;
        }
        .product-category {
            font-size: 1rem;
            color: #aaa;
        }
        .product-description {
            font-size: 1rem;
            color: #ddd;
            margin-top: 15px;
        }
        .btn-outline-light:hover {
            color: #121212;
            background-color: #fff;
        }
        .action-buttons {
            margin-top: 20px;
        }
        .review-form, .reviews {
            margin-top: 40px;
        }
        .review-card {
            background-color: #2a2a2a;
            border-radius: 10px;
            padding: 15px;
            margin-bottom: 15px;
            box-shadow: 0 0 5px rgba(255,255,255,0.05);
        }
        .review-rating {
            color: #f4c430;
        }
        .form-control, .form-select {
            background-color: #1e1e1e;
            color: #fff;
            border: 1px solid #444;
        }
        textarea::placeholder {
            color: #aaa;
        }
    </style>
</head>
<body>

<div class="product-card text-center">
    <img class="product-image" src="${product.productImagePath}" alt="${product.productName}">
    <div class="product-title">${product.productName}</div>
    <div class="product-price">₹ ${product.price}</div>
    <div class="product-category">Category: ${product.category}</div>

    <div class="product-description">
        <hr style="background-color:#333;">
        <p>This product is made with high-quality materials and is designed to meet your everyday needs. Experience excellence, comfort, and performance with every purchase.</p>
    </div>

    <div class="action-buttons">
        <a href="addtocart?productId=${product.productId}" class="btn btn-success">Add to Cart 🛒</a>
        <a href="userproducts" class="btn btn-outline-light ml-2">← Back to Products</a>
    </div>

  
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
