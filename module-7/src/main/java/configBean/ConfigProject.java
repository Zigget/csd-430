package configBean;
import java.sql.*;

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
public class ConfigProject implements java.io.Serializable {

    java.sql.Connection connection;
    java.sql.Statement statement;
    
    /*
     * The serialVersionUID attribute is used to remember versions of a Serializable 
     * class.  The purpose of this is to allow verification that a loaded class 
     * and the serialized object are compatible.
     */
    private static final long serialVersionUID = 111222333444L;
    
    // ***************************************************************
    // ***************************************************************
    // ------------------------ Constructor --------------------------
    // ***************************************************************
    // ***************************************************************
    
    public ConfigProject() {
    	
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		String url = "jdbc:mysql://localhost:3306/csd430?useSSL=false&serverTimezone=UTC";

            connection = DriverManager.getConnection(
                url,
                "student1",
                "pass"
            );    		statement = connection.createStatement();
    	}
    	catch(ClassNotFoundException cnfe) {
    		System.out.print("SQL Exception" + cnfe);
    	}
    	catch(java.sql.SQLException sqle){
    		sqle.printStackTrace();
    	}
    }
    
    // ***************************************************************
    // ***************************************************************
    // ------------------------ Create Table -------------------------
    // ***************************************************************
    // ***************************************************************

    public String createTable() {
    	System.out.println("Connection = " + connection);
    	System.out.println("Statement = " + statement);
    	StringBuilder dataStringBuilder = new StringBuilder();

    	try{
    		statement.executeUpdate("DROP TABLE film");
    		dataStringBuilder.append("<b>Table film Dropped.</b><br />");
    	}
    	catch(java.sql.SQLException e){
    		dataStringBuilder.append("<b>Table film does not exist.</b><br />");
    	}
    	
    	try{
    		statement.executeUpdate("CREATE TABLE film(film_id INT NOT NULL PRIMARY KEY, film_name CHAR(20) NOT NULL, film_releaseDate INT NOT NULL, film_runtime INT NOT NULL, film_director CHAR(20) NOT NULL)");
    		dataStringBuilder.append("<b>Table film Created.</b><br />");
    	}
    	catch(java.sql.SQLException e){
    		dataStringBuilder.append("<b>Table film Creation failed.</b><br />");
    	}
    	
    	return dataStringBuilder.toString();
    }
    
    // ***************************************************************
    // ***************************************************************
    // ------------------------ Populate Table -----------------------
    // ***************************************************************
    // ***************************************************************

    public String populateTable() {
    	 
    	StringBuilder dataStringBuilder = new StringBuilder();

    	try{
    		statement.executeUpdate("INSERT INTO film(film_id, film_name, film_releaseDate, film_runtime, film_director)VALUES('1', 'Gladiator', 2000, 155, 'Ridley Scott')");
    		statement.executeUpdate("INSERT INTO film(film_id, film_name, film_releaseDate, film_runtime, film_director)VALUES('2', 'Alien', 1979, 117, 'Ridley Scott')");
    		statement.executeUpdate("INSERT INTO film(film_id, film_name, film_releaseDate, film_runtime, film_director)VALUES('3', 'Get Out', 2017, 104, 'Jordan Peele')");
    		statement.executeUpdate("INSERT INTO film(film_id, film_name, film_releaseDate, film_runtime, film_director)VALUES('4', 'Spirited Away', 2001, 125, 'Hayao Miyazaki')");
    		statement.executeUpdate("INSERT INTO film(film_id, film_name, film_releaseDate, film_runtime, film_director)VALUES('5', 'Akira', 1988, 124, 'Katsuhiro Otomo')");
    		statement.executeUpdate("INSERT INTO film(film_id, film_name, film_releaseDate, film_runtime, film_director)VALUES('6', 'Redline', 2009, 102, 'Takeshi Koike')");
    		statement.executeUpdate("INSERT INTO film(film_id, film_name, film_releaseDate, film_runtime, film_director)VALUES('7', 'The Matrix', 1999, 136, 'The Wachowskis')");
    		statement.executeUpdate("INSERT INTO film(film_id, film_name, film_releaseDate, film_runtime, film_director)VALUES('8', 'Blade Runner', 1982, 117, 'Ridley Scott')");
    		statement.executeUpdate("INSERT INTO film(film_id, film_name, film_releaseDate, film_runtime, film_director)VALUES('9', 'Inception', 2010, 148, 'Christopher Nolan')");
    		statement.executeUpdate("INSERT INTO film(film_id, film_name, film_releaseDate, film_runtime, film_director)VALUES('10', 'Princess Mononoke', 1997, 134, 'Hayao Miyazaki')");

    		statement.executeUpdate("COMMIT");
            
            dataStringBuilder.append("Fill of table conpleted.");
            
    	}
    	catch(java.sql.SQLException e){
    		dataStringBuilder.append("<b>Error inserting data</b><br />");
    	}
    	
    	return dataStringBuilder.toString();
    }

    // ***************************************************************
    // ***************************************************************
    // ------------------------ Read Table ---------------------------
    // ***************************************************************
    // ***************************************************************
    
    public String read() {
    	
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
