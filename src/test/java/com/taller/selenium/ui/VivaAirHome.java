package com.taller.selenium.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class VivaAirHome {

    private WebDriver driver;

    public  VivaAirHome(WebDriver driver){
        this.driver = driver;
    }

    public WebElement checkBoxSoloIda(){
        return driver.findElement(By.xpath("//span[contains(text(),'Solo ida')]"));
    }

    public WebElement origen(){
        return driver.findElement(By.xpath("//input[@id='criteria-airport-select-departure-input']"));
    }


    public WebElement destino(){
        return driver.findElement(By.xpath("//input[@id='criteria-airport-select-destination-input']"));
    }

    public WebElement calendarioIda(){
        return driver.findElement(By.id("criteria-dates-from"));
    }

    public WebElement fechaIda(String fecha){
        return driver.findElement(By.xpath(String.format("//button[@date='%s']", fecha)));
    }

    public WebElement botonBuscarVuelos(){
        return driver.findElement(By.xpath("//button[@id='criteria-search-button-desktop']"));
    }



}
