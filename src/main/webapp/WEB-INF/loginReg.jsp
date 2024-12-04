<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <title>Login and Registration Page</title>
</head>
<body>

	<div class = "container">
	<div class = "row">
		<div class = "col-6">
		<p><c:out value="${regError}" /></p>
		    <h1>Register!</h1>
		    <form:form method="POST" action="/registration" modelAttribute="user">
		        <p>
		            <form:label path="firstName">First Name:</form:label>
		            <form:errors path = "firstName"/>
		            <form:input type="firstName" path="firstName"/>
		        </p>
		        <p>
		            <form:label path="lastName">Last Name:</form:label>
		            <form:errors path = "lastName"/>
		            <form:input type="lastName" path="lastName"/>
		        </p>
		        
		        <p>
		            <form:label path="email">Email:</form:label>
		            <form:errors path = "email"/>
		            <form:input type="email" path="email"/>
		        </p>
		        <p>
		            <form:label path="password">Password:</form:label>
		            <form:errors path = "password"/>
		            <form:password path="password"/>
		        </p>
		        <p>
		            <form:label path="passwordConfirmation">Password Confirmation:</form:label>
		            <form:errors path = "passwordConfirmation"/>
		            <form:password path="passwordConfirmation"/>
		        </p>
		        <input type="submit" value="Register!"/>
		    </form:form>
	    </div>

    
	    <div class = "col-4">
	    	<h1>Login</h1>
	    		<p><c:out value="${loginError}" /></p>
			    <form method="post" action="/login">
			        <p>
			            <label for="email">Email</label>
			            <input type="text" id="email" name="email"/>
			        </p>
			        <p>
			            <label for="password">Password</label>
			            <input type="password" id="password" name="password"/>
			        </p>
			        <input type="submit" value="Login!"/>
			    </form>
	    </div>
  	</div>
    </div>
    
</body>
</html>