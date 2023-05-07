package com.openclassrooms.entrevoisins.ui.neighbour_list;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNull.notNullValue;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourApiService;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class ListNeighbourActivityTest {
    public List<Neighbour> mNeighboursList = new DummyNeighbourApiService().getNeighbours();
    public int size = mNeighboursList.size() ;
    String name = mNeighboursList.get(0).getName();
    Neighbour neighbourToAdd = new Neighbour(size,"sebastien","","46 avenue du test","0606060606","je fait un test pour verifier que tous vas bien.");

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityScenarioRule =
            new ActivityTestRule(ListNeighbourActivity.class);


        @Before
    public void setUp() {
        mActivityScenarioRule.getActivity();
        assertThat(mActivityScenarioRule, notNullValue());
    }

    @Test
    public void detailActivityTest() {
        onView(allOf(withId(R.id.list_neighbours),
                isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        ViewInteraction textView = onView(
                allOf(withText("Detail Neighbour"),
                        withParent(allOf(withId(com.google.android.material.R.id.action_bar),
                                withParent(withId(com.google.android.material.R.id.action_bar_container)))),
                        isDisplayed()));
        textView.check(matches(withText("Detail Neighbour")));

        ViewInteraction textViewName = onView(
                allOf(withId(R.id.textViewNeighbourName1), withText(name),
                        withParent(allOf(withId(R.id.DetailActivityView),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        textViewName.check(matches(withText(name)));
    }
    @Test
    public void createNeighbourtest(){
        onView(allOf(withId(R.id.add_neighbour))).perform(click());
        ViewInteraction textInputEditText = onView(
                Matchers.allOf(withId(R.id.name),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nameLyt),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText.perform(replaceText("sebastien"), closeSoftKeyboard());

        ViewInteraction textInputEditText2 = onView(
                Matchers.allOf(withId(R.id.name), withText("sebastien"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nameLyt),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText2.perform(pressImeActionButton());

        ViewInteraction textInputEditText3 = onView(
                Matchers.allOf(withId(R.id.phoneNumber),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.phoneNumberLyt),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText3.perform(replaceText("0606060606"), closeSoftKeyboard());

        ViewInteraction textInputEditText4 = onView(
                Matchers.allOf(withId(R.id.phoneNumber), withText("0606060606"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.phoneNumberLyt),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText4.perform(pressImeActionButton());

        ViewInteraction textInputEditText5 = onView(
                Matchers.allOf(withId(R.id.address),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.addressLyt),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText5.perform(replaceText("46 avenue du test"), closeSoftKeyboard());

        ViewInteraction textInputEditText6 = onView(
                Matchers.allOf(withId(R.id.address), withText("46 avenue du test"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.addressLyt),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText6.perform(pressImeActionButton());

        ViewInteraction textInputEditText7 = onView(
                Matchers.allOf(withId(R.id.aboutMe),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.aboutMeLyt),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText7.perform(replaceText("je fait un test pour verifier que tous vas bien."), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                Matchers.allOf(withId(R.id.create), withText("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                5),
                        isDisplayed()));
        materialButton.perform(click());
        assert (mNeighboursList.contains(neighbourToAdd));
    }
    @Test
    public void FavoriteslistOnlyTest(){
        onView(allOf(withId(R.id.list_neighbours),
                isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.floatingActionButtonFavoris)));
        floatingActionButton.perform(click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.imageButtonBack)));
        appCompatImageButton.perform(click());

        ViewInteraction tabView = onView(
                Matchers.allOf(withContentDescription("Favoris"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabs),
                                        0),
                                1),
                        isDisplayed()));
        tabView.perform(click());
        onView(allOf(withId(R.id.list_neighbours),
                isDisplayed())).check(withItemCount(1));
        }


    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<>() {
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
