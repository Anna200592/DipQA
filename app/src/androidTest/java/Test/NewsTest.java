package Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import static Step.NewsStep.getFillDescription;
import static Step.NewsStep.getFillTitle;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import Page.NewsPage;
import Step.AuthStep;
import Step.MainStep;
import Step.NewsStep;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class NewsTest {

    NewsStep newsStep = new NewsStep();
    NewsPage newsPage = new NewsPage();

    String expectedTitle = getFillTitle();
    String expectedDescription = getFillDescription();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void logIn() throws InterruptedException {
        AuthStep.logIn();
        MainStep.loadingHomePage();
    }

    @Test
    @DisplayName("Открыть описание новости в блоке")
    public void NewsTest() throws InterruptedException {
        newsStep.goOpenNews();
        newsStep.openNewsDescription();

        newsPage.newsPageDescription.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Создание новости")
    public void shouldNewNewsCreat() throws InterruptedException {
        newsStep.goOpenNews();
        newsStep.GoCreatingNewsPage();

        newsStep.fillCategory("no","yes",4);
        newsStep.fillTitle("Super News");
        newsStep.fillDate("no");
        newsStep.fillTime("no","dial","save");
        newsStep.fillDescription("no","This is a test description");
        newsPage.saveButton.perform(click());

        NewsStep.goOpenNews();

        NewsStep.checkNewsData(expectedTitle,expectedDescription);
    }

    @Test
    @DisplayName("Удаление ранее созданной новости новости")
    public void DeletingPreviouslyCreatedNewsStory() throws InterruptedException {
        newsStep.goOpenNews();
        newsStep.GoCreatingNewsPage();

        newsStep.fillCategory("no","yes",5);
        newsStep.fillTitle("News Delete");
        newsStep.fillDate("no");
        newsStep.fillTime("no","dial","save");
        newsStep.fillDescription("no","This is a test description");
        newsPage.saveButton.perform(click());

        newsStep.deleteNewsCard(expectedTitle);
    }

}
