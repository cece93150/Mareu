package com.projettest.mareuapp.controler;

import static androidx.test.InstrumentationRegistry.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import com.projettest.mareuapp.views.RecyclerViewAdapter;
import com.projettest.mareuapp.di.DI;
import com.projettest.mareuapp.model.Meetings;
import com.projettest.mareuapp.service.MeetingApiService;
import com.projettest.mareuapp.R;

import java.time.LocalDate;
import java.util.List;

public class ListActivity extends AppCompatActivity implements FilterChoiceFragment.OnFilterListener {

    private MeetingApiService mApiService;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    EditText mDateInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        this.configureToolbar();

        mApiService = DI.getMeetingApiService();
        mRecyclerView = findViewById(R.id.meeting_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        ImageButton addButton = findViewById(R.id.add_item);
        addButton.setOnClickListener(v -> {
            Intent addMeetingIntent = new Intent(ListActivity.this, AddMeetingActivity.class);
            startActivity(addMeetingIntent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu and add it to the Toolbar
        getMenuInflater().inflate(R.menu.menu_activity_list, menu);
        return true;
    }

    private void configureToolbar() {
        // Get the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Handle actions on menu items
        if (item.getItemId() == R.id.menu_filter) {
            //Display dialog
            FilterChoiceFragment FilterFragment = new FilterChoiceFragment();
            FilterFragment.setParentAdapter(mAdapter);
            FilterFragment.show(getSupportFragmentManager(), "Dialog");
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }

    }

    //Init the list of meeting
    private void initList() {
        List<Meetings> meetings = mApiService.getMeetings();
        mAdapter = new RecyclerViewAdapter(meetings);
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onFilterByRoom(String room) {
        List<Meetings> mMeetings = mApiService.getMeetingsByRoom(room);
        if (mAdapter != null) {
            mAdapter.upDateMeetings(mMeetings);
        }
    }

    @Override
    public void onFilterByDate(LocalDate date) {
        List<Meetings> mMeetings = mApiService.getMeetingsByDate(date);
        if (mAdapter != null) {
            mAdapter.upDateMeetings(mMeetings);
        }
    }
}


