package com.taller.selenium.steps;

import com.taller.selenium.interactions.JavaScriptClick;
import com.taller.selenium.ui.ListaVuelos;
import com.taller.selenium.ui.PopupsReserva;
import com.taller.selenium.ui.ResumenReserva;
import com.taller.selenium.ui.VivaAirHome;
import com.taller.selenium.util.FechaHelper;
import com.taller.selenium.util.ParsePrecio;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservarVueloSteps {


    private WebDriver driver;

    private VivaAirHome home;
    private ListaVuelos listaVuelosPage;
    private PopupsReserva popupsReservaPage;
    private ResumenReserva resumenReserva;

    private BuscarVueloSteps buscarVueloSteps;
    private ParametrizarReservaSteps parametrizarReservaSteps;


    public void buscarVueloParaManiana(String desde, String a){
        buscarVueloSteps.soloIda();
        buscarVueloSteps.llenarOrigen("Medellin\n");
        buscarVueloSteps.llenarDestino("Bogota\n");

        //Buscar como fecha del vuelo el día siguiente al de la ejecución
        String fechaIda = FechaHelper.fechaManiana();

        buscarVueloSteps.elegirFechaIda(fechaIda, driver);
        buscarVueloSteps.buscarVuelos();
    }

    public int elegirVueloMasBarato(){
        int precioVueloMasBarato = parametrizarReservaSteps.elegirVueloMasBarato();
        parametrizarReservaSteps.elegirComboMasBarato();
        parametrizarReservaSteps.declinarPopups(driver);
        parametrizarReservaSteps.negarSwitches();
        return precioVueloMasBarato;
    }




    public int obtenerPrecioResumen(){
        WebElement resumenEle = resumenReserva.precioResumen();

        return ParsePrecio.parsePrecioVueloResumen(resumenEle.getText());
    }

    public void setHome(VivaAirHome home){
        this.home = home;
    }

    public void setListaVuelosPage(ListaVuelos listaVuelosPage){
        this.listaVuelosPage = listaVuelosPage;
    }

    public void setPopupsPage(PopupsReserva popupsReservaPage){
        this.popupsReservaPage = popupsReservaPage;
    }

    public void setResumenReserva(ResumenReserva resumenReserva) {
        this.resumenReserva = resumenReserva;
    }

    public void setBuscarVueloSteps(BuscarVueloSteps buscarVueloSteps) {
        this.buscarVueloSteps = buscarVueloSteps;
    }

    public void setParametrizarReservaSteps(ParametrizarReservaSteps parametrizarReservaSteps) {
        this.parametrizarReservaSteps = parametrizarReservaSteps;
    }

    public void setDriver(WebDriver driver){
        this.driver = driver;
    }


    private Map<WebElement,Integer> vueloMenorPrecio(List<WebElement> vuelos){
        WebElement minVuelo = null;
        int minVal = Integer.MAX_VALUE;

        for (WebElement e:vuelos) {
            int precioActual = ParsePrecio.parsePrecioVuelo(e.getText());

            if(precioActual < minVal){
                minVal = precioActual;
                minVuelo = e;
            }
        }
        Map<WebElement,Integer> mapa = new HashMap<>();
        mapa.put(minVuelo, minVal);
        return mapa;
    }



}
