/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author yuricampos
 */
public class Historico {
    
    private int id;
    private Medico medico;
    private Paciente paciente;
    private String tipo;
    private String descricao;
    private String observacao;
    private Date dataDiagnostico;
    private char status;
    private Date dataResolucao;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the medico
     */
    public Medico getMedico() {
        return medico;
    }

    /**
     * @param medico the medico to set
     */
    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    /**
     * @return the paciente
     */
    public Paciente getPaciente() {
        return paciente;
    }

    /**
     * @param paciente the paciente to set
     */
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the observacao
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * @param observacao the observacao to set
     */
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    /**
     * @return the dataDiagnostico
     */
    public Date getDataDiagnostico() {
        return dataDiagnostico;
    }

    /**
     * @param dataDiagnostico the dataDiagnostico to set
     */
    public void setDataDiagnostico(Date dataDiagnostico) {
        this.dataDiagnostico = dataDiagnostico;
    }

    /**
     * @return the status
     */
    public char getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(char status) {
        this.status = status;
    }

    /**
     * @return the dataResolucao
     */
    public Date getDataResolucao() {
        return dataResolucao;
    }

    /**
     * @param dataResolucao the dataResolucao to set
     */
    public void setDataResolucao(Date dataResolucao) {
        this.dataResolucao = dataResolucao;
    }

}
