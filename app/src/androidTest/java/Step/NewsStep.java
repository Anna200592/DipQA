package Step;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static org.hamcrest.Matchers.allOf;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;

import static Page.NewsPage.okButton;

import android.widget.TimePicker;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.RootMatchers;

import org.hamcrest.Matchers;

import java.util.Calendar;

import Helper.FirstMatcher;
import Page.MainPage;
import Page.NewsPage;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;


public class NewsStep {
    static MainPage mainPage = new MainPage();
    static NewsPage newsPage = new NewsPage();
    private static String getFillTitle;
    private static String getFillDescription;

    @Step("Переход на страницу 'News'")//Выполняется в тесте
    public static void goOpenNews() {
        mainPage.mainMenuButton.perform(click());
        mainPage.menuNews.check(matches(isDisplayed()));
        mainPage.menuNews.perform(click());
        newsPage.news.check(matches(isDisplayed()));
    }

    @Step("Переход на страницу 'Creating News'")
    public void GoCreatingNewsPage() {
        newsPage.editNews.perform(click());
        newsPage.controlPanel.check(matches(isDisplayed()));
        newsPage.addNews.perform(click());
        newsPage.CreatingTitle.check(matches(isDisplayed()));
    }

    @Step("Заполнение поля Категория при создании новости")
    public static void fillCategory(String emptyCategory, String choiceOfCategory, int category) {
        String[] categories = {"Объявление", "День рождения", "Зарплата", "Профсоюз", "Праздник", "Массаж",
                "Благодарность", "Нужна помощь"};

        if (category < 0 || category >= categories.length) {
            throw new IllegalArgumentException("Номер категории выходит за пределы допустимого диапазона.");
        }

        String chosenCategory = categories[category];
        // заполнение поля "Категория"
        if ("no".equals(emptyCategory)) {
            if ("yes".equals(choiceOfCategory)) {
                NewsPage.buttonForShowingDropdownMenu.perform(click());
                // Выбор категории
                onView(withText(chosenCategory)).inRoot(RootMatchers.isPlatformPopup()).perform(click());
            } else {
                NewsPage.categoryList.perform(replaceText(chosenCategory));
                NewsPage.categoryList.check(matches(isDisplayed()));
            }
        }
    }

    @Step("Заполнение поля Заголовок при создании новости")
    public static void fillTitle(String title) {
        NewsPage.addCreatingNewsTitle.perform(replaceText(title));
        NewsPage.addCreatingNewsTitle.check(matches(withText(title)));
        getFillTitle = title;
    }
    public static String getFillTitle() {
        return getFillTitle;
    }


    @Step("Заполнение поля Дата при создании новости")
    public static void fillDate(String emptyDate) {
        if ("no".equals(emptyDate)) {
            NewsPage.newsPublishData.perform(click());
            okButton.perform(click());
        }
    }

    @Step("Заполнение поля Время при создании новости")
    public static void fillTime(String emptyTime, String withDialPadOrTextInput, String saveOrCancelTime) {
        if ("no".equals(emptyTime)) {
            NewsPage.newsPublishTime.perform(ViewActions.click());

            // Получение текущего времени
            Calendar calendar = Calendar.getInstance();
            int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
            int currentMinute = calendar.get(Calendar.MINUTE);

            if ("dial".equals(withDialPadOrTextInput)) {
                if ("save".equals(saveOrCancelTime)) {
                    onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                            .perform(PickerActions.setTime(currentHour, currentMinute));
                    okButton.perform(click());
                } else {
                    NewsPage.cancelButton.perform(click());
                }
            } else {
                NewsPage.buttonForSwitchToTextInput.perform(click());
                onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                        .perform(PickerActions.setTime(7, 45));
                okButton.perform(click());

            }
        }
    }

    @Step("Заполнение поля Описание при создании новости")
    public static void fillDescription(String emptyDescription, String description) {
        if ("no".equals(emptyDescription)) {
            NewsPage.newsCreatingDescription.perform(replaceText(description));
            NewsPage.newsCreatingDescription.check(matches(withText(description)));
            getFillDescription = description;
        }
    }
    public static String getFillDescription() {
        return getFillDescription;
    }


    @Step("Cоданная новость содержит ранее введенные данные")
    public static void checkNewsData(String title, String description) {
        onView(FirstMatcher.first(allOf(withId(R.id.news_item_title_text_view), withText(title),
                withParent(withParent(withId(R.id.news_item_material_card_view))))))
                .check(matches(withText(title)));

        newsPage.newNewsCard
                .check(matches(isDisplayed()))
                .perform(actionOnItemAtPosition(0, click()));

        onView(FirstMatcher.first(allOf(withId(R.id.news_item_description_text_view), withText(description),
                withParent(withParent(withId(R.id.news_item_material_card_view))))))
                .check(matches(withText(description)));

    }

    @Step("Удалить созданную новость")
    public static void deleteNewsCard(String title){
         onView(FirstMatcher.first(allOf(withId(R.id.delete_news_item_image_view),
                 withContentDescription("News delete button"),
                 withParent(withParent(withId(R.id.news_item_material_card_view))))))
                 .perform(click());

        newsPage.okButton
                .check(matches(isClickable()))
                .perform(scrollTo(), click());

        onView(FirstMatcher.first(allOf(withId(R.id.news_item_title_text_view), withText(title),
                withParent(withParent(withId(R.id.news_item_material_card_view))))))
                .check(doesNotExist());
    }

    @Step("Сменить активность новости")
    public static void changeNewsActivity(){
        newsPage.editNews.perform(click());
        newsPage.sortNews.perform(click());
        onView(FirstMatcher.first(allOf(withId(R.id.edit_news_item_image_view),
                withContentDescription("News editing button"),
                withParent(withParent(withId(R.id.news_item_material_card_view))))))
                .perform(click());
        newsPage.switcher.perform(click());

    }
}
