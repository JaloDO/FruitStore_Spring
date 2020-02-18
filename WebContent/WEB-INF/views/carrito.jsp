<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
  <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Carrito</title>
</head>
<body>

 <div class="container">
 <div class="row text-center">
 
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
  <td><img width=50 height=50  src="https://augustobrigadaw.000webhostapp.com/resources2/img/${cesta.imagen}"></td>
  <td>${cesta.precio}</td>
  <td><a href="../eliminarV/${cesta.id}">Quitar</a></td>
    <c:set var="suma" value="${suma + cesta.precio}" scope="page"/>
   
  </c:forEach>
  </tbody>
   </table>
   <h5>${suma}</h5>
     <a href = "<c:url value = "../listadoVegetales2/0"/>">Volver</a>
 </div>
 </div> 
</body>
</html>