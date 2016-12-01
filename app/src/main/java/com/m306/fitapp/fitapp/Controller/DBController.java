package com.m306.fitapp.fitapp.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.m306.fitapp.fitapp.Controller.FitnessplanContract.*;
import com.m306.fitapp.fitapp.Model.Fitnessplan;
import com.m306.fitapp.fitapp.Model.Exercise;

import java.util.ArrayList;
import java.util.Map;

public class DBController {

    public static SQLiteDatabase DB;

    public static void setDatabase(Context context){
        FitnessplanDbHelper DbHelper = new FitnessplanDbHelper(context);
        DB = DbHelper.getWritableDatabase();
    }

    public static ArrayList<Fitnessplan> getAllFitnessplan(){

        ArrayList<Fitnessplan> fitnessplans = new ArrayList<>();

        String[] semesterProjection = {
                FitnessplanEntry._ID,
                FitnessplanEntry.COLUMN_NAME_NAME
        };

        Cursor cursor = DB.query(
                FitnessplanEntry.TABLE_NAME,
                semesterProjection,
                null,
                null,
                null,
                null,
                null
        );

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            long fitnessplanID = cursor.getInt(cursor.getColumnIndexOrThrow(FitnessplanEntry._ID));
            Fitnessplan semester = new Fitnessplan(
                    cursor.getLong(cursor.getColumnIndexOrThrow(FitnessplanEntry._ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(FitnessplanEntry.COLUMN_NAME_NAME)),
                    getExerciseFromFitnessplan(fitnessplanID)
            );
            fitnessplans.add(semester);
            cursor.moveToNext();
        }
        return fitnessplans;
    }

    public static ArrayList<Exercise> getExerciseFromFitnessplan(long id){
        ArrayList<Exercise> exercises =  new ArrayList<>();

        String[] subjectProjection = {
                ExerciseEntry._ID,
                ExerciseEntry.COLUMN_NAME_NAME,
                ExerciseEntry.COLUMN_NAME_WIEDERHOLUNGEN,
        };

        String selection = ExerciseEntry.COLUMN_NAME_FITNESSPLAN + " LIKE ?";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor cursorFitnessplanModel = DB.query(
                ExerciseEntry.TABLE_NAME,
                subjectProjection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        cursorFitnessplanModel.moveToFirst();
        while(!cursorFitnessplanModel.isAfterLast()){
            Exercise exercise = new Exercise(
                    cursorFitnessplanModel.getLong(cursorFitnessplanModel.getColumnIndexOrThrow(ExerciseEntry._ID)),
                    cursorFitnessplanModel.getString(cursorFitnessplanModel.getColumnIndexOrThrow(ExerciseEntry.COLUMN_NAME_NAME)),
                    cursorFitnessplanModel.getInt(cursorFitnessplanModel.getColumnIndexOrThrow(ExerciseEntry.COLUMN_NAME_WIEDERHOLUNGEN))
            );
            exercises.add(exercise);
            cursorFitnessplanModel.moveToNext();
        }
        return exercises;
    }

    public static long insert(String tableName, Map<String,String> valueMap){
        ContentValues values = new ContentValues();
        for (Map.Entry<String ,String> entry:valueMap.entrySet()) {
            values.put(entry.getKey(), entry.getValue());
        }
        return DB.insert(
                tableName,
                "null",
                values
        );
    }

    public static void update(long id, String newValue, String tableName, String columnToUpdate, String IDColumn){
        ContentValues values = new ContentValues();
        values.put(columnToUpdate, newValue);

        String selection = IDColumn + " LIKE ?";
        String[] selectionArgs = {String.valueOf(id)};

        DB.update(
                tableName,
                values,
                selection,
                selectionArgs
        );
    }

    public static void delete(long id, String tableName, String IDColumn){
        String selection = IDColumn + " LIKE ?";
        String[] selectionArgs = {String.valueOf(id)};

        DB.delete(
                tableName,
                selection,
                selectionArgs
        );
    }
}