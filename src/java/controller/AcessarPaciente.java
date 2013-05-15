/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.MedicamentoDAO;
import DAO.PacienteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Paciente;

/**
 *
 * @author yuricampos
 */
public class AcessarPaciente extends HttpServlet {

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
            String paciente = (String) request.getSession(false).getAttribute("id");
            PacienteDAO pdao = new PacienteDAO();
            Paciente p = new Paciente();
            Paciente resultado = new Paciente();
            if (paciente == null) {
                String idRequest = request.getParameter("idForm");
                int id = Integer.parseInt(idRequest);
                String chave = request.getParameter("chave");
                p.setId(id);
                p.setChave(chave);
                resultado = (Paciente) pdao.recuperarKey(p);
            } else {
                int pid = Integer.parseInt(paciente);
                p.setId(pid);
                resultado = (Paciente) pdao.recuperar(pid);
            }


            if (resultado == null) {
                request.setAttribute("status", "erro");
                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            } else {
                HttpSession httpSession = request.getSession();
                httpSession.removeAttribute("idPaciente");
                httpSession.removeAttribute("chavePaciente");
                request.removeAttribute("chavePaciente");
                request.removeAttribute("cpfPaciente");
                request.removeAttribute("dataNascimentoPaciente");
                request.removeAttribute("emailPaciente");
                request.removeAttribute("grupoSanguineoPaciente");
                request.removeAttribute("idPaciente");
                request.removeAttribute("loginPaciente");
                request.removeAttribute("nomePaciente");
                request.removeAttribute("rgPaciente");
                request.removeAttribute("sexoPaciente");
                request.setAttribute("chavePaciente", resultado.getChave());
                request.setAttribute("cpfPaciente", resultado.getCpf());
                request.setAttribute("dataNascimentoPaciente", resultado.getDataNascimento());
                request.setAttribute("emailPaciente", resultado.getEmail());
                request.setAttribute("grupoSanguineoPaciente", resultado.getGrupoSanguineo());
                request.setAttribute("idPaciente", resultado.getId());
                request.setAttribute("loginPaciente", resultado.getLogin());
                request.setAttribute("nomePaciente", resultado.getNome());
                request.setAttribute("rgPaciente", resultado.getRg());
                request.setAttribute("sexoPaciente", resultado.getSexo());
                httpSession.setAttribute("idPaciente", resultado.getId());
                httpSession.setAttribute("chavePaciente", resultado.getChave());
                getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);

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
        } catch (Exception ex) {
            Logger.getLogger(AcessarPaciente.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AcessarPaciente.class.getName()).log(Level.SEVERE, null, ex);
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
