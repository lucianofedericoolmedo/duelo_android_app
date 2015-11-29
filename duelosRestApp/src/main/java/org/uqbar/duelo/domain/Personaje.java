package org.uqbar.duelo.domain;

import java.io.Serializable;

/**
 * Created by Juan on 27-Nov-15.
 */
public class Personaje implements Serializable{

    private int id;
    private String nombre;
/*
    public static final Personaje[] personajes = {
            new Personaje("The Limb Loosener",
                    "5 Handstand push-ups\n10 1-legged squats\n15 Pull-ups"),
            new Personaje("Core Agony",
                    "100 Pull-ups\n100 Push-ups\n100 Sit-ups\n100 Squats"),
            new Personaje("The Wimp Special",
                    "5 Pull-ups\n10 Push-ups\n15 Squats"),
            new Personaje("Strength and Length",
                    "500 meter run\n21 x 1.5 pood kettleball swing\n21 x pull-ups")
    };*/


    private Personaje(int id, String name) {
        this.id = id;
        this.nombre = name;
    }

    public int getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getImage(){
        return this.getNombre().toLowerCase().replace("","_");
    }

    public String toString() {
        return this.nombre;
    }
}
