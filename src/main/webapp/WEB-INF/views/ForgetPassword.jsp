<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
           
        </ul>
    </div>
</nav>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-3">
		
		</div>
		<div class="col-md-6">
			<h1> Ecom Reset Password</h1>


			<form action="updatepassword" method="post">
			<div class="form-group">
			<label>Email: </label><input type="text" class="form-control" name="email"  value="${emailValue}" placeholder="Enter your Email Address"/><span style="color: red" >${emailError}</span><br>
			</div>
			<div class="form-group">
			<label>New Password: </label><input type="password" class="form-control" name="newpassword" value="${passwordValue}" placeholder="Enter New Password" /><span style="color: red">${passwordError}</span><br><br>
			  
			</div>
			<div class="form-group">
			<label>Confirm Password: </label><input type="password" class="form-control" name="confirmpassword" value="${passwordValue}" placeholder="Enter confirm Password" /><span style="color: red">${passwordError}</span><br><br>
			  
			</div>
			<input type="submit" value="Reset Password" class="btn btn-success"/>
			</form><br>
			<p class="sign-up">Don't have an Account?<a href="esignup"> Sign Up</a></p>
		</div>
		
	</div>
</div>
</body>
</html>