package org.uqbar.duelo.domain;

import java.io.Serializable;

/**
 * Created by luciano on 02/12/15.
 */
public class DatoDeEstadisticas implements Serializable {

    private String valorDelCampo;
    private String nombreCampoDeEstadistica;

    public DatoDeEstadisticas(){}
    public DatoDeEstadisticas(String nombreCampoDeEstadistica, String valorDelCampo){
        this.nombreCampoDeEstadistica = nombreCampoDeEstadistica;
        this.valorDelCampo = valorDelCampo;
    }

    public String getNombreCampoDeEstadistica() {
        return nombreCampoDeEstadistica;
    }

    public void setNombreCampoDeEstadistica(String nombreCampoDeEstadistica) {
        this.nombreCampoDeEstadistica = nombreCampoDeEstadistica;
    }

    public String getValorDelCampo() {
        return valorDelCampo;
    }

    public void setValorDelCampo(String valorDelCampo) {
        this.valorDelCampo = valorDelCampo;
    }



}
