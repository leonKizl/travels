package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class HotelSearch {
    @Test
    public void searchHotel(){
        WebDriver driver = WebDriverSelector.getDriver(WebDriverSelector.BrowserType.EDGE);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
        driver.findElement(By.linkText("Search by Hotel or City Name")).click();
        driver.findElement(By.xpath("//div[@id='select2-drop']//input")).sendKeys("Dubai");
        driver.findElement(By.xpath("//span[text() = 'Dubai']")).click();
        driver.findElement(By.xpath("//input[@name = 'checkin']")).sendKeys("25/12/2024");
        driver.findElement(By.cssSelector("input[name*='checkout']")).click();
        driver.findElements(By.xpath("//td[@class='day ' and text()='31']")).stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("adultPlusBtn")).click();
        driver.findElement(By.id("childPlusBtn")).click();
        driver.findElement(By.xpath("//button[text()=' Search']")).click();
        List<String> hotelNames =  driver.findElements(By.xpath("//h4/a/b")).stream()
                .map(el -> el.getAttribute("textContent"))
                .collect(Collectors.toList());
        System.out.println(hotelNames.size());
        hotelNames.forEach(el-> System.out.println(el));
        Assert.assertEquals("Jumeirah Beach Hotel",hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower",hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana",hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth",hotelNames.get(3));

    }
}