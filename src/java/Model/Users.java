/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Controller.SaveInfoServlet.ATT_ERRORS;
import static Controller.SaveInfoServlet.ATT_RESULT;
import static Controller.SaveInfoServlet.CONF_PASS;
import static Controller.SaveInfoServlet.DEPARTMENT;
import static Controller.SaveInfoServlet.EMAIL;
import static Controller.SaveInfoServlet.FIRST_NAME;
import static Controller.SaveInfoServlet.GROUP_NAME;
import static Controller.SaveInfoServlet.GROUP_PASS;
import static Controller.SaveInfoServlet.LAST_NAME;
import static Controller.SaveInfoServlet.PASS;
import static Controller.SaveInfoServlet.STATUS;
import static Controller.SaveInfoServlet.VUE;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author Samanthol
 */
public class Users {
    private Database data = new Database();
//    private int IdDBSess = -1;
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

    
    public Boolean createUSER(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
    String fname = request.getParameter(FIRST_NAME);
        String lname = request.getParameter(LAST_NAME);
        String email = request.getParameter(EMAIL);
        String pswd = request.getParameter(PASS);
        String confpswd = request.getParameter(CONF_PASS);
        String gname = request.getParameter(GROUP_NAME);
        String gpswd = request.getParameter(GROUP_PASS);
        String status = request.getParameter(STATUS);
        String dpt = request.getParameter(DEPARTMENT);
        String result;
        Map <String, String> errors = new HashMap<String, String>();
        Boolean created = false;
        
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
            System.out.println(" Passé par ici");
            result = data.addUser(fname, lname, email, pswd, gname, gpswd, status);
            System.out.println("Sorti par là ");
        } else {
            result = "Registration failed.";
        }
        
        /* Stockage du résultat et des messages d'erreur dans l'objet request */
        request.setAttribute( ATT_ERRORS, errors );
        request.setAttribute( ATT_RESULT, result );
        
        if("Data".equals(result))
        {
            created = true;           
        }
        

        return created;
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

    
}
