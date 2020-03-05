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
            <a class="nav-link" href="/Fruteria-ADO/listadoVegetales2/0">Volver</a>
          </li>
          <li class="nav-item active">
            <a class="nav-link" href="#">
            	<img src="https://augustobrigadaw.000webhostapp.com/resources2/Carrito.png" class="d-inline-block align-top" width="30" height="30"/></a>
          </li>
          
          <li class="nav-item">
            	<a class="nav-link" href="/Fruteria-ADO/logout">Cerrar Sesion</a>
            </li>
        </ul>
      </div>
    </div>
  </nav>
	<div class="container-fluid" >
	<p></p><br />
	<div class="row justify-content-center" >
 			
 			<form:form method="post" action="/Fruteria-ADO/comprar">
 			
 				<table class="table table-dark table-striped">
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
						<a href="/Fruteria-ADO/eliminarV/${cesta.id}" class="btn btn-danger">Quitar</a>
					</td>
					<c:set var="suma" value="${suma + cesta.precio}" scope="page"/>
				</tr>
  				</c:forEach>
				</tbody>
				</table>
   				<div class="form-group">
   					<h5><fmt:formatNumber type="number" maxFractionDigits="2" value="${suma}"/> &euro;</h5>
					<input type="submit" value="Comprar" class="btn btn-success"/>
				</div>
			</form:form>
		</div>
		
	</div>
</body>
</html>