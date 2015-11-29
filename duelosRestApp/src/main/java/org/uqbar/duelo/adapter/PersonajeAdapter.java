package org.uqbar.duelo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.uqbar.duelo.domain.Personaje;
import org.uqbar.duelosApp.R;

import java.util.List;

/**
 * Created by fernando on 27/10/15.
 */
public class PersonajeAdapter extends ArrayAdapter<Personaje> {

    public PersonajeAdapter(Context context, List<Personaje> personajes) {
        super(context, R.layout.personaje_row, personajes);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.personaje_row, parent, false);
        final Personaje personaje = getItem(position);

        TextView tvPersonaje = (TextView) rowView.findViewById(R.id.lblPersonaje);
        tvPersonaje.setText(personaje.toString());
        return rowView;
    }
}
