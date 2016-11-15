package com.m306.fitapp.fitapp.Model;

/**
 * Created by jonas on 08.11.16.
 */

public class Exercise {
    public long ID;
    public String Name;
    public int Repetition;

    public Exercise(long ID, String name, int repetition) {
        this.ID = ID;
        Name = name;
        Repetition = repetition;
    }
}
