package Test;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import Step.AboutStep;
import Step.AuthStep;
import Step.MainStep;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class AboutTest {

    AboutStep aboutStep = new AboutStep();
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void logIn() throws InterruptedException {
        AuthStep.logIn();
        MainStep.loadingHomePage();
    }

   @Test//баг, при переходе на сайт происходит сбой в работе приложения
   @DisplayName("Переход по ссылке Политика конфиденциальности")
    public void followingLinkPrivacyPolicy() {
        aboutStep.goOpenAbout();
        aboutStep.allElementsOnPageAreVisible();
        aboutStep.checkingClickabilityTermsOfUse();
    }

    @Test//баг, при переходе на сайт происходит сбой в работе приложения
    @DisplayName("Переход по ссылке  Условия использования")

    public void followTheLinkTermsOfUse() {
        aboutStep.goOpenAbout();
        aboutStep.allElementsOnPageAreVisible();
        aboutStep.checkingClickabilityPrivacyPolicy();
    }
}
