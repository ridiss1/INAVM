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
        
        //Create container
        String vmid = "106";
        result = iaas.creerContainer(vmid);
        if(result)
        {
            System.out.println("Container created !!");
        }
        
        iaas.startContainer(Integer.parseInt(vmid));
        //iaas.stopContainer(Integer.parseInt(vmid));

//        String user = "root";
//        String ipRtr = "149.202.70.57";
//        int port = 22;
//        
//        String password = "xxyyUUIIOOBB12";
//        
//        String CT_ID="103";
//        String TEMPLATE_PATH="/var/lib/vz/template/cache/debian-7.0-standard_7.0-2_i386.tar.gz";
//        String IP_ADDRESS="192.168.1.1";
//        String HOSTNAME="Test";
//        String MEMORY_SIZE="2048";
//        String SWAP_SIZE="2048";
//        String DISK_SIZE="40";
//        String CPU_COUNT="2";
//        
// //       String myCommand = "test.bat";
//        String myCommand = "pvectl create "+CT_ID+" "+ TEMPLATE_PATH+" "+" -ip_address="+IP_ADDRESS+" -onboot 1 -hostname "+HOSTNAME+" -cpus "+CPU_COUNT+" -memory="+MEMORY_SIZE+" -swap="+SWAP_SIZE+" -disk="+DISK_SIZE;
////        try {
////            //Execute the command
////            Runtime.getRuntime().exec(myCommand);
////        } catch (IOException ex) {
////            Logger.getLogger(INAVM_CLOUD.class.getName()).log(Level.SEVERE, null, ex);
////        }
//        JSch jsch=new JSch();
//	Session session;
//        try {
//            session = jsch.getSession(user, ipRtr, port);
//            session.setPassword(password);
//            Properties config = new Properties();
//            config.put("StrictHostKeyChecking", "no");
//            session.setConfig(config);
//            session.connect();
//
//            ChannelExec channel=(ChannelExec) session.openChannel("exec");
//            BufferedReader in=new BufferedReader(new InputStreamReader(channel.getInputStream()));
//            channel.setCommand(myCommand);
//            channel.connect();
//            String msg=null;
//            while((msg=in.readLine())!=null){
//                System.out.println(msg);
//            }
//
//            channel.disconnect();
//            session.disconnect();
//        } catch (JSchException ex) {
//            Logger.getLogger(INAVM_CLOUD.class.getName()).log(Level.SEVERE, null, ex);
//        }   
//        
    }
    
}
