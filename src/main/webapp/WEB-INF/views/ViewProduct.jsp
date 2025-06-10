<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Product</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <style>
        .product-card {
            max-width: 600px;
            margin: 50px auto;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            border-radius: 15px;
            overflow: hidden;
        }
        .product-image {
            width: 100%;
            height: auto;
            border-bottom: 1px solid #eee;
        }
        .product-details {
            padding: 25px;
        }
        .product-title {
            font-size: 1.8rem;
            font-weight: bold;
        }
        .product-price {
            font-size: 1.5rem;
            color: #28a745;
        }
        .product-category {
            font-size: 1rem;
            color: #6c757d;
        }
    </style>
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
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
