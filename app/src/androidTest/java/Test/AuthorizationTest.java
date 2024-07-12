package Test;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import static Helper.Helper.generateScreenshotName;
import static Helper.TestData.PassWithSpaces;
import static Helper.TestData.invalidLog;
import static Helper.TestData.invalidPass;
import static Helper.TestData.loginWithSpaces;
import static Helper.TestData.validLogin;
import static Helper.TestData.validPass;

import static Step.AuthStep.clickButton;
import static Step.AuthStep.invalidLogin;
import static Step.AuthStep.invalidPassword;
import static Step.AuthStep.loginAction;
import static Step.AuthStep.passAction;

import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import Page.AuthPage;
import Step.AuthStep;
import Step.MainStep;
import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import kotlin.jvm.JvmField;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AuthorizationTest {

    AuthStep authStep = new AuthStep();
    MainStep mainStep = new MainStep();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule(ScreenshotRule.Mode.FAILURE,
            generateScreenshotName("Failed"));

    @Before
    public void logOut() throws InterruptedException {
        authStep.logOut();
    }

    @Test
    @DisplayName("Вход авторизованного пользователя и выход из профиля")
    public void authorizedUserLogin() {
        authStep.loginAction(validLogin);
        authStep.passAction(validPass);
        authStep.clickButton();
        mainStep.loadingHomePage();
        mainStep.logOut();

        AuthPage.Authorization.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Вход авторизованного пользователя с пробелами в начале и в конце" +
            "в полях логин и пароль")// баг
    public void loginWithSpaces() {
        authStep.loginActionWithSpaces(loginWithSpaces, PassWithSpaces);
        authStep.clickButton();

        AuthPage.Authorization.check(matches(isDisplayed()));
        authStep.wrongLoginAndPass();
    }

    @Test
    @DisplayName("Ввод невалидных значений логина и пароля")
    public void invalidUserLogin(){
        invalidLogin(invalidLog);
        invalidPassword(invalidPass);
        clickButton();

        authStep.wrongLoginAndPass();
    }
    @Test
    @DisplayName("Попытка входа с пустыми полями логин и пароль")
    public void emptyLoginAndPassword(){
        clickButton();
        authStep.emptyLoginAndPass();
    }
}
