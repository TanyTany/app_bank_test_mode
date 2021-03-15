package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class TestMode {

    @BeforeEach
    void setUp() {
        open("http://0.0.0.0:9999/");
    }

    @BeforeAll
    static void setUpAll() {
        Configuration.headless = true;
    }

    @Test
    void shouldValidActiveUser() {
        User userActive = DataGenerator.Registration.registrationActiveUser();
        Request.setUpAll(userActive);
        $("[data-test-id=login] .input__control").setValue(userActive.getLogin());
        $("[data-test-id=password] .input__control").setValue(userActive.getPassword());
        $("[data-test-id=action-login]").click();
        $(withText("Личный кабинет")).shouldBe(Condition.visible);
    }

    @Test
    void shouldValidActiveUserVasya() {
        User userVsya = DataGenerator.Registration.registrationVasya();
        Request.setUpAll(userVsya);
        $("[data-test-id=login] .input__control").setValue(userVsya.getLogin());
        $("[data-test-id=password] .input__control").setValue(userVsya.getPassword());
        $("[data-test-id=action-login]").click();
        $(withText("Личный кабинет")).shouldBe(Condition.visible);
    }

    @Test
    void shouldValidBlockedUser() {
        User userBlock = DataGenerator.Registration.registrationBlockedUser();
        Request.setUpAll(userBlock);
        $("[data-test-id=login] .input__control").setValue(userBlock.getLogin());
        $("[data-test-id=password] .input__control").setValue(userBlock.getPassword());
        $("[data-test-id=action-login]").click();
        $(withText("Пользователь заблокирован")).shouldBe(Condition.visible);
    }

    @Test
    void shouldUseWrongLogin() {
        User userActive = DataGenerator.Registration.registrationActiveUser();
        Request.setUpAll(userActive);
        $("[data-test-id=login] .input__control").setValue("ryiefgsjf");
        $("[data-test-id=password] .input__control").setValue(userActive.getPassword());
        $("[data-test-id=action-login]").click();
        $(withText("Неверно указан логин или пароль")).shouldBe(Condition.visible);
    }

    @Test
    void shouldUseWrongPssword() {
        User userActive = DataGenerator.Registration.registrationActiveUser();
        Request.setUpAll(userActive);
        $("[data-test-id=login] .input__control").setValue(userActive.getLogin());
        $("[data-test-id=password] .input__control").setValue("fhshjh");
        $("[data-test-id=action-login]").click();
        $(withText("Неверно указан логин или пароль")).shouldBe(Condition.visible);
    }




}
