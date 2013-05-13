/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author yuricampos
 */
public class Conexao {
    private static final String url = "jdbc:postgresql://localhost:5432/treinamento";
    private static final String user = "postgres";
    private static final String pass = "postgres";
    private static Conexao instance = null;
    private static Connection connection;
        private Conexao() throws ClassNotFoundException, SQLException, Exception{
        try {
            Class.forName( "org.postgresql.Driver" );
            connection = DriverManager.getConnection(url, user, pass);
        } 
        catch ( ClassNotFoundException cnfex ) {
            System.err.println("Failed to load JDBC/ODBC driver. erro" + cnfex.toString() );
        }
        catch ( SQLException sqlex ) {
            System.err.println( "Unable to connect" + sqlex.toString() );
        }
    }
    
    public static Conexao getInstance() {
        try{
        if(instance != null)
            return instance;
        else
            return new Conexao();
        }
        catch(Exception e){
            return null;
        }
    }
    
    public static Connection getConexao() throws SQLException{
        return connection;
    }
}
