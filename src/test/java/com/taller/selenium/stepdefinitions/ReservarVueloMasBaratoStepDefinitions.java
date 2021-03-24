package com.taller.selenium.stepdefinitions;

import com.taller.selenium.steps.BuscarVueloSteps;
import com.taller.selenium.steps.ParametrizarReservaSteps;
import com.taller.selenium.steps.ReservarVueloSteps;
import com.taller.selenium.ui.ListaVuelos;
import com.taller.selenium.ui.PopupsReserva;
import com.taller.selenium.ui.ResumenReserva;
import com.taller.selenium.ui.VivaAirHome;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.util.concurrent.TimeUnit;

public class ReservarVueloMasBaratoStepDefinitions {

    private WebDriver driver;
    private ReservarVueloSteps steps;
    private int precioVueloMasBarato;

    @Given("Estoy navegando por el internet")
    public void iAmBrowsingTheInternet() {


        System.setProperty("web.chrome.driver", "chromedriver.exe");
        String url = "https://www.vivaair.com/co/es";


        ChromeOptions options = new ChromeOptions();

        options.addArguments("--lang=es");
        options.addArguments("--disable-popup-blocking");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

        driver.get(url);

        steps = new ReservarVueloSteps();

        //Sería más conveniente hacer una factory pero como serenity se encargará del manejo de dependencias lo omití

        BuscarVueloSteps buscarVueloSteps = new BuscarVueloSteps();
        buscarVueloSteps.setHome(new VivaAirHome(driver));

        ParametrizarReservaSteps parametrizarReservaSteps = new ParametrizarReservaSteps();

        parametrizarReservaSteps.setDriver(driver);
        parametrizarReservaSteps.setPopupsReservaPage(new PopupsReserva(driver));
        parametrizarReservaSteps.setListaVuelosPage(new ListaVuelos(driver));
        parametrizarReservaSteps.setResumenReserva(new ResumenReserva(driver));

        steps.setParametrizarReservaSteps(parametrizarReservaSteps);
        steps.setBuscarVueloSteps(buscarVueloSteps);

        steps.setHome(new VivaAirHome(driver));
        steps.setListaVuelosPage(new ListaVuelos(driver));
        steps.setPopupsPage(new PopupsReserva(driver));
        steps.setResumenReserva(new ResumenReserva(driver));
        steps.setDriver(driver);

    }


    @When("Reservo el vuelo mas barato entre Bogota y Medellin")
    public void reservoElVueloMasBaratoEntreBogotaYMedellin() {

        steps.buscarVueloParaManiana("Medellin\n", "Bogota\n");
        precioVueloMasBarato = steps.elegirVueloMasBarato();

    }
    @Then("El valor del vuelo deberia ser el mismo al final de la transaccion")
    public void elValorDelVueloDeberaaSerElMismoAlFinalDeLaTransaccian() {
        int precioObtenido = steps.obtenerPrecioResumen();

        System.out.println(precioObtenido + " PRECIO");

        Assert.assertEquals(precioVueloMasBarato,precioObtenido);
    }

    @After
    public void cleanup(){
        //driver.close();
    }



}
