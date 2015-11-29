package org.uqbar.duelosApp;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.MenuItem;
import android.widget.ImageView;

import org.uqbar.duelo.adapter.IconPersonajeAdapter;
import org.uqbar.duelo.domain.Personaje;

/**
 * An activity representing a single Pelicula detail screen. This
 * activity is only used on handset devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link PersonajeListActivity}.
 * <p/>
 * This activity is mostly just a 'shell' activity containing nothing
 * more than a {@link PersonajeDetailFragment}.
 */
public class PersonajeDetailActivity extends AppCompatActivity {

    Toolbar toolbar;
    Personaje personaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personaje_detail);
        toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EstadisticasActivity.class);
                //Persopersonaje = (Personaje) getIntent().getSerializableExtra(PersonajeDetailFragment.ARG_ITEM_ID);
                //intent.putExtra("personaje",personaje);
                intent.putExtra("idPersonaje", personaje.getId());
                intent.putExtra("nombrePersonaje",personaje.getNombre());
                startActivity(intent);
            }
        });

        // Show the Up button in the action bar.
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();

            arguments.putSerializable(PersonajeDetailFragment.ARG_ITEM_ID,
                    getIntent().getSerializableExtra(PersonajeDetailFragment.ARG_ITEM_ID));
            PersonajeDetailFragment fragment = new PersonajeDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.personaje_detail_container, fragment)
                    .commit();

        }
    }

    public void setToolBarAndButton(Personaje personaje){
         this.personaje = personaje;
         getSupportActionBar().setTitle(this.personaje.toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, PersonajeListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
