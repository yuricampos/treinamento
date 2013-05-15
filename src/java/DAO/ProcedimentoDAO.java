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
import model.Medico;
import model.Procedimento;

/**
 *
 * @author yuricampos
 */
public class ProcedimentoDAO implements IObjectDAO {
    
    
    private final String SQL_INSERT = "INSERT INTO procedimento VALUES(DEFAULT,?,?,?,?,?,?);";
    private final String SQL_UPDATE = "UPDATE procedimento SET nome = ?, descricao = ?, observacao = ?, "
            + "data = ? WHERE id = ?";
    private final String SQL_GETALL = "SELECT p.id as p_id, p.medico as p_medico, p.paciente as p_paciente,"
            + "p.nome as p_nome, p.descricao as p_descricao, p.observacao as p_observacao, p.data as p_data"
            + " FROM procedimento AS p WHERE p.paciente = ?";
    private final String SQL_GET =  "SELECT p.id as p_id, p.medico as p_medico, p.paciente as p_paciente,"
            + "p.nome as p_nome, p.descricao as p_descricao, p.observacao as p_observacao, p.data as p_data"
            + " FROM procedimento AS p WHERE p.id = ?";
    private PreparedStatement ps;
    private ResultSet rs;

    public Object criarObjetoTemplate() throws SQLException, Exception {
        Procedimento output = new Procedimento();
        output.setId(this.rs.getInt("p_id"));
        Medico m = new Medico();
        MedicoDAO d = new MedicoDAO();
        m = (Medico) d.recuperar(this.rs.getString("h_medico"));
        output.setMedico(m);
        //Paciente p = new Paciente();
        //PacienteDAO pdao = new PacienteDAO();
        //p = (Paciente) pdao.recuperar(this.rs.getInt("h_paciente"));
        //output.setPaciente(p);
        output.setData(this.rs.getDate("p_data"));
        output.setDescricao(this.rs.getString("p_descricao"));
        output.setNome(this.rs.getString("p_nome"));
        output.setObservacao(this.rs.getString("p_observacao"));
        return output;
    }

    public ArrayList<Object> buscarVariosObjetosTemplate() throws SQLException, Exception {
        ArrayList<Object> output = new ArrayList<>();
        while (this.rs.next()) {
            output.add((Procedimento) this.criarObjetoTemplate());
        }

        return output;
    }

    @Override
    public void inserir(Object input) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizar(Object input) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void apagar(Object input) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object recuperar(Object input) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
