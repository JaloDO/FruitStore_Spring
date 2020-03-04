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
				<h4 class="text-center">Regístrate</h4>
				<form:form method="post" action="registrar">
					<div class="form-group">
						<label for = "username">Nombre de usuario: </label>
						<form:input path="username" class="form-control"/>
						<form:errors path="username" cssClass="error"/>
					</div>
					<div class="form-group">
						<label for = "password">Contraseña: </label>
						<form:input path="password" class="form-control"/>
						<form:errors path="password" cssClass="error"/>
					</div>
					<div class="form-group">
						<label for = "email">Email: </label>
						<form:input path="email" class="form-control"/>
						<form:errors path="email" cssClass="error"/>
					</div>
					<div class="form-group">
						<label for = "name">Nombre: </label>
						<form:input path="name" class="form-control"/>
						<form:errors path="name" cssClass="error"/>
					</div>
					<div class="form-group">
						<label for ="age">Edad:</label>
						<form:input path="age" class="form-control"/>
						<form:errors path="age" cssClass="error"/>
					</div>
					<div class="form-group">
						<input type="submit" value="Registrar" class="btn btn-success"/>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>