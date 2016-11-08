package com.m306.fitapp.fitapp.Model;

/**
 * Created by jonas on 08.11.16.
 */

public class Exercise {
    public long ID;
    public String Name;
    public int Wiederholungen;

    public Exercise(long ID, String name, int wiederholungen) {
        this.ID = ID;
        Name = name;
        Wiederholungen = wiederholungen;
    }
}
