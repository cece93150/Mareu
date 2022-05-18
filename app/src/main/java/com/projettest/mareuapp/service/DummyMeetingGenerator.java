package com.projettest.mareuapp.service;

import com.projettest.mareuapp.model.Meetings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingGenerator {

    public static List<Meetings> DUMMY_MEETINGS = Arrays.asList(
            new Meetings(1, 0x44BF0909, "Meeting A", "10-05-2022", "8h00", "Room 1", "jack@lamzone.com, john@lamzone.com, julie@lamzone.com"),
            new Meetings(2, 0xFFFF9800, "Meeting B", "11-05-2022", "9h00", "Room 2", "jack@lamzone.com, john@lamzone.com, julie@lamzone.com"),
            new Meetings(3, 0xFFFFEB3B, "Meeting C", "12-05-2022", "10h00", "Room 3", "jack@lamzone.com, john@lamzone.com, julie@lamzone.com"),
            new Meetings(4, 0xFF8BC34A, "Meeting D", "13-05-2022", "8h00", "Room 4", "jack@lamzone.com, john@lamzone.com, julie@lamzone.com"),
            new Meetings(5, 0xFF009688, "Meeting E", "14-05-2022", "11h00", "Room 5", "jack@lamzone.com, john@lamzone.com, julie@lamzone.com"),
            new Meetings(6, 0xFF03A9F4, "Meeting F", "15-05-2022", "11h00", "Room 6", "jack@lamzone.com, john@lamzone.com, julie@lamzone.com"),
            new Meetings(7, 0xFF3F51B5, "Meeting G", "10-05-2022", "8h00", "Room 7", "jack@lamzone.com, john@lamzone.com, julie@lamzone.com"),
            new Meetings(8, 0xFF673AB7, "Meeting H", "11-05-2022", "8h00", "Room 8", "jack@lamzone.com, john@lamzone.com, julie@lamzone.com"),
            new Meetings(9, 0xFF9C27B0, "Meeting I", "12-05-2022", "9h00", "Room 9", "jack@lamzone.com, john@lamzone.com, julie@lamzone.com"),
            new Meetings(10, 0xFFF14D9A, "Meeting J", "13-05-2022", "9h00", "Room 10", "jack@lamzone.com, john@lamzone.com, julie@lamzone.com"),
            new Meetings(11, 0x44BF0909, "Meeting K", "14-05-2022", "10h00", "Room 1", "jack@lamzone.com, john@lamzone.com, julie@lamzone.com"),
            new Meetings(12, 0xFFFF9800, "Meeting L", "15-05-2022", "10h00", "Room 2", "jack@lamzone.com, john@lamzone.com, julie@lamzone.com")
    );

    public static List<Meetings> createMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }


}
