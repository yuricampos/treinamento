/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import model.Medico;
import model.Paciente;

/**
 *
 * @author yuricampos
 */
public class PacienteDAO implements IObjectDAO {

    private final String SQL_LOGIN = "SELECT p.id as p_id, p.nome as p_nome, p.cpf as p_cpf, p.rg as"
            + " p_rg, p.foto as p_foto, p.chave as p_chave, p.sexo as p_sexo, p.grupoSanguineo as p_grupoSanguineo, "
            + "p.login as p_login, p.email as p_email FROM paciente AS p WHERE p.login = ? AND p.senha = ?";
    private final String SQL_INSERT = "INSERT INTO paciente VALUES(DEFAULT,?,?,?,?,?,?,?,?,?,?,?);";
    private final String SQL_UPDATE = "UPDATE paciente SET senha = ?, email = ? WHERE id = ?";
    private final String SQL_GET = "SELECT p.id as p_id, p.nome as p_nome, p.cpf as p_cpf, p.rg as"
            + " p_rg, p.foto as p_foto, p.chave as p_chave, p.sexo as p_sexo, p.grupoSanguineo as p_grupoSanguineo, "
            + "p.login as p_login, p.email as p_email FROM paciente AS p WHERE p.id = ?";
    private final String SQL_GET_BY_KEY = "SELECT p.id as p_id, p.nome as p_nome, p.cpf as p_cpf, p.rg as"
            + " p_rg, p.foto as p_foto, p.chave as p_chave, p.sexo as p_sexo, p.grupoSanguineo as p_grupoSanguineo, "
            + "p.login as p_login, p.email as p_email FROM paciente AS p WHERE p.chave = ?";
    private final String SQLUPDATEKEY = "UPDATE paciente SET chave = ? WHERE id = ?";
    private PreparedStatement ps;
    private ResultSet rs;

    public String gerarKey() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVYWXZ";

        Random random = new Random();

        String armazenaChaves = "";
        int index = -1;
        for (int i = 0; i < 9; i++) {
            index = random.nextInt(letras.length());
            armazenaChaves += letras.substring(index, index + 1);
        }
        return armazenaChaves;
    }
    
    public void atualizarKey(Object input){
        Paciente p = (Paciente) input;

        
    }

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
        Paciente output = new Paciente();
        output.setId(this.rs.getInt("p_id"));
        output.setNome(this.rs.getString("p_nome"));
        output.setCpf(this.rs.getString("p_cpf"));
        output.setRg(this.rs.getString("p_rg"));
        output.setSexo(this.rs.getString(p_sexo));
        output.setFoto(this.rs.getString("p_foto"));
        output.setChave(this.rs.getString("p_rg"));
        return output;
    }

    @Override
    public void inserir(Object input) throws Exception {
        Paciente p = (Paciente) input;
        this.ps = Conexao.getInstance().getConexao().prepareStatement(SQL_INSERT);
        this.ps.setString(1, p.getNome());
        this.ps.setString(2, p.getCpf());
        this.ps.setString(3, p.getRg());
        this.ps.setString(4, p.getFoto());
        this.ps.setString(1, p.getNome());
        this.ps.execute();
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
