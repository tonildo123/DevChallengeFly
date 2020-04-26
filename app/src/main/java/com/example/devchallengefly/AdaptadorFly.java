package com.example.devchallengefly;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdaptadorFly extends BaseAdapter {


    Context context;
    ArrayList<FormatoDataSet> pasajes;
    LayoutInflater inflater;


    public AdaptadorFly(Context context, ArrayList<FormatoDataSet> pasajes) {
        this.context = context;
        this.pasajes = pasajes;

    }



    @Override
    public int getCount() {
        return pasajes.size();
    }

    @Override
    public Object getItem(int posicion) {
        return pasajes.get(posicion);
    }

    @Override
    public long getItemId(int posicion) {
        return posicion;
    }

    @Override
    public View getView(int posicion, View convertView, ViewGroup parent) {
        if (inflater== null)
        {
            inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        } if(convertView==null)
        {
            convertView = inflater.inflate(R.layout.modelo_pasaje, parent, false);

        }


        MiHolder holder=new MiHolder(convertView);


        holder.aqui_origin    .setText(pasajes.get(posicion).getOrigin());
        holder.aqui_destinity .setText(pasajes.get(posicion).getDestination());
        holder.aqui_availibity.setText(String.valueOf(pasajes.get(posicion).getAvailability()));
        holder.aqui_price     .setText(String.valueOf(pasajes.get(posicion).getPrice()));
        holder.aqui_date      .setText(pasajes.get(posicion).getFecha());

        return convertView;
    }

}
