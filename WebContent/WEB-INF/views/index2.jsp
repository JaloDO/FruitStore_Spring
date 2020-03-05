<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>Back Vegetales</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- Bootstrap core CSS -->
  <link href="https://augustobrigadaw.000webhostapp.com/resources2/bootstrap.css" rel="stylesheet">
<!-- Custom styles for this template -->
  <link href="https://augustobrigadaw.000webhostapp.com/resources2/css/shop-homepage.css" rel="stylesheet">
</head>
<body style="background-color:#f7f7ff;">
	<!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
      
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item active">
            <a class="nav-link" href="#">Lista Vegetales
              <span class="sr-only">(current)</span>
            </a>
          </li>
          
          <li class="nav-item">
            <a class="nav-link" href="/Fruteria-ADO/aniadirVegetal">Nuevo Vegetal</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/Fruteria-ADO/listadoVegetales2/0">Tienda</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/Fruteria-ADO/logout">Cerrar sesion</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>





<div id="general_container" style="position:relative;">
<div class="container-fluid" style="min-height:80vh;">
	<p></p>
	<div class="row-fluid">
		<div class="col-md-6">
			<h4 class="text">Bienvenido Administrador</h4>
			<div class="container">
			     
       <form action="../buscarCategoria" method="post">
    	<input type="text" name="categoria">
    	<input type="submit" value="buscar">
    	</form>
    	<a href="listadoVegetal/0">Todos</a>
  
		<hr>
	   <c:set var="ind" value="0" scope="page" />
	    <c:forEach items="${listaVegetal}" var="item" varStatus="status2">
         
          <c:if test="${status2.index % 5 == 0}">
          
           <a href="../listadoVegetal/${ind}">${ind+1}</a> 
            <c:set var="ind" value="${ind + 1}" scope="page"/>
          </c:if>
       </c:forEach>
			<table class="table table-bordered table-striped">
				<thead>
				<tr>
					<th>Id</th>
					<th>Nombre</th>
					<th>Categoria</th>
					<th>Imagen</th>
					<th>procedencia</th>
					<th>Precio</th>
					<th>Editar</th>
					<th>Eliminar</th>
					
				</tr>
				</thead>
				<tbody>
				
				
				<c:forEach var="veg" items="${lista}" varStatus="status">
					<tr>
					  
						<td>${veg.id}</td>
						<td>${veg.nombre}</td>
						<td>${veg.categoria}</td>
						<td>${veg.imagen}</td>
						<td>${veg.procedencia}</td>
						<td>${veg.precio}</td>
						<td><a href="../editarVegetal/${veg.id}" class="btn btn-info btn-xs"><i class="glyphicon glyphicon-check"></i> Editar</a></td>
						<td><a href="../eliminarVegetal/${veg.id}" class="btn btn-danger btn-xs"><i class="glyphicon glyphicon-trash"></i> Eliminar</a></td>
					</tr>
				</c:forEach>				
				</tbody>
			</table>
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