package org.uqbar.duelo.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by luciano on 28/11/15.
 */
public class Estadisticas implements Serializable{

    private DatoDeEstadisticas jugadas;
    private DatoDeEstadisticas ganadas;
    private DatoDeEstadisticas kills;
    private DatoDeEstadisticas deads;
    private DatoDeEstadisticas assists;
    private DatoDeEstadisticas mejorUbicacion;
    private DatoDeEstadisticas puntaje;


    public Estadisticas(DatoDeEstadisticas jugadas, DatoDeEstadisticas ganadas, DatoDeEstadisticas kills,
                        DatoDeEstadisticas deads, DatoDeEstadisticas assists, DatoDeEstadisticas mejorUbicacion,
                        DatoDeEstadisticas puntaje) {
        this.jugadas = jugadas;
        this.ganadas =ganadas;
        this.kills = kills;
        this.deads = deads;
        this.assists =assists;
        this.mejorUbicacion = mejorUbicacion;
        this.puntaje = puntaje;
    }

    public List<DatoDeEstadisticas> estadisticas(){
        return Arrays.asList(new DatoDeEstadisticas[]{getJugadas(),getGanadas(),getKills(),getAssists(),getMejorUbicacion(),getPuntaje()});
    }

    public DatoDeEstadisticas getJugadas() {
        return this.jugadas;
    }

    public DatoDeEstadisticas getGanadas() {
        return this.ganadas;
    }

    public DatoDeEstadisticas getKills() {
        return this.kills;
    }

    public DatoDeEstadisticas getDeads() {
        return this.deads;
    }

    public DatoDeEstadisticas getAssists() {
        return this.assists;
    }

    public DatoDeEstadisticas getMejorUbicacion() {
        return this.mejorUbicacion;
    }

    public DatoDeEstadisticas getPuntaje() {
        return this.puntaje;
    }

}