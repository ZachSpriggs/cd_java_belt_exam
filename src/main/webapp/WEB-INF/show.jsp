<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>Shows</title>
</head>
<body>
	<div class = "container">
	<nav class='navbar navbar-light bg-dark text-white'>
            <a href = "/logout" class='navbar brand text-white'>Logout</a>
            <a href = "/home" class = "navbar brand text-white">Home</a>
			<h3>Welcome, <c:out value = "${user.firstName}"/></h3>
        </nav>
	<h3><c:out value = "${thisShow.title}"/></h3>
	<h5><c:out value = "${thisShow.network}"/></h5>
	<table class="table table-striped">
		  <thead>
		    <tr>
		      <th scope="col">Name</th>
		      <th scope="col">Rating</th>
		    </tr>
		  </thead>
		  <tbody>
		  <c:forEach items = "${ratings}" var = "rating">
		    <tr>
		      <td><c:out value = "${rating.user.firstName}"/></td>
		      <td><c:out value = "${rating.rating}"/></td>
		    </tr>
		    </c:forEach>
		  </tbody>
	</table>
	<a href = "/editShow/${thisShow.id}/edit"><button class = "btn btn-dark">Edit</button></a>

	<c:choose>
		<c:when test = "${userrating.id == user.id}">
			<p>You've already rated this show</p>
		</c:when>
		<c:otherwise>
		 <form:form action="/rate/${thisShow.id}" method="POST" modelAttribute = "userrating">
            <div class="row">
                <div class="col-4">
                    <p>
	  					<form:label path = "rating">Leave a Rating:</form:label>
	  					<form:errors path = "rating"/>
	  					<form:input path = "rating" type = "number"/>
  					</p>
  					
                </div>
            </div>
            <div class='row'>
            	<form:hidden path = "show" value = "${thisShow.id}"/>
                <button class='btn btn-outline-dark ml-3 mt-3'>Rate!</button>
            </div>
        </form:form>
        </c:otherwise>
</c:choose>

</div>

</body>
</html>