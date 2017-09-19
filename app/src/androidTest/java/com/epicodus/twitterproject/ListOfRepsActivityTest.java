package com.epicodus.twitterproject;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.epicodus.twitterproject.ui.ListOfRepsActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by emiliethoreson on 9/8/17.
 */

public class ListOfRepsActivityTest {

    @Rule
    public ActivityTestRule<ListOfRepsActivity> activityTestRule =
            new ActivityTestRule<>(ListOfRepsActivity.class);

    @Test
    public void listItemClickDisplayToastWithCorrectRepresentative() {
        View activityDecorView = activityTestRule.getActivity().getWindow().getDecorView();
        String repName = "Jim Ferrell";
        onData(anything())
                .inAdapterView(withId(R.id.listView))
                .atPosition(0)
                .perform(click());
        onView(withText(repName)).inRoot(withDecorView(not(activityDecorView)))
                .check(matches(withText(repName)));
    }
}
