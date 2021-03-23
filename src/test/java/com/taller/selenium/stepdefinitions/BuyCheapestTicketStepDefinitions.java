package com.taller.selenium.stepdefinitions;

import com.taller.selenium.steps.BookTicketSteps;
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


import javax.sound.midi.Soundbank;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class BuyCheapestTicketStepDefinitions {

    private WebDriver driver;
    private BookTicketSteps steps;
    private int precioVueloMasBarato;

    @Given("I am browsing the internet")
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

        steps = new BookTicketSteps();
        steps.setHome(new VivaAirHome(driver));
        steps.setListaVuelosPage(new ListaVuelos(driver));
        steps.setPopupsPage(new PopupsReserva(driver));
        steps.setResumenReserva(new ResumenReserva(driver));

    }


    @When("I Book the cheapest flight")
    public void iBookTheCheapestFlight() {

        steps.soloIda();
        steps.llenarOrigen("Medellin\n");
        steps.llenarDestino("Bogota\n");

        //Buscar como fecha del vuelo el día siguiente al de la ejecución
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String ida = dtf.format(now.plusDays(1));

        steps.elegirFechaIda(ida, driver);
        steps.buscarVuelos();

        precioVueloMasBarato = steps.elegirVueloMasBarato();
        steps.elegirComboMasBarato();
        steps.declinarPopups(driver);
        steps.negarSwitches();

    }
    @Then("The value of the flight should be the same at the end of the transaction")
    public void theValueOfTheFlightShouldBeTheSameAtTheEndOfTheTransaction() {
        int precioObtenido = steps.obtenerPrecioResumen();

        System.out.println(precioObtenido + " PRECIO");

        Assert.assertEquals(precioVueloMasBarato,precioObtenido);
    }

    @After
    public void cleanup(){
        driver.close();
    }



}
