package org.uqbar.duelo.domain;

import java.io.Serializable;

/**
 * Created by luciano on 02/12/15.
 */
public class DatosDeEstadisticas implements Serializable {

    private String valorDelCampo;
    private String nombreDelCampo;

    public DatosDeEstadisticas(){}
    public DatosDeEstadisticas(String nombreDelCampo, String valorDelCampo){
        this.nombreDelCampo = nombreDelCampo;
        this.valorDelCampo = valorDelCampo;
    }

    public String getNombreDelCampo() {
        return nombreDelCampo;
    }

    public void setNombreDelCampo(String nombreDelCampo) {
        this.nombreDelCampo = nombreDelCampo;
    }

    public String getValorDelCampo() {
        return valorDelCampo;
    }

    public void setValorDelCampo(String valorDelCampo) {
        this.valorDelCampo = valorDelCampo;
    }



}
