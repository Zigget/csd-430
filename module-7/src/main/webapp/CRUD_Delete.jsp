<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>cruD Delete</title>
</head>
<body>

  <h1>cruD Delete</h1>

  <jsp:useBean id='myDB' class='database.DbBean' />

  <br /> <a href="index_02.html">index 02.html</a> <br />
  
    <%
    if(request.getMethod().equals("GET")){
    	
    	String value = myDB.formGetPK("CRUD_Delete.jsp");
    	
    	out.print(value);
    }
    %>
    
    <%
    if(request.getMethod().equals("POST")){
    	
    	String year = request.getParameter("baseball_Year");
    	
    	out.print(myDB.delete(Integer.parseInt(year)));

    	out.println("<br />");

     	out.print(myDB.readAll());
    }
    %>

</body>
</html>