package com.m306.fitapp.fitapp.Controller;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.m306.fitapp.fitapp.Adapters.FitnessplanAdapter;
import com.m306.fitapp.fitapp.Model.Exercise;
import com.m306.fitapp.fitapp.Model.Fitnessplan;
import com.m306.fitapp.fitapp.R;

import java.util.ArrayList;
import java.util.HashMap;

public class OverviewActivity extends AppCompatActivity {

    ArrayList<Fitnessplan> Fitnessplans;
    Context Context;
    com.m306.fitapp.fitapp.Adapters.FitnessplanAdapter FitnessplanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Context = this;
        DBController.setDatabase(Context);

        Fitnessplans = DBController.getAllFitnessplan();
        generateOverview();
    }

    private void generateOverview(){

        RecyclerView planList = (RecyclerView) findViewById(R.id.plan_list);
        FitnessplanAdapter = new FitnessplanAdapter(Fitnessplans, Context);
        planList.setAdapter(FitnessplanAdapter);
        planList.setLayoutManager(new LinearLayoutManager(Context));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Dialog("Neuer Fitnessplan.","Name des Fitnessplans",Context) {
                    @Override
                    public void save() {
                        long ID = DBController.insert(FitnessplanContract.FitnessplanEntry.TABLE_NAME, new HashMap<String, String>(){{
                            put(FitnessplanContract.FitnessplanEntry.COLUMN_NAME_NAME, Name);
                        }});
                        Fitnessplans.add(new Fitnessplan(ID, Name, new ArrayList<Exercise>()));
                        FitnessplanAdapter.notifyDataSetChanged();
                    }
                }.show();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        Fitnessplans = DBController.getAllFitnessplan();
        generateOverview();
    }
}
