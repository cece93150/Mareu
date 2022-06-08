package com.projettest.mareuapp.service;

import com.projettest.mareuapp.model.Meetings;

import java.time.LocalDate;
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
     *
     * @param
     */
    void deleteMeeting(Meetings meetings);

    /**
     * create a meeting
     *
     * @param meetings
     */
    void addMeeting(Meetings meetings);

    /**
     * Get meetings by room
     *
     * @return List
     */
    List<Meetings> getMeetingsByRoom(String room);

    /**
     * Get meetings by date
     *
     * @return List
     */
    List<Meetings> getMeetingsByDate(LocalDate date);


}