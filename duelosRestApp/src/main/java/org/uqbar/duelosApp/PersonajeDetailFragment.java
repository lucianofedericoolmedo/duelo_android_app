package org.uqbar.duelosApp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.uqbar.duelo.adapter.IconPersonajeAdapter;
import org.uqbar.duelo.domain.Caracteristicas;
import org.uqbar.duelo.domain.Personaje;
import org.uqbar.duelo.service.DueloService;
import org.uqbar.duelo.service.DueloServiceInstance;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A fragment representing a single Pelicula detail screen.
 * This fragment is either contained in a {@link PersonajeListActivity}
 * in two-pane mode (on tablets) or a {@link PersonajeDetailActivity}
 * on handsets.
 */
public class PersonajeDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Personaje personaje;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PersonajeDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            personaje = (Personaje) getArguments().get(ARG_ITEM_ID);
            obtenerCaracteristicasPersonaje(personaje);
           /* Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(personaje.getNombre());
            } else {
                activity.setTitle(personaje.getNombre());
            }*/
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.personaje_detail_fragment, container, false);

        if (personaje != null){
            ImageView imagePersonaje = ((ImageView) rootView.findViewById(R.id.imgPersonaje));
            imagePersonaje.setImageDrawable(getResources().getDrawable((new IconPersonajeAdapter().getIconoPersonaje(personaje))));
        }

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
//        super.onActivityCreated(savedInstanceState);
        Activity activity = this.getActivity();
        ((PersonajeDetailActivity) activity).setToolBarAndButton(personaje);
    }


    private void obtenerCaracteristicasPersonaje(Personaje personaje) {
        DueloService dueloService = DueloServiceInstance.createDueloService();

        Call<Caracteristicas> personajeCall = dueloService.getCaracteristicasPersonaje(
                String.valueOf(personaje.getId()));

        personajeCall.enqueue(new Callback<Caracteristicas>() {
            @Override
            public void onResponse(Response<Caracteristicas> response, Retrofit retrofit) {
                mostrarCaracteristicasPersonaje(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Log.e("DuelosApp", t.getMessage());
            }
        });

    }

    private void mostrarCaracteristicasPersonaje( Caracteristicas caracteristicas) {
        mostrarCaracteristicas(caracteristicas.getEspecialidades());
        mostrarCaracteristicas(caracteristicas.getDebilidades());
        //mostrarCaracteristica(caracteristicas.getUbicacionIdeal());
    }

    private void mostrarCaracteristicas(List<String> caracteristicas) {
        LinearLayout layout = (LinearLayout) this.getView().findViewById(R.id.caracteristicas_del_personaje);

        LayoutInflater inflater = LayoutInflater.from(getContext());
        for(String item : caracteristicas) {
            TextView view = (TextView) inflater.inflate(R.layout.caracteristica_row, layout, false);
            view.setText(item);
            layout.addView(view);
        }

//        LinearLayout layout = (LinearLayout) this.getView().findViewById(R.id.caracteristicas_del_personaje);
//        LinearLayout layout = (LinearLayout) this.getView().findViewById(R.id.caracteristicas_del_personaje);

    }

    /*
    Esto deberia andar mal, creo que se pisarian los datos, porque es un TextView !
     */
    private void mostrarCaracteristica(@IdRes int idImg ,@IdRes int id,String caracteristica){
        ((TextView) this.getView().findViewById(id)).setText(caracteristica);
    }


}
