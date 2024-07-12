package Step;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static Helper.Helper.waitDisplayed;
import static Helper.TestData.validLogin;
import static Helper.TestData.validPass;

import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;

import Helper.ToastMatcher;
import Page.AuthPage;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;


public class AuthStep {
    static AuthPage authPage = new AuthPage();

    public static void loginScreenOpen() {
        Allure.step("Открыт экран авторизации");
        authPage.Authorization.check(matches(isDisplayed()));
    }

    public static void loginAction(String validLogin){
        Allure.step("Ввод валидного логина "+validLogin);
        authPage.Login.perform(replaceText(validLogin), closeSoftKeyboard());
    }

    public static void invalidLogin(String invalidLog) {
        Allure.step("Ввод не валидного логина "+ invalidLog);
        authPage.Login.perform(replaceText(invalidLog), closeSoftKeyboard());
    }
    public static void loginActionWithSpaces(String loginWithSpaces, String PassWithSpaces) {
        Allure.step("Ввод валидного логина и  пароля с пробелом в начале и в конце" + loginWithSpaces + PassWithSpaces);
        authPage.Login.perform(replaceText(loginWithSpaces), closeSoftKeyboard());
        authPage.Password.perform(replaceText(PassWithSpaces), closeSoftKeyboard());
    }

    public static void passAction(String validPass) {
        Allure.step("Ввод валидного пароля " + validPass);
        authPage.Password.perform(replaceText(validPass), closeSoftKeyboard());
    }

    public static void invalidPassword(String invalidPass){
        Allure.step("Ввод не валидного пароля "+ invalidPass);
        authPage.Password.perform(replaceText(invalidPass), closeSoftKeyboard());
    }

    public static void clickButton() {
        Allure.step("Клик по кнопке входа 'SING IN'");
        AuthPage.Button.check(matches(isClickable()));
        AuthPage.Button.perform(click());
    }

    public static void emptyLoginAndPass(){
        Allure.step("Вывод сообщения об ошибке(пуспое поле логин или пароль)");
        onView(withText(R.string.empty_login_or_password)).inRoot(new ToastMatcher())
                .check(matches(withText("Login and password cannot be empty")));
    }

    public static void wrongLoginAndPass(){
        Allure.step("Вывод сообщения об ошибке(Что-то пошло не так. Попробуйте позже.)");
        onView(withText(R.string.error)).inRoot(new ToastMatcher())
                .check(matches(withText("Something went wrong. Try again later.")));
    }

    public void logOut() {

        try {
            onView(isRoot()).perform(waitDisplayed(withId(R.id.authorization_image_button), 5000));
            onView(withId(R.id.authorization_image_button)).perform(click());
            onView(withId(android.R.id.title)).perform(click());
        } catch (Exception e){
        }
    }

    public static void logIn() {
        try {
            onView(isRoot()).perform(waitDisplayed(withId(R.id.enter_button), 5000));
            loginAction(validLogin);
            passAction(validPass);
            clickButton();
            onView(isRoot()).perform(waitDisplayed(withId(R.id.authorization_image_button), 1000));
        } catch (Exception e){
        }
    }
}