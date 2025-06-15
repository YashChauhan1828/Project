<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Checkout - Payment</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<!-- Header -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark px-4">
    <a class="navbar-brand" href="#">E-Shop</a>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ms-auto">
            <li class="nav-item"><a class="nav-link" href="ehome">Home</a></li>
            <li class="nav-item"><a class="nav-link" href="userproducts">Products</a></li>
            <li class="nav-item"><a class="nav-link" href="mycart">Cart</a></li>
            <li class="nav-item"><a class="nav-link" href="logout">Logout</a></li>
        </ul>
    </div>
</nav>

<!-- Main Content -->
<div class="container mt-5">
    <h2 class="mb-4 text-center">💳 Secure Checkout</h2>

    <div class="card shadow p-4">
        <form action="epayment" method="post">
            <div class="mb-3">
                <label class="form-label fw-bold">Final Amount (₹)</label>
                <input type="text" class="form-control" value="${totalPrice}" readonly>
                <input type="hidden" name="price" value="${totalPrice}">
            </div>

            <div class="mb-3">
                <label class="form-label">Credit Card Number</label>
                <input type="text" name="creditcardnumber" class="form-control" placeholder="Enter your credit card number">
                <div class="text-danger small">${CardError}</div>
            </div>

            <div class="row">
                <div class="col-md-6 mb-3">
                    <label class="form-label">Expiry Date (MM/YY)</label>
                    <input type="text" name="date" class="form-control" placeholder="e.g. 08/27">
                    <div class="text-danger small">${DateError}</div>
                </div>

                <div class="col-md-6 mb-3">
                    <label class="form-label">CVV</label>
                    <input type="text" name="cvv" class="form-control" placeholder="e.g. 123">
                    <div class="text-danger small">${CVVError}</div>
                </div>
            </div>

            <button type="submit" class="btn btn-primary w-100 mt-3">Pay Now</button>
        </form>
    </div>
</div>

<!-- Footer -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<footer class="bg-dark text-white mt-5">
    <div class="container py-5">
        <div class="row">
            <!-- About Section -->
            <div class="col-md-4">
                <h5>About E-Shop</h5>
                <p>
                    E-Shop is your one-stop online store for electronics, fashion, and more.  
                    We deliver quality products with fast and secure service.
                </p>
            </div>

            <!-- Quick Links -->
            <div class="col-md-4">
                <h5>Quick Links</h5>
                <ul class="list-unstyled">
                    <li><a href="ehome" class="text-white text-decoration-none">Home</a></li>
                    <li><a href="userproducts" class="text-white text-decoration-none">Products</a></li>
                    <li><a href="mycart" class="text-white text-decoration-none">My Cart</a></li>
                    <li><a href="inputmail" class="text-white text-decoration-none">Checkout</a></li>
                    <li><a href="logout" class="text-white text-decoration-none">Logout</a></li>
                </ul>
            </div>

            <!-- Contact Info -->
            <div class="col-md-4">
                <h5>Contact Us</h5>
                <p>
                    📍 Surat, Gujarat, India<br>
                    📞 +91 98765 43210<br>
                    ✉️ support@eshop.com
                </p>
                <div>
                    <a href="#" class="text-white me-3"><i class="bi bi-facebook"></i></a>
                    <a href="#" class="text-white me-3"><i class="bi bi-twitter"></i></a>
                    <a href="#" class="text-white"><i class="bi bi-instagram"></i></a>
                </div>
            </div>
        </div>
        <hr class="border-light mt-4">
        <div class="text-center">
            &copy; 2025 E-Shop. All rights reserved.
        </div>
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
