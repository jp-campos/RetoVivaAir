package com.taller.selenium.steps;

import com.taller.selenium.ui.ListaVuelos;
import com.taller.selenium.ui.PopupsReserva;
import com.taller.selenium.ui.ResumenReserva;
import com.taller.selenium.ui.VivaAirHome;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

public class BookTicketSteps {



    private VivaAirHome home;
    private ListaVuelos listaVuelosPage;
    private PopupsReserva popupsReservaPage;
    private ResumenReserva resumenReserva;

    public void soloIda(){
        WebElement el = home.checkBoxSoloIda();
        el.click();
    }

    public void llenarOrigen(String origen){
        WebElement fieldOrigen = home.origen();
        fieldOrigen.clear();
        fieldOrigen.sendKeys(origen);
    }

    public void llenarDestino(String destino){
        WebElement fieldDestino = home.destino();
        fieldDestino.clear();
        fieldDestino.sendKeys(destino);
    }

    public void elegirFechaIda(String fecha, WebDriver driver){
        WebElement calendario = home.calendarioIda();
        calendario.click();

        WebElement botonFecha = home.fechaIda(fecha);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", botonFecha);
    }


    public void buscarVuelos(){
        home.botonBuscarVuelos().click();
    }


    public int elegirVueloMasBarato(){
        List<WebElement> vuelos = listaVuelosPage.preciosVuelos();
        Map<WebElement,Integer> vueloPrecio = vueloMenorPrecio(vuelos);
        WebElement el = (WebElement) vueloPrecio.keySet().toArray()[0];
        el.click();

        return vueloPrecio.get(el);
    }

    public void elegirComboMasBarato(){
       listaVuelosPage.continuarALaCarta().click();
       listaVuelosPage.botonContinuarPrincipal().click();
    }


    public void declinarPopups(WebDriver driver){
        WebElement popups= popupsReservaPage.noGracias();
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", popups);
        executor.executeScript("arguments[0].click();",  popupsReservaPage.botonConfirmarNoGracias());

        popups= popupsReservaPage.noGracias();
        executor.executeScript("arguments[0].click();", popups);
    }

    public void negarSwitches(){
        for (WebElement e: resumenReserva.switches()) {
            e.click();
        }
    }

    public int obtenerPrecioResumen(){
        WebElement resumenEle = resumenReserva.precioResumen();

        return parsePrecioVueloResumen(resumenEle.getText());
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

    private Map<WebElement,Integer> vueloMenorPrecio(List<WebElement> vuelos){
        WebElement minVuelo = null;
        int minVal = Integer.MAX_VALUE;

        for (WebElement e:vuelos) {
            int precioActual = parsePrecioVuelo(e.getText());

            if(precioActual < minVal){
                minVal = precioActual;
                minVuelo = e;
            }
        }
        Map<WebElement,Integer> mapa = new HashMap<>();
        mapa.put(minVuelo, minVal);
        return mapa;
    }


    private int parsePrecioVuelo(String texto){
        return Integer.parseInt(texto.split(" ")[1].replace(",",""));
    }

    private int parsePrecioVueloResumen(String texto){
        return Integer.parseInt(texto.split(" ")[2].replace(",",""));
    }


}
