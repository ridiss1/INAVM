/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpSession;
import net.elbandi.pve2api.data.Container;
import Model.Database;

/**
 *
 * @author ridiss
 */
public class AuthentificationServlet extends HttpServlet {
    
    private static final String ATTR_LOGIN = "login";
    private static final String ATTR_PASWWORD = "password";
    public static final String ATT_SESSION_USER = "sessionUser";
    private String nextPage;
    Database bd = new Database();
                                    
                                    
                                   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        nextPage = "/accueilProf.jsp";

        this.getServletContext().getRequestDispatcher(nextPage).forward(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result;
        String login = request.getParameter(ATTR_LOGIN).trim();
        String password = request.getParameter(ATTR_PASWWORD).trim();
        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();
        String[] log=login.split("@");
        
        session.setAttribute(ATT_SESSION_USER, log[0]);
        result = bd.verifID(login, password);
        if(result.equals("voila")){
        nextPage = "/accueilProf.jsp";
        this.getServletContext().getRequestDispatcher(nextPage).forward(request, response);
        }
        else{
            nextPage = "/index.jsp";
            this.getServletContext().getRequestDispatcher(nextPage).forward(request, response);
        }
        

    }

}
