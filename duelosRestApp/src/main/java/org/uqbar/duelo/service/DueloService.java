package org.uqbar.duelo.service;

import org.uqbar.duelo.domain.Caracteristicas;
import org.uqbar.duelo.domain.DatosDeEstadisticas;
import org.uqbar.duelo.domain.Personaje;

import java.util.List;


import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Juan on 26/11/15.
 */
public interface DueloService {

    @GET("/datos_minimos_personajes")
    public Call<List<Personaje>> getPersonajes();

    @GET("/descripcion_personaje/1/{personajeId}")
    public Call<Caracteristicas> getCaracteristicasPersonaje(@Path("personajeId") String personajeId);

    @GET("/estadisticas/1/{personajeId}")
    public Call<List<DatosDeEstadisticas>> getEstadisticasPersonaje(@Path("personajeId") String personajeId);
}




