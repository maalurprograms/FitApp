package com.m306.fitapp.fitapp.Controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.PlanName.setText(Plans.get(position).Name);
//        holder.ExerciceRepetition.setText(String.valueOf(Plans.get(position).getSemesterAverage()));
    }

    @Override
    public int getItemCount() {
        return Plans.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView PlanName;
//        public TextView ExerciceRepetition;

        public ViewHolder(View itemView) {
            super(itemView);
            PlanName = (TextView)itemView.findViewById(R.id.plan_name);
//            ExerciceRepetition = (TextView)itemView.findViewById(R.id.bar_average);
        }
    }
}