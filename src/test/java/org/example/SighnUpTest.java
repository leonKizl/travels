package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class SighnUpTest {
    @Test
    public void sighnUp(){
        WebDriver driver = WebDriverSelector.getDriver(WebDriverSelector.BrowserType.CHROME);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
        driver.findElements(By.xpath("//li[@id='li_myaccount']/a")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        driver.findElement(By.name("firstname")).sendKeys("Leon");
        String lastName = "Testowski";
        driver.findElement(By.name("lastname")).sendKeys(lastName);
        driver.findElement(By.name("phone")).sendKeys("6663628");
        String email = "Testowski" + (int) (Math.random() * 1000) + "@mail.ru";
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("Testowanie!!!");
        driver.findElement(By.name("confirmpassword")).sendKeys("Testowanie!!!");
        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();
        WebElement heading = driver.findElement(By.cssSelector("h3.RTL"));
        Assert.assertTrue(heading.isDisplayed(),"Heading is displaying");
        Assert.assertTrue(heading.getText().contains(lastName));
        Assert.assertEquals(heading.getText(),"Hi, Leon Testowski");


    }
}
