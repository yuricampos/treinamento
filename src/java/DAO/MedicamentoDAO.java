/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Historico;
import model.Medicamento;
import model.Medico;
import model.Paciente;

/**
 *
 * @author yuricampos
 */
public class MedicamentoDAO implements IObjectDAO {

    private final String SQL_INSERT = "INSERT INTO medicamento VALUES(DEFAULT,?,?,?,?,?,?);";
    private final String SQL_UPDATE = "UPDATE medicamento SET nome = ?, dataInicio = ?, dataFim = ?, "
            + "observacao = ? WHERE id = ?";
    private final String SQL_GETALL = "SELECT m.id as m_id, m.nome as m_nome, m.medico as m_medico,"
            + "m.paciente as m_paciente, m.dataInicio as m_dataInicio, m.dataFim as m_dataFim, m.observacao as m_observacao"
            + "FROM medicamento AS m WHERE m.paciente = ?";
    private final String SQL_GET = "SELECT m.id as m_id, m.nome as m_nome, m.medico as m_medico,"
            + "m.paciente as m_paciente, m.dataInicio as m_dataInicio, m.dataFim as m_dataFim, m.observacao as m_observacao"
            + "FROM medicamento AS m WHERE m.id = ?";
    private PreparedStatement ps;
    private ResultSet rs;

    public Object criarObjetoTemplate() throws SQLException, Exception {
        Medicamento output = new Medicamento();
        output.setId(this.rs.getInt("m_id"));
        Medico m = new Medico();
        MedicoDAO d = new MedicoDAO();
        m = (Medico) d.recuperar(this.rs.getString("m_medico"));
        output.setMedico(m);
        output.setNome(this.rs.getString("m_nome"));
        output.setObservacao(this.rs.getString("m_observacao"));
        output.setDataFim(this.rs.getDate("m_dataFim"));
        output.setDataInicio(this.rs.getDate("m_dataInicio"));
        return output;
    }

    public ArrayList<Object> buscarVariosObjetosTemplate() throws SQLException, Exception {
        ArrayList<Object> output = new ArrayList<>();
        while (this.rs.next()) {
            output.add((Medicamento) this.criarObjetoTemplate());
        }

        return output;
    }

    @Override
    public void inserir(Object input) throws Exception {
        Medicamento m = (Medicamento) input;
        this.ps = Conexao.getInstance().getConexao().prepareStatement(SQL_INSERT);
        this.ps.setString(1, m.getNome());
        this.ps.setString(2, m.getMedico().getCrm());
        this.ps.setInt(3, m.getPaciente().getId());
        this.ps.setDate(4, m.getDataInicio());
        this.ps.setDate(5, m.getDataFim());
        this.ps.setString(6, m.getObservacao());
        this.ps.execute();
    }

    @Override
    public void atualizar(Object input) throws Exception {

        Medicamento m = (Medicamento) input;
        this.ps = Conexao.getInstance().getConexao().prepareStatement(SQL_UPDATE);
        this.ps.setString(1, m.getNome());
        this.ps.setDate(2, m.getDataInicio());
        this.ps.setDate(3, m.getDataFim());
        this.ps.setString(4, m.getObservacao());
        this.ps.setInt(5, m.getId());
        this.ps.execute();
    }

    @Override
    public void apagar(Object input) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object recuperar(Object input) throws Exception {
        Medicamento m = (Medicamento) input;
        this.ps = Conexao.getInstance().getConexao().prepareStatement(SQL_GET);
        this.ps.setInt(1, m.getId());
        this.rs = this.ps.executeQuery();
        rs.next();
        return this.criarObjetoTemplate();
    }

    public Object getAll(Object input)throws Exception {
        Medicamento m = (Medicamento) input;
        this.ps = Conexao.getInstance().getConexao().prepareStatement(SQL_GETALL);
        this.ps.setInt(1, m.getPaciente().getId());
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
