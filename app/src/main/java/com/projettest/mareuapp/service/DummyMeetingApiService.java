package com.projettest.mareuapp.service;

import com.projettest.mareuapp.model.Meetings;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {

    private List<Meetings> mMeetings = DummyMeetingGenerator.createMeetings();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Meetings> getMeetings() {
        return mMeetings;
    }

    /**
     * {@inheritDoc}
     *
     * @param meeting
     */
    @Override
    public void addMeeting(Meetings meeting) {
        if (!meeting.getNameOfMeeting().equals("") && !meeting.getDate().equals("") && !meeting.getHour().equals("") && !meeting.getRoom().equals("Choisir une salle") && !meeting.getMembers().equals("")) {
            mMeetings.add(meeting);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void deleteMeeting(Meetings meeting) {
        mMeetings.remove(meeting);
    }


    @Override
    public List<Meetings> getMeetingsByRoom(String room) {
        List<Meetings> result = new ArrayList<>();
        if (room.equals("Toutes les salles")) {
            return mMeetings;
        }
        for (Meetings meeting : mMeetings) {
            if (meeting.getRoom().equals(room)) {
                result.add(meeting);
            }
        }
        return result;
    }

    @Override
    public List<Meetings> getMeetingsByDate(LocalDate date) {
        List<Meetings> result = new ArrayList<>();
        if (date == null) {
            return mMeetings;
        }
        for (Meetings meeting : mMeetings) {
            if (meeting.getDate().equals(date)) {
                result.add(meeting);
            }
        }
        return result;
    }
}