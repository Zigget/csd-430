<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>cRud Read</title>
</head>
<body>

  <h1>cRud Read</h1>

  <jsp:useBean id='myDB' class='database.DbBean' />

  <br /> <a href="index_02.html">Previous Screen</a> <br />
    
    <%
    if(request.getMethod().equals("GET")){
    	
    	String value = myDB.formGetPK("CRUD_Read.jsp");
    	
    	out.print(value);
    	
    }
    %>

    <%
    if(request.getMethod().equals("POST")){
    	
    	out.print(myDB.formGetPK("CRUD_Read.jsp"));

    	String film_id = request.getParameter("film_id");

    	out.print(myDB.read(Integer.parseInt(film_id)));

    	out.println("<br />");

     	out.print(myDB.readAll());
    }
    %>

</body>
</html>