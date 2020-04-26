package com.example.devchallengefly;


import android.app.ProgressDialog;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 */
public class VistaVueloDeIda extends Fragment {


    private ListView lista;
    private List<FormatoDataSet> lista_data_set= new ArrayList<FormatoDataSet>();
    private List<ModeloRepo> lista_de_rechazados= new ArrayList<ModeloRepo>();
    ArrayAdapter<FormatoDataSet> arrayAdapterDataSet;

    FormatoDataSet formato_seleccionado;

   // ArrayList<HashMap<String, String>> filtrar_viajes;
    private ProgressDialog  progressDialog;


    private TextView origin, destinnity, availability, price, date;
    private Button buy;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista_ida = inflater.inflate(R.layout.fragment_vista_vuelo_de_ida, container, false);
        lista = (ListView) vista_ida.findViewById(R.id.lista_modelo);
        origin = vista_ida.findViewById(R.id.txtGo2);
        destinnity = vista_ida.findViewById(R.id.txtBack2);
        availability = vista_ida.findViewById(R.id.txtAvailability2);
        price = vista_ida.findViewById(R.id.txtPrice2);
        date = vista_ida.findViewById(R.id.txtDate2);
        buy = vista_ida.findViewById(R.id.button_buy_ticket);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                formato_seleccionado = (FormatoDataSet) parent.getItemAtPosition(position);
                origin.setText(formato_seleccionado.getOrigin());
                destinnity.setText(formato_seleccionado.getDestination());
                availability.setText(String.valueOf(formato_seleccionado.getAvailability()));
                price.setText(String.valueOf(formato_seleccionado.getPrice()));
                date.setText(String.valueOf(formato_seleccionado.getFecha()));

            }
        });

        progressDialog = new ProgressDialog(getContext());
        ObetenerLista();


        return vista_ida;
    }

    private void ObetenerLista() {

        String city_origin=null;
        String city_destination=null;
        String people=null;

        Bundle data = this.getArguments();
            if(data != null) {
                progressDialog.setMessage("Realizando busqueda en linea...");
                progressDialog.show();
                city_origin = data.getString("ciudad_de_origen");
                city_destination = data.getString("ciudad_de_destino");
                people = data.getString("personas");
                Toast.makeText(getContext(), "se recibio datos!" + city_origin + "\n" +
                        city_destination + "\n" + people, Toast.LENGTH_SHORT).show();


            String json = null;
            try {
                InputStream is = getActivity().getAssets().open("dataset.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                json = new String(buffer, "UTF-8");
                JSONArray archivo_json_array = new JSONArray(json);



                for (int i=0; i< archivo_json_array.length(); i++){
                    FormatoDataSet formato = new FormatoDataSet();
                    ModeloRepo repo = new ModeloRepo();
                    JSONObject elementos_de_erray = archivo_json_array.getJSONObject(i);
                    String ciudad_o = elementos_de_erray.getString("origin");
                    String ciudad_d = elementos_de_erray.getString("destination");
                    String fecha2 = elementos_de_erray.getString("data");
                    Double precio2 = elementos_de_erray.getDouble("price");
                    int disponibilidad_2 = elementos_de_erray.getInt("availability");

                    if (elementos_de_erray.getString("origin").contains(city_origin)==true
                            && elementos_de_erray.getString("destination").contains(city_destination)==true
                            && elementos_de_erray.getInt("availability") > Integer.parseInt(people)){

                        formato.setOrigin(ciudad_o);
                        formato.setDestination(ciudad_d);
                        formato.setFecha(fecha2);
                        formato.setPrice(precio2);
                        formato.setAvailability(disponibilidad_2);
                        lista_data_set.add(formato);

                        } else {

                        repo.setOrigin(ciudad_o);
                        repo.setDestination(ciudad_d);
                        repo.setFecha(fecha2);
                        repo.setPrice(precio2);
                        repo.setAvailability(disponibilidad_2);
                        lista_de_rechazados.add(repo);
                        }

                    arrayAdapterDataSet = new ArrayAdapter<FormatoDataSet>(getContext(),
                            android.R.layout.simple_list_item_1, lista_data_set);
                    lista.setAdapter(arrayAdapterDataSet);
                }

                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Listado Actualizado" + "\nout", Toast.LENGTH_LONG).show();


            } catch (IOException ex) {
                Log.v("MainActivity", "Error: " + ex.getMessage());
                ex.printStackTrace();
                Toast.makeText(getActivity(),
                        "No se pudo Actualizar" + ex, Toast.LENGTH_LONG).show();

            } catch (JSONException e) {

                Log.v("MainActivity", "Error: " + e.getMessage());
                e.printStackTrace();
                Toast.makeText(getActivity(),
                        "No se pudo Actualizar" + e, Toast.LENGTH_LONG).show();
            }

            }

    }



}

////// codigo para obtener json local
/*
*  String json = null;
        try {
            InputStream is = getActivity().getAssets().open("dataset.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            JSONArray archivo_json_array = new JSONArray(json);

            for (int i =0 ; i< archivo_json_array.length();i++){

                JSONObject objetos_del_array = archivo_json_array.getJSONObject(i);


                String origen      = objetos_del_array.getString("origin");
                String destino     = objetos_del_array.getString("destination");
                String fecha       = objetos_del_array.getString("data") ;
                Double precio      = objetos_del_array.getDouble("price") ;
                int disponibilidad = objetos_del_array.getInt("availability");


                    FormatoDataSet data_set = new FormatoDataSet();
                    data_set.setOrigin(city_origin);
                    data_set.setDestination(city_destination);
                    data_set.setFecha(fecha);
                    data_set.setPrice(precio);
                    data_set.setAvailability(disponibilidad);
                    lista_data_set.add(data_set);
                    arrayAdapterDataSet = new ArrayAdapter<FormatoDataSet>(getContext(),
                            android.R.layout.simple_list_item_1, lista_data_set);
                    lista.setAdapter(arrayAdapterDataSet);


            }
            Toast.makeText(getActivity(),"Listado Actualizado", Toast.LENGTH_LONG).show();
        } catch (IOException ex) {
            Log.v("MainActivity", "Error: " + ex.getMessage());
            ex.printStackTrace();
            Toast.makeText(getActivity(),
                    "No se pudo Actualizar" + ex, Toast.LENGTH_LONG).show();

        } catch (JSONException e) {

            Log.v("MainActivity", "Error: " + e.getMessage());
            e.printStackTrace();
            Toast.makeText(getActivity(),
                    "No se pudo Actualizar" + e, Toast.LENGTH_LONG).show();
        }
    }*/

/////////////////// para hashmap


/*     HashMap<String, String> ofertas = new HashMap<>();

                    // adding each child node to HashMap key => value
                    ofertas.put("origin", city_origin);
                    ofertas.put("destination", city_destination);
                    ofertas.put("data", fecha);
                    ofertas.put("price", String.valueOf(precio));
                    ofertas.put("availability", String.valueOf(disponibilidad));
                    // adding ofertas to ofertas list
                    filtrar_viajes.add(ofertas);


                ListAdapter adapter = new SimpleAdapter(
                        getActivity(), filtrar_viajes,
                        R.layout.list_item, new String[]{"origin", "destination",
                        "data", "price", "availability"}, new int[]{R.id.tetv_origen,
                        R.id.txtv_destino, R.id.txtv_fecha, R.id.txtv_precio, R.id.txtv_disponibilidad});

                lista.setAdapter(adapter);*/
