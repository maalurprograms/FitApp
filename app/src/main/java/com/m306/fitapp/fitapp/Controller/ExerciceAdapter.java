package com.m306.fitapp.fitapp.Controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.m306.fitapp.fitapp.Model.Exercise;
import com.m306.fitapp.fitapp.R;

import java.util.ArrayList;

/**
 * Created by jonas on 15.11.16.
 */

public class ExerciceAdapter extends RecyclerView.Adapter<ExerciceAdapter.ViewHolder>{

    private ArrayList<Exercise> Exercices;
    private android.content.Context Context;

    public ExerciceAdapter(ArrayList<Exercise> exercises, android.content.Context context) {
        Exercices = exercises;
        Context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(Context);
        View contView = inflater.inflate(R.layout.exercice_prefab, parent, false);
        return new ViewHolder(contView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.ExerciceName.setText(Exercices.get(position).Name);
        holder.ExerciceRepetition.setText(Exercices.get(position).Repetition);
    }

    @Override
    public int getItemCount() {
        return Exercices.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView ExerciceName;
        public TextView ExerciceRepetition;

        public ViewHolder(View itemView) {
            super(itemView);
            ExerciceName = (TextView)itemView.findViewById(R.id.exercice_name);
            ExerciceRepetition = (TextView)itemView.findViewById(R.id.exercice_repetition);
        }
    }
}
