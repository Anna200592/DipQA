package Page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static Helper.Helper.childAtPosition;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class MainPage {

    //Выход из профиля
    public static ViewInteraction authorizationButton = onView(withId(R.id.authorization_image_button));
    public static ViewInteraction logOutButton = onView(withText("Log out"));

    //Главная страница
    public static ViewInteraction AllNews = onView((withId(R.id.all_news_text_view)));
    public static ViewInteraction expandNews = onView(allOf(withId(R.id.expand_material_button),
            childAtPosition(childAtPosition(withId(R.id.container_list_news_include_on_fragment_main), 0), 4)));
    public static ViewInteraction expandOneNews =onView(allOf(withId(R.id.news_list_recycler_view),
            childAtPosition(withId(R.id.all_news_cards_block_constraint_layout), 0)));

    //Кнопка Меню
    public static ViewInteraction mainMenuButton = onView(withId(R.id.main_menu_image_button));
    public static ViewInteraction menuMain = onView(withText("Main"));
    public static ViewInteraction menuNews = onView(allOf(withText("News")));
    public static ViewInteraction menuAbout = onView(withText("About"));

    //Блок цытат о хосписе
    public static ViewInteraction loveIsAll = onView(withId(R.id.our_mission_image_button));
    public static ViewInteraction loveIsAllDescription = onView(allOf(withId(R.id.our_mission_item_list_recycler_view),
                    childAtPosition(withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                            0)));
    public static ViewInteraction loveIsAllDescriptionText = onView(allOf(withId(R.id.our_mission_title_text_view)));
}
