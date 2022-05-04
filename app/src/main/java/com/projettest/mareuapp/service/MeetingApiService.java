package com.projettest.mareuapp.service;

import com.projettest.mareuapp.model.Meetings;

import java.util.List;

public interface MeetingApiService {

/**
 * Meeting API client
 */

    /**
     * get all my meetings
     * return {@link List}
     */
    List<Meetings> getMeetings();

    /**
     * delete a meeting
     * @param position
     */
    void deleteMeeting(int position);

    /**
     * create a meeting
     * @param meetings
     */
    void addMeeting(Meetings meetings);

    /**
     * Get meetings by room
     * @return List
     */
    List<Meetings> getMeetingsByRoom(String room);

    /**
     * Get meetings by date
     * @return  List
     */
    List<Meetings> getMeetingsByDate(String date);


}