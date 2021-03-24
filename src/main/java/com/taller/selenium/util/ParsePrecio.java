package com.taller.selenium.util;

public class ParsePrecio {

    public static int parsePrecioVuelo(String texto){
        return Integer.parseInt(texto.split(" ")[1].replace(",",""));
    }
    public static int parsePrecioVueloResumen(String texto){
        return Integer.parseInt(texto.split(" ")[2].replace(",",""));
    }


}
