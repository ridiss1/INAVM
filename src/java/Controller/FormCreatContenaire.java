/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import IAAS.Iaas;
import Model.Database;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.login.LoginException;
import org.json.JSONException;

/**
 *
 * @author Ridiss
 */
public class FormCreatContenaire extends HttpServlet {
    
    Iaas iaas;
    Boolean resul;
    private Database data = new Database();

    public FormCreatContenaire() throws JSONException {
        try {
            this.iaas = new Iaas();
        } catch (LoginException ex) {
            Logger.getLogger(FormCreatContenaire.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-zspecific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FormCreatContenaire</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FormCreatContenaire at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       
       System.out.println("pass1");
       String adress = data.GetAvailableIpAdress();
       int vmid = data.GetLastContainerId();
       int remotePort = data.GetLastRemotePort();
       String CPU_COUNT = request.getParameter("cpus");
       String TEMPLATE = request.getParameter("template");
       String DISK_SIZE = request.getParameter("disk");
       String MEMORY_SIZE = request.getParameter("ram");
       String HOSTNAME = request.getParameter("hostname");
       String PASSWORD_CONTAINER = request.getParameter("passwordDefault");
       
       System.out.println("*************************VMID : "+vmid);
       
       resul= iaas.creerContainer(Integer.toString(vmid),adress,CPU_COUNT,TEMPLATE,DISK_SIZE,MEMORY_SIZE,HOSTNAME,PASSWORD_CONTAINER);
       if(resul)
       {
           data.UpdateIpadress(adress, true);
           data.AddContainer(vmid, adress,remotePort);
           
           /********Add the iptables NAT rule******/
           int localPort = 22; //Port à lancer sur le VNC ??
           String myCommand = "iptables -t nat -A PREROUTING -i vmbr0 -p tcp --dport "+ remotePort +" -j DNAT --to "+adress+":"+localPort;
           JSch jsch=new JSch();
           Session session;
           try {
                session = jsch.getSession("root", "149.202.70.57", 22);
                session.setPassword("****************"); //Set the true Password
                Properties config = new Properties();
                config.put("StrictHostKeyChecking", "no");
                session.setConfig(config);
                session.connect();

                ChannelExec channel=(ChannelExec) session.openChannel("exec");
                BufferedReader in=new BufferedReader(new InputStreamReader(channel.getInputStream()));
                channel.setCommand(myCommand);
                channel.connect();
                String msg=null;
                while((msg=in.readLine())!=null){
                    System.out.println(msg);
                }

                channel.disconnect();
                session.disconnect();
                System.out.println("**NAT well configured !!**");
           } 
           catch (JSchException ex) {
            Logger.getLogger(FormCreatContenaire.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("**Error configuration NAT !!**");
           }
           
           response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet FormCreatContenaire</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet FormCreatContenaire at " + request.getContextPath() + "</h1>");
                out.println("<h1>Container Created !!!</h1>");
                out.println("</body>");
                out.println("</html>");
            }
       }
       else{
          response.setContentType("text/html;charset=UTF-8");
          try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FormCreatContenaire</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FormCreatContenaire at " + request.getContextPath() + "</h1>");
            out.println("<h1>ERROR Container not created !!!</h1>");
            out.println("</body>");
            out.println("</html>");
          }      
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
