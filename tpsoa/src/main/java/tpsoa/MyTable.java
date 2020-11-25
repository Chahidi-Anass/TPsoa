package tpsoa;

import java.sql.Statement;
import java.util.ArrayList;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


/**
 * Root resource (exposed at "db" path)
 */
@Path("db")
public class MyTable {
	
	private String dbUrl;
	
	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}
	
	public MyTable() {
		super();
		this.dbUrl = "jdbc:postgresql://ec2-23-23-36-227.compute-1.amazonaws.com:5432/dapmb4dms9lcs6?password=e5269a3e5c04f15c8e9c382fe0172a3ea328511b5cd32795a050f5b90c7a81bf&sslmode=require&user=bjfqyxueeufoqa";
	}
	
	public Connection getConnection() throws SQLException {
	    //String dbUrl = System.getenv("JDBC_DATABASE_URL");
	    return (Connection) DriverManager.getConnection(this.getDbUrl());
		
	}
	
    /**
     * Method handling HTTP GET requests. The returned object wURISyntaxExceptionill be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     * @throws SQLException 
     * @throws URISyntaxException 
     */
	
	@Path("/nouveau")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String MyTableTick() throws SQLException {
			MyTable db = new MyTable();
	    	Connection conn = db.getConnection();
	    	Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
            stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
            
            
			return "creation ticks";
	}
	    

	@Path("/myticks")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMyTableTick() throws SQLException {
			MyTable db = new MyTable();
	    	Connection conn = db.getConnection();
	    	Statement stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

            ArrayList<String> output = new ArrayList<String>();
            while (rs.next()) {
                output.add("Read from DB: " + rs.getTimestamp("tick"));
            }
			return output.toString();
	}
}

