package org.uqbar.duelo.domain;

import java.io.Serializable;

/**
 * Created by luciano on 28/11/15.
 */
public class Estadisticas implements Serializable{

    private int jugadas;
    private int ganadas;
    private int kills;
    private int deads;
    private int assists;
    private String mejorUbicacion;
    private String puntaje;

    public Estadisticas(int jugadas, int ganadas, int kills, int deads, int assists, String mejorUbicacion, String puntaje) {
        this.jugadas = jugadas;
        this.ganadas = ganadas;
        this.kills = kills;
        this.deads = deads;
        this.assists = assists;
        this.mejorUbicacion =mejorUbicacion;
        this.puntaje = puntaje;
    }

    public int getJugadas() {
        return this.jugadas;
    }

    public int getGanadas() {
        return this.ganadas;
    }

    public int getKills() {
        return this.kills;
    }

    public int getDeads() {
        return this.deads;
    }

    public int getAssists() {
        return this.assists;
    }

    public String getMejorUbicacion() {
        return this.mejorUbicacion;
    }

    public String getPuntaje() {
        return this.puntaje;
    }

}