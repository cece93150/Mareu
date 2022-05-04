package com.projettest.mareuapp.service;

import com.projettest.mareuapp.model.Meetings;

import java.util.ArrayList;
import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {

    private List<Meetings> meetings = DummyMeetingGenerator.createMeetings();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Meetings> getMeetings() {
        return meetings;
    }

    /**
     * {@inheritDoc}
     *
     * @param meeting
     */
    @Override
    public void addMeeting(Meetings meeting) {
        if (!meeting.getNameOfMeeting().equals("") && !meeting.getDate().equals("") && !meeting.getHour().equals("") && !meeting.getRoom().equals("Choisir une salle") && !meeting.getMembers().equals("")) {
            meetings.add(meeting);
        } else {
            throw new IllegalArgumentException();
        }
    }


    @Override
    public void deleteMeeting(int position) {
        meetings.remove(position);
    }


    @Override
    public List<Meetings> getMeetingsByRoom(String room) {
        List<Meetings> result = new ArrayList<>();
        if (room.equals("Toutes les salles")) {
            return meetings;
        }
        for (Meetings meeting : meetings) {
            if (meeting.getRoom().equals(room)) {
                result.add(meeting);
            }
        }
        return result;
    }

    @Override
    public List<Meetings> getMeetingsByDate(String date) {
        List<Meetings> result = new ArrayList<>();
        for (Meetings meeting : meetings) {
            if (meeting.getDate().equals(date)) {
                result.add(meeting);
            }
        }
     return result;
    }
}