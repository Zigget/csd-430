<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Record Here</title>
</head>
<body>

    <jsp:useBean id='myDB' class='database.DbBean' />
    <h1>Select Film to Update</h1>
	<a href="index_02.html">Previous Screen</a><br><br>
	
    <%
    if(request.getMethod().equals("GET")){
   	%>
		<%= myDB.formGetPK("CRUD_Update.jsp")%>
    	<%= myDB.readAll()%>
    <%
    }
    %>
    
</body>
</html>