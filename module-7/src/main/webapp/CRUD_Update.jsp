<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

    <jsp:useBean id='myDB' class='database.DbBean' />

    <%
    if(request.getMethod().equals("GET")){
    %>
    	<form action="CRUD_Update.jsp" method='post'>
    	
    	<h3><label for="Record You Wish To Change">Record You Wish To Update or Add:</label></h3><br><br>
    	
    	<label for="winningTeam">Film Name:</label>
    	<input type="text" id="film_name" name="film_name"><br><br>
    	
    	<label for="city">Film Release Date:</label>
    	<input type="text" id="film_releaseDate" name="film_releaseDate"><br><br>
    	
    	<label for="year">Runtime Minutes:</label>
    	<input type="text" id="film_runtime" name="film_runtime"><br><br>
    	  	
    	<label for="loserCity">Director:</label>
    	<input type="text" id="film_director" name="film_director"><br><br>
    	
    	<input type='submit' value='Submit'>
    	
    	</form>
    <% 	
    }
    %>

    <%
    if(request.getMethod().equals("POST")){

    	
    	String year = request.getParameter("year");
    	
    	out.print(myDB.delete(Integer.parseInt(year)));
    	
    	myDB.createRecord(request.getParameter("film_name"),
    			Integer.parseInt(request.getParameter("film_releaseDate")),
    			Integer.parseInt(request.getParameter("film_runtime")),
    			request.getParameter("film_director"));
    	
    	out.print(myDB.read(Integer.parseInt(request.getParameter("film_id"))));

    	out.println("<br />");

     	out.print(myDB.readAll());    
    }
    %>
</body>
</html>