package Step;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static Step.NewsStep.mainPage;

import Page.AboutPage;
import io.qameta.allure.kotlin.Step;

public class AboutStep {

    @Step("Переход на страницу 'About'")
    public static void goOpenAbout(){
        mainPage.mainMenuButton.perform(click());
        mainPage.menuAbout.check(matches(isDisplayed()));
        mainPage.menuAbout.perform(click());
    }

    @Step("Видны все элементы на странице")
    public static void allElementsOnPageAreVisible(){
        AboutPage.versionTitle.check(matches(allOf(withText("Version:"), isDisplayed())));
        AboutPage.version.check(matches(allOf(withText("1.0.0"), isDisplayed())));
        AboutPage.termsTitle.check(matches(allOf(withText("Terms of use:"), isDisplayed())));
        AboutPage.copyright.check(matches(allOf(withText("© I-Teco, 2022"), isDisplayed())));
    }

    @Step("Проверка кликабельности ссылки")
    public void checkingClickabilityTermsOfUse() {
        AboutPage.termsUrl.check(matches(isClickable()));
    }

    @Step("Проверка кликабельности ссылки")
    public void checkingClickabilityPrivacyPolicy() {
        AboutPage.privacyPolicy.check(matches(isClickable()));
    }
}
