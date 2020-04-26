package com.example.devchallengefly;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MiHolder {


    TextView aqui_origin, aqui_destinity, aqui_availibity, aqui_price, aqui_date;

    public  MiHolder(View itemView)
    {
        aqui_origin  = itemView.findViewById(R.id.aqui_origin);
        aqui_destinity    = itemView.findViewById(R.id.aqui_destinity);
        aqui_availibity     = itemView.findViewById(R.id.aqui_availability);
        aqui_price       = itemView.findViewById(R.id.aqui_price);
        aqui_date = itemView.findViewById(R.id.aqui_date);



    }
}
