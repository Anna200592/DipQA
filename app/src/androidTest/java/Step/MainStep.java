package Step;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;

import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static Helper.Helper.waitDisplayed;
import static Page.MainPage.AllNews;
import static Page.MainPage.authorizationButton;
import static Page.MainPage.expandNews;
import static Page.MainPage.expandOneNews;
import static Page.MainPage.logOutButton;
import static Page.MainPage.loveIsAll;

import Page.MainPage;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class MainStep {

    public static void loadingHomePage() {
        Allure.step("Загрузка главной страницы");
        onView(isRoot()).perform(waitDisplayed(withId(R.id.all_news_text_view), 7000));
    }

    public static void logOut() {
        Allure.step("Выход из профиля");
        authorizationButton.perform(click());
        logOutButton.perform(click());
    }

    public static void expandNews () {
        Allure.step("Развернуть список новостей на главном экране");
        expandNews.perform(click());
    }

    public void expandOneNews () {
        Allure.step("Развернуть одну новость на главном экране");
        expandOneNews.perform(click());
    }

    public void allNews () {
        Allure.step("Переход на вкладку All News");
        AllNews.check(matches(isClickable()));
        AllNews.perform(click());
    }

    public void loveIsAll () {
        Allure.step("Переход на вкладку Love is all");
        loveIsAll.check(matches(isClickable()));
        loveIsAll.perform(click());
    }
    
    public void DescriptionText () {
        Allure.step("Открыть текст описания во вкладке Love is all");
        MainPage.loveIsAllDescription.perform(click());
    }

}