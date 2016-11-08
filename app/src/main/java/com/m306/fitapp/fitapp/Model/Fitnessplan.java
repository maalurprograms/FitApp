package com.m306.fitapp.fitapp.Model;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by jonas on 08.11.16.
 */

public class Fitnessplan {

    public long ID;
    public String Name;
    public ArrayList<Exercise> Exercises;

    public Fitnessplan(long ID, String name, ArrayList<Exercise> exercises) {
        this.ID = ID;
        Name = name;
        Exercises = exercises;
    }
}
