/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.HistoricoDAO;
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
import model.Historico;
import model.Medico;
import model.Paciente;

/**
 *
 * @author yuricampos
 */
public class CadastroHistorico extends HttpServlet {

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
            Historico h = new Historico();
            String tipo = request.getParameter("tipoHistorico");
            String descricao = request.getParameter("descricaoHistorico");
            String observacao = request.getParameter("observacaoHistorico");
            String dataDiagnosticoRequest = request.getParameter("dataDiagnosticoHistorico");
            Uteis u = new Uteis();
            Date dataDiagnotico = u.coverteStringData(dataDiagnosticoRequest);
            String status = request.getParameter("statusHistorico");
            String dataResolucaoRequest = request.getParameter("dataResolucaoHistorico");
            Date dataResolucao = u.coverteStringData(dataResolucaoRequest);
            String crm = (String) request.getSession(false).getAttribute("crm");
            String chave = (String) request.getSession(false).getAttribute("chavePaciente");
            String idRequest = (String) request.getSession(false).getAttribute("idPaciente");
            int idPaciente = Integer.parseInt(idRequest);
            Paciente p = new Paciente();
            p.setChave(chave);
            p.setId(idPaciente);
            Medico m = new Medico();
            m.setCrm(crm);
            h.setPaciente(p);
            h.setMedico(m);
            h.setDataDiagnostico(dataDiagnotico);
            h.setDataResolucao(dataResolucao);
            h.setDescricao(descricao);
            h.setObservacao(observacao);
            h.setStatus(status);
            h.setTipo(tipo);
            HistoricoDAO hdao = new HistoricoDAO();
            hdao.inserir(h);

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
            Logger.getLogger(CadastroHistorico.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CadastroHistorico.class.getName()).log(Level.SEVERE, null, ex);
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
