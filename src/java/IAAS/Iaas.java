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
    private String password = "XEJ4UyPtmY5N";
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
        String finalHostname = HOSTNAME+""+vmid;
        
        Container containerT = new Container(TEMPLATE_PATH, vmid, CPU_COUNT, DISK_SIZE, HOSTNAME, MEMORY_SIZE, PASSWORD_CONTAINER);
        containerT.setIp_address(IP_ADDRESS);
        
        System.out.println("\n AdresseIP: "+IP_ADDRESS+"\n"+"Template: "+TEMPLATE_PATH+"\n"+"Vmid: "+vmid+"\n"+"Cpu: "+CPU_COUNT+"\n"+"Disk: "+DISK_SIZE+"\n"+"Hostname: "+finalHostname+"\n"+"Memoire: "+MEMORY_SIZE+"\n"+"password: "+PASSWORD_CONTAINER);
        
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
    public String getStatistics(String node,int vmid,String param) throws JSONException{
         String result=null;
        try {
            result = pve.getStatistics(node, vmid, param);
            System.out.println(result);            
        } catch (LoginException ex) {
            Logger.getLogger(Iaas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
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
    
}
