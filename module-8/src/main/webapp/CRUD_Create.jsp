<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Form to Create</title>
</head>
<body>

  <h1>Crud Create</h1>
  
  <jsp:useBean id='myDB' class='database.DbBean' />
  
    <br /> <a href="index_02.html">Previous Screen</a> <br />
  
    <%
    if(request.getMethod().equals("GET")){
    	
    	String value = myDB.formGetCreateOrUpdate("CRUD_Create.jsp");
    	
    	out.print(value);
    }
    %>
    
    <%
    if(request.getMethod().equals("POST")){
    	
    	myDB.createRecord(Integer.parseInt(request.getParameter("film_id")),
    			request.getParameter("film_name"),
    			Integer.parseInt(request.getParameter("film_releaseDate")),
    			Integer.parseInt(request.getParameter("film_runtime")),
    			request.getParameter("film_director"));

    	out.println("<br />");

     	out.print(myDB.readAll());
    }
    %>
  
</body>
</html>