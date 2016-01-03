/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import IAAS.Iaas;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.elbandi.pve2api.data.Container;
import org.json.JSONException;

/**
 *
 * @author Ridiss
 */
@WebServlet(name = "VmProfUpdateServlet", urlPatterns = {"/VmProfUpdateServlet"})
public class VmProfUpdateServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   private static final String VUE_VM_PROF = "vmProf.jsp";
    private static final String VUE_VM_PROF_CREATION = "/vmProfCreation.jsp";
    private static final String VUE_VM_PROF_MODIFY = "/vmProfModify.jsp";
    private static final String ATTR_LISTE_TEMPLATE = "ListeTemplate";
    private static final String ATTR_LISTE_GROUPE = "ListeGroupe";
    private static final String ATTR_LISTE_CONTAINER = "ListeContainer";
    private static final String ATTR_INFO_CONTAINER = "InfoContainer";
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       try {
           Iaas ias = new Iaas();
           HttpSession session = request.getSession();
           
           request.setAttribute("RequestUpdate", false);
           Container c = ias.getContainer(Integer.parseInt(request.getParameter("actionChange")));
           c.setVmid(request.getParameter("actionChange"));
           //System.out.println("id====="+ request.getParameter("VMid"));
           
           
           long disk=Long.parseLong(c.getDisk())/(1024*1024*1024);
           c.setDisk(Long.toString(disk));
           long ram=Long.parseLong(c.getMemory())/(1024*1024);
           c.setMemory(Long.toString(ram));
           //session.setAttribute(ATTR_INFO_CONTAINER, c);
           request.setAttribute(ATTR_INFO_CONTAINER, c);
           System.out.println("test=" + c.toString()); 
           this.getServletContext().getRequestDispatcher(VUE_VM_PROF_MODIFY).forward(request, response);
       } catch (JSONException ex) {
           Logger.getLogger(VmProfUpdateServlet.class.getName()).log(Level.SEVERE, null, ex);
       } catch (LoginException ex) {
           Logger.getLogger(VmProfUpdateServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
}