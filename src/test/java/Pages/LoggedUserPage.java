package Pages;

import Utils.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoggedUserPage {

    @FindBy(css = "h3.RTL")
    private WebElement userNameHeading;

    private WebDriver driver;

    public String getUserNameHeading() {
        SeleniumHelper.waitForElementToBeVisible(driver,userNameHeading);
        return userNameHeading.getText();
    }


    public LoggedUserPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
