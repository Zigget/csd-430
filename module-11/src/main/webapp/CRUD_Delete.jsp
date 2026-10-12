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
  <h1>Select Film to Delete</h1>
  <br /> <a href="index_02.html">Previous Screen</a> <br />
  
    <%
    if(request.getMethod().equals("GET")){
   	%>
		<%= myDB.formGetPK("CRUD_Delete.jsp")%>
    	<%= myDB.readAll()%>
    <%
    }
    %>
    
    <%
    if(request.getMethod().equals("POST")){
    	
    	String film_id = request.getParameter("film_id");
    	out.print(myDB.delete(Integer.parseInt(film_id)));
     	
     	%>
        <%= myDB.formGetPK("CRUD_Delete.jsp")%>
        <%
        out.print(myDB.readAll());
    }
    %>

</body>
</html>