package Utils;

import com.aventstack.extentreports.model.Media;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.MediaEntityBuilder;


import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class SeleniumHelper {
    public static void waitForElementToExist(WebDriver driver, By locator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static void waitForElementToBeVisible(WebDriver driver, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForNotEmptyList(WebDriver driver, By locator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(browser -> browser.findElements(locator).size()>0);
    }

    public static Media getScreenshot(WebDriver driver) throws IOException {
        String path = takeScreenshot(driver);
        return MediaEntityBuilder.createScreenCaptureFromPath(path).build();
    }

    private static String takeScreenshot(WebDriver driver) throws IOException {
        int randomNumber = (int) (Math.random()*1000);
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File file = screenshot.getScreenshotAs(OutputType.FILE);
        String path = "src/test/resources/screenshots" + randomNumber +".png";
        FileUtils.copyFile(file,new File(path));
        return path;
    }
}
