/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inavm_cloud;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jean
 */
public class INAVM_CLOUD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        String TEMPLATE_PATH="/var/lib/vz/template/cache/debian-7.0-standard_7.0-2_i386.tar.gz";
        String IP_PREFIX="192.168.1.1";
        String HOSTNAME="Test";
        String MEMORY_SIZE="2048";
        String SWAP_SIZE="2048";
        String DISK_SIZE="40";
        String CPU_COUNT="2";
        
        String myCommand = "createCT";
        
        try {
            //Create the Container
            Runtime.getRuntime().exec(myCommand);
        } catch (IOException ex) {
            Logger.getLogger(INAVM_CLOUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
