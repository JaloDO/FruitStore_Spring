<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<!-- Bootstrap core CSS -->
  <link href="https://augustobrigadaw.000webhostapp.com/resources2/bootstrap.css" rel="stylesheet">
<!-- Custom styles for this template -->
  <link href="https://augustobrigadaw.000webhostapp.com/resources2/css/shop-homepage.css" rel="stylesheet">
</head>
<body style="background-color:#f7f7ff;">
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
      
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <a class="nav-link" href="/Fruteria-ADO/listadoVegetales2/0">
            	<img src="https://www.nicepng.com/png/full/266-2660273_expand-slideshow-white-back-icon-png.png" width="20" height="30"/></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/Fruteria-ADO/carrito">
            	<img src="https://augustobrigadaw.000webhostapp.com/resources2/Carrito.png" class="d-inline-block align-top" width="30" height="30"/>
            	
            </a>
          </li>
          <li class="nav-item active">
            <a class="nav-link" href="#" disabled>Acceso
            	<span class="sr-only">(current)</span>
            </a>
          </li>
        </ul>
      </div>
    </div>
  </nav>



<div id="general_container" style="position:relative;">
   <div id="login" style="min-height:80vh;">
        <h3 class="text-center text-white pt-5"> <br/><br/></h3>
        
	     ${error}
        <div class="container">
            <div id="login-row" class="row justify-content-center align-items-center">
                <div id="login-column" class="col-md-6">
                    <div id="login-box" class="col-md-12">
                        <form id="login-form" class="form" action="login" method="post">
                            <h3 class="text-center text-info">Login</h3>
                            <div class="form-group">
                                <label for="username" class="text-info">Usuario:</label><br>
                                <input type="text" id="username" name="username" class="form-control" />             
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-info">Password:</label><br>
                                <input type="password" id="password" name="password" class="form-control" /> 
                            </div>
                            <div class="form-group text-center">
                               
                                <input type="submit" name="submit" value="Aceptar" class="btn btn-info btn-md" value="submit">
                            </div>
                            <div class="form-group text-center">
                               
                                <a href="/Fruteria-ADO/signUp" class="link" value="ir a registro">Todavía no tengo cuenta</a>
                            </div>
                        </form>
                        
                    </div>
                </div>
            </div>
        </div>
    </div>
	<!-- Footer -->
  <footer class="py-5 bg-dark" style="position:relative;width:100%;bottom:0;">
    		<div class="container">
      			<p class="m-0 text-center text-white">Copyright &copy; FruitStore ADO 2020</p>
   		 </div>
    <!-- /.container -->
  </footer>
	</div>
</body>
</html>