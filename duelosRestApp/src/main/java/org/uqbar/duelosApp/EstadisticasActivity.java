package org.uqbar.duelosApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.uqbar.duelo.domain.Estadisticas;
import org.uqbar.duelo.domain.Personaje;
import org.uqbar.duelo.service.DueloService;
import org.uqbar.duelo.service.DueloServiceInstance;

import java.util.ArrayList;
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
        setContentView(R.layout.activity_estadisticas);

        idPersonaje = getIntent().getIntExtra("idPersonaje",0);
        nombrePersonaje = getIntent().getStringExtra("nombrePersonaje");
        setTitle(nombrePersonaje);
        obtenerEstadisticasPersonaje(idPersonaje);

    }


    private void obtenerEstadisticasPersonaje(int personajeID) {
        DueloService dueloService = DueloServiceInstance.createDueloService();
        Call<Estadisticas> personajeCall = dueloService.getEstadisticasPersonaje(String.valueOf(personajeID));

        personajeCall.enqueue(new Callback<Estadisticas>() {
            @Override
            public void onResponse(Response<Estadisticas> response, Retrofit retrofit) {
                mostrarCaracteristicasPersonaje(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Log.e("DuelosApp", t.getMessage());
            }
        });

    }

    private void mostrarCaracteristicasPersonaje( Estadisticas estadistica) {
        mostrarEstadisticas(R.id.estadisticas_detail, estadistica);
    }

    private void mostrarEstadisticas(@IdRes int id,Estadisticas estadistica){
        TextView textBox = (TextView) findViewById(R.id.textView);
        ListView listView = (ListView) this.findViewById(id);
        List<Object> valoresDeEstadistica = new ArrayList<Object>();
        valoresDeEstadistica.add(estadistica.getJugadas());
        valoresDeEstadistica.add(estadistica.getGanadas());
        valoresDeEstadistica.add(estadistica.getKills());
        valoresDeEstadistica.add(estadistica.getDeads());
        valoresDeEstadistica.add(estadistica.getAssists());
        valoresDeEstadistica.add(estadistica.getMejorUbicacion());
        valoresDeEstadistica.add(estadistica.getPuntaje());
        ArrayAdapter<Object> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, valoresDeEstadistica);
        listView.setAdapter(adapter);
    }



}
