<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Carrito</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body style="background-color:#f7f7ff;">
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
      
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <a class="nav-link" href="/Fruteria-ADO/listadoVegetales2/0">Volver</a>
          </li>
          <li class="nav-item active">
            <a class="nav-link" href="#" disabled>Carrito
            	<span class="sr-only">(current)</span>
            </a>
          </li>
          <li class="nav-item">
            	<a class="nav-link" href="/Fruteria-ADO/logout">Cerrar Sesion</a>
            </li>
        </ul>
      </div>
    </div>
  </nav>
  
  
	<div class="container">
		<div class="row text-center">
 			<p></p>
 			<form:form method="post" action="/Fruteria-ADO/comprar">
 				<table class="table table-dark">
  				<thead>
    			<tr>
      				<th scope="col">ID</th>
      				<th scope="col">Producto</th>
      				<th scope="col">Categoría</th>
      				<th scope="col">Imagen</th>
      				<th scope="col">Precio</th>
      				<th scole="col">Eliminar</th>
    			</tr>
  				</thead>
  				<tbody>
  				<c:set var="suma" value="0" scope="page" />
				<c:forEach items="${listaC}" var="cesta" varStatus="status">
				<tr>
					<td>${cesta.id}</td>
					<td>${cesta.nombre}</td>
					<td>${cesta.categoria}</td>
					<td>
						<img width=50 height=50  src="https://augustobrigadaw.000webhostapp.com/resources2/img/${cesta.imagen}">
					</td>
					<td>${cesta.precio}</td>
					<td>
						<a href="../eliminarV/${cesta.id}">Quitar</a>
					</td>
					<c:set var="suma" value="${suma + cesta.precio}" scope="page"/>
				</tr>
  				</c:forEach>
				</tbody>
				</table>
   				<div class="form-group">
   					<h5><fmt:formatNumber type="number" maxFractionDigits="2" value="${suma}"/> &euro;</h5>
					<input type="submit" value="Modificar" class="btn btn-success"/>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>