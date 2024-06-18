package Test;



import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;


import static Helper.TestData.invalidLog;
import static Helper.TestData.invalidPass;
import static Helper.TestData.validLogin;
import static Helper.TestData.validPass;
import static Step.AuthStep.clickButton;
import static Step.AuthStep.invalidLogin;
import static Step.AuthStep.invalidPassword;
import static Step.AuthStep.loginAction;
import static Step.AuthStep.passAction;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import Page.AuthPage;
import Step.AuthStep;
import Step.MainStep;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AuthorizationTest {

    AuthStep authStep = new AuthStep();
    MainStep mainStep = new MainStep();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void logOutCheck() {
        authStep.logOut();
    }

    @Test
    @DisplayName("Вход автозированного пользователя и выход из профиля")
    public void authorizedUserLogin() throws InterruptedException {

        loginAction(validLogin);
        passAction(validPass);
        clickButton();
        mainStep.loadingHomePage();
        mainStep.logOut();

        AuthPage.Authorization.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Ввод невалидных значений логина и пароля")
    public void invalidUserLogin() throws InterruptedException {
        invalidLogin(invalidLog);
        invalidPassword(invalidPass);
        clickButton();

        authStep.wrongLoginAndPass();
    }
    @Test
    @DisplayName("Попытка входа с пустыми полями логин и пароль")
    public void emptyLoginAndPassword() throws InterruptedException {
        clickButton();
        authStep.emptyLoginAndPass();
    }
}
