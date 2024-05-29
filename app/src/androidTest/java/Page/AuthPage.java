package Page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.allOf;


import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class AuthPage {
   public static ViewInteraction Authorization = onView(allOf(withText("Authorization"),
           withParent(withParent(withId(R.id.nav_host_fragment)))));
   public static ViewInteraction Login = onView(allOf(withHint("Login"),
           withParent(withParent(withId(R.id.login_text_input_layout)))));
   public static ViewInteraction Password = onView(allOf(withHint("Password"),
           withParent(withParent(withId(R.id.password_text_input_layout)))));
   public static ViewInteraction Button = onView(withId(R.id.enter_button));
}
