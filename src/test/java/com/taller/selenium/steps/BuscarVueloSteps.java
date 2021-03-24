package com.taller.selenium.steps;

import com.taller.selenium.interactions.JavaScriptClick;
import com.taller.selenium.ui.VivaAirHome;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BuscarVueloSteps {

    private VivaAirHome home;

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
        JavaScriptClick.click(driver, botonFecha);
    }


    public void buscarVuelos(){
        home.botonBuscarVuelos().click();
    }

    public void setHome(VivaAirHome home) {
        this.home = home;
    }
}
