package Pages;

import Utils.SeleniumHelper;
import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class SignUpPage {
    @FindBy(name = "firstname")
    private WebElement firstNameInput;

    @FindBy(name = "lastname")
    private WebElement lastnameInput;

    @FindBy(name = "phone")
    private WebElement phoneInput;

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(name = "confirmpassword")
    private WebElement confirmPasswordInput;

    @FindBy(xpath = "//button[text()=' Sign Up']")
    private WebElement signUpButton;

    @FindBy(css = "div.alert-danger > p")
    private List<WebElement> alertsList;

    private WebDriver driver;



    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public List<String> GetAlertsMessages() {
        SeleniumHelper.waitForNotEmptyList(driver,By.cssSelector("div.alert-danger > p"));
        return alertsList.stream().map(el -> el.getText()).collect(Collectors.toList());
    }

    public void setFirstName(String firstname) {
        firstNameInput.sendKeys(firstname);
    }

    public void setLastName(String lastName) {
        lastnameInput.sendKeys(lastName);
    }

    public void setPhone(String phone) {
        phoneInput.sendKeys(phone);
    }

    public void setEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void setPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void setConfirmPassword(String password) {
        confirmPasswordInput.sendKeys(password);
    }

    public void SignUp() {
        signUpButton.click();
    }

    public void userSignUp(User user) {
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
        setPhone(user.getPhone());
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        setConfirmPassword(user.getPassword());
        SignUp();
    }
}
