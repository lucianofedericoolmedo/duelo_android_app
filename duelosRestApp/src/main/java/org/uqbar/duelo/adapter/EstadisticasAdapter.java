package org.uqbar.duelo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.uqbar.duelo.domain.DatoDeEstadisticas;
import org.uqbar.duelo.domain.Personaje;
import org.uqbar.duelosApp.EstadisticasActivity;
import org.uqbar.duelosApp.R;

import java.util.List;

/**
 * Created by Owner on 01-Dec-15.
 */

/*

public class EstadisticasAdapter extends ArrayAdapter<DatoDeEstadisticas> {

    public EstadisticasAdapter(Context context, List<DatoDeEstadisticas> datos) {
        super(context, R.layout.activity_estadisticas_personaje);
    }

    @Override
    public DatoDeEstadisticas getItem(int position) {
        return getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.estadistica_row, parent, false);
        final DatoDeEstadisticas datos = getItem(position);

        TextView campo = (TextView) rowView.findViewById(R.id.clave_dato);
        campo.setText(datos.getNombreCampoDeEstadistica().toString());

        TextView valor = (TextView) rowView.findViewById(R.id.valor_dato);
        valor.setText(datos.getNombreCampoDeEstadistica().toString());
        return rowView;
    }
}
*/

public class EstadisticasAdapter extends BaseAdapter {


    private static List<DatoDeEstadisticas> datosDeEstadisticas;
    private final LayoutInflater myInflater;

    public EstadisticasAdapter(Context context, List<DatoDeEstadisticas> datosDeEstadisticas) {
        this.datosDeEstadisticas =  datosDeEstadisticas;
        myInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return datosDeEstadisticas.size();
    }

    @Override
    public Object getItem(int position) {
        return datosDeEstadisticas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        if( convertView == null){
            convertView = myInflater.inflate(R.layout.estadistica_row,null);
            holder = new MyViewHolder();
            holder.clave = (TextView) convertView.findViewById(R.id.clave_dato);
            holder.valor = (TextView) convertView.findViewById(R.id.valor_dato);
            convertView.setTag(holder);
        }
        else{
            holder = (MyViewHolder) convertView.getTag();
        }
        holder.clave.setText(datosDeEstadisticas.get(position).getNombreCampoDeEstadistica());
        holder.valor.setText(datosDeEstadisticas.get(position).getValorDelCampo());
        return convertView;
    }


    static class MyViewHolder{
        TextView clave;
        TextView valor;
    }
}