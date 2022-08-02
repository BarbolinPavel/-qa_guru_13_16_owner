package tests.config;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.function.Supplier;

public class WebDriverProvider implements Supplier<WebDriver> {
    private final WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());

    public DesiredCapabilities capabilities = new DesiredCapabilities();

    @Override
    public WebDriver get() {
        //WebDriver host = createDriver();
        //host.get(config.getHost());
        WebDriver driver = createDriver();
        driver.get(config.getBaseUrl());
        return driver;
    }

    public WebDriver createDriver() {
        if (System.getProperty("host").equals("local")) {
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
        }else if (System.getProperty("host").equals("remote")){
            //DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);

            Configuration.browserCapabilities = capabilities;
            Configuration.baseUrl = config.getBaseUrl();
            Configuration.browser = config.getRemoteBrowser();
            Configuration.browserVersion = config.getVersionBrowser();
            Configuration.remote = String.format("https://%s:%s@%s", config.login(), config.password(),
                    config.selenoidUrl());
        }
        throw new RuntimeException("No such host");
    }
}
