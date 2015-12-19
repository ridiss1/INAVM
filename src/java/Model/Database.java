/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jean
 */
public class Database {
    private final String url = "jdbc:derby://localhost:1527/INAVM_Platform;user=inavm;password=inavm";
    private Connection conn = null;
    private Statement stmt = null;
    private final String IPadressesTable = "IPADRESSES";
    private final String ContainersTable = "CONTAINER";
    
    //Constructor
    public Database(){
        createConnection();
    }
    
    public void createConnection() {
        try {
            //Get a connection based on the db URL
            conn = DriverManager.getConnection(url);
            System.out.println("Database Connected");
        } catch (Exception except) {
            System.out.println("Exception in DataBase class=" + except);
        }
    }
    
    public String GetAvailableIpAdress() {
        String adress = "No IP Adress";
        
        try {
            // creates a SQL Statement object in order to execute the SQL select command
            stmt = conn.createStatement();
            // the SQL select command will provide a ResultSet containing the query results
            ResultSet results;
           // the SQL select command will provide a ResultSet containing the query results 
           results = stmt.executeQuery("SELECT  *  FROM "+IPadressesTable+" WHERE ("+IPadressesTable+".AFFECTED='false') FETCH FIRST ROW ONLY");    
            // the ResultSetMetaData object will provide information about the columns
            // for instance the number of columns, their labels, etc.
            //ResultSetMetaData rsmd = results.getMetaData();
            System.out.println("Results==========" + results);
            
            if (results.next()) {
                adress = results.getString(results.findColumn("ADRESS"));
                System.out.println("***************" + adress + "---");
            }
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            adress = sqlExcept.toString();
        }
        return adress;
    }
    
    public synchronized String AddContainer(int idContainer, String IpAdress,int remotePort) {
        int refIpAdress = 0;
        String affected = "true";
        String insert = "Insertion";
        try {
            // creates a SQL Statement object in order to execute the SQL insert command
            stmt = conn.createStatement();
            ResultSet resultsIp = stmt.executeQuery("SELECT  *  FROM "+IPadressesTable+" WHERE (" + IPadressesTable + ".ADRESS='" + IpAdress + "')");
            //ResultSetMetaData rsmd = resultsIp.getMetaData();
            if (resultsIp.next()) {
                refIpAdress = resultsIp.getInt(resultsIp.findColumn("ID"));
                System.out.println("***************" + refIpAdress + "---");
            }
            stmt.execute("insert into " + ContainersTable + " (IDCONTAINER,AFFECTED,IPADRESS,REMOTEPORT) values ("+ idContainer + "," + affected +","+ refIpAdress + "," + remotePort + ")");
            stmt.close();
        } catch (SQLException sqlExcept) {
            insert = "Container : " + sqlExcept.toString();
        }
        System.out.println(insert);
        return insert;
    }
    
    public int GetLastContainerId() {
        int contId= 0;
        
        try {
            // creates a SQL Statement object in order to execute the SQL select command
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            // the SQL select command will provide a ResultSet containing the query results
            ResultSet results;
           // the SQL select command will provide a ResultSet containing the query results 
           results = stmt.executeQuery("SELECT  *  FROM "+ContainersTable);    
           System.out.println("Results==========" + results);
           
           if (results.last()) {
                contId = results.getInt(results.findColumn("IDCONTAINER"));
                System.out.println("***************" + contId + "---");
                contId ++;
           }
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            String error = sqlExcept.toString();
            System.out.println("***************" + error + "---");
        }
        
        return contId;
    }
    
    public int GetLastRemotePort() {
        int remotePort= 0;
        
        try {
            // creates a SQL Statement object in order to execute the SQL select command
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            // the SQL select command will provide a ResultSet containing the query results
            ResultSet results;
           // the SQL select command will provide a ResultSet containing the query results 
           results = stmt.executeQuery("SELECT  *  FROM "+ContainersTable);    
           System.out.println("Results==========" + results);
           
           if (results.last()) {
                remotePort = results.getInt(results.findColumn("REMOTEPORT"));
                System.out.println("***************Last Remote POrt" + remotePort + "---");
                remotePort ++;
           }
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            String error = sqlExcept.toString();
            System.out.println("***************" + error + "---");
        }
        
        return remotePort;
    }
    
    public boolean UpdateIpadress(String IpAdress, Boolean affected) {
        String insert = "Insertion";
        boolean executed = false;
        try {
            // creates a SQL Statement object in order to execute the SQL insert command
            stmt = conn.createStatement();
            stmt.execute("UPDATE "+IPadressesTable+" SET AFFECTED = "+affected+" WHERE (" + IPadressesTable + ".ADRESS='" + IpAdress + "')");
            executed = true;
            stmt.close();
        } catch (SQLException sqlExcept) {
            insert = "IpAdress : " + sqlExcept.toString();
            executed = false;
        }
        System.out.println(insert);
        return executed;
    }
    
}
