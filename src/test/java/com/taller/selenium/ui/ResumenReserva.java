package com.taller.selenium.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ResumenReserva {

    private WebDriver driver;
    public ResumenReserva(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> switches(){
        return driver.findElements(By.xpath("//label[@class='switch']"));
    }

    public WebElement precioResumen(){
        return driver.findElement(By.xpath("//span[@class='price']"));
    }

    public WebElement botonContinuar(){
        return  new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[span[contains(text(),'Continuar')]]")));
    }

}
