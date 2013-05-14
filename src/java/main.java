
import DAO.MedicoDAO;
import DAO.PacienteDAO;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.Medico;
import model.Paciente;

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
         *
         * Medico m = new Medico(); m.setNome("yuri"); m.setCrm("123456");
         * m.setEmail("a@a.com"); m.setSenha("111"); mdao.inserir(m);
         * ArrayList<Object> medicos = new ArrayList<Object>(); medicos =
         * (ArrayList<Object>) mdao.buscar("f"); for(int i = 0 ; i <
         * medicos.size() ; i++){ m = (Medico) medicos.get(i);
         * System.out.println(m.getNome()); } m = (Medico)
         * mdao.recuperar("123"); System.out.println(m.getEmail());
         * m.setNome("yuri"); m.setCrm("123456"); m.setEmail("b@a.com");
         * m.setSenha("111"); mdao.atualizar(m);
         * System.out.println(m.getEmail());
        *
         */
        PacienteDAO pdao = new PacienteDAO();
        String chave = pdao.gerarKey();
        System.out.println(chave);
        Paciente p = new Paciente();
        //  p.setNome("yuri");
        //p.setCpf("123456");
        //p.setSexo("M");
        //p.setDataNascimento(coverteStringData("10/10/2010"));
        // p.setLogin("1");
        // p.setSenha("12345");
        // p.setEmail("p@p.aaaacom");
        //  pdao.inserir(p);
        //p.setId(2);
        //pdao.atualizar(p);
        // p = (Paciente) pdao.verificaLogin("1", "12345");
        Paciente p1 = new Paciente();
        p1.setId(2);
        p1.setChave("ZRSUBPLUL");
        p = (Paciente) pdao.recuperarKey(p1);
        System.out.println(p.getNome());

    }

    public static String converteData(Date d) {
        if (d == null) {
            return new String("");
        }
        return new SimpleDateFormat("dd/MM/yyyy").format(d);
    }

    public static Date coverteStringData(String data) {
        if (data.equals("")) {
            return null;
        }

        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return new java.sql.Date(formatador.parse(data).getTime());
        } catch (ParseException ex) {
            System.out.println("Erro conversao de Date!");
            return null;
        }
    }
}
