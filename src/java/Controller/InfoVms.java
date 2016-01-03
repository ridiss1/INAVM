/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Controller;

import Model.Vms;
import IAAS.Iaas;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.Database;
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
@WebServlet(name = "InfoVms", urlPatterns = {"/InfoVms"})
public class InfoVms extends HttpServlet {
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    
    private static final String ATTR_LISTE_CONTAINER = "ListeContainer";
    private static final String VUE_VM_PROF = "/vmProf.jsp";
    private static final String VUE_VM_PROF_CREATION = "/vmProfCreation.jsp";
    private static final String VUE_VM_PROF_MODIFY = "/vmProfModify.jsp";
    private static final String ATTR_LISTE_TEMPLATE = "ListeTemplate";
    private static final String ATTR_LISTE_GROUPE = "ListeGroupe";
    private static final String ATTR_INFO_CONTAINER = "InfoContainer";

    /**
     * **********************Listes des attribusts *************
     */
    private static final String ATTR_TEMPLATE = "template";
    private static final String ATTR_GROUPE = "groupe";
    private static final String ATTR_HOSTNAME = "hostname";
    private static final String ATTR_RAM = "ram";
    private static final String ATTR_CPU = "cpus";
    private static final String ATTR_DISK = "disk";
    private static final String ATTR_PASSWORD = "passwordDefault";
    
    
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
                Database DB= new Database();
        try {
            Iaas iass= new Iaas();
            List<Container> listContainer = new ArrayList ();
            ArrayList<Vms> Vms= DB.GetVMs();;
            
//
            for(Vms vmt : Vms){
                Container c = iass.getContainer(vmt.getVMid());
                System.out.println("Container " + c.toString());
                c.setVmid(String.valueOf(vmt.getVMid()));
                listContainer.add(c);
            }
            request.setAttribute(ATTR_LISTE_CONTAINER, listContainer);
            
        } catch (JSONException ex) {
            Logger.getLogger(InfoVms.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LoginException ex) {
            Logger.getLogger(InfoVms.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.getServletContext().getRequestDispatcher(VUE_VM_PROF).forward(request, response);
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
        try {
            Iaas ias = new Iaas();
            HttpSession session = request.getSession();
           // User prof = (User) session.getAttribute("sessionUser");
            
            
            //Mettre à jour les paramètres du container
            if (request.getParameter("actionUpdate") != null) {
                request.setAttribute("RequestUpdate", true);
                boolean resultat = false;
                System.out.println("RAM=" + request.getParameter("ram"));
                System.out.println("CPUS=" + request.getParameter("cpus"));
                System.out.println("DISK=" + request.getParameter("disk"));
                System.out.println("DISK=" + request.getParameter("VMid"));
                Container c = new Container(request.getParameter("VMid"), request.getParameter("cpus"), request.getParameter("disk"), request.getParameter("ram"));
                ias.UpdateContainer(c);
                System.out.println("UPDATE OK pour VM " + request.getParameter("VMid"));
                resultat= true;
                
                //session.setAttribute("demandUpdate", request.getParameter("actionUpdate"));
                request.setAttribute("InfoUpdate", resultat);
                c = ias.getContainer(Integer.parseInt(request.getParameter("VMid")));
                c.setVmid(request.getParameter("VMid"));
                long disk=Long.parseLong(c.getDisk())/(1024*1024*1024);
                c.setDisk(Long.toString(disk));
                long ram=Long.parseLong(c.getMemory())/(1024*1024);
                c.setMemory(Long.toString(ram));
                request.setAttribute(ATTR_INFO_CONTAINER, c);
                
                this.getServletContext().getRequestDispatcher(VUE_VM_PROF_MODIFY).forward(request, response);
            }
            //Supprimer un container
            
            if (request.getParameter("actionDelete") != null) {
                request.setAttribute("RequestDelete", true);
                String result = ias.deleteContainer(Integer.parseInt(request.getParameter("VMid")));
                System.out.println("result==" + result);
//     Supression de la base de donnée à faire
               
                if (result.equals("OK")) {
                    
                    System.out.println("DELETE OK pour VM " + request.getParameter("VMid"));
                } else {
                    
                    
                    System.out.println("Problem DELETE pour VM " + request.getParameter("VMid"));
                }
                
                request.setAttribute("InfoDelete", result);
                request.setAttribute("InfoVM", request.getParameter("VMid"));
                this.getServletContext().getRequestDispatcher(VUE_VM_PROF).forward(request, response);
            }
        } catch (JSONException ex) {
            Logger.getLogger(InfoVms.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LoginException ex) {
            Logger.getLogger(InfoVms.class.getName()).log(Level.SEVERE, null, ex);
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
