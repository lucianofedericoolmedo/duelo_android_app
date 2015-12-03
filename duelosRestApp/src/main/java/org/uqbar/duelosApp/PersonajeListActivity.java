package org.uqbar.duelosApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.uqbar.duelo.domain.Personaje;



public class PersonajeListActivity extends AppCompatActivity
        implements PersonajeListFragment.Callbacks {

    private boolean isTablet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personaje_app_bar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (findViewById(R.id.personaje_detail_container) != null) {
            isTablet = true;
            ((PersonajeListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.personaje_list))
                    .setActivateOnItemClick(true);
        }

    }

    @Override
    public void onItemSelected(Personaje personaje) {
        if (isTablet) {
            Bundle arguments = new Bundle();
            arguments.putSerializable(PersonajeDetailFragment.ARG_ITEM_ID, personaje);
            PersonajeDetailFragment fragment = new PersonajeDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.personaje_detail_container, fragment)
                    .commit();

        }
        else {
            Intent detailIntent = new Intent(this, PersonajeDetailActivity.class);
            detailIntent.putExtra(PersonajeDetailFragment.ARG_ITEM_ID, personaje);
            startActivity(detailIntent);
        }
    }
}
