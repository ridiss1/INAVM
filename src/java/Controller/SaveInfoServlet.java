/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Database;
import Model.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author olga
 */
//@WebServlet(name = "SaveInfoServlet", urlPatterns = {"/SaveInfoServlet"})
public class SaveInfoServlet extends HttpServlet {

    public static final String VUE = "/index.jsp";
    public static final String FIRST_NAME = "fname";
    public static final String LAST_NAME = "lname";
    public static final String EMAIL = "email";
    public static final String PASS = "pswd";
    public static final String CONF_PASS = "confpswd";
    public static final String GROUP_NAME = "gname";
    public static final String GROUP_PASS = "gpswd";
    public static final String STATUS = "status";
    public static final String DEPARTMENT = "dpt";
    
    public static final String ATT_ERRORS  = "errors";
    public static final String ATT_RESULT = "result";

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
            out.println("<title>Servlet SaveInfoServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SaveInfoServlet at " + request.getContextPath() + "</h1>");
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
        
        System.out.println("Servlet SAveINfo");

        HttpSession session = request.getSession();
        Users user = new Users();

        System.out.println("Form Create User");
        
        Boolean succes = user.createUSER(request, response);
        /* Transmission de la paire d'objets request/response à notre JSP */
        if(succes)
        {
            this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        }
        
        
    }



/**
 * Returns a short description of the servlet.
 *
 * @return a String containing servlet description
 */
@Override
        public String getServletInfo() {
        return "Servlet to retrieve all the info of a new user";
    }// </editor-fold>

}
