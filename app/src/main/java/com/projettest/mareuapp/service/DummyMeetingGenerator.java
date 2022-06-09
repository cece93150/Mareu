package com.projettest.mareuapp.service;

import com.projettest.mareuapp.model.Meetings;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingGenerator {

    public static List<Meetings> DUMMY_MEETINGS = Arrays.asList(
            new Meetings(1, 0x44BF0909, "Meeting A", LocalDate.of(2022, 6, 10), LocalTime.of(8, 0), "Room 1", "jack@lamzone.com, john@lamzone.com, julie@lamzone.com"),
            new Meetings(2, 0xFFFF9800, "Meeting B", LocalDate.of(2022, 6, 11), LocalTime.of(9, 0), "Room 2", "jack@lamzone.com, john@lamzone.com, julie@lamzone.com"),
            new Meetings(3, 0xFFFFEB3B, "Meeting C", LocalDate.of(2022, 6, 12), LocalTime.of(10, 0), "Room 3", "jack@lamzone.com, john@lamzone.com, julie@lamzone.com"),
            new Meetings(4, 0xFF8BC34A, "Meeting D", LocalDate.of(2022, 6, 13), LocalTime.of(11, 0), "Room 4", "jack@lamzone.com, john@lamzone.com, julie@lamzone.com"),
            new Meetings(5, 0xFF009688, "Meeting E", LocalDate.of(2022, 6, 14), LocalTime.of(12, 0), "Room 5", "jack@lamzone.com, john@lamzone.com, julie@lamzone.com"),
            new Meetings(6, 0xFF03A9F4, "Meeting F", LocalDate.of(2022, 6, 15), LocalTime.of(8, 0), "Room 6", "jack@lamzone.com, john@lamzone.com, julie@lamzone.com"),
            new Meetings(7, 0xFF3F51B5, "Meeting G", LocalDate.of(2022, 6, 10), LocalTime.of(9, 0), "Room 7", "jack@lamzone.com, john@lamzone.com, julie@lamzone.com"),
            new Meetings(8, 0xFF673AB7, "Meeting H", LocalDate.of(2022, 6, 11), LocalTime.of(10, 0), "Room 8", "jack@lamzone.com, john@lamzone.com, julie@lamzone.com"),
            new Meetings(9, 0xFF9C27B0, "Meeting I", LocalDate.of(2022, 6, 12), LocalTime.of(11, 0), "Room 9", "jack@lamzone.com, john@lamzone.com, julie@lamzone.com"),
            new Meetings(10, 0xFFF14D9A, "Meeting J", LocalDate.of(2022, 6, 13), LocalTime.of(12, 0), "Room 10", "jack@lamzone.com, john@lamzone.com, julie@lamzone.com"),
            new Meetings(11, 0x44BF0909, "Meeting J", LocalDate.of(2022, 6, 13), LocalTime.of(12, 0), "Room 1", "jack@lamzone.com, john@lamzone.com, julie@lamzone.com")
    );
    public static List<String> DUMMY_ROOMS = Arrays.asList(
            "Room 1",
            "Room 2",
            "Room 3",
            "Room 4",
            "Room 5",
            "Room 6",
            "Room 7",
            "Room 8",
            "Room 9",
            "Room 10");


    public static List<Meetings> createMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }


}
