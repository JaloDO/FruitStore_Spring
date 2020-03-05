<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
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
            <a class="nav-link" href="/Fruteria-ADO/listadoVegetal/0">
            	<img src="https://www.nicepng.com/png/full/266-2660273_expand-slideshow-white-back-icon-png.png" width="20" height="30"/></a>
          </li>
          <li class="nav-item active">
            <a class="nav-link" href="#">Editar Vegetal
            	<span class="sr-only">(current)</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/Fruteria-ADO/listadoVegetales2/0">Tienda</a>
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
				<h4 class="btn btn-success btn-lg btn-block" disabled>Editar Vegetal</h4>
				<form:form method="post" commandName="command" action="../editarGuardar">
				<div class="form-group">
						<label for = "id">Id: </label>
						<form:input path="id" readonly="true" class="form-control"/>
						<form:errors path="id"/>
					</div>
					<div class="form-group">
						<label for = "nombre">Nombre: </label>
						<form:input path="nombre" class="form-control"/>
						<form:errors path="nombre"/>
					</div>
					<div class="form-group">
						<label for = "categoria">Categoría: </label>
						<form:input path="categoria" class="form-control"/>
						<form:errors path="categoria"/>
					</div>
					<div class="form-group">
						<label for = "procedencia">Procedencia: </label>
						<form:input path="procedencia" class="form-control"/>
						<form:errors path="procedencia"/>
					</div>
					<div class="form-group">
						<label for = "imagen">Imagen: </label>
						<form:input path="imagen" class="form-control"/>
						<form:errors path="imagen"/>
					</div>
					<div class="form-group">
						<label for ="precio">Precio:</label>
						<form:input path="precio" class="form-control"/>
						<form:errors path="precio"/>
					</div>
					<div class="form-group">
						<input type="submit" value="Modificar" class="btn btn-success"/>
					</div>
				</form:form>
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