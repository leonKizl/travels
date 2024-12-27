package Utils;

import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    public enum BrowserType {
        CHROME, FIREFOX, EDGE
    }

    public static WebDriver getDriver(BrowserType browser) {
        String driverPath = System.getProperty("webdriver.driver.path", "C:\\Users\\leoni\\OneDrive\\Рабочий стол\\instalators\\drivers\\");
        switch (browser) {
            case CHROME:
                setDriverPath("webdriver.chrome.driver", driverPath + "chromedriver.exe");
                return new ChromeDriver(getChromeOptions());
            case FIREFOX:
                setDriverPath("webdriver.gecko.driver", driverPath + "geckodriver.exe");
                return new FirefoxDriver(getFirefoxOptions());
            case EDGE:
                setDriverPath("webdriver.edge.driver", driverPath + "msedgedriver.exe");
                return new EdgeDriver(getEdgeOptions());
            default:
                throw new InvalidArgumentException("Unsupported browser: " + browser);
        }
    }

    private static void setDriverPath(String driverName, String driverPath) {
        System.setProperty(driverName, driverPath);
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        return options;
    }

    private static FirefoxOptions getFirefoxOptions() {
        return new FirefoxOptions();
    }

    private static EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--disable-features=IsolateOrigins,site-per-process", "--remote-allow-origins=*");
        return options;
    }
}
