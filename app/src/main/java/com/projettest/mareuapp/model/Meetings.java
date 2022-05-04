package com.projettest.mareuapp.model;

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
    private String date;

    /**
     * Hour of meeting start
     */
    private String hour;

    /**
     * meeting room
     */
    private String room;

    /**
     * members of meeting
     */
    private String members;


    /**
     * Constructor
     *
     * @param color
     * @param nameOfMeeting
     * @param date
     * @param hour
     * @param room
     * @param members
     */
    public Meetings(int color, String nameOfMeeting, String date, String hour, String room, String members) {
        this.color = color;
        this.nameOfMeeting = nameOfMeeting;
        this.date = date;
        this.hour = hour;
        this.room = room;
        this.members = members;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
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
