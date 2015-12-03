package org.uqbar.duelo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.uqbar.duelo.domain.DatosDeEstadisticas;
import org.uqbar.duelosApp.R;

import java.util.List;

/**
 * Created by Owner on 01-Dec-15.
 */


public class EstadisticasAdapter extends BaseAdapter {


    private static List<DatosDeEstadisticas> datosDeEstadisticas;
    private final LayoutInflater myInflater;

    public EstadisticasAdapter(Context context, List<DatosDeEstadisticas> datosDeEstadisticas) {
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
        holder.clave.setText(datosDeEstadisticas.get(position).getNombreDelCampo());
        holder.valor.setText(datosDeEstadisticas.get(position).getValorDelCampo());
        return convertView;
    }


    static class MyViewHolder{
        TextView clave;
        TextView valor;
    }
}