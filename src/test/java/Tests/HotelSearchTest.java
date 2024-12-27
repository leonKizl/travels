package Tests;

import Pages.HotelSearchhPage;
import Pages.HotelsResultPage;
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

        HotelSearchhPage hotelSearchhPage = new HotelSearchhPage(driver);
        List<String> hotelNames = hotelSearchhPage.setCity("Dubai")
                        .setDates("27/12/2024","29/12/2024")
                        .setTravelers(2,2)
                        .performSearch().getHotelsNames();

        System.out.println(hotelNames.size());
        Assert.assertEquals(hotelNames.get(0), "Jumeirah Beach Hotel");
        Assert.assertEquals(hotelNames.get(1), "Oasis Beach Tower");
        Assert.assertEquals(hotelNames.get(2), "Rose Rayhaan Rotana");
        Assert.assertEquals(hotelNames.get(3), "Hyatt Regency Perth");

    }
    @Test
    public void searchHotelwithoutCity(){
        HotelSearchhPage hotelSearchhPage = new HotelSearchhPage(driver);
        hotelSearchhPage.setDates("28/12/2024","31/12/2024");
        hotelSearchhPage.setTravelers(0,3);
        hotelSearchhPage.performSearch();
        HotelsResultPage hotelsResultPage = new HotelsResultPage(driver);
        Assert.assertTrue(hotelsResultPage.noHotelsHeading.isDisplayed());
        Assert.assertEquals(hotelsResultPage.noHotelsHeading.getText(),"No Results Found");


    }
}
