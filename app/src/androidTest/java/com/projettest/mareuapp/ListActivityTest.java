package com.projettest.mareuapp;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.contrib.PickerActions.setDate;
import static androidx.test.espresso.contrib.PickerActions.setTime;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.projettest.mareuapp.ItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.AllOf.allOf;



import android.widget.DatePicker;
import android.widget.TimePicker;


import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.projettest.mareuapp.controler.ListActivity;
import com.projettest.mareuapp.di.DI;


import org.junit.Before;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;




/**
 * Instrumented test, which will execute on an Android device.
 */
@RunWith(AndroidJUnit4.class)
public class ListActivityTest {

    @Rule
    public ActivityTestRule<ListActivity> mActivityRule = new ActivityTestRule<>(ListActivity.class);

    @Before
    public void setUp() {
        ListActivity activity = mActivityRule.getActivity();
        assertThat(activity, notNullValue());
    }

    @Test
    public void meeting_List_Is_Not_Empty() {
        int itemCount = DI.getMeetingApiService().getMeetings().size();
        onView(withId(R.id.meeting_list)).check(withItemCount(itemCount));
    }

    @Test
    public void delete_Meeting_With_Success() {
        int itemCount = DI.getMeetingApiService().getMeetings().size();
        onView((withId(R.id.meeting_list))).perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        onView((withId(R.id.meeting_list))).check((withItemCount(itemCount - 1)));
    }

    @Test
    public void add_New_Meeting_With_Success() {
        int itemCount = DI.getMeetingApiService().getMeetings().size();
        onView(withId(R.id.add_item)).perform(click());
        final String item = "Room 3";
        onData(allOf(is(instanceOf(String.class)), is(item))).perform();
        onView(withId(R.id.input_name)).perform(replaceText("Test meeting"));
        onView(withId(R.id.input_members)).perform(replaceText("jack@lamzone.com"));
        onView(withId(R.id.input_date)).perform(click());
        onView(isAssignableFrom(DatePicker.class)).perform(setDate(2022, 6, 15));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.input_hour)).perform(click());
        onView(isAssignableFrom(TimePicker.class)).perform(setTime(8,0));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.add_button)).perform(click());
        onView(withId(R.id.meeting_list)).check(withItemCount(itemCount + 1));
    }

    @Test
    public void filter_Meeting_By_Room_With_Success() {
        onView(withId(R.id.menu_filter)).perform(click());
        final String item = "Room 4";
        onData(allOf(is(instanceOf(String.class)), is(item))).perform();
        onView(withId(R.id.button_room_filter)).perform(click());
        onView(withId(R.id.meeting_list)).check(withItemCount(1));
    }

    @Test
    public void filter_Meeting_By_Date_With_Success() {
        onView(withId(R.id.menu_filter)).perform(click());
        onView(withId(R.id.date_input)).perform(click());
        onView(isAssignableFrom(DatePicker.class)).perform(setDate(2022, 6, 10));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.button_date_filter)).perform(click());
        onView(withId(R.id.meeting_list)).check(withItemCount(2));
    }


}