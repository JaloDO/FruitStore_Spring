<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
	<title>CRUD SPRING MVC CON MYSQL</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">
		<div class="row-fluid">
			<div class="col-md-6">
				<h4 class="text-center">Agregar Nuevo Vegetal</h4>
				<hr>
				<form:form method="post" action="guardar">
					<div class="form-group">
						<label for = "nombre">Nombre: </label>
						<form:input path="nombre" class="form-control"/>
					</div>
					<div class="form-group">
						<label for = "imagen">Imagen: </label>
						<form:input path="imagen" class="form-control"/>
					</div>
					<div class="form-group">
						<label for ="precio">Precio:</label>
						<form:input path="precio" class="form-control"/>
					</div>
					<div class="form-group">
						<input type="submit" value="Guardar" class="btn btn-success"/>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>