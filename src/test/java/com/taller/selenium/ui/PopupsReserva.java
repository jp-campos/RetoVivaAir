package com.taller.selenium.ui;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PopupsReserva {

    private WebDriver driver;
    public PopupsReserva(WebDriver driver){
        this.driver= driver;
    }

    public WebElement noGracias(){
        return new WebDriverWait(driver,15)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath( "//span[contains(text(),'No, gracias')]")));
    }

    public WebElement botonConfirmarNoGracias(){
        return driver.findElement(By.xpath("//div[@class='vue-dialog-buttons']/button[1]"));
    }

    public WebElement botonContinuar(){
        return  new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[span[contains(text(),'Continuar')]]")));
    }
}
