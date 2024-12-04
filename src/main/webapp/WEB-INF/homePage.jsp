<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>Home Page</title>
</head>
<body>

	<div class = "container">
	<nav class='navbar navbar-light bg-dark text-white'>
            <a href = "/logout" class='navbar brand text-white'>Logout</a>
			<h3>Welcome, <c:out value = "${user.firstName}"/></h3>
        </nav>
	<h3>TV Shows</h3>
	<table class="table table-striped">
		  <thead>
		    <tr>
		      <th scope="col">Show</th>
		      <th scope="col">Network</th>
		    </tr>
		  </thead>
		  <tbody>
		  <c:forEach items = "${shows}" var = "show">
		    <tr>
		      <td><a href = "/home/show/${show.id}"><c:out value = "${show.title}"/></a></td>
		      <td><c:out value = "${show.network}"/></td>
		    </tr>
		   </c:forEach>
		  </tbody>
	</table>
	<a href = "/newShow"><button class = "btn btn-dark">Add a Show</button></a>



</div>
</body>
</html>