package com.taller.selenium.steps;

import com.taller.selenium.interactions.JavaScriptClick;
import com.taller.selenium.ui.ListaVuelos;
import com.taller.selenium.ui.PopupsReserva;
import com.taller.selenium.ui.ResumenReserva;
import com.taller.selenium.util.ParsePrecio;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParametrizarReservaSteps {

    private ListaVuelos listaVuelosPage;
    private PopupsReserva popupsReservaPage;
    private ResumenReserva resumenReserva;
    private  WebDriver driver;

    public int elegirVueloMasBarato(){
        List<WebElement> vuelos = listaVuelosPage.preciosVuelos();
        Map<WebElement,Integer> vueloPrecio = vueloMenorPrecio(vuelos);
        WebElement el = (WebElement) vueloPrecio.keySet().toArray()[0];
        JavaScriptClick.click(driver, el);

        return vueloPrecio.get(el);
    }

    public void elegirComboMasBarato(){
        listaVuelosPage.continuarALaCarta().click();
        JavaScriptClick.click(driver, listaVuelosPage.botonContinuarPrincipal());

    }


    public void declinarPopups(WebDriver driver){
        WebElement popups= popupsReservaPage.noGracias();
        JavaScriptClick.click(driver,popups);

        popupsReservaPage.botonConfirmarNoGracias().click();

        popups= popupsReservaPage.noGracias();
        JavaScriptClick.click(driver,popups);

    }

    public void negarSwitches(){
        for (WebElement e: resumenReserva.switches()) {
            JavaScriptClick.click(driver, e);
        }
    }

    public int obtenerPrecioResumen(){
        WebElement resumenEle = resumenReserva.precioResumen();

        return ParsePrecio.parsePrecioVueloResumen(resumenEle.getText());
    }

    public void setDriver(WebDriver driver){
        this.driver = driver;
    }

    public void setResumenReserva(ResumenReserva resumenReserva) {
        this.resumenReserva = resumenReserva;
    }

    public void setListaVuelosPage(ListaVuelos listaVuelosPage) {
        this.listaVuelosPage = listaVuelosPage;
    }

    public void setPopupsReservaPage(PopupsReserva popupsReservaPage) {
        this.popupsReservaPage = popupsReservaPage;
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
