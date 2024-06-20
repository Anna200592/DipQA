package Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static Step.NewsStep.goOpenNews;
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


    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void logIn() throws InterruptedException {
        AuthStep.logIn();
        MainStep.loadingHomePage();
    }

    @Test
    @DisplayName("Создание новости")//тест нестабилен, не проходит в автоматизации
    public void shouldNewNewsCreat() throws InterruptedException {
        newsStep.goOpenNews();
        newsStep.GoCreatingNewsPage();

        newsStep.fillCategory("no","yes",3);
        newsStep.fillTitle("Test Super News");
        newsStep.fillDate("no");
        newsStep.fillTime("no","dial","save");
        newsStep.fillDescription("no","This is a test");
        newsPage.saveButton.perform(click());

        NewsStep.goOpenNews();

        NewsStep.checkNewsData("Test Super News","This is a test");
    }

    @Test
    @DisplayName("Удаление ранее созданной новости новости")//тест нестабилен, не проходит в автоматизацииc
    public void DeletingPreviouslyCreatedNewsStory() throws InterruptedException {
        newsStep.goOpenNews();
        newsStep.GoCreatingNewsPage();

        newsStep.fillCategory("no","yes",5);
        newsStep.fillTitle("News Delete");
        newsStep.fillDate("no");
        newsStep.fillTime("no","dial","save");
        newsStep.fillDescription("no","This is a test description");
        newsPage.saveButton.perform(click());

        newsStep.deleteNewsCard("News Delete");
    }

    @Test
    @DisplayName("Заменить статус на 'not Active' у новости")
    public void changeNewsActivity(){
        goOpenNews();
        newsStep.changeNewsActivity();
        newsPage.saveButton.perform(click());

        newsPage.publishNews.check(matches(withText("NOT ACTIVE")));
    }

}
