package Tests;

import Pages.HotelSearchhPage;
import Pages.HotelsResultPage;
import Utils.SeleniumHelper;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class HotelSearchTest extends BaseTest {
    @Test
    public void searchHotel() throws IOException {
        ExtentTest test = extentReports.createTest("Search Hotel Test");

        HotelSearchhPage hotelSearchhPage = new HotelSearchhPage(driver);
        hotelSearchhPage.setCity("Dubai");
        test.log(Status.PASS,"Setting city done", SeleniumHelper.getScreenshot(driver));
        hotelSearchhPage.setDates("27/12/2024", "29/12/2024");
        test.log(Status.PASS,"Setting dates done",SeleniumHelper.getScreenshot(driver));
        hotelSearchhPage.setTravelers(2, 2);
        test.log(Status.PASS,"Setting travelers done",SeleniumHelper.getScreenshot(driver));
        hotelSearchhPage.performSearch();
        test.log(Status.PASS,"Performing Search done",SeleniumHelper.getScreenshot(driver));
        HotelsResultPage hotelsResultPage = new HotelsResultPage(driver);
        List<String> hotelNames = hotelsResultPage.getHotelsNames();
        Assert.assertEquals(hotelNames.get(0), "Jumeirah Beach Hotel");
        Assert.assertEquals(hotelNames.get(1), "Oasis Beach Tower");
        Assert.assertEquals(hotelNames.get(2), "Rose Rayhaan Rotana");
        Assert.assertEquals(hotelNames.get(3), "Hyatt Regency Perth");
        test.log(Status.PASS,"Assertions passed");
        test.log(Status.PASS,"Screenshot", SeleniumHelper.getScreenshot(driver));

    }

    @Test
    public void searchHotelwithoutCity() throws IOException {
        HotelSearchhPage hotelSearchhPage = new HotelSearchhPage(driver);
        ExtentTest test = extentReports.createTest("Search Hotel without entering city");
        hotelSearchhPage.setDates("28/12/2024", "31/12/2024");
        test.log(Status.PASS,"Setting dates", SeleniumHelper.getScreenshot(driver));
        hotelSearchhPage.setTravelers(0, 3);
        test.log(Status.PASS,"Setting travelers", SeleniumHelper.getScreenshot(driver));
        hotelSearchhPage.performSearch();
        test.log(Status.PASS,"Performing Search Done", SeleniumHelper.getScreenshot(driver));
        HotelsResultPage hotelsResultPage = new HotelsResultPage(driver);
        Assert.assertTrue(hotelsResultPage.noHotelsHeading.isDisplayed());
        Assert.assertEquals(hotelsResultPage.noHotelsHeading.getText(), "No Results Found");
        test.log(Status.PASS,"Assertions done", SeleniumHelper.getScreenshot(driver));


    }
}
