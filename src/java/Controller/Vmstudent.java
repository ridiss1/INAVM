/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.AuthentificationServlet.ATT_SESSION_USER;
import Model.Vms;
import IAAS.Iaas;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.Database;
import Model.Users;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpSession;
import net.elbandi.pve2api.data.Container;
import org.json.JSONException;

/**
 *
 * @author Ridiss
 */
@WebServlet(name = "Vmstudent", urlPatterns = {"/Vmstudent"})
public class Vmstudent extends HttpServlet {
    private static final String VUE = "/vm.jsp";
     private static final String CLASS_START = "start";
     private static final String CLASS_STOP = "stop";
     private static final String STATUS_START = "statusStart";
     private static final String STATUS_STOP = "statusStop";
     private static final String CLASS_CONSOLE = "console";
     //private List<Container> listContainer = null;
     private static final String LISTE_CONTAINER = "ListContainer";

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
        Vms vm =new Vms();
        Database DB= new Database();
        List<Container> listContainer = new ArrayList ();
      
        try {
              Iaas ias;
            HttpSession session = request.getSession();
            String user= session.getAttribute("login").toString();
            int id= Integer.parseInt(session.getAttribute("Numer").toString());
            String requeteStart= request.getParameter("start");
            String requeteStop= request.getParameter("stop");
            request.setAttribute("requete_start", false);
            request.setAttribute("requete_stop", false);
            ias = new Iaas();
            
            if (requeteStart != null) {
                int vmid= Integer.parseInt(request.getParameter("start"));
                Boolean result = ias.startContainer(vmid);
                System.out.println("actinStart *************** result ==" + result);
                session.setAttribute(CLASS_START, ias.getContainer(vmid).getStatus());
                request.setAttribute(STATUS_START, ias.getContainer(vmid).getStatus());
                request.setAttribute("requete_start", true);
                
            }
            
            if (requeteStop != null) {
                int vmid= Integer.parseInt(request.getParameter("stop"));
                Boolean result = ias.stopContainer(vmid);
                session.setAttribute(CLASS_STOP, ias.getContainer(vmid).getStatus());
                request.setAttribute(STATUS_STOP, ias.getContainer(vmid).getStatus());
                request.setAttribute("requete_stop", true);
                
            }
            
            Vms vmt = DB.GetVMsbygroup(user,id);
            
            
                Container c = ias.getContainer(vmt.getVMid());
                System.out.println("Container " + c.toString());
                c.setVmid(String.valueOf(vmt.getVMid()));
                c.setConsole(ias.getConsole(vmt.getVMid()));
                System.out.println("le cont"+listContainer.toString());
                listContainer.add(c);
            

                session.setAttribute(LISTE_CONTAINER, listContainer);
                //statique à afficher
               // vm.writeFile(listContainer);
               
                
            } catch (JSONException ex) {
            Logger.getLogger(Vmstudent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LoginException ex) {
            Logger.getLogger(Vmstudent.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            
            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
       

    
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
        processRequest(request, response);
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
