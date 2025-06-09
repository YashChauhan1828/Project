<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html data-coreui-theme="dark">
<head>
    <meta charset="UTF-8">
    <title>Welcome - Ecom App</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    
    <style>
        body {
            background-color: #121212;
            color: #fff;
        }
        .navbar {
            margin-bottom: 20px;
        }
        .profile-card {
            max-width: 500px;
            margin: auto;
            background-color: #1e1e1e;
            border-radius: 16px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.5);
            padding: 20px;
            text-align: center;
        }
        .profile-card img {
            border-radius: 12px;
            max-width: 100%;
            height: auto;
        }
        .profile-card h3 {
            margin-top: 15px;
            font-weight: 600;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark px-4">
    <a class="navbar-brand" href="#">E-Shop</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
  
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ms-auto">
            <li class="nav-item">
                <a class="nav-link active" href="ehome">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="userproducts">Products</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="mycart">My Cart</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="inputmail">Checkout</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-danger" href="logout">Logout</a>
            </li>
        </ul>
    </div>
</nav>

<div class="container my-5">
    <div class="profile-card">
        <h3>Welcome, ${firstName}!</h3>
        <img src="${profilePicturePath}" alt="Profile Picture">
    </div>
</div>
<div class="container mt-5">
    <div class="p-5 rounded-4 bg-dark text-white shadow-sm">
        <h1 class="display-5 fw-bold">Welcome to E-Shop</h1>
        <p class="lead mt-3">
            Discover a seamless online shopping experience with a wide range of electronics, fashion, and lifestyle products.  
            Whether you're upgrading your phone or shopping for the latest sneakers, we’ve got everything you need.
        </p>
        <hr class="border-light">
        <p>
            Enjoy secure payments, fast checkout, and reliable delivery across India.  
            Sign in to view personalized deals and manage your orders.
        </p>
        <a href="userproducts" class="btn btn-outline-light btn-lg mt-3">Browse Products</a>
    </div>
</div>
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

</body>
</html>
