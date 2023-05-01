package com.openclassrooms.entrevoisins.ui.neighbour_list;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourApiService;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ListNeighbourActivityTest {


    public List<Neighbour> mNeighboursList = new DummyNeighbourApiService().getNeighbours();

    public int size = mNeighboursList.size() ;
    String name = mNeighboursList.get(0).getName();

    @Rule
    public ActivityScenarioRule<ListNeighbourActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(ListNeighbourActivity.class);

    @Test
    public void detailActivityStartWithSuccessTest() {
        onView(allOf(withId(R.id.list_neighbours),
                isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        ViewInteraction textView = onView(
                allOf(withText("Detail Neighbour"),
                        withParent(allOf(withId(com.google.android.material.R.id.action_bar),
                                withParent(withId(com.google.android.material.R.id.action_bar_container)))),
                        isDisplayed()));
        textView.check(matches(withText("Detail Neighbour")));
        mActivityScenarioRule.getScenario().close();
    }

    @Test
    public void detailNeighbourActivityTest(){
                ViewInteraction v = onView(allOf(withId(R.id.list_neighbours),
                isDisplayed()));
                v.perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));

                ViewInteraction textView = onView(
                allOf(withId(R.id.textViewNeighbourName1), withText(name),
                        withParent(allOf(withId(R.id.DetailActivityView),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        textView.check(matches(withText(name)));
        mActivityScenarioRule.getScenario().close();
    }
    @Test
    public void eraseNeighbourTest(){
        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.item_list_delete_button), withContentDescription("delete Neighbour"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.list_neighbours),
                                         5),
                                2),
                        isDisplayed()));
        appCompatImageButton.perform(click());
        ViewInteraction currentSize = onView(allOf(withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(size -1 ));
        mActivityScenarioRule.getScenario().close();
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
