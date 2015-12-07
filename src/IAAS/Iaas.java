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
    private String password="pppppppp";
    
    public Iaas() throws JSONException, LoginException{
        
        pve = new Pve2Api(password, user, address, password);
        
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
    public boolean creerContainer ( ) {
        return false;
       
        
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
    public String startContainer (int vmid){
        
        String result=null;
        
        return result;
    }
    
    /**
     * 
     * @param vmid = id du container 
     * @return 
     */
    public String stopContainer (int vmid){
       
       String result=null;
       
       return result;
    }
    
    
    
    /**
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
