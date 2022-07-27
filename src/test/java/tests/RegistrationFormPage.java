package tests;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class RegistrationFormPage {

    public ResultsTableComponent resultsTableComponent = new ResultsTableComponent();

    SelenideElement fullNameInput = $("#full-name-input"),
            dateOfBirthInput = $("#date-of-birth"),
            userEmailInput = $("#email-input"),
            userNumberInput = $("#phone-number"),

    practiceFormCheck = $(".practice-form-wrapper"),
            exampleModalCheck = $("#example-modal-sizes-title-lg");

    public RegistrationFormPage openPage(){
        open("/automation-practice-form");
        practiceFormCheck.shouldHave(text("Student Registration Form"));
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#close-fixedban').remove()");

        return this;
    }

    public RegistrationFormPage setFullName(String value){
        fullNameInput.setValue(value);

        return this;
    }

    public RegistrationFormPage setDateOfBirth(String value){
        dateOfBirthInput.click();
        dateOfBirthInput.setValue(value);

        return this;
    }

    public RegistrationFormPage setUserEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }


    public RegistrationFormPage setUserNumber(String value){
        userNumberInput.setValue(value);

        return this;
    }

    public RegistrationFormPage openTable(){
        exampleModalCheck.shouldHave(text("Thanks for submitting the form"));

        return this;
    }

    public RegistrationFormPage checkResult(String key, String value){
        resultsTableComponent.checkResult(key, value);

        return this;
    }
}
}
