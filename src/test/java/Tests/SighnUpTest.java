package Tests;

import Pages.HotelSearchhPage;
import Pages.LoggedUserPage;
import Pages.SignUpPage;

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
        String email = "Testowski" + (int) (Math.random() * 1000) + "@mail.ru";
        LoggedUserPage loggedUserPage = new SignUpPage(driver)
                .setFirstName("Peppa")
                .setLastName("Pig")
                .setPhone("6663628")
                .setEmail(email)
                .setPassword("Testowanie!!!")
                .setConfirmPassword("Testowanie!!!")
                .SignUp();
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
        LoggedUserPage loggedUserPage = new SignUpPage(driver)
                .setFirstName("Leon")
                .setLastName("Testowski")
                .setPhone("889899")
                .setEmail("Email")
                .setPassword("Testowanie!!!")
                .setConfirmPassword("Testowanie!!!")
                .SignUp();
        Assert.assertTrue(signUpPage.GetAlertsMessages().contains("The Email field must contain a valid email address."));
    }


}
