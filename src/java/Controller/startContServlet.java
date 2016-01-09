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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;

/**
 *
 * @author Jean
 */
public class startContServlet extends HttpServlet {

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
    
     /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Iaas ias;
        try {
            ias = new Iaas();
            Boolean result = ias.startContainer(Integer.parseInt(request.getParameter("VMid")));
            System.out.println("actinStart *************** result ==" + result);
            
            if(result)
            {
                this.getServletContext().getRequestDispatcher("/succesStart.jsp").forward(request, response);
            }
            else
            {
                this.getServletContext().getRequestDispatcher("/errorStart.jsp").forward(request, response);
            }
        } catch (JSONException ex) {
            Logger.getLogger(startContServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LoginException ex) {
            Logger.getLogger(startContServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
