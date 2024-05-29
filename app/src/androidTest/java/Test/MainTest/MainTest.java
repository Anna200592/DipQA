package Test.MainTest;


import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import static Helper.Data.validLogin;
import static Helper.Data.validPass;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import Helper.Data;
import Helper.Helper;
import Page.MainPage;
import Step.AuthStep;
import Step.MainStep;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class MainTest {
    Data data = new Data();
    Helper helper = new Helper();
    AuthStep authStep = new AuthStep();
    MainStep mainStep = new MainStep();
    MainPage mainPage = new MainPage();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void logIn() throws InterruptedException {
        AuthStep.logIn();
    }

    @Test
    @DisplayName("Развернуть и свернуть блок новостей на главном экране")
    public void MainTest() {
        mainStep.expandOneNews();
        mainStep.expandNews();
        mainStep.expandNews();

        mainPage.expandOneNews.check(matches((isDisplayed())));
    }

    @Test
    @DisplayName("Развернуть и свернуть блок цитат на вкладке Love is All")
    public void expandBlockOfQuotes() {
        mainStep.loveIsAll();
        mainStep.DescriptionText();

        MainPage.loveIsAllDescriptionText.check(matches(isDisplayed()));
    }
}
