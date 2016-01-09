/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Controller.SaveInfoServlet.CONF_PASS;
import static Controller.SaveInfoServlet.DEPARTMENT;
import static Controller.SaveInfoServlet.EMAIL;
import static Controller.SaveInfoServlet.FIRST_NAME;
import static Controller.SaveInfoServlet.GROUP_NAME;
import static Controller.SaveInfoServlet.GROUP_PASS;
import static Controller.SaveInfoServlet.LAST_NAME;
import static Controller.SaveInfoServlet.PASS;
import static Controller.SaveInfoServlet.STATUS;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.IOException;
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

    
    public void createUSER(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
    String fname = request.getParameter(FIRST_NAME);
        String lname = request.getParameter(LAST_NAME);
        String email = request.getParameter(EMAIL);
        String pswd = request.getParameter(PASS);
        String confpswd = request.getParameter(CONF_PASS);
        String gname = request.getParameter(GROUP_NAME);
        String gpswd = request.getParameter(GROUP_PASS);
        String status = request.getParameter(STATUS);
        String dpt = request.getParameter(DEPARTMENT);
        
        String result = data.addUser(fname, lname, email, pswd, gname, gpswd, dpt);
        
        if("Data".equals(result))
            {
                String s="ok";
                if("ok".equals(s))
                {
                    RequestDispatcher rd = request.getRequestDispatcher("accueilProf.jsp");
                    rd.include(request, response);
                }
                              
            }
    }
    

    
}
