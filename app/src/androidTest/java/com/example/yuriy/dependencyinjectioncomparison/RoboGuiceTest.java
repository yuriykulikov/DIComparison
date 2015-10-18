package com.example.yuriy.dependencyinjectioncomparison;

import android.app.Application;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.yuriy.dependencyinjectioncomparison.roboguice.RoboGuiceActivity;
import com.example.yuriy.dependencyinjectioncomparison.roboguice.RoboGuiceModule;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Scopes;
import com.google.inject.util.Modules;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import roboguice.RoboGuice;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Matchers.contains;
import static org.mockito.Matchers.startsWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
public class RoboGuiceTest {
    private ILogger logger = mock(ILogger.class);
    @Rule
    public ActivityTestRule<RoboGuiceActivity> mActivityRule = new ActivityTestRule<>(RoboGuiceActivity.class, false, false);

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
        Application context = (Application) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();

        Module moduleOverride = new Module() {
            @Override
            public void configure(Binder binder) {
                binder.bind(ILogger.class).toInstance(logger);
            }
        };

        //Now we configure Guice with the "real" module and then we override a logger binding
        RoboGuice.overrideApplicationInjector(context, Modules.override(new RoboGuiceModule()).with(moduleOverride));

        mActivityRule.launchActivity(null);

        onView(withText("Throw pie"))
                .perform(click())
                .check(matches(isDisplayed()));

        verify(logger).d(contains("CheeseCake"));
    }
}