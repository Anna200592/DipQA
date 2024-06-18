package Page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static Helper.Helper.childAtPosition;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matchers;

import Helper.Helper;
import ru.iteco.fmhandroid.R;

public class NewsPage {

    //Страница News
    public static ViewInteraction news = onView(withText("News"));
    public static ViewInteraction editNews = onView((withId(R.id.edit_news_material_button)));
    public static ViewInteraction switcher = onView(withText("Active"));

    //Страница Control panel
    public static ViewInteraction controlPanel = onView(withText("Control panel"));
    public static ViewInteraction addNews = onView((withId(R.id.add_news_image_view)));
    public static ViewInteraction sortNews = onView((withId(R.id.sort_news_material_button)));
    public static ViewInteraction publishNews = onView(allOf(withId(R.id.news_item_published_text_view), withText("NOT ACTIVE"),
                    withParent(withParent(withId(R.id.news_item_material_card_view)))));


    // Страница Creating News
    public static ViewInteraction CreatingTitle = onView(withText("Creating"));
    public static ViewInteraction addCreatingNewsTitle = onView((withId(R.id.news_item_title_text_input_edit_text)));
    public static ViewInteraction newsPublishData = onView((withId(R.id.news_item_publish_date_text_input_edit_text)));
    public static ViewInteraction newsPublishTime = onView((withId(R.id.news_item_publish_time_text_input_edit_text)));
    public static ViewInteraction newsCreatingDescription = onView((withId(R.id.news_item_description_text_input_edit_text)));
    public static ViewInteraction categoryList = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    public static ViewInteraction buttonForSwitchToTextInput = onView(withContentDescription("Switch to text input mode for the time input."));
    public static ViewInteraction okButton = onView(withText("OK"));
    public static ViewInteraction saveButton = onView((withId(R.id.save_button)));
    public static ViewInteraction cancelButton = onView((withId(R.id.cancel_button)));
    public static ViewInteraction buttonForShowingDropdownMenu = onView(withContentDescription("Show dropdown menu"));
    public static ViewInteraction newNewsCard = onView(allOf(withId(R.id.news_list_recycler_view),
            childAtPosition(withId(R.id.all_news_cards_block_constraint_layout), 0)));
}