/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.PacienteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Paciente;

/**
 *
 * @author yuricampos
 */
public class CadastroPaciente extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            Uteis u = new Uteis();
            String nome = request.getParameter("nome");
            String cpf = request.getParameter("crm");
            String rg = request.getParameter("crm");
            String sexo = request.getParameter("sexo");
            String grupoSanguineo = request.getParameter("grupoSanguineo");
            String login = request.getParameter("login");
            String senha = request.getParameter("senha");
            String email = request.getParameter("email");
            String dataNascimentoRequest = request.getParameter("crm");
            Date dataNascimento = u.coverteStringData(dataNascimentoRequest);
            Paciente p = new Paciente();
            p.setCpf(cpf);
            p.setDataNascimento(dataNascimento);
            p.setEmail(email);
            p.setGrupoSanguineo(grupoSanguineo);
            p.setLogin(login);
            p.setNome(nome);
            p.setRg(rg);
            p.setSenha(senha);
            p.setSexo(sexo);
            PacienteDAO pdao = new PacienteDAO();
            pdao.inserir(p);
            request.setAttribute("status", "cadastrado");
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

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
        } catch (Exception ex) {
            Logger.getLogger(CadastroPaciente.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (Exception ex) {
            Logger.getLogger(CadastroPaciente.class.getName()).log(Level.SEVERE, null, ex);
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
