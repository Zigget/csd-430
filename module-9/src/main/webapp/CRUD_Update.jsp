<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Film</title>
</head>
<body>

    <jsp:useBean id='myDB' class='database.DbBean' />
    <h1>crUd Update</h1>
    <a href="index_02.html">CRUD Screen</a> <br />
	<a href="CRUD_Update_2.jsp">Previous Screen</a> <br />
	<%
	

    if(request.getMethod().equals("GET")){
	    	
		    int film_id = Integer.parseInt(request.getParameter("film_id"));
		
		    java.sql.ResultSet rs = null;
		    try {
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        String url = "jdbc:mysql://localhost:3306/csd430?";
		        java.sql.Connection conn = java.sql.DriverManager.getConnection(url + "user=student1&password=pass");
		        java.sql.Statement stmt = conn.createStatement();
		        rs = stmt.executeQuery("SELECT * FROM film WHERE film_id = " + film_id);
		    } catch(Exception e){}
		
		    String film_name="", film_director="";
		    int film_releaseDate=0, film_runtime=0;
		
		    if(rs != null && rs.next()){
		        film_name = rs.getString("film_name");
		        film_releaseDate = rs.getInt("film_releaseDate");
		        film_runtime = rs.getInt("film_runtime");
		        film_director = rs.getString("film_director");
		    }
	%>
    	<form action="CRUD_Update.jsp" method='post'>
    	
    	<h3><label for="Record You Wish To Change">Record You Wish To Update or Add:</label></h3><br>
    	
    	<label for="Film ID">Film ID:</label>
    	<input type="hidden" id="film_id" name="film_id" value="<%= film_id%>"><br><br>
    	
    	<label for="Film Name">Film Name:</label>
    	<input type="text" id="film_name" name="film_name" value="<%= film_name%>"><br><br>
    	
    	<label for="Release Year">Film Release Date:</label>
    	<input type="text" id="film_releaseDate" name="film_releaseDate" value="<%= film_releaseDate%>"><br><br>
    	
    	<label for="Runtime (Minutes)">Runtime Minutes:</label>
    	<input type="text" id="film_runtime" name="film_runtime" value="<%= film_runtime%>"><br><br>
    	  	
    	<label for="Director">Director:</label>
    	<input type="text" id="film_director" name="film_director" value="<%= film_director%>"><br><br>
    	
    	<input type='submit' value='Submit'><br><br>
    	
    	</form>
    	
    <%
    }
    %>

    <%  
    if(request.getMethod().equals("POST")){
    	%>
		<br>
        <% 
    	
    	myDB.updateRecord(Integer.parseInt(request.getParameter("film_id")),
    			request.getParameter("film_name"),
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