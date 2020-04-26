package com.example.devchallengefly;

import org.json.JSONException;
import org.json.JSONObject;



public class FormatoDataSet {

    private String origin;
    private String destination;
    private double price;
    private int availability;
    private String fecha;


    public FormatoDataSet() {

    }

    public FormatoDataSet(String origin, String destination, double price, int availability, String fecha) {
        this.origin = origin;
        this.destination = destination;
        this.price = price;
        this.availability = availability;
        this.fecha = fecha;
    }


    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return  " ORIGEN"+"          " + origin +"      "+ "\n" +
                " DESTINO"+"         " + destination+"  "+ "\n" +
                " PRECIO"+"          " + price+"        "+ "\n" +
                " DISPONIBILIDAD"+"  " + availability +""+ "\n" +
                " FECHA"+"           " + fecha+"        "+ "\n" ;
    }


}
