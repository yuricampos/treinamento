
import DAO.MedicoDAO;
import DAO.PacienteDAO;
import java.util.ArrayList;
import model.Medico;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author yuricampos
 */
public class main {

    public static void main(String[] args) throws Exception {
        MedicoDAO mdao = new MedicoDAO();
    //    int a = mdao.verificaLogin("123", "123");
     //   System.out.println(a);
        /**
         * 
         
        Medico m = new Medico();
        m.setNome("yuri");
        m.setCrm("123456");
        m.setEmail("a@a.com");
        m.setSenha("111");
        mdao.inserir(m);
        ArrayList<Object> medicos = new ArrayList<Object>();
        medicos = (ArrayList<Object>) mdao.buscar("f");
        for(int i = 0 ; i < medicos.size() ; i++){
            m = (Medico) medicos.get(i);
            System.out.println(m.getNome());
        } 
        m = (Medico) mdao.recuperar("123");
        System.out.println(m.getEmail());
        m.setNome("yuri");
        m.setCrm("123456");
        m.setEmail("b@a.com");
        m.setSenha("111");
        mdao.atualizar(m);
        System.out.println(m.getEmail());
        * */
        
        PacienteDAO pdao = new PacienteDAO();
        String chave = pdao.gerarKey();
        System.out.println(chave);
    }
}
