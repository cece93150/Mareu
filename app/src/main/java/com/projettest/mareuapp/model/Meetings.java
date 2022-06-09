package com.projettest.mareuapp.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Meetings {
/**
 * Model object representing a Meeting
 */

    /**
     * Color of logo
     */
    private int color;

    /**
     * name of meeting
     */
    private String nameOfMeeting;

    /**
     * Date of meeting
     */
    private LocalDate date;

    /**
     * Hour of meeting start
     */
    private LocalTime hour;

    /**
     * meeting room
     */
    private String room;

    /**
     * members of meeting
     */
    private String members;

    /**
     * id of meeting
     */
    public int id;

    /**
     * Constructor
     *
     * @param id
     * @param color
     * @param nameOfMeeting
     * @param date
     * @param hour
     * @param room
     * @param members
     */
    public Meetings(int id, int color, String nameOfMeeting, LocalDate date, LocalTime hour, String room, String members) {
        this.id = id;
        this.color = color;
        this.nameOfMeeting = nameOfMeeting;
        this.date = date;
        this.hour = hour;
        this.room = room;
        this.members = members;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getNameOfMeeting() {
        return nameOfMeeting;
    }

    public void setNameOfMeeting(String nameOfMeeting) {
        this.nameOfMeeting = nameOfMeeting;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

}
