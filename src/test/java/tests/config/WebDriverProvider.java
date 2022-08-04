package tests.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.function.Supplier;

import static com.codeborne.selenide.Browsers.CHROME;
import static com.codeborne.selenide.Browsers.FIREFOX;

public class WebDriverProvider implements Supplier<WebDriver> {
    private final WebDriverConfig config;

    public DesiredCapabilities capabilities = new DesiredCapabilities();

    public WebDriverProvider() {
        this.config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());
    }


    @Override
    public WebDriver get() {

        WebDriver driver = createDriver();
        driver.get(config.getBaseUrl());
        return driver;
    }

    public WebDriver createDriver() {

        if (System.getProperty("host","local").equals("remote")) {
            switch (config.getBrowser()) {
                case CHROME: {
                    RemoteWebDriver remoteChromeWebDriver = new RemoteWebDriver(config.getSelenoidUrl(), new ChromeOptions());
                    capabilities.setVersion(config.getVersionBrowser());
                    return remoteChromeWebDriver;
                }
                case FIREFOX: {
                    RemoteWebDriver remoteFirefoxWebDriver = new RemoteWebDriver(config.getSelenoidUrl(), new FirefoxOptions());
                    capabilities.setVersion(config.getVersionBrowser());
                    return remoteFirefoxWebDriver;
                }
                default: {
                    throw new RuntimeException("No such driver");
                }
            }
        } else {
            switch (config.getBrowser()) {
                case CHROME: {
                    WebDriverManager.chromedriver().setup();
                    capabilities.setVersion(config.getVersionBrowser());
                    return new ChromeDriver();
                }
                case FIREFOX: {
                    WebDriverManager.firefoxdriver().setup();
                    capabilities.setVersion(config.getVersionBrowser());
                    return new FirefoxDriver();
                }
                default: {
                    throw new RuntimeException("No such driver");
                }
            }
        }
    }
}
