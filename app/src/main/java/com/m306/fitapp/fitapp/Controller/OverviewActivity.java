package com.m306.fitapp.fitapp.Controller;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;

import com.m306.fitapp.fitapp.Model.Exercise;
import com.m306.fitapp.fitapp.Model.Fitnessplan;
import com.m306.fitapp.fitapp.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class OverviewActivity extends AppCompatActivity {

    ArrayList<Fitnessplan> Fitnessplans;
    Context Context;
    FitnessplanAdapter FitnessplanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Context = this;
        DBController.setDatabase(Context);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Dialog("Geben Sie den Namen des Fitnesssplan ein.","Name des Fitnessplans",Context) {
                    @Override
                    public void save() {
                        long ID = DBController.insert(FitnessplanContract.FitnessplanEntry.TABLE_NAME, new HashMap<String, String>(){{
                            put(FitnessplanContract.FitnessplanEntry.COLUMN_NAME_NAME, Input.getText().toString());
                        }});
                        Fitnessplans.add(new Fitnessplan(ID, Input.getText().toString(), new ArrayList<Exercise>()));
                        FitnessplanAdapter.notifyDataSetChanged();
                    }
                }.show();
            }
        });

        Fitnessplans = DBController.getAllFitnessplan();
        generateOverview();
    }

    private void generateOverview(){
        RecyclerView planList = (RecyclerView) findViewById(R.id.plan_list);
        FitnessplanAdapter = new FitnessplanAdapter(Fitnessplans, Context);
        planList.setAdapter(FitnessplanAdapter);
        planList.setLayoutManager(new LinearLayoutManager(Context));
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
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
