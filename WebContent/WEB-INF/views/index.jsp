<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>

<head>

 
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>La Frutería</title>

 
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
            <a class="nav-link" href="#">Inicio
              <span class="sr-only">(current)</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/Fruteria-ADO/carrito">Carrito</a>
          </li>

          <c:if test="${sessionScope.username == 'admin'}">
        	<li class="nav-item">
            	<a class="nav-link" href="/Fruteria-ADO/listadoVegetal/0">Back</a>
            </li>
        </c:if>
        <c:if test="${sessionScope.username == null}">
        	<li class="nav-item">
            	<a class="nav-link" href="/Fruteria-ADO/signIn">Acceso</a>
            </li>
        </c:if>
        <c:if test="${sessionScope.username != null}">
        	<li class="nav-item">
            	<a class="nav-link" href="/Fruteria-ADO/logout">Cerrar Sesion</a>
            </li>
        </c:if>
          
        </ul>
      </div>
    </div>
  </nav>

  <!-- Page Content -->
  <div class="container">
    <div class="row">	
      <div class="col-lg-3">
      	<div>
        <h1 class="my-4">La frutería</h1>
        <c:if test="${sessionScope.username != null}">
			<h4 class="nav-item">${sessionScope.username}</h4>
		</c:if>
		</div>
        <div class="list-group">
          <a href="/Fruteria-ADO/listadoVegetales2/0" class="list-group-item">Todo</a>
          <a href="/Fruteria-ADO/buscarCategoria/Fruta" class="list-group-item">Fruta</a>
          <a href="/Fruteria-ADO/buscarCategoria/Hortaliza" class="list-group-item">Hortaliza </a>
          <a href="/Fruteria-ADO/buscarCategoria/Legumbre" class="list-group-item">Legumbre </a>
        </div>
         <!-- paginación -->
           <c:set var="ind" value="0" scope="page" />
           <c:set var="ind2" value="0" scope="page" />
	       <c:forEach items="${listaT}" var="item" varStatus="status2">
         
           <c:if test="${ind2 == 6}">
          
              <a href="../listadoVegetales2/${ind}">${ind+1}</a> 
              <c:set var="ind" value="${ind + 1}" scope="page"/>
              <c:set var="ind2" value="0" scope="page"/>
          </c:if>
          <c:set var="ind2" value="${ind2 + 1}" scope="page"/>
       </c:forEach>
       <!-- fin paginación -->
      </div>
      <!-- /.col-lg-3 -->

      <div class="col-lg-9">
 <div id="myCarousel" class="carousel slide my-4"  data-interval="1000" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
        <c:forEach items="${listaSlides}" var="listaS" varStatus="status">
         <c:if test="${status.index==0}">
            <li data-target="#myCarousel" data-slide-to="${status.index}" class="active"></li>
            </c:if>
          <c:if test="${status.index!=0}">
            <li data-target="#myCarousel" data-slide-to="${status.index}"></li>
            </c:if>     
        </c:forEach>  
        </ol>
        <!-- Image Size 1140 x 250 -->
        <div class="carousel-inner " role="listbox">
        <c:forEach items="${listaSlides}" var="listaS" varStatus="status">
         <c:if test="${status.index==0}">
           <div class="carousel-item active">         
            <img class="d-block img-fluid" src="${listaS.imagen}" alt="Slide" title="${listaS.texto}"  >
            </div>
          </c:if>
          <c:if test="${status.index!=0}">
           <div class="carousel-item">         
            <img class="d-block img-fluid" src="${listaS.imagen}" alt="Slide" title="${listaS.texto}">
            </div>
          </c:if>
          </c:forEach>
        </div>
        <a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="sr-only">Next</span>
        </a>
      </div><!-- /.carousel -->

        

        <div class="row">
            <c:forEach var="vegetal" items="${listaF}">
          <div class="col-lg-4 col-md-6 mb-4">
          
              
                 <div class="card h-100">
                <a href="../carrito/${vegetal.id}"><img width=150 height=150 class="card-img-top" src="https://augustobrigadaw.000webhostapp.com/resources2/img/${vegetal.imagen}" alt=""></a>
               <div class="card-body">
                <h4 class="card-title">
                  <a href="../carrito/${vegetal.id}">${vegetal.nombre}</a>
                </h4>
                <small class="text-muted">Id:${vegetal.id}</small>
                <h5>${vegetal.precio}€</h5>
                <p class="card-text">${vegetal.categoria}</p>
              </div>
              <div class="card-footer">
                <small class="text-muted">${vegetal.procedencia}</small>
               
              </div>
            </div>
          </div>
    </c:forEach> 
      
        </div>
        <!-- /.row -->

      </div>
      <!-- /.col-lg-9 -->

    </div>
    <!-- /.row -->

  </div>
  <!-- /.container -->

  <!-- Footer -->
  <footer class="py-5 bg-dark">
    <div class="container">
      <p class="m-0 text-center text-white">Copyright &copy; FruitStore ADO 2020</p>
    </div>
    <!-- /.container -->
  </footer>

  <!-- Bootstrap core JavaScript -->
    <script src="https://augustobrigadaw.000webhostapp.com/resources2/vendor/jquery/jquery.js"></script>
  <script src="https://augustobrigadaw.000webhostapp.com/resources2/vendor/bootstrap/js/bootstrap.bundle.js"></script>


</body>

</html>
