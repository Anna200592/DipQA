package Step;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static Step.NewsStep.mainPage;

import Page.AboutPage;
import io.qameta.allure.kotlin.Allure;

public class AboutStep {

    public static void goOpenAbout(){
        Allure.step("Переход на страницу 'About'");
        mainPage.mainMenuButton.perform(click());
        mainPage.menuAbout.check(matches(isDisplayed()));
        mainPage.menuAbout.perform(click());
    }

    public static void allElementsOnPageAreVisible(){
        Allure.step("Видны все элементы на странице");
        AboutPage.versionTitle.check(matches(allOf(withText("Version:"), isDisplayed())));
        AboutPage.version.check(matches(allOf(withText("1.0.0"), isDisplayed())));
        AboutPage.termsTitle.check(matches(allOf(withText("Terms of use:"), isDisplayed())));
        AboutPage.copyright.check(matches(allOf(withText("© I-Teco, 2022"), isDisplayed())));
    }

    public void checkingClickabilityTermsOfUse() {
        Allure.step("Проверка кликабельности ссылки Terms of use");
        AboutPage.termsUrl.check(matches(isClickable()));
    }

    public void checkingClickabilityPrivacyPolicy() {
        Allure.step("Проверка кликабельности ссылки Privacy Policy");
        AboutPage.privacyPolicy.check(matches(isClickable()));
    }
}
