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
            <a class="nav-link" href="/Fruteria-ADO/listadoVegetal/0">Volver</a>
          </li>
          
          <li class="nav-item active">
            <a class="nav-link" href="#">Nuevo Vegetal
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



<div class="container-fluid">
		<p></p>
		<div class="row justify-content-center align-items-center">
			<div class="col-md-6">
				<h4 class="text-center">Agregar Nuevo Vegetal</h4>
				<form:form method="post" action="guardar">
					<div class="form-group">
						<label for = "nombre">Nombre: </label>
						<form:input path="nombre" class="form-control"/>
						<form:errors path="nombre" cssClass="error"/>
					</div>
					<div class="form-group">
						<label for = "categoria">Categor�a: </label>
						<form:input path="categoria" class="form-control"/>
						<form:errors path="categoria" cssClass="error"/>
					</div>
					<div class="form-group">
						<label for = "procedencia">Procedencia: </label>
						<form:input path="procedencia" class="form-control"/>
						<form:errors path="procedencia" cssClass="error"/>
					</div>
					<div class="form-group">
						<label for = "imagen">Imagen: </label>
						<form:input path="imagen" class="form-control"/>
						<form:errors path="imagen" cssClass="error"/>
					</div>
					<div class="form-group">
						<label for ="precio">Precio:</label>
						<form:input path="precio" class="form-control"/>
						<form:errors path="precio" cssClass="error"/>
					</div>
					<div class="form-group">
						<input type="submit" value="Guardar" class="btn btn-success"/>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	
	<!-- Footer -->
  <footer class="py-5 bg-dark">
    <div class="container">
      <p class="m-0 text-center text-white">Copyright &copy; FruitStore ADO 2020</p>
    </div>
    <!-- /.container -->
  </footer>
</body>
</html>