<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  


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
            <a class="nav-link" href="/Fruteria-ADO/listadoVegetales2/0">
            	<img src="https://www.nicepng.com/png/full/266-2660273_expand-slideshow-white-back-icon-png.png" width="20" height="30"/></a>
          </li>
          <li class="nav-item active">
            <a class="nav-link" href="#">
            	<img src="https://augustobrigadaw.000webhostapp.com/resources2/Carrito.png" class="d-inline-block align-top" width="30" height="30"/>
            	<c:if test="${not empty sessionScope.listaCarrito}">(${sessionScope.contador})</c:if>
            </a>
          </li>
          <c:if test="${sessionScope.user == null}">
        	<li class="nav-item">
            	<a class="nav-link" href="/Fruteria-ADO/signIn">Acceso</a>
            </li>
          </c:if>
          <c:if test="${sessionScope.user != null}">
        	<li class="nav-item">
            	<a class="nav-link" href="/Fruteria-ADO/datos">Mis Datos</a>
            </li>
        	<li class="nav-item">
            	<a class="nav-link" href="/Fruteria-ADO/logout">Cerrar Sesion</a>
            </li>
        </c:if>
        </ul>
      </div>
    </div>
  </nav>
  <div id="general_container" style="position:relative;">
	<div class="container" style="min-height:80vh;">
	<div><br /></div>
	<div class="row text-center" >
 			
 			<form:form method="post" action="comprar" style="width:100%;">
 				<!-- gobernamos el formulario en funcion de si el carrito tiene elementos -->
 				<c:if test="${not empty sessionScope.listaCarrito}">
 				<table class="table table-dark table-striped">
  				<thead>
    			<tr>
      				<th scope="col">ID</th>
      				<th scope="col">Producto</th>
      				<th scope="col">Categoría</th>
      				<th scope="col">Imagen</th>
      				<th scope="col">Precio</th>
      				<th scope="col">Eliminar</th>
    			</tr>
  				</thead>
  				<tbody>
  				<c:set var="suma" value="0" scope="page" />
				<c:forEach items="${sessionScope.listaCarrito}" var="item" varStatus="status">
				<tr>
					<td>${item.id}</td>
					<td>${item.nombre}</td>
					<td>${item.categoria}</td>
					<td>
						<img width=50 height=50  src="https://augustobrigadaw.000webhostapp.com/resources2/img/${item.imagen}">
					</td>
					<td>${item.precio} &euro;</td>
					<td>
						<a href="/Fruteria-ADO/eliminarV/${item.id}" class="btn btn-danger">Quitar</a>
					</td>
					<c:set var="suma" value="${suma + item.precio}" scope="page"/>
				</tr>
  				</c:forEach>
  				<!--  aqui va la magia -->
  				<tr>
					<th>
						<td style="text-align:left;" colspan=3>TOTAL</td>
						<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${suma}"/> &euro;</td>
						<td>
							<a href="/Fruteria-ADO/eliminarTodo" class="btn btn-danger">Quitar Todo</a>
						</td>
					</th>
				</tr>
				</tbody>
				</table>
   				<div class="form-group">
					<input type="submit" value="Comprar" class="btn btn-success"/>
				</div>
				</c:if>
				<c:if test="${empty sessionScope.listaCarrito}">
					<c:if test="${sessionScope.comprado == false}">
					
						<label class="badge badge-danger" style="width:100%;font-size:3em;">No te gusta nada!</label>
						<img src="https://www.pngitem.com/pimgs/m/102-1025083_worry-svg-png-icon-free-download-black-sad.png" width=50% />
					</c:if>
					<c:if test="${sessionScope.comprado == true}">
						<label class="badge badge-success" style="width:100%;font-size:3em;">Gracias por tu compra!</label>
						<img src="https://cdn.memegenerator.es/imagenes/memes/full/19/28/19281144.jpg" width=50% />
					</c:if>
				</c:if>
			</form:form>
			
			<!--  
				//////////////////////////////// FIN DEL FORMULARIO /////////////////////////////////////
			 -->
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