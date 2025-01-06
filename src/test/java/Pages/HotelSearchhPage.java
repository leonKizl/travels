package Pages;

import Utils.SeleniumHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

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

    @FindBy(xpath = "//li[@id='li_myaccount']/a")
    private List<WebElement> myAccountLink;

    @FindBy(xpath = "//a[text()='  Sign Up']")
    private List<WebElement> signUpLink;

    private static final Logger logger = LogManager.getLogger();

    public HotelSearchhPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void setCity(String cityname) {
        logger.info("Setting city " + cityname);
        searchHotelSpan.click();
        searchHotelInput.sendKeys(cityname);
        StringBuilder xpath = new StringBuilder("//span[text() = '" + cityname + "']");
        String corXpath = xpath.toString();
        SeleniumHelper.waitForElementToExist(driver,By.xpath(corXpath));
        driver.findElement(By.xpath(xpath.toString())).click();
        logger.info("Setting city done");

    }

    public void setDates(String checkin, String checkout) {
        logger.info("Setting checkin data (" + checkin + ") and checkout (" + checkout + ")");
        checkinInput.sendKeys(checkin);
        checkoutInput.sendKeys(checkout);
        logger.info("Setting dates done");
    }

    public void setTravelers(int numbersOfAdultsToAdd, int numbersOfChildToAdd) {
        logger.info("Setting travelers - Adults : " +numbersOfAdultsToAdd +"; Children : "+ numbersOfChildToAdd);
        travellersInput.click();
        SeleniumHelper.waitForElementToBeVisible(driver,adultPlusBtn);
        for (int i = 0; i < numbersOfAdultsToAdd; i++) {
            adultPlusBtn.click();
        }
        SeleniumHelper.waitForElementToBeVisible(driver,childPlusBtn);
        for (int i = 0; i < numbersOfChildToAdd; i++) {
            childPlusBtn.click();
        }
        logger.info("Setting travelers done");
    }

    public void performSearch() {
        logger.info("Search perform");
        searchButton.click();
        logger.info("Search done");
    }

    public void openSignUpForm() {
        logger.info("Open sign up form");
        myAccountLink.stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        signUpLink.stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        logger.info("Sign up form opened");
    }

}
