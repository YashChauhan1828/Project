<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Email Verification</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .form-container {
            max-width: 500px;
            margin: 80px auto;
            padding: 30px;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0px 0px 12px rgba(0, 0, 0, 0.1);
        }
        footer {
            background-color: #343a40;
            color: white;
            padding: 20px 0;
            margin-top: 100px;
        }
    </style>
</head>
<body>

<!-- Header -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark px-4">
    <a class="navbar-brand font-weight-bold" href="ehome">E-Shop</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item"><a class="nav-link" href="ehome">Home</a></li>
            <li class="nav-item"><a class="nav-link" href="userproducts">Products</a></li>
            <li class="nav-item"><a class="nav-link" href="mycart">My Cart</a></li>
            <li class="nav-item active"><a class="nav-link" href="inputmail">Checkout</a></li>
            <li class="nav-item"><a class="nav-link text-danger" href="logout">Logout</a></li>
        </ul>
    </div>
</nav>

<!-- Main Content -->
<div class="container">
    <div class="form-container">
        <h3 class="text-center mb-4">📧 Email Verification</h3>
        
        <form action="sendmail" method="post">
            <div class="form-group">
                <label for="email">Enter your email address</label>
                <input type="email" name="email" class="form-control" placeholder="you@example.com" required>
                <small class="form-text text-danger">
                    ${EmailError}
                </small>
            </div>
            <button type="submit" class="btn btn-primary btn-block">Send Verification Mail</button>
            <a href="mycart" class="btn btn-outline-secondary btn-block mt-2">Back to Cart</a>
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

<!-- Scripts -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
