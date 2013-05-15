/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Historico;
import model.Medico;
import model.Paciente;

/**
 *
 * @author yuricampos
 */
public class HistoricoDAO implements IObjectDAO {

    private final String SQL_INSERT = "INSERT INTO historico VALUES(DEFAULT,?,?,?,?,?,?,?,?);";
    private final String SQL_UPDATE = "UPDATE historico SET tipo = ?, descricao = ?, observacao = ?, "
            + "dataDiagnostico = ?, status = ?, dataResolucao = ? WHERE id = ?";
    private final String SQL_GETALL = "SELECT h.id as h_id, h.medico as h_medico, h.paciente as h_paciente,"
            + " h.tipo as h_tipo, h.descricao as h_descricao, h.observacao as h_observacao, h.dataDiagnostico as h_dataDiagnostico,"
            + " h.status as h_status, h.dataResolucao as h_dataResolucao FROM historico AS h WHERE h.paciente = ?";
    private final String SQL_GET = "SELECT h.id as h_id, h.medico as h_medico, h.paciente as h_paciente,"
            + " h.tipo as h_tipo, h.descricao as h_descricao, h.observacao as h_observacao, h.dataDiagnostico as h_dataDiagnostico,"
            + " h.status as h_status, h.dataResolucao as h_dataResolucao FROM historico AS h WHERE h.id = ?";
    private PreparedStatement ps;
    private ResultSet rs;

    public Object criarObjetoTemplate() throws SQLException, Exception {
        Historico output = new Historico();
        output.setId(this.rs.getInt("h_id"));
        Medico m = new Medico();
        MedicoDAO d = new MedicoDAO();
        m = (Medico) d.recuperar(this.rs.getString("h_medico"));
        output.setMedico(m);
        //Paciente p = new Paciente();
        //PacienteDAO pdao = new PacienteDAO();
        //p = (Paciente) pdao.recuperar(this.rs.getInt("h_paciente"));
        //output.setPaciente(p);
        output.setTipo(this.rs.getString("h_tipo"));
        output.setDescricao(this.rs.getString("h_descricao"));
        output.setObservacao(this.rs.getString("h_observacao"));
        output.setDataDiagnostico(this.rs.getDate("h_dataDiagnostico"));
        output.setStatus(this.rs.getString("h_status"));
        output.setDataResolucao(this.rs.getDate("h_dataResolucao"));
        return output;
    }

    public List<Object> buscarVariosObjetosTemplate() throws SQLException, Exception {
        List<Object> output = null;
        output.clear();
        while (this.rs.next()) {
            output.add((Historico) this.criarObjetoTemplate());
        }

        return output;
    }

    @Override
    public void inserir(Object input) throws Exception {
        Historico h = (Historico) input;
        this.ps = Conexao.getInstance().getConexao().prepareStatement(SQL_INSERT);
        this.ps.setString(1, h.getMedico().getCrm());
        this.ps.setInt(2, h.getPaciente().getId());
        this.ps.setString(3, h.getTipo());
        this.ps.setString(4, h.getDescricao());
        this.ps.setString(5, h.getObservacao());
        this.ps.setDate(6, h.getDataDiagnostico());
        this.ps.setString(7, h.getStatus());
        this.ps.setDate(8, h.getDataResolucao());
        this.ps.execute();
    }

    @Override
    public void atualizar(Object input) throws Exception {
        Historico h = (Historico) input;
        this.ps = Conexao.getInstance().getConexao().prepareStatement(SQL_UPDATE);
        this.ps.setString(1, h.getTipo());
        this.ps.setString(2, h.getDescricao());
        this.ps.setString(3, h.getObservacao());
        this.ps.setDate(4, h.getDataDiagnostico());
        this.ps.setString(5, h.getStatus());
        this.ps.setDate(6, h.getDataResolucao());
        this.ps.setInt(7, h.getId());
        this.ps.execute();
    }

    @Override
    public void apagar(Object input) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object recuperar(Object input) throws Exception {
        Historico h = (Historico) input;
        this.ps = Conexao.getInstance().getConexao().prepareStatement(SQL_GET);
        this.ps.setInt(1, h.getId());
        this.rs = this.ps.executeQuery();
        rs.next();
        return this.criarObjetoTemplate();
    }

    public Object getAll(Object input) throws Exception {
        Historico h = (Historico) input;
        this.ps = Conexao.getInstance().getConexao().prepareStatement(SQL_GETALL);
        this.ps.setInt(1, h.getPaciente().getId());
        this.rs = this.ps.executeQuery();
        return this.buscarVariosObjetosTemplate();
    }

    @Override
    public Object listar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object buscar(Object input) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
