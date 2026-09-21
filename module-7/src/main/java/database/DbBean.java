package database;

/* ******************************************************************
 *
 * Professor Darrell Payne
 * Bellevue University
 *
 * Samuel Sidzyik
 * CSD-430
 * 
 * ******************************************************************
 */

public class DbBean implements java.io.Serializable {

    java.sql.Connection connection;
    java.sql.Statement statement;
    
    /*
     * The serialVersionUID attribute is used to remember versions of a Serializable 
     * class.  The purpose of this is to allow verification that a loaded class 
     * and the serialized object are compatible.
     */
    private static final long serialVersionUID = 111222333444L;
    
    // ---------------------------------------------------------------
    // ---------------------------------------------------------------
    // ------------------------ Constructor --------------------------
    // ---------------------------------------------------------------
    // ---------------------------------------------------------------
    
    public DbBean() {
    	
    	try {
    		
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		String url = "jdbc:mysql://localhost:3306/csd430?";
    		connection = java.sql.DriverManager.getConnection(url + "user=student1&password=pass");
    		statement = connection.createStatement();
    	}
    	catch(ClassNotFoundException cnfe) {
    		
    		System.out.print("SQL Exception" + cnfe);
    	}
    	catch(java.sql.SQLException sqle){
    		
    		System.out.print("SQL Exception" + sqle);
    	}
    }

    // ***************************************************************
    // ***************************************************************
    // ------------------------ Update Record ------------------------
    // ***************************************************************
    // ***************************************************************

    public String updateRecord(String winningTeam, String winningCity, int year, 
            String loserTeam, String loserCity) {
    	
    	try {
    		
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		String url = "jdbc:mysql://localhost:3306/csd430?";
    		connection = java.sql.DriverManager.getConnection(url + "user=student1&password=pass");
    	}
    	catch(ClassNotFoundException cnfe) {
    		
    		System.out.print("SQL Exception" + cnfe);
    	}
    	catch(java.sql.SQLException sqle){
    		
    		System.out.print("SQL Exception" + sqle);
    	}
    	
		String sql = "UPDATE world_series SET team = ?, city = ?,  loserTeam = ?, loserCity = ? WHERE year_t = ?";
	    		
		try {
	    		java.sql.PreparedStatement sqlStatement = connection.prepareStatement(sql);
	    		
	    		sqlStatement.setString(1, winningTeam );    		
	    		sqlStatement.setString(2, winningCity );    		
	    		sqlStatement.setString(3, loserTeam );    		
	    		sqlStatement.setString(4, loserCity );
	    		
	    		sqlStatement.setInt(5, year ); 
	    		
	    		sqlStatement.executeUpdate();
			    statement.close();
		}
    	catch(java.sql.SQLException sqle){
    		
    		System.out.print("SQL Exception" + sqle);
    	}
		
    	return "Complete";
    }
    
    // ***************************************************************
    // ***************************************************************
    // ------------------------ Create Record ------------------------
    // ***************************************************************
    // ***************************************************************
    
    public void createRecord(String film_name, int film_releaseDate, int film_runtime, 
    		                   String film_director) {
    	
    	try {
    		
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		String url = "jdbc:mysql://localhost:3306/csd430?";
    		connection = java.sql.DriverManager.getConnection(url + "user=student1&password=pass");
    	}
    	catch(ClassNotFoundException cnfe) {
    		
    		System.out.print("SQL Exception" + cnfe);
    	}
    	catch(java.sql.SQLException sqle){
    		
    		System.out.print("SQL Exception" + sqle);
    	}
    	
    	try {

    		String sql = "INSERT INTO film(film_name, film_releaseDate, film_runtime, film_director" + 
    		   "VALUES(?, ?, ?, ?)";
    		
    		java.sql.PreparedStatement sqlStatement = connection.prepareStatement(sql);
    		
    		sqlStatement.setString(1, film_name );    		
    		sqlStatement.setInt(2, film_releaseDate );    		
    		sqlStatement.setInt(3, film_runtime );    		
    		sqlStatement.setString(4, film_director );
    		
    		sqlStatement.executeUpdate();
    		
    		sqlStatement.close();
    	}
    	catch(java.sql.SQLException sqle) {
    		
    	}
    }
    
    
    // ***************************************************************
    // ***************************************************************
    // ------------------------ FormGetCreate ------------------------
    // ***************************************************************
    // ***************************************************************
    
    public String formGetCreateOrUpdate(String requestURL) {
    	    	
    	StringBuilder dataStringBuilder = new StringBuilder();

        java.sql.ResultSet resultSet = null;
    	
    	try {
    		
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		String url = "jdbc:mysql://localhost:3306/csd430?";
    		connection = java.sql.DriverManager.getConnection(url + "user=student1&password=pass");
    		statement = connection.createStatement();
    	}
    	catch(ClassNotFoundException cnfe) {
    		
    		System.out.print("SQL Exception" + cnfe);
    	}
    	catch(java.sql.SQLException sqle){
    		
    		System.out.print("SQL Exception" + sqle);
    	}
    	
    	try{
    		
        	resultSet = statement.executeQuery("SELECT max(film_id) as max_value from film");
        	if (resultSet.next()) {
                int max = resultSet.getInt(1) + 1;
                dataStringBuilder.append("<br /><br />"); 
                dataStringBuilder.append("<label for='film_name'>Film Name</label>");
            	dataStringBuilder.append("&nbsp&nbsp");
            	dataStringBuilder.append(max);
            }
    	}
        catch(java.sql.SQLException e){
        	
        }
    	
        // ---------------------------------------------------------------
        // ---------------------------------------------------------------
        // ------------------------ Open Form ----------------------
        // ---------------------------------------------------------------
        // ---------------------------------------------------------------

    	dataStringBuilder.append("<form method='post' action='" + requestURL + "'>");
    	dataStringBuilder.append("<br />");  

        // ---------------------------------------------------------------
        // ---------------------------------------------------------------
        // ------------------------ Enter Film Name --------------------
        // ---------------------------------------------------------------
        // ---------------------------------------------------------------
    	
    	dataStringBuilder.append("<label for='film_name'>Film Name</label>");
    	dataStringBuilder.append("&nbsp&nbsp");  
    	dataStringBuilder.append("<input type='text' name='film_name'>");     
    	dataStringBuilder.append("</input>");  
    	dataStringBuilder.append("<br /><br />");  
    	
        // ---------------------------------------------------------------
        // ------------------------ Close Film Name ------------------
        // ---------------------------------------------------------------


        // ---------------------------------------------------------------
        // ---------------------------------------------------------------
        // ------------------------ Open Film Release Year --------------------
        // ---------------------------------------------------------------
        // ---------------------------------------------------------------
    	
    	if( requestURL.equals("CRUD_Create.jsp") ) {
        	
            dataStringBuilder.append("<label for='film_releaseDate'>Release Date</label>");
            dataStringBuilder.append("&nbsp&nbsp");   
            dataStringBuilder.append("<input type='text' name='film_releaseDate' maxlength='20'>");      	
            dataStringBuilder.append("<br /><br />");   
    	} 
    	
        // ---------------------------------------------------------------
        // ------------------------ Close Film Release Year -------------------
        // ---------------------------------------------------------------

    	
        // ---------------------------------------------------------------
        // ---------------------------------------------------------------
        // ------------------------ Open Runtime ----------------------------
        // ---------------------------------------------------------------
        // ---------------------------------------------------------------
    	
    	if( requestURL.equals("CRUD_Create.jsp") ) {
    	
            dataStringBuilder.append("<label for='film_runtime'>Runtime (Minutes)</label>");
            dataStringBuilder.append("&nbsp&nbsp");   
            dataStringBuilder.append("<input type='text' name='film_runtime' maxlength='20'>");      	
            dataStringBuilder.append("<br /><br />");   
    	}
    	
        // ---------------------------------------------------------------
        // ------------------------ Close Runtime ---------------------------
        // ---------------------------------------------------------------


        // ---------------------------------------------------------------
        // ---------------------------------------------------------------
        // ------------------------ Open Director --------------------
        // ---------------------------------------------------------------
        // ---------------------------------------------------------------

    	dataStringBuilder.append("<label for='film_director'>Director</label>");
    	dataStringBuilder.append("&nbsp&nbsp");   
    	dataStringBuilder.append("<input type='text' name='film_director>");
    	dataStringBuilder.append("</input>");  
    	dataStringBuilder.append("<br /><br />");  
    	
        // ---------------------------------------------------------------
        // ------------------------ Close Director -------------------
        // ---------------------------------------------------------------
    	dataStringBuilder.append("<br /><br />");  
    	dataStringBuilder.append("<input type='submit' value='Submit'>");
    	
    	dataStringBuilder.append("</form>");

        // ---------------------------------------------------------------
        // ---------------------------------------------------------------
        // ------------------------ Close Form ---------------------------
        // ---------------------------------------------------------------
        // ---------------------------------------------------------------
    	
    	return dataStringBuilder.toString();
    }
    
    // ***************************************************************
    // ***************************************************************
    // ------------------------ FormGetPK ----------------------------
    // ***************************************************************
    // ***************************************************************

    public String formGetPK(String requestURL) {
    	
    	java.sql.ResultSet resultSet = null;
    	
    	try{
    		
    		Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/csd430?";
            connection = java.sql.DriverManager.getConnection(url + "user=student1&password=pass");
            statement = connection.createStatement();
        }
    	catch(ClassNotFoundException cnfe) {
    		
    	}
    	catch(java.sql.SQLException e){
        
    	}
        try{
        	
        	resultSet = statement.executeQuery("SELECT film_id FROM film");
        }
        catch(java.sql.SQLException e){
        }
    	
    	
    	StringBuilder dataStringBuilder = new StringBuilder();
    	
    	// Add Data to StringBuilder
    	dataStringBuilder.append("<form method='post' action='" + requestURL + "'>\n");    	
    	dataStringBuilder.append("<label>Select a Film ID</label>&nbsp;&nbsp;&nbsp;\n");    	
    	dataStringBuilder.append("<br /> \n");    	
    	dataStringBuilder.append("<label for=\\\"film_id\\\">Select an ID:</label>\n");    	
    	dataStringBuilder.append("<select name=\"film_id\" id=\"film_id\">\n");    	

        try{
            
            while(resultSet.next()){

              for(int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++){

            	dataStringBuilder.append("<option value=\"");

            	dataStringBuilder.append((resultSet.getString(i)));

            	dataStringBuilder.append("\">\n");

            	dataStringBuilder.append((resultSet.getString(i)));

            	dataStringBuilder.append("</option>");          	
              }
            }
            
          }
          catch(Exception e){

        	System.out.print("<b>Exception.</b><br />");
        	System.out.print(e);
          }
    
    	dataStringBuilder.append("</select>");

    	dataStringBuilder.append("<input type='submit' />");

    	dataStringBuilder.append("</form>");

    	resultSet = null;
    	
    	return dataStringBuilder.toString();
    }
    
    // ***************************************************************
    // ***************************************************************
    // ------------------------ Read ---------------------------------
    // ***************************************************************
    // ***************************************************************

    public String read(int year) {
    	
    	StringBuilder dataStringBuilder = new StringBuilder();    	
    	
    	java.sql.ResultSet resultSet = null;   	
    	
    	try{
    		
    		Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/csd430?";
            connection = java.sql.DriverManager.getConnection(url + "user=student1&password=pass");
            statement = connection.createStatement();
        }
    	catch(ClassNotFoundException cnfe) {
    		
    	}
    	catch(java.sql.SQLException e){
        
    	}
        try{
        	resultSet = statement.executeQuery("SELECT * FROM film WHERE film_id = " + year);
        }
        catch(java.sql.SQLException e){
        }
        
        try{
            
        	dataStringBuilder.append("<table border='1' bgcolor='FA8072'>");
            
            while(resultSet.next()){
            	
            	dataStringBuilder.append("<tr>");
                  
              for(int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++){
            	  
            	  dataStringBuilder.append("<td>");
            	  dataStringBuilder.append((resultSet.getString(i)).trim());
            	  dataStringBuilder.append("</td>");
              }
                  
              dataStringBuilder.append("</tr>");
            }
              
            dataStringBuilder.append("</table>");            
          }
          catch(Exception e){

        	System.out.print("<b>Exception.</b><br />");
        	System.out.print(e);
          }
    	
    	return dataStringBuilder.toString();
    }
    
    // ***************************************************************
    // ***************************************************************
    // ------------------------ Delete -------------------------------
    // ***************************************************************
    // ***************************************************************

    public String delete(int year) {
    	
    	StringBuilder dataStringBuilder = new StringBuilder();    	
    	
    	try{
    		
    		Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/csd430?";
            connection = java.sql.DriverManager.getConnection(url + "user=student1&password=pass");
            statement = connection.createStatement();
        }
    	catch(ClassNotFoundException cnfe) {
    		
    	}
    	catch(java.sql.SQLException e){
        
    	}
        try{
        	
        	statement.executeUpdate("DELETE FROM World_Series WHERE year_t = " + year);
        	
        	dataStringBuilder.append("The record has been deleted.");
        }
        catch(java.sql.SQLException e){
        	
        }

    	return dataStringBuilder.toString();
    }

    // ***************************************************************
    // ***************************************************************
    // ------------------------ Read All -----------------------------
    // ***************************************************************
    // ***************************************************************
    
    public String readAll() {
    	
    StringBuilder dataStringBuilder = new StringBuilder();    	
	
	java.sql.ResultSet resultSet = null;   	
	
	try{
		
		Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/csd430?";
        connection = java.sql.DriverManager.getConnection(url + "user=student1&password=pass");
        statement = connection.createStatement();
    }
	catch(ClassNotFoundException cnfe) {
		
	}
	catch(java.sql.SQLException e){
    
	}
    try{
    	resultSet = statement.executeQuery("SELECT * FROM film");
    }
    catch(java.sql.SQLException e){
    }
    
    try{
        
    	dataStringBuilder.append("<table border='1' bgcolor='FFFF00'>");
        
        while(resultSet.next()){
        	
        	dataStringBuilder.append("<tr>");
              
          for(int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++){
        	  
        	  dataStringBuilder.append("<td>");
        	  dataStringBuilder.append((resultSet.getString(i)).trim());
        	  dataStringBuilder.append("</td>");
          }
              
          dataStringBuilder.append("</tr>");
        }
          
        dataStringBuilder.append("</table>");            
      }
      catch(Exception e){

    	System.out.print("<b>Exception.</b><br />");
    	System.out.print(e);
      }
	
	return dataStringBuilder.toString();
}

    // ***************************************************************
    // ***************************************************************
    // ------------------------ Close Connection ---------------------
    // ***************************************************************
    // ***************************************************************

    public void closeConnection(){
    	
    	try {
    		
    		statement.close();
    		connection.close();
    	}
    	catch(java.sql.SQLException sqle){
    		
    		System.out.print("SQL Exception" + sqle);    		
    	}    	
    }
}
