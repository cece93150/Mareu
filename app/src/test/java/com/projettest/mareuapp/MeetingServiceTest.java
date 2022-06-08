package com.projettest.mareuapp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;


import com.projettest.mareuapp.di.DI;
import com.projettest.mareuapp.model.Meetings;
import com.projettest.mareuapp.service.DummyMeetingGenerator;
import com.projettest.mareuapp.service.MeetingApiService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Unit test on Meeting service
 */
@RunWith(JUnit4.class)
public class MeetingServiceTest {

    private MeetingApiService mApiService;

    @Before
    public void setup() {
        mApiService = DI.getNewInstanceForTest();
    }

    @Test
    public void getMeetingWithSuccess() {
        List<Meetings> meetings = mApiService.getMeetings();
        List<Meetings> expectedMeetings = DummyMeetingGenerator.DUMMY_MEETINGS;
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeetings.toArray()));
    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meetings meetingsToDelete = mApiService.getMeetings().get(3);
        mApiService.deleteMeeting(meetingsToDelete);
        assertFalse(mApiService.getMeetings().contains(meetingsToDelete));
    }

    @Test
    public void addMeetingWithSuccess() {
        Meetings meetingToAdd = new Meetings(17, 0xFFEF5350, "Test meeting", LocalDate.of(2022,6,15), LocalTime.of(9,0), "Room 5", "jack@lamzon.com");
        mApiService.addMeeting(meetingToAdd);
        assertTrue(mApiService.getMeetings().contains(meetingToAdd));
    }

    @Test
    public void filterMeetingByRoomWithSuccess() {
        Meetings meetingInRoom1 = new Meetings(16, 0x44BF0909, "Test meeting", LocalDate.of(2022,6,20), LocalTime.of(9,0), "Room 1", "jack@lamzone.com");
        mApiService.addMeeting(meetingInRoom1);
        assertFalse(mApiService.getMeetingsByRoom("Room 2").contains(meetingInRoom1));
        assertTrue(mApiService.getMeetingsByRoom("Room 1").contains(meetingInRoom1));
    }

    @Test
    public void filterMeetingByDateWithSuccess() {
        Meetings meetingOnDate = new Meetings(15, 0xFFFF9800, "Réunion Test", LocalDate.of(2022,6,16), LocalTime.of(8,0), "Room 2", "john@lamzone.com");
        mApiService.addMeeting(meetingOnDate);
        assertFalse(mApiService.getMeetingsByDate(LocalDate.of(2022,6,18)).contains(meetingOnDate));
        assertTrue(mApiService.getMeetingsByDate(LocalDate.of(2022,6,16)).contains(meetingOnDate));
    }
}