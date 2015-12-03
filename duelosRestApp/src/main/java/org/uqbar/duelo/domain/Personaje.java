package org.uqbar.duelo.domain;

import java.io.Serializable;

/**
 * Created by Juan on 27-Nov-15.
 */
public class Personaje implements Serializable{

    private int id;
    private String nombre;


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
