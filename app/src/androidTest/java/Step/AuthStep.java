package Step;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static Helper.TestData.validLogin;
import static Helper.TestData.validPass;

import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;

import Helper.ToastMatcher;
import Page.AuthPage;
import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;


public class AuthStep {
    AuthPage authPage = new AuthPage();

    @Step("Открыт экран авторизации")
    public static void loginScreenOpen() {
        AuthPage.Authorization.check(matches(isDisplayed()));
    }

    public static void loginAction(String validLogin) throws InterruptedException {
        Allure.step("Ввод валидного логина "+validLogin);
        AuthPage.Login.perform(replaceText(validLogin), closeSoftKeyboard());
    }

    public static void invalidLogin(String invalidLog) throws InterruptedException {
        Allure.step("Ввод не валидного логина "+ invalidLog);
        AuthPage.Login.perform(replaceText(invalidLog), closeSoftKeyboard());
    }

    public static void passAction(String validPass) throws InterruptedException {
        Allure.step("Ввод валидного пароля" + validPass);
        AuthPage.Password.perform(replaceText(validPass), closeSoftKeyboard());
    }

    public static void invalidPassword(String invalidPass) throws InterruptedException {
        Allure.step("Ввод не валидного пароля "+ invalidPass);
        AuthPage.Password.perform(replaceText(invalidPass), closeSoftKeyboard());
    }

    @Step("Клик по кнопке входа 'SING IN'")
    public static void clickButton() {
        AuthPage.Button.check(matches(isClickable()));
        AuthPage.Button.perform(click());
    }

    @Step("Вывод сообщения об ошибке(пуспое поле логин или пароль)")
    public static void emptyLoginAndPass(){
        onView(withText(R.string.empty_login_or_password)).inRoot(new ToastMatcher())
                .check(matches(withText("Login and password cannot be empty")));
    }

    @Step("Вывод сообщения об ошибке()")
    public static void wrongLoginAndPass(){
        onView(withText(R.string.error)).inRoot(new ToastMatcher())
                .check(matches(withText("Something went wrong. Try again later.")));
    }
    public static void logOut(){
        SystemClock.sleep(5000);
        try {
            AuthStep.loginScreenOpen();
        } catch (NoMatchingViewException e) {
            MainStep.logOut();
        }
    }

    public static void logIn() throws InterruptedException {
        SystemClock.sleep(5000);
        try {
            AuthStep.loginScreenOpen();
        } catch (NoMatchingViewException e) {
            return;
        }
        loginAction(validLogin);
        passAction(validPass);
        clickButton();
    }
}