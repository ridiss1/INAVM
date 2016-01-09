/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Controller.SaveInfoServlet.EMAIL;
import static Controller.SaveInfoServlet.FIRST_NAME;
import static Controller.SaveInfoServlet.LAST_NAME;
import static Controller.SaveInfoServlet.PASS;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @olga
 */
public class RegistrationForm {
    private static final String FIRST_NAME = "fname";
    private static final String LAST_NAME = "lname";
    private static final String EMAIL = "email";
    private static final String PASS = "pswd";
    private static final String CONF_PASS = "confpswd";
    private static final String GROUP_NAME = "gname";
    private static final String GROUP_PASS = "gpswd";
    private static final String STATUS = "status";
    private static final String DEPARTMENT = "dpt";
    
    private String result;
    private Map<String, String> errors = new HashMap<String, String>();

    public Users registerUser( HttpServletRequest request ) {

    
    String fname = getValeurChamp( request, EMAIL ) ;
    String lname = getValeurChamp( request, EMAIL );
    String email= getValeurChamp( request, EMAIL );
    String pswd = getValeurChamp( request, PASS );
    String confpswd = getValeurChamp( request, CONF_PASS );
    String gname = getValeurChamp( request, GROUP_NAME );
    String gpswd = getValeurChamp( request, GROUP_PASS );
    String status = getValeurChamp( request, STATUS );
    String dpt = getValeurChamp( request, DEPARTMENT );

    Users user = new Users();

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

    return user;
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
    
    public String getResult() {
        return result;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    /*
 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
 */
private void setError( String champ, String message ) {
    errors.put( champ, message );
}

/*
 * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
 * sinon.
 */
    
    private String getValeurChamp(HttpServletRequest request, String nomChamp) {
        String valeur = request.getParameter( nomChamp );
    if ( valeur == null || valeur.trim().length() == 0 ) {
        return null;
    } else {
        return valeur.trim();
    }
    }
    
    
}
