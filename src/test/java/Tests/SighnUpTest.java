package Tests;

import Pages.HotelSearchhPage;
import Pages.LoggedUserPage;
import Pages.SignUpPage;
import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SighnUpTest extends BaseTest {


    @Test
    public void sighnUp() {

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
        Assert.assertEquals(loggedUserPage.getUserNameHeading(), "Hi, Peppa Pig");


    }

    @Test
    public void emptyFormTest() {
        HotelSearchhPage hotelSearchhPage = new HotelSearchhPage(driver);
        hotelSearchhPage.openSignUpForm();
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.SignUp();

        Assert.assertTrue(signUpPage.GetAlertsMessages().contains("The Email field is required."), "Email field");
        Assert.assertTrue(signUpPage.GetAlertsMessages().contains("The Password field is required."), "Password field");
        Assert.assertTrue(signUpPage.GetAlertsMessages().contains("The First name field is required."), "FirstName field");
        Assert.assertTrue(signUpPage.GetAlertsMessages().contains("The Last Name field is required."), "LastName field");


    }

    @Test
    public void EmailIsInvalidTest() {
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

    @Test
    public void UserSignUpTest() {
        HotelSearchhPage hotelSearchhPage = new HotelSearchhPage(driver);
        hotelSearchhPage.openSignUpForm();
        SignUpPage signUpPage = new SignUpPage(driver);
        User Wlad = new User();
        Wlad.setFirstName("Wlad");
        Wlad.setEmail("Wlasefd@mail.ru");
        Wlad.setPhone("343242442");
        Wlad.setPassword("Testowanie!!!!!!");
        Wlad.setLastName(" ");
        signUpPage.userSignUp(Wlad);
        Assert.assertTrue(signUpPage.GetAlertsMessages().contains("The Last Name field is required."));

    }

    @Test
    public void UserSignUpTestConstructor() {
        HotelSearchhPage hotelSearchhPage = new HotelSearchhPage(driver);
        hotelSearchhPage.openSignUpForm();
        SignUpPage signUpPage = new SignUpPage(driver);
        User Kirill = new User("Kirill", "Hodarkowski", "6666666", "92837sef437jhgjhggjgh", "kjksseffessefssfkj@mail.ru");
        signUpPage.userSignUp(Kirill);
        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);
        Assert.assertTrue(loggedUserPage.getUserNameHeading().contains(Kirill.getLastName()));

    }

}
