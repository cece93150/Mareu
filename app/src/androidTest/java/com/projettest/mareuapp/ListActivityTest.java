package com.projettest.mareuapp;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import static com.projettest.mareuapp.ItemCountAssertion.withItemCount;

import android.support.test.rule.ActivityTestRule;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

import com.projettest.mareuapp.controler.ListActivity;
import com.projettest.mareuapp.di.DI;


/**
 * Instrumented test, which will execute on an Android device.
 *
 */
@RunWith(AndroidJUnit4.class)
public class ListActivityTest {

    private ListActivity mActivity;

    @Rule
    public ActivityTestRule<ListActivity> mActivityRule = new ActivityTestRule<>(ListActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    @Test
   public void meetingListIsNotEmpty() {
       onView(withId(R.id.meeting_list)).check(matches(hasMinimumChildCount(1)));
   }

    @Test
    public void addNewMeetingWhithSuccess() {
        int itemCount = DI.getMeetingApiService().getMeetings().size();
        onView(withId(R.id.add_item)).perform(click());
        final String item = "Room 3";
        onData(allOf(is(instanceOf(String.class)), is(item))).perform();
        onView(withId(R.id.meeting_name)).perform(replaceText("Test meeting"));
        onView(withId(R.id.meeting_members)).perform(replaceText("jack@lamzone.com"));
        onView(withId(R.id.date_input)).perform(replaceText("19-05-2022"));
        onView(withId(R.id.input_hour)).perform(replaceText("9h00"));
        onView(withId(R.id.add_button)).perform(click());
        onView(withId(R.id.meeting_list)).check(withItemCount(itemCount+1));
    }

}