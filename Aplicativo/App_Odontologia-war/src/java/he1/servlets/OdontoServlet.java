/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he1.servlets;

import he1.utilities.SesionSeguridades;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luis_guanoluiza
 */
@WebServlet(name = "OdontoServlet", urlPatterns = {"/paciente"})
public class OdontoServlet extends HttpServlet {

    @EJB
    private SesionSeguridades sesionSeguridades;

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
        HttpSession session = request.getSession();
        boolean existeUsuario = !request.getParameter("user").equalsIgnoreCase("SIN DATO");
        String usuario, clave;
        if (existeUsuario) {
            usuario = sesionSeguridades.decyrpDinamico(request.getParameter("user"));
            clave = sesionSeguridades.decyrpDinamico(request.getParameter("pac"));
            System.out.println("user "+usuario+" clave "+clave);
            session.setAttribute("usuarioDB", usuario);
            session.setAttribute("usuarioClave", clave);
            if (usuario.substring(0, 1).equalsIgnoreCase("F")) {
                response.sendRedirect("http://des04:8080/He1odonto/pages/asignarTurno.xhtml");
                //response.sendRedirect("http://172.16.60.12:8080/He1odonto/pages/asignarTurno.xhtml");
            } else if (usuario.substring(0, 1).equalsIgnoreCase("M")) {
                response.sendRedirect("http://des04:8080/He1odonto");
               // response.sendRedirect("http://172.16.60.12:8080/He1odonto");
            }

//             response.sendRedirect("http://172.16.60.12:8080/uci");
//            response.sendRedirect("http://bidbb:8080/uci");
        } else {
            request.getServletContext().getRequestDispatcher(sesionSeguridades.buscaURLPortal()).forward(request, response);
        }

        //
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
