/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.MedicoDAO;
import DAO.PacienteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Medico;
import model.Paciente;

/**
 *
 * @author yuricampos
 */
public class LoginPaciente extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String usuario = request.getParameter("usuario");
            String senha = request.getParameter("senha");
            HttpSession httpSession = request.getSession();
            PacienteDAO d = new PacienteDAO();
            Paciente resultado = (Paciente) d.verificaLogin(usuario, senha);
            if (resultado == null) {
                request.setAttribute("status", "erro");
                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            } else {
                httpSession.setAttribute("nome", resultado.getNome());
                httpSession.setAttribute("chave", resultado.getChave());
                httpSession.setAttribute("cpf",resultado.getCpf());
                httpSession.setAttribute("dataNascimento",resultado.getDataNascimento());
                httpSession.setAttribute("email",resultado.getEmail());
                httpSession.setAttribute("grupoSanguineo",resultado.getGrupoSanguineo());
                httpSession.setAttribute("id",resultado.getId());
                httpSession.setAttribute("login",resultado.getLogin());
                httpSession.setAttribute("rg",resultado.getRg());
                httpSession.setAttribute("sexo",resultado.getSexo());
                getServletContext().getRequestDispatcher("/homepaciente.jsp").forward(request, response);


            }
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
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
