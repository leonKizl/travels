package Tests;

import org.example.WebDriverSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class HotelSearchTest extends BaseTest{
    @Test
    public void searchHotel(){
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
        Assert.assertEquals(hotelNames.get(0), "Jumeirah Beach Hotel");
        Assert.assertEquals(hotelNames.get(1), "Oasis Beach Tower");
        Assert.assertEquals(hotelNames.get(2), "Rose Rayhaan Rotana");
        Assert.assertEquals(hotelNames.get(3), "Hyatt Regency Perth");

    }
    @Test
    public void searchHotelwithoutCity(){

        driver.findElement(By.xpath("//input[@name = 'checkin']")).click();
        driver.findElements(By.xpath("//td[@class='day ' and text()='27']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);

        driver.findElement(By.cssSelector("input[name*='checkout']")).click();
        driver.findElements(By.xpath("//td[@class='day ' and text()='31']")).stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("adultPlusBtn")).click();
        driver.findElement(By.id("childPlusBtn")).click();
        driver.findElement(By.xpath("//button[text()=' Search']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("h2.text-center")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.cssSelector("h2.text-center")).getText(),"No Results Found");


    }
}
