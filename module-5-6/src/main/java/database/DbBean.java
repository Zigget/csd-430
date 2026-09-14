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
    
    public void createRecord(String winningTeam, String winningCity, int year, 
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
    	
    	try {

    		String sql = "INSERT INTO World_Series(Team, City, Year_T, LoserTeam, LoserCity)" + 
    		   "VALUES(?, ?, ?, ?, ?)";
    		
    		java.sql.PreparedStatement sqlStatement = connection.prepareStatement(sql);
    		
    		sqlStatement.setString(1, winningTeam );    		
    		sqlStatement.setString(2, winningCity );    		
    		sqlStatement.setInt(3, year );    		
    		sqlStatement.setString(4, loserTeam );    		
    		sqlStatement.setString(5, loserCity );
    		
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
    		
        	resultSet = statement.executeQuery("SELECT DISTINCT Team FROM film ORDER BY Team ASC");
        }
        catch(java.sql.SQLException e){
        	
        }
    	
        // ---------------------------------------------------------------
        // ---------------------------------------------------------------
        // ------------------------ Open Form ----------------------
        // ---------------------------------------------------------------
        // ---------------------------------------------------------------

    	dataStringBuilder.append("<form method='post' action='" + requestURL + "'>");
    	dataStringBuilder.append("<br /><br />");  

        // ---------------------------------------------------------------
        // ---------------------------------------------------------------
        // ------------------------ Open Winning Team --------------------
        // ---------------------------------------------------------------
        // ---------------------------------------------------------------
    	
    	dataStringBuilder.append("<label for='winningTeam'>Winning Team</label>");
    	dataStringBuilder.append("&nbsp&nbsp");  
    	dataStringBuilder.append("<input name='winningTeam' list='winningTeam'>");     
    	dataStringBuilder.append("<datalist id='winningTeam'>\n");  
    	
        try{
            
            while(resultSet.next()){
                  
           	  dataStringBuilder.append("<option value='" + resultSet.getString(1) + "'>\n");
            }         
          }
          catch(Exception e){

        	System.out.print("<b>Exception.</b><br />");
        	System.out.print(e);
          }
    	
    	dataStringBuilder.append("</datalist>");
    	dataStringBuilder.append("</input>");  
    	dataStringBuilder.append("<br /><br />");  
    	
        // ---------------------------------------------------------------
        // ------------------------ Close Winning Teams ------------------
        // ---------------------------------------------------------------

    	try{
    		
        	resultSet = statement.executeQuery("SELECT DISTINCT City FROM film ORDER BY City ASC");

        }
        catch(java.sql.SQLException e){
        	
        }

        // ---------------------------------------------------------------
        // ---------------------------------------------------------------
        // ------------------------ Open Winning City --------------------
        // ---------------------------------------------------------------
        // ---------------------------------------------------------------
    	
    	dataStringBuilder.append("<label for='city'>Winning City</label>");
    	dataStringBuilder.append("&nbsp&nbsp");   
    	dataStringBuilder.append("<input name='city' list='city'>");      	
    	dataStringBuilder.append("<datalist id='city'>\n");  
    	
        try{
            
            while(resultSet.next()){
                  
           	  dataStringBuilder.append("<option value='" + resultSet.getString(1) + "'>\n");
            }         
          }
          catch(Exception e){

        	System.out.print("<b>Exception.</b><br />");
        	System.out.print(e);
          }
    	
    	dataStringBuilder.append("</datalist>");
    	dataStringBuilder.append("</input>");  
    	dataStringBuilder.append("<br /><br />");  
    	
        // ---------------------------------------------------------------
        // ------------------------ Close Winning City -------------------
        // ---------------------------------------------------------------

    	
        // ---------------------------------------------------------------
        // ---------------------------------------------------------------
        // ------------------------ Open Year ----------------------------
        // ---------------------------------------------------------------
        // ---------------------------------------------------------------
    	
    	if( requestURL.equals("CRUD_Create.jsp") ) {
    	
            dataStringBuilder.append("<label for='year'>Year</label>");
            dataStringBuilder.append("&nbsp&nbsp");   
            dataStringBuilder.append("<input type='text' name='year' maxlength='20'>");      	
            dataStringBuilder.append("<br /><br />");   
    	}
    	else if ( requestURL.equals("CRUD_Update_2.jsp") ) {
    	
        	try{
        		
        		resultSet = statement.executeQuery("SELECT DISTINCT Year_T FROM World_Series ORDER BY Year_T ASC");
            }
            catch(java.sql.SQLException e){
            	
            }
    		
            dataStringBuilder.append("<label for='year'>Year</label>");
            dataStringBuilder.append("&nbsp&nbsp");   
            dataStringBuilder.append("<select name='year' id='year' maxlength='20'>");   
            
            try {
                while(resultSet.next()) {
            	    dataStringBuilder.append("<option value=" + resultSet.getString(1) + ">" + resultSet.getString(1) + "</option>");
                }
            }
            catch(java.sql.SQLException e){
            	dataStringBuilder.append("<br /><br />");   
            }
   
            dataStringBuilder.append("</select>");
            dataStringBuilder.append("<br /><br />");
    	}
    	
        // ---------------------------------------------------------------
        // ------------------------ Close Year ---------------------------
        // ---------------------------------------------------------------

    	try{
    		
    		resultSet = statement.executeQuery("SELECT DISTINCT LoserTeam FROM World_Series ORDER BY LoserTeam ASC");
        }
        catch(java.sql.SQLException e){
        	
        }

        // ---------------------------------------------------------------
        // ---------------------------------------------------------------
        // ------------------------ Open Losing Teams --------------------
        // ---------------------------------------------------------------
        // ---------------------------------------------------------------

    	dataStringBuilder.append("<label for='loserTeam'>Losing Team</label>");
    	dataStringBuilder.append("&nbsp&nbsp");   
    	dataStringBuilder.append("<input name='loserTeam' list='loserTeam'>");      	
    	dataStringBuilder.append("<datalist id='loserTeam'>\n");  
    	
        try{
            
            while(resultSet.next()){

           	  dataStringBuilder.append("<option value='" + resultSet.getString(1) + "'>\n");
            }         
          }
          catch(Exception e){

        	System.out.print("<b>Exception.</b><br />");
        	System.out.print(e);
          }
    	
    	dataStringBuilder.append("</datalist>");
    	dataStringBuilder.append("</input>");  
    	dataStringBuilder.append("<br /><br />");  
    	
        // ---------------------------------------------------------------
        // ------------------------ Close Losing Teams -------------------
        // ---------------------------------------------------------------

    	try{
    		
        	resultSet = statement.executeQuery("SELECT DISTINCT LoserCity FROM World_Series ORDER BY LoserCity ASC");
        }
        catch(java.sql.SQLException e){
        	
        }

        // ---------------------------------------------------------------
        // ---------------------------------------------------------------
        // ------------------------ Open Losing Cities -------------------
        // ---------------------------------------------------------------
        // ---------------------------------------------------------------

    	dataStringBuilder.append("<label for='loserCity'>Losing City</label>");
    	dataStringBuilder.append("&nbsp&nbsp");  
    	dataStringBuilder.append("<input name ='loserCity' list='loserCity'>");      	
    	dataStringBuilder.append("<datalist id='loserCity'>\n");  
    	
        try{
            
            while(resultSet.next()){

           	  dataStringBuilder.append("<option value='" + resultSet.getString(1) + "'>\n");
            }         
          }
          catch(Exception e){

        	System.out.print("<b>Exception.</b><br />");
        	System.out.print(e);
          }
    	
    	dataStringBuilder.append("</datalist>");
    	dataStringBuilder.append("</input>");  
    	dataStringBuilder.append("<br /><br />");  
    	
        // ---------------------------------------------------------------
        // ------------------------ Close Losing Cities ======------------
        // ---------------------------------------------------------------
    	
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
        	
        	resultSet = statement.executeQuery("SELECT year_t FROM World_Series");
        }
        catch(java.sql.SQLException e){
        }
    	
    	
    	StringBuilder dataStringBuilder = new StringBuilder();
    	
    	// Add Data to StringBuilder
    	dataStringBuilder.append("<form method='post' action='" + requestURL + "'>\n");    	
    	dataStringBuilder.append("<label>Enter your favorite World Series Year</label>&nbsp;&nbsp;&nbsp;\n");    	
    	dataStringBuilder.append("<br /> \n");    	
    	dataStringBuilder.append("<label for=\\\"baseballYear\\\">Select a Year:</label>\n");    	
    	dataStringBuilder.append("<select name=\"baseball_Year\" id=\"baseballYear\">\n");    	

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
        	resultSet = statement.executeQuery("SELECT * FROM World_Series WHERE year_t = " + year);
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
    	resultSet = statement.executeQuery("SELECT * FROM World_Series");
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
