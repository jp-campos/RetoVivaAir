package com.taller.selenium.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ListaVuelos {

    private WebDriver driver;

    public ListaVuelos(WebDriver driver){
        this.driver = driver;
    }

    public List<WebElement> preciosVuelos(){
        return driver.findElements(By.xpath("//div[@class='from-price']"));
    }

    public WebElement continuarALaCarta(){
        return driver.findElement(By.xpath("//div[contains(text(),'Comprar A la carta')]"));
    }

    public WebElement botonContinuarPrincipal(){
        return driver.findElement(By.xpath("//span[contains(text(),'Continuar')]"));
    }

}
