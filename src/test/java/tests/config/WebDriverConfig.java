package tests.config;

import org.aeonbits.owner.Config;
import java.net.URL;

@Config.Sources({
        "classpath:${host}.properties"
})

public interface WebDriverConfig extends Config{

    @Key("baseUrl")
    @DefaultValue("https://github.com")
    String getBaseUrl();

    @Key("browser")
    @DefaultValue("chrome")
    String getBrowser();

    @Key("remoteUrl")
    URL getSelenoidUrl();

    @Key("versionBrowser")
    @DefaultValue("100.0")
    String getVersionBrowser();
}
