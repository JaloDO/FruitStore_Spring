<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
	<title>CRUD SPRING MVC CON MYSQL</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
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
            <a class="nav-link" href="/Fruteria-ADO/signIn">
            	<img src="https://www.nicepng.com/png/full/266-2660273_expand-slideshow-white-back-icon-png.png" width="20" height="30"/></a>
          </li>
        <li class="nav-item">
            <a class="nav-link" href="/Fruteria-ADO/listadoVegetales2/0">Tienda</a>
          </li>
          <li class="nav-item active">
            <a class="nav-link" href="#" disabled>Registro
            	<span class="sr-only">(current)</span>
            </a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <div id="general_container" style="position:relative;">
<div class="container-fluid" style="min-height:80vh;">

		<p></p>
		<div class="row justify-content-center align-items-center">
			<div class="col-md-6">
				<h3 class="text-center text-info">Regístrate</h3>
				<form:form method="post" commandName="command" action="registrar">
					<div class="form-group">
						<label for = "username" class="text-info">Usuario: </label>
						<form:input path="username" class="form-control"/>
						<form:errors path="username"/>
					</div>
					<div class="form-group">
						<label for = "password" class="text-info">Contraseña: </label>
						<form:input type="password" path="password" class="form-control"/>
						<form:errors path="password"/>
					</div>
					<div class="form-group">
						<label for = "email" class="text-info">Email: </label>
						<form:input path="email" class="form-control"/>
						<form:errors path="email"/>
					</div>
					<div class="form-group">
						<label for = "name" class="text-info">Nombre: </label>
						<form:input path="name" class="form-control"/>
						<form:errors path="name"/>
					</div>
					<div class="form-group">
						<label for ="age" class="text-info">Edad:</label>
						<form:input path="age" class="form-control"/>
						<form:errors path="age"/>
					</div>
					<div class="form-group text-center">
						<input type="submit" value="Registrar" class="btn btn-info btn-md"/>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<footer class="py-5 bg-dark" style="position:relative;width:100%;bottom:0;">
    		<div class="container">
      			<p class="m-0 text-center text-white">Copyright &copy; FruitStore ADO 2020</p>
   		 </div>
    <!-- /.container -->
  </footer>
	</div>
</body>
</html>