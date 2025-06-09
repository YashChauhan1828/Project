<%@page import="com.entity.EcomProductEntity"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Popular Products</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
	integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<style>
@import
	url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;500&display=swap')
	;

body {
	font-family: "Poppins", sans-serif;
	color: #444444;
}

a, a:hover {
	text-decoration: none;
	color: inherit;
}

.section-products {
	padding: 80px 0 54px;
}

.section-products .header {
	margin-bottom: 50px;
}

.section-products .header h3 {
	font-size: 1rem;
	color: #fe302f;
	font-weight: 500;
}

.section-products .header h2 {
	font-size: 2.2rem;
	font-weight: 400;
	color: #444444;
}

.section-products .single-product {
	margin-bottom: 26px;
}

.section-products .single-product .part-1 {
	position: relative;
	height: 290px;
	max-height: 290px;
	margin-bottom: 20px;
	overflow: hidden;
}

.section-products .single-product .part-1::before {
	position: absolute;
	content: "";
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	z-index: -1;
	transition: all 0.3s;
}

.section-products .single-product:hover .part-1::before {
	transform: scale(1.2, 1.2) rotate(5deg);
}

.section-products #product-1 .part-1::before {
	background: url("https://i.ibb.co/L8Nrb7p/1.jpg") no-repeat center;
	background-size: cover;
	transition: all 0.3s;
}

.section-products #product-2 .part-1::before {
	background: url("https://i.ibb.co/cLnZjnS/2.jpg") no-repeat center;
	background-size: cover;
}

.section-products #product-3 .part-1::before {
	background: url("https://i.ibb.co/L8Nrb7p/1.jpg") no-repeat center;
	background-size: cover;
}

.section-products #product-4 .part-1::before {
	background: url("https://i.ibb.co/cLnZjnS/2.jpg") no-repeat center;
	background-size: cover;
}

.section-products .single-product .part-1 .discount, .section-products .single-product .part-1 .new
	{
	position: absolute;
	top: 15px;
	left: 20px;
	color: #ffffff;
	background-color: #fe302f;
	padding: 2px 8px;
	text-transform: uppercase;
	font-size: 0.85rem;
}

.section-products .single-product .part-1 .new {
	left: 0;
	background-color: #444444;
}

.section-products .single-product .part-1 ul {
	position: absolute;
	bottom: -41px;
	left: 20px;
	margin: 0;
	padding: 0;
	list-style: none;
	opacity: 0;
	transition: bottom 0.5s, opacity 0.5s;
}

.section-products .single-product:hover .part-1 ul {
	bottom: 30px;
	opacity: 1;
}

.section-products .single-product .part-1 ul li {
	display: inline-block;
	margin-right: 4px;
}

.section-products .single-product .part-1 ul li a {
	display: inline-block;
	width: 40px;
	height: 40px;
	line-height: 40px;
	background-color: #ffffff;
	color: #444444;
	text-align: center;
	box-shadow: 0 2px 20px rgb(50 50 50/ 10%);
	transition: color 0.2s;
}

.section-products .single-product .part-1 ul li a:hover {
	color: #fe302f;
}

.section-products .single-product .part-2 .product-title {
	font-size: 1rem;
}

.section-products .single-product .part-2 h4 {
	display: inline-block;
	font-size: 1rem;
}

.section-products .single-product .part-2 .product-old-price {
	position: relative;
	padding: 0 7px;
	margin-right: 2px;
	opacity: 0.6;
}

.section-products .single-product .part-2 .product-old-price::after {
	position: absolute;
	content: "";
	top: 50%;
	left: 0;
	width: 100%;
	height: 1px;
	background-color: #444444;
	transform: translateY(-50%);
}

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
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.5);
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
<!-- HEADER -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark px-4">
    <a class="navbar-brand" href="ehome">E-Shop</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item"><a class="nav-link active" href="ehome">Home</a></li>
            <li class="nav-item"><a class="nav-link" href="userproducts">Products</a></li>
            <li class="nav-item"><a class="nav-link" href="mycart">My Cart</a></li>
            <li class="nav-item"><a class="nav-link" href="inputmail">Checkout</a></li>
            <li class="nav-item"><a class="nav-link text-danger" href="logout">Logout</a></li>
        </ul>
    </div>
</nav>
<% List<EcomProductEntity> products = (List<EcomProductEntity>) request.getAttribute("products"); %>
<div class="container mb-4">
    <form class="form-inline d-flex justify-content-center" action="search" method="get">
        <input class="form-control mr-2 w-50" type="text" name="query" placeholder="Search products..." aria-label="Search">
        <button class="btn btn-danger" type="submit">Search</button>
    </form>
</div>

<section class="section-products">
		<div class="container">
				<div class="row justify-content-center text-center">
						<div class="col-md-8 col-lg-6">
								<div class="header">
										<h3>Featured Product</h3>
										<h2>Popular Products</h2>
								</div>
						</div>
				</div>
				<div class="row">
				<% 
		for(EcomProductEntity p : products)
		{ %>
						<!-- Single Product -->
						<div class="col-md-6 col-lg-4 col-xl-3">
								<div id="product-1" class="single-product">
										<div class="part-1">
												<a href="userproductview?productId=<%=p.getProductId()%>">
												<img alt="" src="<%=p.getProductImagePath()%>" height="100%" width="100%"/>
												</a>
												<ul>
														<li><a href="addtocart?productId=<%=p.getProductId()%>"><i class="fas fa-shopping-cart"></i></a></li>
														<li><a href="#"><i class="fas fa-heart"></i></a></li>											
														<li><a href="userproductview?productId=<%=p.getProductId()%>"><i class="fas fa-eye"></i></a></li>
												</ul>
										</div>
										<div class="part-2">
												<h3 class="product-title"><%=p.getProductName() %></h3>
												<h4 class="product-old-price"><%=p.getPrice()*0.1 + p.getPrice() %></h4>
												<h4 class="product-price"><%=p.getPrice() %></h4>
										</div>
								</div>
						</div>
						<%} %>
				</div>
		</div>
</section>
<!-- FOOTER -->
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