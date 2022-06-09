package com.projettest.mareuapp.controler;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.projettest.mareuapp.di.DI;
import com.projettest.mareuapp.model.Meetings;
import com.projettest.mareuapp.service.DummyMeetingGenerator;
import com.projettest.mareuapp.service.MeetingApiService;
import com.projettest.mareuapp.R;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddMeetingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private MeetingApiService mApiService;
    private int newMeetingColor;
    private String room;
    private LocalDate date;
    private LocalTime hour;
    private String name;
    private String members;
    private int id;


    Spinner mSpinner;
    EditText mNameInput;
    EditText mDateInput;
    EditText mHourInput;
    EditText mMembersInput;
    Button mButtonNewMeeting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        mApiService = DI.getMeetingApiService();
        mDateInput = findViewById(R.id.input_date);
        mHourInput = findViewById(R.id.input_hour);
        mSpinner = findViewById(R.id.spinner_room_choice);
        mNameInput = findViewById(R.id.input_name);
        mMembersInput = findViewById(R.id.input_members);
        mButtonNewMeeting = findViewById(R.id.add_button);

        // masquer le clavier lorsque l'on clique sur l'EditText
        mDateInput.setInputType(InputType.TYPE_NULL);
        mHourInput.setInputType(InputType.TYPE_NULL);

        SpinnerRoom();
        EditDateAndAddMeeting();
    }


    //Get the meeting room chose
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        room = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void showDateDialog(EditText editTextDateInput) {
        final Calendar calendar = Calendar.getInstance();
        //creating a DateSet Listener
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                date = LocalDate.of(year, month + 1, dayOfMonth);
                //Définir la valeur de la date dans le calendrier
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                //Format de la date
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                //Définir la date dans l'EditText
                mDateInput.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };

        //creating DatePicker Dialog
        new DatePickerDialog(AddMeetingActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void ShowTimeDialog(EditText editTextTimeInput) {
        final Calendar calendar = Calendar.getInstance();
        //creating a TimeSet Listener
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //Définir la valeur de l'heure dans le calendrier
                hour = LocalTime.of(hourOfDay, minute);
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                //Format de l'heure
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH'h'mm");
                //Définir l'heure dans l'EditText
                mHourInput.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        //creating TimePiker Dialog
        new TimePickerDialog(AddMeetingActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
    }

    //obtenir la couleur en fonction du choix de la room
    public int getRoomColor(Spinner mSpinner) {
        switch (room) {
            case "Room 1":
                newMeetingColor = 0x44BF0909;
                break;
            case "Room 2":
                newMeetingColor = 0xFFFF9800;
                break;
            case "Room 3":
                newMeetingColor = 0xFFFFEB3B;
                break;
            case "Room 4":
                newMeetingColor = 0xFF8BC34A;
                break;
            case "Room 5":
                newMeetingColor = 0xFF009688;
                break;
            case "Room 6":
                newMeetingColor = 0xFF03A9F4;
                break;
            case "Room 7":
                newMeetingColor = 0xFF3F51B5;
                break;
            case "Room 8":
                newMeetingColor = 0xFF673AB7;
                break;
            case "Room 9":
                newMeetingColor = 0xFF9C27B0;
                break;
            case "Room 10":
                newMeetingColor = 0xFFF14D9A;
                break;
        }
        return newMeetingColor;
    }

    //Ajouter la réunion créée à la liste des réunions
    public void createMeeting() {

        int color = getRoomColor(mSpinner);

        Meetings meetings = new Meetings(id, color, name, date, hour, room, members);

        try {
            mApiService.addMeeting(meetings);
        } catch (IllegalArgumentException e) {
            //
            Toast.makeText(getBaseContext(), "Input Field is Empty", Toast.LENGTH_LONG).show();
        }
    }

    public void SpinnerRoom() {
        //Spinner meeting room list
        List<String> rooms = DummyMeetingGenerator.DUMMY_ROOMS;
        List<String> result = new ArrayList<>(rooms);
        result.add(0, "Choisir une salle");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, result);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(this);
    }

    public void EditDateAndAddMeeting() {
        //editer la date et l'heure
        mDateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(mDateInput);
            }
        });
        mHourInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowTimeDialog(mHourInput);
            }
        });

        //ajoute un nouveau meeting lorsque le bouton est clické
        mButtonNewMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //collecte les données saisie
                name = mNameInput.getText().toString();
                members = mMembersInput.getText().toString();

                //vérifie que les champs de données sont remplies
                if (room.matches("Choisir une salle"))
                    Toast.makeText(AddMeetingActivity.this, "Vous devez renseigner une salle de réunion !", Toast.LENGTH_SHORT).show();
                else if (name.matches(""))
                    Toast.makeText(AddMeetingActivity.this, "Vous devez renseigner un nom de réunion !", Toast.LENGTH_SHORT).show();
                else if (date == null)
                    Toast.makeText(AddMeetingActivity.this, "Vous devez renseigner une date de réunion !", Toast.LENGTH_SHORT).show();
                else if (hour == null)
                    Toast.makeText(AddMeetingActivity.this, "Vous devez renseigner une heure de réunion !", Toast.LENGTH_SHORT).show();
                else if (members.matches(""))
                    Toast.makeText(AddMeetingActivity.this, "Vous devez renseigner un participant !", Toast.LENGTH_SHORT).show();
                else {
                    createMeeting();
                    finish();
                }
            }
        });
    }
}



