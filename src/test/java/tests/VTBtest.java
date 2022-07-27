package tests;

import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import tests.config.TestBase;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class VTBtest extends TestBase {

    Faker faker = new Faker();
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    @Test
    public void testVTB {
        String fullName = "Иванов Иван";
        String dateBirth = "01062000";
        String email = faker.internet().emailAddress();
        String mobile = faker.phoneNumber().subscriberNumber(9);

        step("Открываем главную страницу", () -> {
            Selenide.open("https://www.vtb.ru/");
        });

        step("Подтверждаем соглашение в отношении использования пользовательских данных", ()-> {
            $(".cookies-notification .size-small").click();
        });

        step("Переходим на страницу оформления дебетовой мультикарты", () -> {
            $(".carousel-slide").sibling(0).click();
            $(".hero-card--large .large-card-buttons").$(".button").click();
            Selenide.sleep(2000);
            $("#hero").shouldBe(visible);
            $(".hTYhVE").click();
            Selenide.sleep(3000);
            $("[role='dialog']").shouldBe(visible);
            $("[role='dialog']").$("[for='Android']").click();
        });

        step("Заполняем форму", () -> {
            registrationFormPage.setFullName(fullName)
                    .setDateOfBirth(dateBirth)
                    .setUserEmail(email)
                    .setUserNumber("8" + mobile);
        });

        step("Проверяем активность кнопки 'Получить код'", ()-> {
            $("button").shouldHave(attribute("disabled"));
        });
    }
}
