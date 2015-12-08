/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inavm_cloud;

import IAAS.Iaas;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import static java.lang.System.in;
import javax.security.auth.login.LoginException;
import org.json.JSONException;

/**
 *
 * @author Jean
 */
public class INAVM_CLOUD {

    /**
     * @param args the command line arguments
     * XEJ4UyPtmY5N
     */
    public static void main(String[] args) throws IOException, JSONException, LoginException {
        // TODO code application logic here
        boolean result;
        
        Iaas iaas = new Iaas();
        
		String stat = null;	//For the stats
		
        //Create container
//        String vmid = "106";
//        result = iaas.creerContainer(vmid);
//        if(result)
//        {
//            System.out.println("Container created !!");
//        }
//        
//        iaas.startContainer(Integer.parseInt(vmid));
//		stat = iaas.getStatistics("ns3021937", Integer.parseInt(vmid), "December");
        //iaas.stopContainer(Integer.parseInt(vmid));

                iaas.getStatistics("ns3021937", 104, "hour");
    }
    
}
