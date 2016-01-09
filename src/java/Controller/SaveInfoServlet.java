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

    public static final String VUE = "/web/registrationForm.jsp";
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
        processRequest(request, response);

        HttpSession session = request.getSession();
        Users user = new Users();
//        private Database data = new Database();
        String result;
        Map <String, String> errors = new HashMap<String, String>();

        System.out.println("Form Create User");
        /* Récupération des champs du formulaire. */
        String fname = request.getParameter(FIRST_NAME);
        String lname = request.getParameter(LAST_NAME);
        String email = request.getParameter(EMAIL);
        String pswd = request.getParameter(PASS);
        String confpswd = request.getParameter(CONF_PASS);
        String gname = request.getParameter(GROUP_NAME);
        String gpswd = request.getParameter(GROUP_PASS);
        String status = request.getParameter(STATUS);
        String dpt = request.getParameter(DEPARTMENT);
        user.createUSER(request, response);
        
        try {
            validationFName(fname);
        }catch(Exception e){
            errors.put(FIRST_NAME, e.getMessage());
        }
        try{
            validationLName(lname);
        }catch(Exception e){
            errors.put(LAST_NAME, e.getMessage());
        }
        try{
            validationEmail(email);
        }catch(Exception e){
            errors.put(EMAIL, e.getMessage());
        }
        try{
            validationPassword(pswd, confpswd);
        }catch(Exception e){
            errors.put(PASS, e.getMessage());
        }

        /* Initialisation du résultat global de la validation. */
        if ( errors.isEmpty() ) {
            result = "Registration successful";
        } else {
            result = "Registration failed.";
        }

        /* Stockage du résultat et des messages d'erreur dans l'objet request */
        request.setAttribute( ATT_ERRORS, errors );
        request.setAttribute( ATT_RESULT, result );

        /* Transmission de la paire d'objets request/response à notre JSP */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    private void validationFName(String fname) throws Exception {
        if ( fname != null && fname.trim().length() < 3 ) {
        throw new Exception( "The first name must have at least 3 caracteres." );
    }
    }

    private void validationLName(String lname) throws Exception {
        if ( lname != null && lname.trim().length() < 3 ) {
        throw new Exception( "The last name must have at least 3 caracteres." );
    }
    }
/**
 * Valide l'adresse mail saisie.
 */
    
    private void validationEmail(String email) throws Exception {
        if ( email != null && email.trim().length() != 0 ) {
        if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
            throw new Exception( "Please enter a valid email address" );
        }
    } else {
        throw new Exception( "Please enter a valid email address" );
    }
    }

    private void validationPassword(String pswd, String confpswd) throws Exception {
        if (pswd != null && pswd.trim().length() != 0 && confpswd != null && confpswd.trim().length() != 0) {
        if (!pswd.equals(confpswd)) {
            throw new Exception("Given passwords are different, please check they are they are the same");
        } else if (pswd.trim().length() < 3) {
            throw new Exception("The passwords must have at least 3 caracteres.");
        }
    } else {
        throw new Exception("Please enter and validate your password");
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
