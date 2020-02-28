<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>Back Vegetales</title>
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
			<h4 class="text-center">Lista Vegetales</h4>
			<div class="container">
			     
       <form action="../buscarCategoria" method="post">
    	<input type="text" name="categoria">
    	<input type="submit" value="buscar">
    	</form>
    	<a href="listadoVegetal/0">Todos</a>
  
			<hr>
				<br/>
			
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
		
		 
			<br />
		 <a href="../aniadirVegetal" class="btn btn-success"><i class="glyphicon glyphicon-edit"></i> Nuevo Vegetal</a>
		</div>
		
	</div>
</div>
</div>
</body>
</html>