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
    public ArrayList<Object> listar(Object input) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Object> buscar(Object input) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
