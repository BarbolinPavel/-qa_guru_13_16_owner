package tests.config;

import org.aeonbits.owner.Config;
import java.net.URL;

@Config.Sources({
        "classpath:${host}.properties",
        "classpath:config/test.properties"
})

public interface WebDriverConfig extends Config{

    @Key("host")
    @DefaultValue("local")
    String getHost();

    @Key("baseUrl")
    @DefaultValue("https://github.com")
    String getBaseUrl();

    @Key("browser")
    @DefaultValue("CHROME")
    Browser getBrowser();

    @Key("versionBrowser")
    @DefaultValue("99")
    String getVersionBrowser();

    @Key("remoteBrowser")
    @DefaultValue("Chrome")
    String getRemoteBrowser();

    @Key("login")
    String login();

    @Key("password")
    String password();

    @Key("selenoidUrl")
    String selenoidUrl();
}
