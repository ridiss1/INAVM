/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IAAS;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.login.LoginException;
import net.elbandi.pve2api.Pve2Api;
import net.elbandi.pve2api.data.Container;
import net.elbandi.pve2api.data.VncData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Ridiss
 */
public class Iaas {
    
    private Pve2Api pve;
    /**
     * SSH Attributes
     */
   
   
    private int port = 22;
    
    /**
    * Attrites for ssh connection
    */
    
     //Server dist distant
    final  private String address="149.202.70.57"; 
    private String user="root";
    //XEJ4UyPtmY5N
    private String password = "**********";
    private String hostname="root";
    private String pve_realm = "pam";
    
    public Iaas() throws JSONException, LoginException{
        
        pve = new Pve2Api(address, user, pve_realm, password);
        
        try {
            pve.login();
        } catch (IOException ex) {
            Logger.getLogger(Iaas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    /**
     * 
     * @param container : objet container 
     * @param nbrectainer : utilisé pour numéroter la machine de façcon unique
     * via son IP 
     */
    public boolean creerContainer ( String vmid, String IP_ADDRESS,String CPU_COUNT, String TEMPLATE,String DISK_SIZE,String MEMORY_SIZE,String HOSTNAME,String PASSWORD_CONTAINER ) {
        
        boolean result = false; 
        String node = "ns3021937";
        //String TEMPLATE_PATH="/var/lib/vz/template/cache/debian-7.0-standard_7.0-2_i386.tar.gz";
        String TEMPLATE_PATH="/var/lib/vz/template/cache/"+TEMPLATE;
        
        Container containerT = new Container(TEMPLATE_PATH, vmid, CPU_COUNT, DISK_SIZE, HOSTNAME, MEMORY_SIZE, PASSWORD_CONTAINER);
        containerT.setIp_address(IP_ADDRESS);
        
        try {
            pve.createOpenvz(node, containerT);
            result = true;
        } catch (JSONException ex) {
            Logger.getLogger(Iaas.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        } catch (LoginException ex) {
            Logger.getLogger(Iaas.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        } catch (IOException ex) {
            Logger.getLogger(Iaas.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        }
    
        return result;
    }
    
    
    
    /**
     * 
     * @param vmid = id du container 
     * @return 
     */
    public String deleteContainer (int vmid){
        
        String result=null;
        
        return result;
    }
    
    /**
     * Mettre à jour les caractériqtiques d'un container en l'occurence : 
     * cpus , diskspace , memory(RAM)
     * @param lecontainer 
     */
    public void UpdateContainer(){
        
       
    }
    
    /**
     * 
     * @param vmid=id du container 
     * @return 
     */
    public void startContainer (int vmid){
        
        String result=null;
        try {
            pve.startOpenvz("ns3021937", vmid);
           
        } catch (LoginException ex) {
            Logger.getLogger(Iaas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Iaas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Iaas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * 
     * @param vmid = id du container 
     * @return 
     */
    public void stopContainer (int vmid){
       
        try {
            pve.stopOpenvz("ns3021937", vmid);
        } catch (LoginException ex) {
            Logger.getLogger(Iaas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Iaas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Iaas.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
    }
    
    
    
       /**
     * This method should allow usto get statistics about VM usage
     * 
     * @param node = 
     * @param vmid = id du container 
     * @param param = heure,jr
     * @return un tableau de json contenant les caractéristiques par 
     * heure de la machine depuis son allumage ( cas "hour") : RRD data
     */
   public String getStatistics(String node, int vmid, String param) throws JSONException {
        String result = null;
        String result1, result2, result3, result4;
        
        try {
            /*
            *Returns the statistics on one single line with the square brackets
            *At the beginning and at the end
             */
            result = pve.getStatistics(node, vmid, param);
            /*
            The following result is without the "[]". 
            So our json results now are surrounded by "{}"
            */
            result1 = result.substring(1, result.length()-1);
            result2 = result.replaceAll("\\[", "{ \"result2\":["); 
            result3 = result2.concat("}");
//            result4 = result3.replace("{\"disk\"", "\\\\n {\"disk\"");
            
//            System.out.println("Result: "+result);
//            System.out.println("Result1: "+result1);  
            System.out.println("Result2: "+result2);
//            System.out.println("Result4: "+result4);
//            /*
//             To parse the previous results in an array
//             */
            JSONObject jObj = new JSONObject(result3);
//            System.out.println("Objet Json : " +jObj);
            JSONArray jArr = jObj.getJSONArray("result2");
//            for (int i = 0; i < jArr.length(); i++) {
////                System.out.println("JSONArray : " +jArr.getInt(i));
//

            // Get the first array of elements
//            JSONArray values = jArr.getJSONArray(0);
//            System.out.println("Values: "+values);
////
            for (int i = 0; i < jArr.length() ; i++) {

                JSONObject item = jArr.getJSONObject(i);
                
                int disk = item.getInt("disk");
                int mem = item.getInt("mem");
                int maxdisk = item.getInt("maxdisk");
                int diskread = item.getInt("diskread");
                int cpu = item.getInt("cpu");
                int time = item.getInt("time");
                int netout = item.getInt("netout");
                int maxcpu = item.getInt("maxcpu");
                int maxmem = item.getInt("maxmem");
                int diskwrite = item.getInt("diskwrite");
                int netin = item.getInt("netin");

//                //The display of the array with the results obtained above
                System.out.println(disk + ", " + mem + ", " + maxdisk + ", " + diskread + ", " + cpu + ", " + time + ", " + netout + ","
                        + " " + maxcpu + ", " + maxmem + ", " + diskwrite + ", " + netin);
                

              System.out.println(result);
    }
        }catch (LoginException ex) {
            Logger.getLogger(Iaas.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex) {
            Logger.getLogger(Iaas.class.getName()).log(Level.SEVERE, null, ex);
        }
            return result;
        }
    
 
     
    
    
        /**
     *
     * @param containerID
     * @param userID : the ID of user in User table will be used as foreign key
     * @param comments : user's comments to describe the aim of the template
     * @throws JSchException
     * @throws IOException
     */
    public boolean createCustomerTemplate(int containerID, String comments) {

     
        return true;
    }
    
    
    /**
     * 
     * @param fileName
     * @return
     * @throws JSchException
     * @throws IOException 
     */
    public boolean deleteTemplate(String fileName) {
       
        return true;
    }

        /**
     * 
     * @param vmid = id du container 
     * @return ( Utilisé la methode container.toString pour afficher les résultats retournés )
     */
    public Container getContainer (int vmid){
        
            Container container = null;
            try {
                container =  pve.getOpenvzCT("ns3021937", vmid);
            } catch (JSONException | LoginException | IOException ex) {
            Logger.getLogger(Iaas.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        return container;
    }
    
    /**
     * @param vmid = id du container 
     * @return lien https vers la console du container  
     */
    public String getConsole(int vmid){
        
       // retourne url de la console 
       Container container =this.getContainer(vmid);
       String url ="https://"+pve.getPve_hostname()+":8006/?console=openvz&novnc=1&vmid="+vmid+"&vmname="+container.getHostname()+"&node=ns3021937";
       return url;
    }
}
