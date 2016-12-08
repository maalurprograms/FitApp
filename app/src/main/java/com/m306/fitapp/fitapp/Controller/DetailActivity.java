package com.m306.fitapp.fitapp.Controller;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.m306.fitapp.fitapp.Adapters.ExerciceAdapter;
import com.m306.fitapp.fitapp.Adapters.FitnessplanAdapter;
import com.m306.fitapp.fitapp.Model.Exercise;
import com.m306.fitapp.fitapp.Model.Fitnessplan;
import com.m306.fitapp.fitapp.R;

import java.util.ArrayList;
import java.util.HashMap;

public class DetailActivity extends AppCompatActivity {

    private long CurrentPlanID;
    private String CurrentPlanName;
    private ArrayList<Exercise> Exercices;
    private android.content.Context Context;
    private ExerciceAdapter ExerciseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        CurrentPlanName = getIntent().getExtras().get("CURRENT_SUBJECT_NAME").toString();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(CurrentPlanName);
        setSupportActionBar(toolbar);

        Context = this;

        CurrentPlanID = Long.valueOf(getIntent().getExtras().get("CURRENT_SUBJECT_ID").toString());
        Exercices = DBController.getExerciseFromFitnessplan(CurrentPlanID);
        generateOverview();
    }

    private void generateOverview(){

        RecyclerView exerciseList = (RecyclerView) findViewById(R.id.exercise_list);
        ExerciseAdapter = new ExerciceAdapter(Exercices, Context);
        exerciseList.setAdapter(ExerciseAdapter);
        exerciseList.setLayoutManager(new LinearLayoutManager(Context));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Dialog("Übung erstellen", "Geben Sie einen Namen ein:", "Geben Sie die Anzahl Wiederholungen ein.", "Beispiel", "0", Context, "Übung wurde erstellt.") {
                    @Override
                    public void save() {
                        long ID = DBController.insert(FitnessplanContract.ExerciseEntry.TABLE_NAME, new HashMap<String, String>(){{
                            put(FitnessplanContract.ExerciseEntry.COLUMN_NAME_NAME, Name);
                            put(FitnessplanContract.ExerciseEntry.COLUMN_NAME_WIEDERHOLUNGEN, String.valueOf(Repetitions));
                            put(FitnessplanContract.ExerciseEntry.COLUMN_NAME_FITNESSPLAN, String.valueOf(CurrentPlanID));
                        }});
                        Exercices.add(new Exercise(ID, Name, Repetitions));
                        ExerciseAdapter.notifyDataSetChanged();
                    }
                }.show();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_overview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up Button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            new Dialog("Fitnessplan bearbeiten", CurrentPlanName, Context, "Änderungen gespeichert.") {
                @Override
                public void save() {
                    DBController.update(CurrentPlanID, Name, FitnessplanContract.FitnessplanEntry.TABLE_NAME, FitnessplanContract.FitnessplanEntry.COLUMN_NAME_NAME, FitnessplanContract.FitnessplanEntry._ID);
                    getSupportActionBar().setTitle(Name);
                }
            }.show();
        }

        return super.onOptionsItemSelected(item);
    }


}
