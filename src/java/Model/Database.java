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
import java.util.ArrayList;
import Model.TemplateVM;

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
    private final String TempDefaultTable = "TEMPLATESDEFAULT";
    private final String TemplatesTable = "TEMPLATES";
    private final String ContTempTable = "CONTAINERTEMPLATE";
    private final String Nomtable="UserDB";
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
    
    // Verifie si on existe dans la DB
    public String verifID(String Email, String pass) {
        String r = "";
        String type; 
        try {
            // creates a SQL Statement object in order to execute the SQL select command
            stmt = conn.createStatement();
            // the ResultSetMetaData object will provide information about the columns
            // for instance the number of columns, their labels, etc.
            try ( // the SQL select command will provide a ResultSet containing the query results
                    ResultSet results = stmt.executeQuery("SELECT * FROM " + Nomtable + " WHERE (" + Nomtable + ".Email='" + Email + "') AND (" + Nomtable + ".Password='" + pass + "')")) {
                // the ResultSetMetaData object will provide information about the columns
                // for instance the number of columns, their labels, etc.
                ResultSetMetaData rsmd = results.getMetaData();
                System.out.println("le res"+results);
                int ID = -5;
                while (results.next()) {
                    ID = results.getInt(results.findColumn("ID"));
                    System.out.println("le id="+ID);
                }
                if (ID != -5) {
                    r = "voila";
                }
            }
            stmt.close();
        } catch (SQLException sqlExcept) {
            r = sqlExcept.toString();
        }
        System.out.println("le r "+r);
        return r;
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
    
    public synchronized String AddContainer(int idContainer, String IpAdress,int remotePort,String finalHostname) {
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
            stmt.execute("insert into " + ContainersTable + " (IDCONTAINER,AFFECTED,IPADRESS,REMOTEPORT,FINALHOSTNAME) values ("+ idContainer + "," + affected +","+ refIpAdress + "," + remotePort+ ",'" + finalHostname + "')");
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
    
    public ArrayList<String> GetTemplatesDefault() {
        
        ArrayList<String> templates = new ArrayList<String>();
        int i=0;
        try {
            // creates a SQL Statement object in order to execute the SQL select command
            stmt = conn.createStatement();
            // the SQL select command will provide a ResultSet containing the query results
            ResultSet results;
            // the SQL select command will provide a ResultSet containing the query results
            results = stmt.executeQuery("SELECT  *  FROM "+TempDefaultTable);
            System.out.println("Results==========" + results);
            
            while(results.next()) {
                
                templates.add(results.getString(results.findColumn("OSTEMPLATE")));
                System.out.println("***************template "+(i+1)+": "+templates.get(i));
                i++;
            }
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            String error = sqlExcept.toString();
            System.out.println("***************" + error + "---");
        }
        
        return templates;
    }
    
    public ArrayList<String> GetTemplatesCustom() {
        ArrayList<String> templates = new ArrayList<String>();
        int i=0;
        
        try {
            // creates a SQL Statement object in order to execute the SQL select command
            stmt = conn.createStatement();
            // the SQL select command will provide a ResultSet containing the query results
            ResultSet results;
            // the SQL select command will provide a ResultSet containing the query results
            results = stmt.executeQuery("SELECT  *  FROM "+TemplatesTable);
            System.out.println("Results==========" + results);
            
            while(results.next()) {
                
                templates.add(results.getString(results.findColumn("NAME")));
                System.out.println("***************template "+(i+1)+": "+templates.get(i));
                i++;
            }
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            String error = sqlExcept.toString();
            System.out.println("***************" + error + "---");
        }
        
        return templates;
    }
    
    public synchronized String AddContainerTemplate(int idContainer,int template,int templateDefault) {//a container is created with template or templedefault, 0 == null
        
        String insert = "Insertion";
        
        try {
            // creates a SQL Statement object in order to execute the SQL insert command
            stmt = conn.createStatement();
            if (template ==0)//container is created with template default
            {
                stmt.execute("insert into " + ContTempTable + " (CONTAINER,TEMPLATE,TEMPLATEDEFAULT) values ("+ idContainer + ",null,"+ templateDefault + ")");
            }
            else
            {//container is created with template custom
                stmt.execute("insert into " + ContTempTable + " (CONTAINER,TEMPLATE,TEMPLATEDEFAULT) values ("+ idContainer + ","+templateDefault+",null)");
            }
            
            stmt.close();
        } catch (SQLException sqlExcept) {
            insert = "Container : " + sqlExcept.toString();
        }
        System.out.println(insert);
        return insert;
    }
    
    public int GetContainerIdByVmId(int vmId) {
        int id= 0;
        
        try {
            // creates a SQL Statement object in order to execute the SQL select command
            stmt = conn.createStatement();
            // the SQL select command will provide a ResultSet containing the query results
            ResultSet results;
            // the SQL select command will provide a ResultSet containing the query results
            results = stmt.executeQuery("SELECT  *  FROM "+ContainersTable+" WHERE(" + ContainersTable + ".IDCONTAINER=" + vmId + ")");
            System.out.println("Results==========" + results);
            
            if (results.next()) {
                id = results.getInt(results.findColumn("ID"));
                System.out.println("***************ID : " + id + "---");
            }
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            String error = sqlExcept.toString();
            System.out.println("***************" + error + "---");
        }
        
        return id;
    }
    
    public int GetTempCusIdByName(String name) {
        int id= 0;
        
        try {
            // creates a SQL Statement object in order to execute the SQL select command
            stmt = conn.createStatement();
            // the SQL select command will provide a ResultSet containing the query results
            ResultSet results;
            // the SQL select command will provide a ResultSet containing the query results
            results = stmt.executeQuery("SELECT  *  FROM "+TemplatesTable+" WHERE(" + TemplatesTable + ".NAME='" + name + "')");
            System.out.println("Results==========" + results);
            
            if (results.next()) {
                id = results.getInt(results.findColumn("ID"));
                System.out.println("***************ID : " + id + "---");
            }
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            String error = sqlExcept.toString();
            System.out.println("***************" + error + "---");
        }
        
        return id;
    }
    
    public int GetTempDefIdByOSTemp(String ostemp) {
        int id= 0;
        
        try {
            // creates a SQL Statement object in order to execute the SQL select command
            stmt = conn.createStatement();
            // the SQL select command will provide a ResultSet containing the query results
            ResultSet results;
            // the SQL select command will provide a ResultSet containing the query results
            results = stmt.executeQuery("SELECT  *  FROM "+TempDefaultTable+" WHERE(" + TempDefaultTable + ".OSTEMPLATE='" + ostemp + "')");
            System.out.println("Results==========" + results);
            
            if (results.next()) {
                id = results.getInt(results.findColumn("ID"));
                System.out.println("***************ID : " + id + "---");
            }
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            String error = sqlExcept.toString();
            System.out.println("***************" + error + "---");
        }
        
        return id;
    }
    
    public ArrayList<TemplateVM> GetTemplatesVM() {
        ArrayList<TemplateVM> templates = new ArrayList<TemplateVM>();
        ArrayList<TemplateVM> templatesDefault = new ArrayList<TemplateVM>();
        ArrayList<TemplateVM> templatesCustom = new ArrayList<TemplateVM>();
        int iTempNameDef=0;
        int iVmTempDef=0;
        int iTempNameCus=0;
        int iVmTempCus=0;
        
        try {
            // creates a SQL Statement object in order to execute the SQL select command
            stmt = conn.createStatement();
            // the SQL select command will provide a ResultSet containing the query results
            ResultSet results;
            /**************Select all the default templates*******************/
            results = stmt.executeQuery("SELECT  *  FROM "+TempDefaultTable);
            System.out.println("Results==========" + results);
            
            while(results.next()) {
                
                TemplateVM tempVm = new TemplateVM(results.getString(results.findColumn("OSTEMPLATE")),results.getInt(results.findColumn("ID")));
                templatesDefault.add(tempVm);
                System.out.println("***************template "+(iTempNameDef+1)+": "+templatesDefault.get(iTempNameDef).getTemplateName()+ "id : "+templatesDefault.get(iTempNameDef).getIdTemplate());
                iTempNameDef++;
            }
            /************select all the vms of the default templates*************/
            for (TemplateVM temp : templatesDefault ) {
                results = stmt.executeQuery("SELECT * FROM "+ContainersTable+" WHERE "+ContainersTable+".ID IN (SELECT "+ContTempTable+".CONTAINER FROM "+ContTempTable+" WHERE "+ContTempTable+".TEMPLATEDEFAULT ="+temp.getIdTemplate()+")");
                while(results.next()) {
                    
                    String vmName = results.getString(results.findColumn("FINALHOSTNAME"));
                    System.out.println("*************** Vm NAme: "+vmName);
                    temp.getVms().add(vmName);
                    System.out.println("*************** "+iVmTempDef+" : "+temp.getTemplateName()+ "Vm : "+vmName);
                    iVmTempDef++;
                }
            }
            
            /**************Select all the custom templates*******************/
            results = stmt.executeQuery("SELECT  *  FROM "+TemplatesTable);
            System.out.println("Results==========" + results);
            
            while(results.next()) {
                
                TemplateVM tempVm = new TemplateVM(results.getString(results.findColumn("NAME")),results.getInt(results.findColumn("ID")));
                templatesCustom.add(tempVm);
                System.out.println("***************template "+(iTempNameCus+1)+": "+templatesCustom.get(iTempNameCus).getTemplateName()+ "id : "+templatesCustom.get(iTempNameCus).getIdTemplate());
                iTempNameCus++;
            }
            /************select all the vms of the custom templates*************/
            for (TemplateVM temp : templatesCustom ) {
                results = stmt.executeQuery("SELECT * FROM "+ContainersTable+" WHERE "+ContainersTable+".ID IN (SELECT "+ContTempTable+".CONTAINER FROM "+ContTempTable+" WHERE "+ContTempTable+".TEMPLATE ="+temp.getIdTemplate()+")");
                while(results.next()) {
                    
                    String vmName = results.getString(results.findColumn("FINALHOSTNAME"));
                    temp.getVms().add(vmName);
                    System.out.println("*************** "+iVmTempCus+" : "+temp.getTemplateName()+ "Vm : "+vmName);
                    iVmTempCus++;
                }
            }
            
            /**********************Merge default and custom templates ******************/
            for (TemplateVM temp : templatesDefault) {
                templates.add(temp);
            }
            for (TemplateVM temp : templatesCustom) {
                templates.add(temp);
            }
            
            
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            String error = sqlExcept.toString();
            System.out.println("***************" + error + "---");
        }
        
        return templates;
    }
    
    public ArrayList<String> GetAllFinalHostnames() {
        ArrayList<String> finalHostnames = new ArrayList<String>();
        int i=0;
        
        try {
            // creates a SQL Statement object in order to execute the SQL select command
            stmt = conn.createStatement();
            // the SQL select command will provide a ResultSet containing the query results
            ResultSet results;
            // the SQL select command will provide a ResultSet containing the query results
            results = stmt.executeQuery("SELECT  *  FROM "+ContainersTable);
            System.out.println("Results==========" + results);
            
            while(results.next()) {
                
                finalHostnames.add(results.getString(results.findColumn("FINALHOSTNAME")));
                System.out.println("***************finalHostname "+(i+1)+": "+finalHostnames.get(i));
                i++;
            }
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            String error = sqlExcept.toString();
            System.out.println("***************" + error + "---");
        }
        
        return finalHostnames;
    }
    
    public int GetContIdByHostname(String hostname) {
        int id= 0;
        
        try {
            // creates a SQL Statement object in order to execute the SQL select command
            stmt = conn.createStatement();
            // the SQL select command will provide a ResultSet containing the query results
            ResultSet results;
            // the SQL select command will provide a ResultSet containing the query results
            results = stmt.executeQuery("SELECT  *  FROM "+ContainersTable+" WHERE(" + ContainersTable + ".FINALHOSTNAME='" + hostname + "')");
            System.out.println("Results==========" + results);
            
            if (results.next()) {
                id = results.getInt(results.findColumn("ID"));
                System.out.println("***************ID : " + id + "---");
            }
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            String error = sqlExcept.toString();
            System.out.println("***************" + error + "---");
        }
        
        return id;
    }
    
    public int GetLastContTempVersion(int container) {
        int version= 0;
        
        try {
            // creates a SQL Statement object in order to execute the SQL select command
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            // the SQL select command will provide a ResultSet containing the query results
            ResultSet results;
            // the SQL select command will provide a ResultSet containing the query results
            results = stmt.executeQuery("SELECT  *  FROM "+TemplatesTable+" WHERE(" + TemplatesTable + ".REFCONTAINER=" + container + ")");
            System.out.println("Results==========" + results);
            
            if (results.last()) {
                version = results.getInt(results.findColumn("VERSION"));
                System.out.println("***************Last Remote POrt" + version + "---");
                version ++;
            }
            else
            {
                version = 1;
            }
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            String error = sqlExcept.toString();
            System.out.println("***************" + error + "---");
        }
        
        return version;
    }
    
    public int GetVmIdByHostname(String hostname) {
        int id= 0;
        
        try {
            // creates a SQL Statement object in order to execute the SQL select command
            stmt = conn.createStatement();
            // the SQL select command will provide a ResultSet containing the query results
            ResultSet results;
            // the SQL select command will provide a ResultSet containing the query results
            results = stmt.executeQuery("SELECT  *  FROM "+ContainersTable+" WHERE(" + ContainersTable + ".FINALHOSTNAME='" + hostname + "')");
            System.out.println("Results==========" + results);
            
            if (results.next()) {
                id = results.getInt(results.findColumn("IDCONTAINER"));
                System.out.println("***************ID : " + id + "---");
            }
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            String error = sqlExcept.toString();
            System.out.println("***************" + error + "---");
        }
        
        return id;
    }
    
    public synchronized String AddCustomTemplate(int refContainer,int version,String name) {//a container is created with template or templedefault, 0 == null
        
        String insert = "Insertion";
        
        try {
            // creates a SQL Statement object in order to execute the SQL insert command
            stmt = conn.createStatement();
            stmt.execute("insert into " + TemplatesTable + " (NAME,REFCONTAINER,VERSION) values ('"+ name + "',"+refContainer+","+ version + ")");
            
            stmt.close();
        } catch (SQLException sqlExcept) {
            insert = "Template : " + sqlExcept.toString();
        }
        System.out.println(insert);
        return insert;
    }
    
}
