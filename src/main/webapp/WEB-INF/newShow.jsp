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
<title>Add a new show</title>
</head>
<body>
	
	<div class = "container">
		<nav class='navbar navbar-light bg-dark text-white'>
			<a href = "/home" class = "navbar brand text-white">Home</a>
            <a href = "/logout" class='navbar brand text-white'>Logout</a>
			<h3>Welcome, <c:out value = "${user.firstName}"/></h3>
        </nav>
        <c:out value = "${error}"/>
        <form:form action="/addShow" method="POST" modelAttribute = "show">
            <div class="row">
                <div class="col-4">
                    <p>
	  					<form:label path = "title">Title:</form:label>
	  					<form:errors path = "title"/>
	  					<form:input path = "title" type = "text"/>
  					</p>
  					<p>
	  					<form:label path = "network">Network:</form:label>
	  					<form:errors path = "network"/>
	  					<form:input path = "network" type = "text"/>
  					</p>
                </div>
            </div>
            <div class='row'>

                <button class='btn btn-outline-dark ml-3 mt-3'>Create</button>
            </div>
        </form:form>
	
	</div>
	

</body>
</html>