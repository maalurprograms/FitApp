package com.m306.fitapp.fitapp.Controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public final class FitnessplanContract {

    public FitnessplanContract(){}

    public static abstract class FitnessplanEntry implements BaseColumns{
        public static final String TABLE_NAME = "FITNESSPLAN";
        public static final String COLUMN_NAME_NAME = "name";
    }

    public static  abstract class ExerciseEntry implements BaseColumns{
        public static final String TABLE_NAME = "UEBUNG";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_WIEDERHOLUNGEN = "wiederholungen";
        public static final String COLUMN_NAME_FITNESSPLAN = "id_fitnessplan";
    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String FLOAT_TYPE = " REAL";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_FITNESSPLAN =
            "CREATE TABLE " + FitnessplanEntry.TABLE_NAME + " (" +
                    FitnessplanEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    FitnessplanEntry.COLUMN_NAME_NAME + TEXT_TYPE +
                    " )";

    private static final String SQL_CREATE_UEBUNG =
            "CREATE TABLE " + ExerciseEntry.TABLE_NAME + " (" +
                    ExerciseEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL" + COMMA_SEP +
                    ExerciseEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    ExerciseEntry.COLUMN_NAME_WIEDERHOLUNGEN + INT_TYPE + COMMA_SEP +
                    ExerciseEntry.COLUMN_NAME_FITNESSPLAN + INT_TYPE +
                    " )";

    private static final String SQL_DELETE_FITNESSPLAN =
            "DROP TABLE IF EXISTS " + FitnessplanEntry.TABLE_NAME;

    private static final String SQL_DELETE_UEBUNG =
            "DROP TABLE IF EXISTS " + ExerciseEntry.TABLE_NAME;

    public static class FitnessplanDbHelper extends SQLiteOpenHelper {
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "FitnessplanEntry.db";

        public FitnessplanDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_FITNESSPLAN);
            db.execSQL(SQL_CREATE_UEBUNG);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SQL_DELETE_FITNESSPLAN);
            db.execSQL(SQL_DELETE_UEBUNG);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }

}