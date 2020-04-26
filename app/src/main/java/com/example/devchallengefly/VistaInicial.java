package com.example.devchallengefly;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.graphics.drawable.AnimationDrawable;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.datatype.Duration;


/**
 * A simple {@link Fragment} subclass.
 */
public class VistaInicial extends Fragment {

    private Spinner spiner_origen, spinnr_destino;
    private EditText texto_cantidad;
    //Calendar calendario = Calendar.getInstance();
    private Button boton_buscar;

    private TextView txt_origen, txt_destino;

    //necesarias para el carrusel de imagenes
    AnimationDrawable rocketAnimation;
    private ImageView rocketImage;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista_inicial =  inflater.inflate(R.layout.fragment_vista_inicial, container, false);
        txt_origen = vista_inicial.findViewById(R.id.texto_origen);
        txt_destino = vista_inicial.findViewById(R.id.texto_destino);


        rocketImage = vista_inicial .findViewById(R.id.rocket_image);
        rocketImage.setBackgroundResource(R.drawable.animation);
        rocketAnimation =  (AnimationDrawable) rocketImage.getBackground();

        spiner_origen = vista_inicial.findViewById(R.id.spinner_origen);
        spinnr_destino = vista_inicial.findViewById(R.id.spinner_destino);
        texto_cantidad=vista_inicial.findViewById(R.id.texto_cant_personas);

        boton_buscar =  vista_inicial.findViewById(R.id.button);

        ArrayAdapter<CharSequence> adapter_vehiculo = ArrayAdapter.createFromResource(getActivity(),
                R.array.spinnerOrigen, android.R.layout.simple_spinner_item);
        adapter_vehiculo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner_origen.setAdapter(adapter_vehiculo);



        obetenerVistadelSegundoSpinner();

        boton_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ObtenerDatosDelViaje();
            }
        });

        return vista_inicial;
    }

    private void obetenerVistadelSegundoSpinner() {


        spiner_origen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, final int itemSeleccionado, long l) {

                if(itemSeleccionado==0){
                    txt_destino.setEnabled(false);
                    spinnr_destino.setEnabled(false);
                    Toast.makeText(getActivity(), "ELIJA UN DESTINO", Toast.LENGTH_LONG).show();

                }
                else if(itemSeleccionado==1){

                    ArrayAdapter<CharSequence> adapter_infraccion = ArrayAdapter.createFromResource(getActivity(),
                            R.array.spinnerDestinoSinEPA, android.R.layout.simple_spinner_item);
                    adapter_infraccion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnr_destino.setAdapter(adapter_infraccion);

                    txt_destino.setEnabled(true);
                    spinnr_destino.setEnabled(true);

                    Toast.makeText(getActivity(), "DESTINOS REMONEDADOS", Toast.LENGTH_LONG).show();
                }
                else if(itemSeleccionado ==2){
                    ArrayAdapter<CharSequence> adapter_infraccion2 = ArrayAdapter.createFromResource(getActivity(),
                            R.array.spinnerDestinoSinMDZ, android.R.layout.simple_spinner_item);
                    adapter_infraccion2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnr_destino.setAdapter(adapter_infraccion2);

                    txt_destino.setEnabled(true);
                    spinnr_destino.setEnabled(true);

                    Toast.makeText(getActivity(), "DESTINOS REMONEDADOS", Toast.LENGTH_LONG).show();
                }
                else if(itemSeleccionado==3){

                    ArrayAdapter<CharSequence> adapter_infraccion3 = ArrayAdapter.createFromResource(getActivity(),
                            R.array.spinnerDestinoSinCOR, android.R.layout.simple_spinner_item);
                    adapter_infraccion3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnr_destino.setAdapter(adapter_infraccion3);

                    txt_destino.setEnabled(true);
                    spinnr_destino.setEnabled(true);

                    Toast.makeText(getActivity(), "DESTINOS REMONEDADOS", Toast.LENGTH_LONG).show();
                }
                else if(itemSeleccionado==4){

                    ArrayAdapter<CharSequence> adapter_infraccion4 = ArrayAdapter.createFromResource(getActivity(),
                            R.array.spinnerDestinoSinBRC, android.R.layout.simple_spinner_item);
                    adapter_infraccion4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnr_destino.setAdapter(adapter_infraccion4);

                    txt_destino.setEnabled(true);
                    spinnr_destino.setEnabled(true);

                    Toast.makeText(getActivity(), "DESTINOS REMONEDADOS", Toast.LENGTH_LONG).show();

                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }


    @Override
    public void onStart() {
        super.onStart();
        rocketAnimation.start();
    }



    public void ObtenerDatosDelViaje() {

        // LeerArchivoJson(); // lectura de archivo jason
        String ciudad_origen = spiner_origen.getSelectedItem().toString().trim();
        String ciudad_destino = spinnr_destino.getSelectedItem().toString().trim();
        String cantidad_de_personas = texto_cantidad.getText().toString().trim();

        if( ciudad_origen.contains("CORDOBA") == true ){
          String  ciudad_origen_reemplazo = ciudad_origen.replace("CORDOBA", "COR");
          ciudad_origen = ciudad_origen_reemplazo;

        } if (ciudad_origen.contains("BUENOS AIRES") == true) {
            String ciudad_origen_reemplazo = ciudad_origen.replace("BUENOS AIRES", "EPA");
            ciudad_origen = ciudad_origen_reemplazo;

        } if (ciudad_origen.contains("MENDOZA") ==true) {
            String ciudad_origen_reemplazo = ciudad_origen.replace("MENDOZA", "MDZ");
            ciudad_origen = ciudad_origen_reemplazo;

        }if (ciudad_origen.contains("BARILOCHE") ==true){
            String ciudad_origen_reemplazo = ciudad_origen.replace("BARILOCHE", "BRC");
            ciudad_origen = ciudad_origen_reemplazo;
           }

        if( ciudad_destino.contains("CORDOBA") == true){
            String ciudad_destino_reemplazo = ciudad_destino.replace("CORDOBA", "COR");
            ciudad_destino = ciudad_destino_reemplazo;

        } if (ciudad_destino.contains("BUENOS AIRES") ==true){
            String ciudad_destino_reemplazo = ciudad_destino.replace("BUENOS AIRES", "EPA");
            ciudad_destino = ciudad_destino_reemplazo;}

         if (ciudad_destino.contains("MENDOZA") ==true) {
             String ciudad_destino_reemplazo = ciudad_destino.replace("MENDOZA", "MDZ");
             ciudad_destino = ciudad_destino_reemplazo;
         }
         if (ciudad_destino.contains("BARILOCHE") ==true){
            String  ciudad_destino_reemplazo = ciudad_destino.replace("BARILOCHE", "BRC");
            ciudad_destino = ciudad_destino_reemplazo;
           }


        VistaVueloDeIda vuelo_de_ida = new VistaVueloDeIda();
        Bundle enviar = new Bundle();

        enviar.putString("ciudad_de_origen",ciudad_origen);
        enviar.putString("ciudad_de_destino",ciudad_destino);
        enviar.putString("personas",cantidad_de_personas);

        if(ciudad_origen==null || ciudad_destino==null || cantidad_de_personas == null){
            Toast.makeText(getContext(), "Complete los datos!", Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(getContext(), "Se envio !" + enviar +"\n" +
                    ciudad_origen + "\n"+ciudad_destino, Toast.LENGTH_SHORT).show();
            vuelo_de_ida.setArguments(enviar);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedor_principal, vuelo_de_ida).addToBackStack(null).commit();
        }

    }


}



// codigo para tomar la fecha
/*  fecha_de_ida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(), date_ida, calendario
                        .get(Calendar.YEAR), calendario.get(Calendar.MONTH),
                        calendario.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        fecha_de_regreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(), date_regreso, calendario
                        .get(Calendar.YEAR), calendario.get(Calendar.MONTH),
                        calendario.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

     ********    AFUERA DE ONCREATE *******

      DatePickerDialog.OnDateSetListener date_ida = new DatePickerDialog.OnDateSetListener() {

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            calendario.set(Calendar.YEAR, year);
            calendario.set(Calendar.MONTH, monthOfYear);
            calendario.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            actualizarInputIda();
        }

    };


         @RequiresApi(api = Build.VERSION_CODES.N)
    private void actualizarInputIda() {
        String formatoDeFecha = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(formatoDeFecha, Locale.US);

        fecha_de_ida.setText(sdf.format(calendario.getTime()));
    }

    DatePickerDialog.OnDateSetListener date_regreso = new DatePickerDialog.OnDateSetListener() {

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            calendario.set(Calendar.YEAR, year);
            calendario.set(Calendar.MONTH, monthOfYear);
            calendario.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            actualizarInputRegreso();
        }

    };

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void actualizarInputRegreso() {
        String formatoDeFecha = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(formatoDeFecha, Locale.US);
        fecha_de_regreso.setText(sdf.format(calendario.getTime()));
    }

         */  //para seleccionar fecha



//String  cantidad_de_dinero = texto_precio.getText().toString().trim();
//String fechaIda =  fecha_de_ida.getText().toString().trim();
//String fechaRegreso = fecha_de_regreso.getText().toString().trim();

// mas codigo de repstirorio
/*
texto_precio= vista_inicial.findViewById(R.id.texto_precio);
        fecha_de_ida= vista_inicial.findViewById(R.id.fecha_ida);
        fecha_de_regreso= vista_inicial.findViewById(R.id.fecha_regreso);
*
*
* */

