package tests.config;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {

    @BeforeEach
    public void startDriver() {
        driver = new WebDriverProvider().get();
    }

    Configuration.browserCapabilities = capabilities;
    Configuration.baseUrl = "https://demoqa.com";
    Configuration.browser = System.getProperty("browser","chrome");
    Configuration.browserVersion = System.getProperty("version","99");
    Configuration.browserSize = System.getProperty("size", "1920x1080");
    Configuration.remote = String.format("https://%s:%s@%s", config.login(), config.password(),
            config.selenoidUrl());*/

    @AfterEach
    public void stopDriver() {
        driver.quit();
    }
}
