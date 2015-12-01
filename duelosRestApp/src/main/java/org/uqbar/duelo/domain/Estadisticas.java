package org.uqbar.duelo.domain;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Created by luciano on 28/11/15.
 */
public class Estadisticas implements Serializable{

    private String jugadas;
    private String ganadas;
    private String kills;
    private String deads;
    private String assists;
    private String mejorUbicacion;
    private String puntaje;

    public Estadisticas(int jugadas, int ganadas, int kills, int deads, int assists, String mejorUbicacion, String puntaje) {
        this.jugadas = String.valueOf(jugadas);
        this.ganadas =String.valueOf(ganadas);
        this.kills = String.valueOf(kills);
        this.deads = String.valueOf(deads);
        this.assists = String.valueOf(assists);
        this.mejorUbicacion = mejorUbicacion;
        this.puntaje = puntaje;
    }

    public List<String> estadisticas(){
        return Arrays.asList(new String[]{getJugadas(),getGanadas(),getKills(),getAssists(),getMejorUbicacion(),getPuntaje()});
    }

    public String getJugadas() {
        return this.jugadas;
    }

    public String getGanadas() {
        return this.ganadas;
    }

    public String getKills() {
        return this.kills;
    }

    public String getDeads() {
        return this.deads;
    }

    public String getAssists() {
        return this.assists;
    }

    public String getMejorUbicacion() {
        return this.mejorUbicacion;
    }

    public String getPuntaje() {
        return this.puntaje;
    }

}