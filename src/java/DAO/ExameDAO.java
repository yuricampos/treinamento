/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Exame;
import model.Historico;
import model.Medico;

/**
 *
 * @author yuricampos
 */
public class ExameDAO implements IObjectDAO {

    private final String SQL_INSERT = "INSERT INTO exame VALUES(DEFAULT,?,?,?,?,?,?);";
    private final String SQL_UPDATE = "UPDATE exame SET nome = ?, descricao = ?, data = ?, "
            + "exame = ?,WHERE id = ?";
    private final String SQL_GETALL = "SELECT e.id as e_id, e.paciente as e_paciente, e.medico as e_medico, "
            + "e.nome as e_nome, e.descricao as e_descricao, e.data as e_data, e.exame as e_exame"
            + " FROM exame AS e WHERE e.paciente = ?";
    private final String SQL_GET = "SELECT e.id as e_id, e.paciente as e_paciente, e.medico as e_medico, "
            + "e.nome as e_nome, e.descricao as e_descricao, e.data as e_data, e.exame as e_exame"
            + " FROM exame AS e WHERE e.id = ?";
    private PreparedStatement ps;
    private ResultSet rs;

    public Object criarObjetoTemplate() throws SQLException, Exception {
        Exame output = new Exame();
        output.setId(this.rs.getInt("e_id"));
        Medico m = new Medico();
        MedicoDAO d = new MedicoDAO();
        m = (Medico) d.recuperar(this.rs.getString("h_medico"));
        output.setMedico(m);
        //Paciente p = new Paciente();
        //PacienteDAO pdao = new PacienteDAO();
        //p = (Paciente) pdao.recuperar(this.rs.getInt("h_paciente"));
        //output.setPaciente(p);
        output.setNome(this.rs.getString("e_nome"));
        output.setDescricao(this.rs.getString("e_descricao"));
        output.setExame(this.rs.getString("e_exame"));
        output.setData(this.rs.getDate("e_data"));
        return output;
    }

    public ArrayList<Object> buscarVariosObjetosTemplate() throws SQLException, Exception {
        ArrayList<Object> output = new ArrayList<>();
        while (this.rs.next()) {
            output.add((Exame) this.criarObjetoTemplate());
        }

        return output;
    }

    @Override
    public void inserir(Object input) throws Exception {
        Exame e = (Exame) input;
        this.ps = Conexao.getInstance().getConexao().prepareStatement(SQL_INSERT);
        this.ps.setInt(1, e.getPaciente().getId());
        this.ps.setString(2, e.getMedico().getCrm());
        this.ps.setString(3, e.getNome());
        this.ps.setString(4, e.getDescricao());
        this.ps.setDate(5, e.getData());
        this.ps.setString(6, e.getExame());
        this.ps.execute();
    }

    @Override
    public void atualizar(Object input) throws Exception {
        
        Exame e = (Exame) input;
        this.ps = Conexao.getInstance().getConexao().prepareStatement(SQL_UPDATE);
        this.ps.setString(1, e.getNome());
        this.ps.setString(2, e.getDescricao());
        this.ps.setDate(3,e.getData());
        this.ps.setString(4,e.getExame());
        this.ps.setInt(5, e.getId());
        this.ps.execute();
    }

    @Override
    public void apagar(Object input) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object recuperar(Object input) throws Exception {
        Exame e = (Exame) input;
        this.ps = Conexao.getInstance().getConexao().prepareStatement(SQL_GET);
        this.ps.setInt(1, e.getId());
        this.rs = this.ps.executeQuery();
        rs.next();
        return this.criarObjetoTemplate();
    }

    public Object getAll(Object input) throws Exception {
        Exame e = (Exame) input;
        this.ps = Conexao.getInstance().getConexao().prepareStatement(SQL_GETALL);
        this.ps.setInt(1, e.getPaciente().getId());
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
