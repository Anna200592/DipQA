package Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static Helper.Helper.generateScreenshotName;
import static Step.AboutStep.allElementsOnPageAreVisible;
import static Step.AboutStep.goOpenAbout;
import static Step.NewsStep.fillEmptyFields;
import static Step.NewsStep.getFillDescription;
import static Step.NewsStep.getFillTitle;
import static Step.NewsStep.goOpenNews;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import Helper.TestData;
import Page.NewsPage;
import Step.AuthStep;
import Step.MainStep;
import Step.NewsStep;
import io.qameta.allure.android.rules.ScreenshotRule;
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
    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule(ScreenshotRule.Mode.FAILURE,
            generateScreenshotName("Failed"));

    @Before
    public void logIn() throws InterruptedException {
        AuthStep.logIn();
        MainStep.loadingHomePage();
    }

    @Test
    @DisplayName("Создание новости")//тест нестабилен, зависит от того как настроен эмулятор
    public void shouldNewNewsCreat() throws InterruptedException {
        newsStep.goOpenNews();
        newsStep.GoCreatingNewsPage();

        newsStep.fillCategory("no", "yes", 3);
        newsStep.fillTitle(TestData.title1);
        newsStep.fillDate("no");
        newsStep.fillTime("no", "dial", "save");
        newsStep.fillDescription("no", TestData.description1);
        newsPage.saveButton.perform(click());

        NewsStep.goOpenNews();

        NewsStep.checkNewsData(getFillTitle(), getFillDescription());
    }

    @Test
    @DisplayName("Удаление ранее созданной новости новости")//тест нестабилен в автоматизации,
    // проходит если запускать отдельно
    public void DeletingPreviouslyCreatedNewsStory(){
        newsStep.goOpenNews();
        newsStep.GoCreatingNewsPage();

        newsStep.fillCategory("no", "yes", 5);
        newsStep.fillTitle(TestData.newsDelete);
        newsStep.fillDate("no");
        newsStep.fillTime("no", "dial", "save");
        newsStep.fillDescription("no", TestData.description2);
        newsPage.saveButton.perform(click());

        newsStep.deleteNewsCard(TestData.newsDelete);
    }

    @Test
    @DisplayName("Заменить статус на 'not Active' у новости")
    public void changeNewsActivity() {
        goOpenNews();
        newsStep.changeNewsActivity();

        newsPage.publishNews.check(matches(withText("NOT ACTIVE")));
    }

    @Test
    @DisplayName("Переход на вкладку about со страницы News")
    public void GoAboutTabFromNewsPage() {
        goOpenNews();
        goOpenAbout();

        allElementsOnPageAreVisible();
    }

    @Test
    @DisplayName("Создание новости с пустим полем 'description'")
    public void creatingNewsItemWithDescriptionFieldEmpty() {
        newsStep.goOpenNews();
        newsStep.GoCreatingNewsPage();

        newsStep.fillCategory("no", "yes", 1);
        newsStep.fillTitle(TestData.title1);
        newsStep.fillDate("no");
        newsStep.fillTime("no", "dial", "save");
        newsStep.fillDescription(" ", "");
        newsPage.saveButton.perform(click());

        fillEmptyFields();
    }

    @Test
    @DisplayName("Создание новости с ручным вводом времени")
    public void creatingNewsWithManualTimeEntry() {
        newsStep.goOpenNews();
        newsStep.GoCreatingNewsPage();

        newsStep.fillCategory("no", "yes", 5);
        newsStep.fillTitle(TestData.title2);
        newsStep.fillDate("no");
        newsStep.fillTime("no", "no", "save");
        newsStep.fillDescription("no", TestData.description1);
        newsPage.saveButton.perform(click());

        NewsStep.goOpenNews();

        NewsStep.checkNewsData(getFillTitle(), getFillDescription());
    }

    @Test
    @DisplayName("Создать новость с заголовком на Кирилице")
    public void createNewsStoryWithHeadlineInCyrillic() throws InterruptedException {
        newsStep.goOpenNews();
        newsStep.GoCreatingNewsPage();

        newsStep.fillCategory("no", "yes", 1);
        newsStep.fillTitle(TestData.title3);
        newsStep.fillDate("no");
        newsStep.fillTime("no", "dial", "save");
        newsStep.fillDescription("no", TestData.description3);
        newsPage.saveButton.perform(click());

        NewsStep.goOpenNews();

        NewsStep.checkNewsData(getFillTitle(), getFillDescription());
    }

}
