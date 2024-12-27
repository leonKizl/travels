package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoggedUserPage {

    @FindBy(css = "h3.RTL")
    private WebElement userNameHeading;

    public String getUserNameHeading() {
        return userNameHeading.getText();
    }


    public LoggedUserPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
