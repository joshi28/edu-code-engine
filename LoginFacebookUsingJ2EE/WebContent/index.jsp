<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login With FaceBook</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!--using facebook javascript sdk  -->
<script type="text/javascript" src="resources/js/fbConfig.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/css/style.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
				<div class="card card-signin my-5 ">
					<div class="card-header">Login</div>
					<div class="card-body">

						<button class="btn btn-lg btn-social btn-facebook"
							onclick="fbLogin()" id="fblogin">
							<i class="fa fa-facebook fa-fw"></i> Login with Facebook
						</button>
						<div id="status"></div>
						<div id="userData"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>