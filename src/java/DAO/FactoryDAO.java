/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author yuricampos
 */
public class FactoryDAO {

    private static String factoryInUse = "";
    private static IObjectDAO instance;

    public static IObjectDAO getFactory(String dao) {
        if (!dao.equals(factoryInUse) || factoryInUse.equals("")) {
            switch (dao) {
                case ("Medico"):
                    factoryInUse = "Medico";
                    instance = new MedicoDAO();
                    break;
                default:
                    factoryInUse = "";
                    instance = null;
                    break;
            }
        }
        return instance;
    }
}
