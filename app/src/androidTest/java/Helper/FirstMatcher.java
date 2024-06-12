package Helper;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class FirstMatcher<T> extends TypeSafeMatcher<T> {
    private final Matcher<T> matcher;
    private boolean isFirst = true;

    public FirstMatcher(Matcher<T> matcher) {
        this.matcher = matcher;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("first matching: ");
        matcher.describeTo(description);
    }

    @Override
    protected boolean matchesSafely(T item) {
        if (matcher.matches(item) && isFirst) {
            isFirst = false;
            return true;
        }
        return false;
    }

    public static <T> Matcher<T> first(Matcher<T> matcher) {
        return new FirstMatcher<>(matcher);
    }
}
