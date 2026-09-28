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
    
    <%
    if(request.getMethod().equals("GET")){
    	
    	String value = myDB.formGetCreateOrUpdate("CRUD_Update_2.jsp");
    	
    	out.print(value);
    }
    %>
    
    <%
    if(request.getMethod().equals("POST")){
    	
    	myDB.updateRecord(request.getParameter("film_name"),
    			Integer.parseInt(request.getParameter("film_releaseDate")),
    			Integer.parseInt(request.getParameter("film_runtime")),
    			request.getParameter("film_director"));

    	
    	out.println("<br /><br />");

    	out.print(myDB.read(Integer.parseInt(request.getParameter("film_id"))));
    	
    	out.println("<br /><br />");
    	
    	out.print(myDB.readAll());
    }
    %>
    
</body>
</html>