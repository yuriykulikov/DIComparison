package com.example.yuriy.dependencyinjectioncomparison;

import android.app.Application;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.yuriy.dependencyinjectioncomparison.dagger2.Dagger2Activity;
import com.example.yuriy.dependencyinjectioncomparison.dagger2.Dagger2Component;
import com.example.yuriy.dependencyinjectioncomparison.dagger2.Dagger2Module;
import com.example.yuriy.dependencyinjectioncomparison.dagger2.DaggerDagger2Component;
import com.example.yuriy.dependencyinjectioncomparison.roboguice.MyApplication;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
public class Dagger2Test {
    private ILogger logger = mock(ILogger.class);
    @Rule
    public ActivityTestRule<Dagger2Activity> mActivityRule = new ActivityTestRule<>(Dagger2Activity.class, false, false);

    /**
     * Some googling and thinking and yes, it works now!
     * We can supply just any module to the builder, it can be an override like this here or
     * we can even have a mockito spy!
     */
    public class TestDagger2Module extends Dagger2Module {
        @Override
        public ILogger provideILogger() {
            return logger;
        }
    }

    @Test
    public void withoutModuleOverride() {
        mActivityRule.launchActivity(null);

        onView(withText("Throw pie"))
                .perform(click())
                .check(matches(isDisplayed()));

        onView(withId(R.id.textView))
                .check(matches(withText("CheeseCake")));
    }

    @Test
    public void withAMock() {
        MyApplication applicationContext = (MyApplication) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();

        Dagger2Component componentWithOverridenLogger = DaggerDagger2Component.builder()
                //see this line here? Awesome! Maybe I was wrong about dagger, it is not that bad after all.
                .dagger2Module(new TestDagger2Module())
                .build();

        Dagger2Component.Singleton.initialze(componentWithOverridenLogger);

        mActivityRule.launchActivity(null);

        onView(withText("Throw pie"))
                .perform(click())
                .check(matches(isDisplayed()));

        verify(logger).d(contains("CheeseCake"));
    }
}