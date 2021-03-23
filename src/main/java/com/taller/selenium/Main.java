package com.taller.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Main {

        public static WebDriver driver;

    public static void main(String[] args){
        try{
            System.setProperty("web.chrome.driver", "chromedriver.exe");
            String ambiente = System.getenv("test");


            System.out.println(ambiente);

            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://www.google.com.co");
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


            WebElement searchBar = driver.findElement(By.xpath("//input[@name=\"q\"]"));
            searchBar.sendKeys("Medellín\n");

            WebElement wiki = driver.findElement(By.xpath("//a[@href=\"https://en.wikipedia.org/wiki/Medell%C3%ADn\"]"));
            wiki.click();

            WebElement heading = driver.findElement(By.className("firstHeading"));

            String text = heading.getText();

            System.out.println(text);


            if(text.equalsIgnoreCase("Medellín")){
                System.out.println("Es igual a Medellín");

            }else{
                System.out.println("No es igual a Medellín");
            }



            WebDriverWait wait = new WebDriverWait(driver,20);

            WebElement searchWiki = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name=\"search\"]")));
            searchWiki.sendKeys("Esto es una prueba");

            Thread.sleep(3000);

            searchWiki.clear();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            driver.quit();

        }

    }

}
