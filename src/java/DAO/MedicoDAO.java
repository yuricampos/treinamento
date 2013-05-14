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
import model.Medico;

/**
 *
 * @author yuricampos
 */
public class MedicoDAO implements IObjectDAO {

    private final String SQL_LOGIN = "SELECT m.crm as m_crm, m.nome as m_nome, m.email as m_email FROM medico AS m WHERE m.crm = ? AND m.senha = ?";
    private final String SQL_INSERT = "INSERT INTO medico VALUES(?,?,?,?);";
    private final String SQL_UPDATE = "UPDATE medico SET nome = ?, email = ?, senha = ? WHERE crm = ?";
    private final String SQL_BUSCAR = "SELECT m.crm as m_crm, m.nome as m_nome, m.email as m_email FROM medico AS m WHERE m.nome LIKE ?";
    private final String SQL_GETALL = "SELECT m.crm as m_crm, m.nome as m_nome, m.email as m_email FROM medico AS m";
    private final String SQL_GET = "SELECT m.crm as m_crm, m.nome as m_nome, m.email as m_email FROM medico AS m WHERE m.crm = ?";
    private PreparedStatement ps;
    private ResultSet rs;

    public Object verificaLogin(String usuario, String senha) throws SQLException {
        this.ps = Conexao.getInstance().getConexao().prepareStatement(SQL_LOGIN);
        this.ps.setString(1, usuario);
        this.ps.setString(2, senha);
        this.rs = this.ps.executeQuery();
        this.rs.next();
        int size = -1;
        size = rs.getRow();
        if (size == 0) {
            return null;
        }
        return this.criarObjetoTemplate();
    }

    public Object criarObjetoTemplate() throws SQLException {
        Medico output = new Medico();
        output.setNome(this.rs.getString("m_nome"));
        output.setCrm(this.rs.getString("m_crm"));
        output.setEmail(this.rs.getString("m_email"));
        return output;
    }

    public ArrayList<Object> buscarVariosObjetosTemplate() throws SQLException {
        ArrayList<Object> output = new ArrayList<>();
        while (this.rs.next()) {
            output.add((Medico) this.criarObjetoTemplate());
        }

        return output;
    }

    @Override
    public void inserir(Object input) throws Exception {
        Medico m = (Medico) input;
        this.ps = Conexao.getInstance().getConexao().prepareStatement(SQL_INSERT);
        this.ps.setString(1, m.getCrm());
        this.ps.setString(2, m.getNome());
        this.ps.setString(3, m.getEmail());
        this.ps.setString(4, m.getSenha());
        this.ps.execute();
    }

    @Override
    public void atualizar(Object input) throws Exception {
        Medico m = (Medico) input;
        this.ps = Conexao.getInstance().getConexao().prepareStatement(SQL_UPDATE);
        this.ps.setString(4, m.getCrm());
        this.ps.setString(1, m.getNome());
        this.ps.setString(2, m.getEmail());
        this.ps.setString(3, m.getSenha());
        this.ps.execute();
    }

    @Override
    public void apagar(Object input) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object recuperar(Object input) throws Exception {
        this.ps = Conexao.getInstance().getConexao().prepareStatement(SQL_GET);
        this.ps.setString(1,(String) input);
        this.rs = this.ps.executeQuery();
        if(!this.rs.next())
            throw new Exception("nao encontrado.");

        return this.criarObjetoTemplate();
    }

    @Override
    public ArrayList<Object> listar() throws Exception {
        this.ps = Conexao.getInstance().getConexao().prepareStatement(SQL_GETALL);
        this.rs = this.ps.executeQuery();
        return this.buscarVariosObjetosTemplate();
    }

    @Override
    public Object buscar(Object input) throws Exception {
        String aux = "%" + (String) input + "%";
        this.ps = Conexao.getInstance().getConexao().prepareStatement(SQL_BUSCAR);
        this.ps.setString(1,aux);
        this.rs = this.ps.executeQuery();
        if(this.rs.isLast())
            return this.criarObjetoTemplate();
        else
            return this.buscarVariosObjetosTemplate();
    }
}
