/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;

/**
 *
 * @author yuricampos
 */
public interface IObjectDAO {

    /**
     * inserir um novo objeto no banco
     *
     * @param input
     * @throws Exception
     */
    public void inserir(Object input) throws Exception;

    /**
     * atualizar um objeto no banco
     *
     * @param input
     * @throws Exception
     */
    public void atualizar(Object input) throws Exception;

    /**
     * apagar um objeto do banco
     *
     * @param input
     * @throws Exception
     */
    public void apagar(Object input) throws Exception;

    /**
     * recuperar um objeto no banco usando primary key
     *
     * @param input
     * @throws Exception
     */
    public Object recuperar(Object input) throws Exception;

    /**
     * listar objetos do banco
     *
     * @param input
     * @throws Exception
     */
    public ArrayList<Object> listar(Object input) throws Exception;

    /**
     * fazer buscas em objetos no banco
     *
     * @param input
     * @return
     * @throws Exception
     */
    public ArrayList<Object> buscar(Object input) throws Exception;
}
