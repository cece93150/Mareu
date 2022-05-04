package com.projettest.mareuapp.controler;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.projettest.mareuapp.R;
import com.projettest.mareuapp.di.DI;
import com.projettest.mareuapp.model.Meetings;
import com.projettest.mareuapp.service.DummyMeetingGenerator;
import com.projettest.mareuapp.views.RecyclerViewAdapter;
import com.projettest.mareuapp.service.MeetingApiService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class FilterChoiceFragment extends DialogFragment {
    private MeetingApiService mApiService;
    private RecyclerViewAdapter mAdapter = null;
    private List<Meetings> mMeetings;
    private String mRoom;

    Spinner mSpinner;
    EditText mDateInput;
    Button mRoomFilter;
    Button mDateFilter;

    public void setParentAdapter(RecyclerViewAdapter adapter) {
        this.mAdapter = adapter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter, container, false);

        mApiService = DI.getMeetingApiService();

        mSpinner = view.findViewById(R.id.sp_room);
        mRoomFilter = view.findViewById(R.id.button_room_filter);
        mDateInput = view.findViewById(R.id.date_input);
        mDateFilter = view.findViewById(R.id.button_date_filter);

        //Spinner meeting room list
        List<Meetings> meetings = DummyMeetingGenerator.DUMMY_MEETINGS;
        List<String> result = new ArrayList<>();
        for (Meetings meeting : meetings) {
            String roomName = meeting.getRoom();
            result.add(roomName);
        }
        result.add(0, "Toutes les salles");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), android.R.layout.simple_spinner_item, result);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);

        //Recuperation de la room
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mRoom = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Filtrage par la room quand le boutton est cliqué
        mRoomFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMeetings = mApiService.getMeetingsByRoom(mRoom);
                if (mAdapter != null) {
                    mAdapter.upDateMeetings(mMeetings);
                }
                dismiss();
            }
        });

        //DateTimePikerDialog
        mDateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimeDialog(mDateInput);
            }
        });

        //Filtrage par la date quand le bouton est cliqué
        mDateFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMeetings = mApiService.getMeetingsByDate(mDateInput.getText().toString());
                String date = mDateInput.getText().toString();
                if (mAdapter != null) {
                    if (date.matches(""))
                        Toast.makeText(getContext(), "Vous devez renseigner une date de réunion !", Toast.LENGTH_SHORT).show();
                    else {
                        mAdapter.upDateMeetings(mMeetings);
                        dismiss();
                    }
                }

            }
        });

        return view;
    }

    private void showDateTimeDialog(final EditText editTextToUpdate) {
        final Calendar calendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Définie la valeur de la date dans le calendrier
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

                editTextToUpdate.setText(simpleDateFormat.format(calendar.getTime()));

            }
        };
        new DatePickerDialog(getView().getContext(), dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
