/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.ExameDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Exame;
import model.Historico;
import model.Medico;
import model.Paciente;

/**
 *
 * @author yuricampos
 */
public class CadastrarExame extends HttpServlet {

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
            String crm = (String) request.getSession(false).getAttribute("crm");
            String chave = (String) request.getSession(false).getAttribute("chavePaciente");
            String idRequest = (String) request.getSession(false).getAttribute("idPaciente");
            int idPaciente = Integer.parseInt(idRequest);
            Paciente p = new Paciente();
            p.setChave(chave);
            p.setId(idPaciente);
            Medico m = new Medico();
            m.setCrm(crm);
            Historico h = new Historico();
            Uteis u = new Uteis();
            String dataRequest = request.getParameter("dataExame");
            Date data = u.coverteStringData(dataRequest);
            String nome = request.getParameter("nomeExame");
            String descricao = request.getParameter("descricaoExame");
            String exame = request.getParameter("exameExame");
            Exame e = new Exame();
            e.setDescricao(descricao);
            e.setExame(exame);
            e.setData(data);
            e.setMedico(m);
            e.setNome(nome);
            e.setPaciente(p);
            ExameDAO edao = new ExameDAO();
            edao.inserir(e);
            
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
            Logger.getLogger(CadastrarExame.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CadastrarExame.class.getName()).log(Level.SEVERE, null, ex);
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
