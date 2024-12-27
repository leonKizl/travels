package Tests;

import Pages.HotelSearchhPage;
import Pages.LoggedUserPage;
import Pages.SignUpPage;
import org.example.WebDriverSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class SighnUpTest extends BaseTest{




    @Test
    public void sighnUp(){

        HotelSearchhPage hotelSearchhPage = new HotelSearchhPage(driver);
        hotelSearchhPage.openSignUpForm();
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstName("Peppa");
        signUpPage.setLastName("Pig");
        signUpPage.setPhone("6663628");
        String email = "Testowski" + (int) (Math.random() * 1000) + "@mail.ru";
        signUpPage.setEmail(email);
        signUpPage.setPassword("Testowanie!!!");
        signUpPage.setConfirmPassword("Testowanie!!!");
        signUpPage.SignUp();
        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);
        Assert.assertEquals(loggedUserPage.getUserNameHeading(),"Hi, Peppa Pig");


    }
    @Test
    public void emptyFormTest(){
        HotelSearchhPage hotelSearchhPage = new HotelSearchhPage(driver);
        hotelSearchhPage.openSignUpForm();
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.SignUp();

        Assert.assertTrue(signUpPage.GetAlertsMessages().contains("The Email field is required."),"Email field");
        Assert.assertTrue(signUpPage.GetAlertsMessages().contains("The Password field is required."),"Password field");
        Assert.assertTrue(signUpPage.GetAlertsMessages().contains("The First name field is required."),"FirstName field");
        Assert.assertTrue(signUpPage.GetAlertsMessages().contains("The Last Name field is required."),"LastName field");


    }
    @Test
    public void EmailIsInvalidTest(){
        HotelSearchhPage hotelSearchhPage = new HotelSearchhPage(driver);
        hotelSearchhPage.openSignUpForm();
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstName("Leon");
        signUpPage.setLastName("Testowski");
        signUpPage.setPhone("889899");
        signUpPage.setEmail("Email");
        signUpPage.setPassword("Testowanie!!!");
        signUpPage.setEmail("Testowanie!!!");
        signUpPage.SignUp();
        Assert.assertTrue(signUpPage.GetAlertsMessages().contains("The Email field must contain a valid email address."));
    }
}
