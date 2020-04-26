package com.example.devchallengefly;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class VistaVueloDeRegreso extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista_de_regreso = inflater.inflate(R.layout.fragment_vista_vuelo_de_regreso, container, false);




        return vista_de_regreso;
    }

}
