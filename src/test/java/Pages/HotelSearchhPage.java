package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HotelSearchhPage {
    private WebDriver driver;

    @FindBy(linkText = "Search by Hotel or City Name")
    private WebElement searchHotelSpan;

    @FindBy(xpath = "//div[@id='select2-drop']//input")
    private WebElement searchHotelInput;

    @FindBy(name = "checkin")
    private WebElement checkinInput;

    @FindBy(name = "checkout")
    private WebElement checkoutInput;

    @FindBy(id = "travellersInput")
    private WebElement travellersInput;

    @FindBy(id = "adultPlusBtn")
    private WebElement adultPlusBtn;

    @FindBy(id = "childPlusBtn")
    private WebElement childPlusBtn;

    @FindBy(xpath = "//button[text()=' Search']")
    private WebElement searchButton;

    public HotelSearchhPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }


    public void setCity(String cityname){
        searchHotelSpan.click();
        searchHotelInput.sendKeys(cityname);
        StringBuilder xpath = new StringBuilder("//span[text() = '" + cityname + "']");
        driver.findElement(By.xpath(xpath.toString())).click();

    }

    public void setDates(String checkin,String checkout){
       checkinInput.sendKeys(checkin);
       checkoutInput.sendKeys(checkout);
    }

    public void setTravelers(int numbersOfAdultsToAdd,int numbersOfChildToAdd){
        travellersInput.click();
        for (int i = 0; i < numbersOfAdultsToAdd; i++ ){
            adultPlusBtn.click();
        }
        for (int i = 0; i < numbersOfChildToAdd; i++){
            childPlusBtn.click();
        }
    }

    public void performSearch(){
        searchButton.click();
    }
}
