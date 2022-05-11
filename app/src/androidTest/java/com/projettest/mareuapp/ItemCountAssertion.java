package com.projettest.mareuapp;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;

public class ItemCountAssertion implements ViewAssertion {

    private final Matcher<Integer> matcher;

    public static ItemCountAssertion withItemCount(int expectedCount) {
        return withItemCount(Matchers.is(expectedCount));
    }

    public static ItemCountAssertion withItemCount(Matcher<Integer> matcher) {
        return new ItemCountAssertion(matcher);
    }

    private ItemCountAssertion(Matcher<Integer> matcher) {
        this.matcher = matcher;
    }
    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        if (noViewFoundException != null) {
            throw noViewFoundException;
        }

        RecyclerView recyclerView = (RecyclerView) view;
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        Assert.assertThat(adapter.getItemCount(), matcher);
    }
}
