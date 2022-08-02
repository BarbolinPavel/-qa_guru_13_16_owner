import com.codeborne.selenide.Condition;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import tests.config.WebDriverConfig;
import tests.config.WebDriverProvider;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutoTest extends WebDriverProvider {

//    @Test
//    void testAuto(){
//        $(".Index__title-h1").shouldHave(Condition.text("Легковые автомобили"));
//    }

    private WebDriver driver;

    @BeforeEach
    public void startDriver() {
        driver = new WebDriverProvider().get();
    }

    @Test
    public void testGithub() {
        String title = driver.getTitle();
        assertEquals("GitHub: Where the world builds software · GitHub", title);
    }

    @AfterEach
    public void stopDriver() {
        driver.quit();
    }

}
