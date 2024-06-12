package Step;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;

import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

import static Helper.Helper.waitDisplayed;
import static Page.MainPage.AllNews;
import static Page.MainPage.authorizationButton;
import static Page.MainPage.expandNews;
import static Page.MainPage.expandOneNews;
import static Page.MainPage.logOutButton;
import static Page.MainPage.loveIsAll;


import Page.MainPage;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class MainStep {

    @Step("Загрузка главной страницы")
    public static void loadingHomePage() {
        onView(isRoot()).perform(waitDisplayed(R.id.all_news_text_view, 7000));
    }

    @Step("Выход из профиля")
    public static void logOut() {
        authorizationButton.perform(click());
        logOutButton.perform(click());
        }

    @Step("Развернуть список новостей на главном экране")
    public static void expandNews () {
        expandNews.perform(click());
        }

    @Step("Развернуть одну новость на главном экране")
    public void expandOneNews () {
        expandOneNews.perform(click());
        }

    @Step("Переход на вкладку All News")
    public void allNews () {
        AllNews.check(matches(isClickable()));
        AllNews.perform(click());
        }

    @Step("Переход на вкладку Love is all")
    public void loveIsAll () {
        loveIsAll.check(matches(isClickable()));
        loveIsAll.perform(click());
        }

    @Step("Открыть текст описания во вкладке Love is all")
    public void DescriptionText () {
        MainPage.loveIsAllDescription.perform(click());
        }

}
