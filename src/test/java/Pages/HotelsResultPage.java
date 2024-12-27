package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.xml.xpath.XPath;
import java.util.List;
import java.util.stream.Collectors;

public class HotelsResultPage {

    @FindBy(xpath = "//h4/a/b")
    private List<WebElement> hotelsList;

    @FindBy(css = "h2.text-center")
    public WebElement noHotelsHeading;

    public HotelsResultPage(WebDriver driver){
        PageFactory.initElements(driver,this);

    }

    public List<String > getHotelsNames(){
        return hotelsList.stream().map(el -> el.getAttribute("textContent")).collect(Collectors.toList());
    }
}
