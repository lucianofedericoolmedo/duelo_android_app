package org.uqbar.duelosApp;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import org.uqbar.duelo.adapter.EstadisticasAdapter;
import org.uqbar.duelo.domain.DatosDeEstadisticas;
import org.uqbar.duelo.service.DueloService;
import org.uqbar.duelo.service.DueloServiceInstance;
import java.util.List;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by luciano on 28/11/15.
 */
public class EstadisticasActivity extends AppCompatActivity {

    private int idPersonaje = 0;
    private String nombrePersonaje = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas_personaje);

        idPersonaje = getIntent().getIntExtra("idPersonaje",0);
        nombrePersonaje = getIntent().getStringExtra("nombrePersonaje");
        setTitle(nombrePersonaje);
    }

    @Override
    public void onStart(){
        super.onStart();
        obtenerEstadisticasPersonaje(idPersonaje);
    }


    private void obtenerEstadisticasPersonaje(int personajeID) {
        DueloService dueloService = DueloServiceInstance.createDueloService();
        Call<List<DatosDeEstadisticas>> personajeCall = dueloService.getEstadisticasPersonaje(String.valueOf(personajeID));

        personajeCall.enqueue(new Callback<List<DatosDeEstadisticas>>() {
            @Override
            public void onResponse(Response<List<DatosDeEstadisticas>> response, Retrofit retrofit) {
                List<DatosDeEstadisticas> estadisticas = response.body();
                mostrarCaracteristicasPersonaje(estadisticas);
            }


            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Log.e("DuelosApp", t.getMessage());
            }
        });

    }

    private void mostrarCaracteristicasPersonaje( List<DatosDeEstadisticas> estadisticas) {
        mostrarEstadisticas(R.id.estadisticas_detail, estadisticas);
    }

    private void mostrarEstadisticas(@IdRes int id,List<DatosDeEstadisticas> datos){

        ListView gridview = (ListView) this.findViewById(id);
        gridview.setAdapter(new EstadisticasAdapter(this,datos));

    }



}
