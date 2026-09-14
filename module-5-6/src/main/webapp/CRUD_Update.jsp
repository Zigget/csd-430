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
    	
    	<label for="winningTeam">Winning Team:</label>
    	<input type="text" id="winningTeam" name="winningTeam"><br><br>
    	
    	<label for="city">Winning City:</label>
    	<input type="text" id="city" name="city"><br><br>
    	
    	<label for="year">Year:</label>
    	<input type="text" id="year" name="year"><br><br>
    	
    	<label for=loserTeam>Loser Team:</label>
    	<input type="text" id="loserTeam" name="loserTeam"><br><br>
    	
    	<label for="loserCity">Loser Team:</label>
    	<input type="text" id="loserCity" name="loserCity"><br><br>
    	
    	<input type='submit' value='Submit'>
    	
    	</form>
    <% 	
    }
    %>

    <%
    if(request.getMethod().equals("POST")){

    	
    	String year = request.getParameter("year");
    	
    	out.print(myDB.delete(Integer.parseInt(year)));
    	
    	myDB.createRecord(request.getParameter("winningTeam"),
    			request.getParameter("city"),
    			Integer.parseInt(request.getParameter("year")),
    			request.getParameter("loserTeam"),
    			request.getParameter("loserCity"));
    	
    	out.print(myDB.read(Integer.parseInt(request.getParameter("year"))));

    	out.println("<br />");

     	out.print(myDB.readAll());    
    }
    %>
</body>
</html>