package org.uqbar.duelosApp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import org.uqbar.duelo.adapter.PersonajeAdapter;
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
 * De  * A list fragment representing a list of Peliculas. This fragment
 * also supports tablet devices by allowing list items to be given an
 * 'activated' state upon selection. This helps indicate which item is
 * currently being viewed in a {@link PersonajeDetailFragment}.
 * <p/>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class PersonajeListFragment extends ListFragment implements View.OnClickListener {

    public static int MIN_BUSQUEDA_PERSONAJES = 2;

    /**
     * The serialization (saved instance state) Bundle key representing the
     * activated item position. Only used on tablets.
     */
    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    /**
     * The fragment's current callback object, which is notified of list item
     * clicks.
     */
    private Callbacks mCallbacks = sDummyCallbacks;

    /**
     * The current activated item position. Only used on tablets.
     */
    private int mActivatedPosition = ListView.INVALID_POSITION;

    private DueloService dueloService;

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    public interface Callbacks {
        void onItemSelected(Personaje personaje);
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    /**
     * A dummy implementation of the {@link Callbacks} interface that does
     * nothing. Used only when this fragment is not attached to an activity.
     */
    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(Personaje personaje) {
        }
    };

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PersonajeListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        dueloService = DueloServiceInstance.createDueloService();
        pedirPersonajes();
    }

    private void pedirPersonajes() {
        DueloService dueloService = DueloServiceInstance.createDueloService();

        Call<List<Personaje>> peliculaCall = dueloService.getPersonajes();

        peliculaCall.enqueue(new Callback<List<Personaje>>() {
            @Override
            public void onResponse(Response<List<Personaje>> response, Retrofit retrofit) {
                List<Personaje> personajes = response.body();
                    setListAdapter(new PersonajeAdapter(
                            getActivity(),
                            personajes));
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Log.e("DuelosApp", t.getMessage());
            }
        });
    }

    private void buscarPeliculas() {
        EditText campoBusqueda = (EditText) this.getView().findViewById(R.id.tituloContiene);
        String titulo = campoBusqueda.getText().toString();

        Call<List<Personaje>> peliculaCall = dueloService.buscarPersonajes(titulo);

        peliculaCall.enqueue(new Callback<List<Personaje>>() {
             @Override
             public void onResponse(Response<List<Personaje>> response, Retrofit retrofit) {
                 List<Personaje> personajes = new ArrayList<Personaje>();
                 if(!(response.body() == null)){
                     personajes.addAll( response.body());
                     setListAdapter(new PersonajeAdapter(
                             getActivity(),
                             personajes));
                 }
             }

             @Override
             public void onFailure(Throwable t) {
                 t.printStackTrace();
                 Log.e("DuelosApp", t.getMessage());
             }
         });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Restore the previously serialized activated item position.
        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }

        // Comportamiento del checkbox que indica si se busca a medida que se escribe, volo

        // Comportamiento del título de búsqueda
        EditText tituloContiene = (EditText) view.findViewById(R.id.tituloContiene);
        tituloContiene.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() >= MIN_BUSQUEDA_PERSONAJES) {
                    buscarPeliculas();
                }
            }
        });

        ((ImageButton) view.findViewById(R.id.btnBuscar)).setOnClickListener(this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        // Reset the active callbacks interface to the dummy implementation.
        mCallbacks = sDummyCallbacks;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        Personaje personaje = (Personaje) listView.getAdapter().getItem(position);
        Toast.makeText(getContext(), personaje.getNombre(), Toast.LENGTH_LONG).show();

        mCallbacks.onItemSelected(personaje);


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            // Serialize and persist the activated item position.
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    /**
     * Turns on activate-on-click mode. When this mode is on, list items will be
     * given the 'activated' state when touched.
     */
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        getListView().setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.personaje_list_fragment, null, false);
    }

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }

        mActivatedPosition = position;
    }

    @Override
    public void onClick(View v) {
        buscarPeliculas();
    }

}
