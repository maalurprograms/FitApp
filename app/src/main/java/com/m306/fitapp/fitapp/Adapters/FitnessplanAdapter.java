package com.m306.fitapp.fitapp.Adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.m306.fitapp.fitapp.Controller.DBController;
import com.m306.fitapp.fitapp.Controller.DetailActivity;
import com.m306.fitapp.fitapp.Controller.FitnessplanContract;
import com.m306.fitapp.fitapp.Model.Fitnessplan;
import com.m306.fitapp.fitapp.R;

import java.util.ArrayList;

/**
 * Created by jonas on 15.11.16.
 */

public class FitnessplanAdapter extends RecyclerView.Adapter<FitnessplanAdapter.ViewHolder>{

    private ArrayList<Fitnessplan> Plans;
    private android.content.Context Context;

    public FitnessplanAdapter(ArrayList<Fitnessplan> plans, android.content.Context context) {
        Plans = plans;
        Context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(Context);
        View contView = inflater.inflate(R.layout.fitnessplan_prefab, parent, false);
        return new ViewHolder(contView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Fitnessplan fitnessplan = Plans.get(position);

        holder.PlanName.setText(fitnessplan.Name);
        ((LinearLayout)holder.PlanName.getParent().getParent()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Context, DetailActivity.class);
                intent.putExtra("CURRENT_SUBJECT_ID", fitnessplan.ID);
                intent.putExtra("CURRENT_SUBJECT_NAME", fitnessplan.Name);
                Context.startActivity(intent);
            }
        });
        int repetitions = DBController.getExerciseFromFitnessplan(fitnessplan.ID).size();
        String text; if (repetitions == 1) text = " Übung"; else text = " Übungen";
        holder.ExerciceRepetition.setText( repetitions + text);

        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBController.delete(fitnessplan.ID, FitnessplanContract.FitnessplanEntry.TABLE_NAME, FitnessplanContract.FitnessplanEntry._ID);
                Plans.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return Plans.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView PlanName;
        public TextView ExerciceRepetition;
        public ImageView Delete;

        public ViewHolder(View itemView) {
            super(itemView);
            PlanName = (TextView)itemView.findViewById(R.id.plan_name);
            ExerciceRepetition = (TextView)itemView.findViewById(R.id.plan_info);
            Delete = (ImageView) itemView.findViewById(R.id.delete_plan);
        }
    }
}