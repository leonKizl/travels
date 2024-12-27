package Pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class SignUpPage {

    private WebDriver driver;
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

    public List<String> GetAlertsMessages(){
        return alertsList.stream()
                .map(el ->el.getText())
                .collect(Collectors.toList());
    }

    public SignUpPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    public SignUpPage setFirstName(String firstname){
        firstNameInput.sendKeys(firstname);
        return this;
    }

    public SignUpPage setLastName(String lastName){
        lastnameInput.sendKeys(lastName);
        return this;
    }

    public SignUpPage setPhone(String phone){
        phoneInput.sendKeys(phone);
        return this;
    }

    public SignUpPage setEmail(String email){
        emailInput.sendKeys(email);
        return this;
    }

    public SignUpPage setPassword(String password){
        passwordInput.sendKeys(password);
        return this;
    }

    public SignUpPage setConfirmPassword(String password){
        confirmPasswordInput.sendKeys(password);
        return this;
    }

    public LoggedUserPage SignUp(){
       signUpButton.click();
       return new LoggedUserPage(driver);
    }

}
