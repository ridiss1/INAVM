/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.AuthentificationServlet.ATT_SESSION_EMAIL;
import Model.Database;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jean
 */
public class CreateTemplate extends HttpServlet {
    
    private Database data = new Database();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
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
            out.println("<title>Servlet CreateTemplate</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateTemplate at " + request.getContextPath() + "</h1>");
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
        ArrayList<String> finalHostnames = new ArrayList<String>(); 
        /* Récupération de la session depuis la requête */
        HttpSession webSession = request.getSession();
        String user = (String) webSession.getAttribute(ATT_SESSION_EMAIL);
        System.out.println("***************USER : "+user);
        finalHostnames = data.GetAllFinalHostnames(user);
        request.setAttribute("containers", finalHostnames);
        request.getRequestDispatcher("createTemplate.jsp").forward(request, response);
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
        System.out.println("Form Create Container");
        String contHostname = request.getParameter("contFinalHostname");
        int vmId = data.GetVmIdByHostname(contHostname);
        int idCont = data.GetContIdByHostname(contHostname);
        int version = data.GetLastContTempVersion(idCont);
        boolean created = false;
        
        /********Create the template with ssh command******/
        String myCommand = "vzctl set "+vmId+" --ipdel all --save;vzctl stop "+vmId+";cd /var/lib/vz/private/"+vmId+";tar -czvf /var/lib/vz/template/cache/"+contHostname+"_"+version+".tar.gz .";
        System.out.println("Commande Template : "+myCommand);
        JSch jsch=new JSch();
        Session session;
        try {
            session = jsch.getSession("root", "149.202.70.57", 22);
            session.setPassword("XEJ4UyPtmY5N"); //Set the true Password
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
            System.out.println("** Template Created !! **");
            created = true;
        } 
        catch (JSchException ex) {
            Logger.getLogger(FormCreatContenaire.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("**Error création Template !!**");
        }
        
        if(created)
        {
            String name = contHostname+"_"+version+".tar.gz";
            /* Récupération de la session depuis la requête */
           HttpSession webSession = request.getSession();
           String user = (String) webSession.getAttribute(ATT_SESSION_EMAIL);
           System.out.println("***************USER : "+user);
           int idUser = data.GetIdByEmail(user);
            String insert = data.AddCustomTemplate(idCont, version, name,idUser);
            System.out.println(insert);
            RequestDispatcher rd = request.getRequestDispatcher("ListTemplates");
            rd.forward(request,response);
        }
        else
        {
            request.getRequestDispatcher("errorTemplate.jsp").forward(request, response);
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
