package com.example.devchallengefly;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class VistaconfirmarVuelo extends Fragment {


    public VistaconfirmarVuelo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista_confirmar_vuelo = inflater.inflate(R.layout.fragment_vistaconfirmar_vuelo, container, false);

        return vista_confirmar_vuelo;
    }

}
