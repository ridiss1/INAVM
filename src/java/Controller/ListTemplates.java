/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Database;
import Model.TemplateVM;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jean
 */
public class ListTemplates extends HttpServlet {
    
    private Database data = new Database();

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
        ArrayList<TemplateVM> templates =data.GetTemplatesVM();
        int virg = 0;
        String tempVm = "";
        for (TemplateVM temp:templates )
        {
            tempVm += "<tr class='odd'><td>"+temp.getTemplateName()+"</td><td>";
            for(String vm: temp.getVms())
            {
                if(virg == 0)
                {
                    tempVm+=vm;
                    virg++;
                }
                else
                {
                    tempVm+=", "+vm;
                }
            }
            if(virg == 0)
            {
                tempVm+="No Vms </td></tr>";
            }
            else
            {
                tempVm+="</td></tr>";
            }
            virg = 0;
            
        }
        
        System.out.println("trame ***************** "+tempVm);
        request.setAttribute("templates", tempVm);
        request.getRequestDispatcher("displayTemplates.jsp").forward(request, response);
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
        //processRequest(request, response);
        ArrayList<TemplateVM> templates =data.GetTemplatesVM();
        int virg = 0;
        String tempVm = "";
        for (TemplateVM temp:templates )
        {
            tempVm += "<tr class='odd'><td>"+temp.getTemplateName()+"</td><td>";
            for(String vm: temp.getVms())
            {
                if(virg == 0)
                {
                    tempVm+=vm;
                    virg++;
                }
                else
                {
                    tempVm+=", "+vm;
                }
            }
            if(virg == 0)
            {
                tempVm+="No Vms </td></tr>";
            }
            else
            {
                tempVm+="</td></tr>";
            }
            virg = 0;
            
        }
        
        System.out.println("trame ***************** "+tempVm);
        request.setAttribute("templates", tempVm);
        request.getRequestDispatcher("displayTemplates.jsp").forward(request, response);
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
