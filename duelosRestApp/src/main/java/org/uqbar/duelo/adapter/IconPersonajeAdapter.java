package org.uqbar.duelo.adapter;

import android.app.Activity;

import org.uqbar.duelo.domain.Personaje;
import org.uqbar.duelosApp.R;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fernando on 03/11/15.
 */
public class IconPersonajeAdapter {

    static Map<String, Integer> mapPersonajes;

    private Map<String, Integer> getMapImages() {
        if (mapPersonajes == null) {
            mapPersonajes = new HashMap<String, Integer>();
            mapPersonajes.put("Amumu",R.drawable.amumu);
            mapPersonajes.put("Ahri",R.drawable.ahri);
            mapPersonajes.put("WitchDoctor",R.drawable.witchdoctor);
            mapPersonajes.put("Viper",R.drawable.viper);
            mapPersonajes.put("Pudge",R.drawable.pudge);
            mapPersonajes.put("Olaf",R.drawable.olaf);
        }
        return mapPersonajes;
    }

    public int getIconoPersonaje(Personaje personaje) {
        //return context.getResources().getIdentifier(personaje.getImage(),"drawable",context.getPackageName());
        Integer result = getMapImages().get(personaje.getNombre());

        return result.intValue();
    }

    public int getIconoPersonaje(Activity context,Personaje personaje) {
        return context.getResources().getIdentifier(personaje.getImage(),"drawable",context.getPackageName());
        //Integer result = getMapImages().get(personaje.getNombre());

        //return result.intValue();
    }

}
