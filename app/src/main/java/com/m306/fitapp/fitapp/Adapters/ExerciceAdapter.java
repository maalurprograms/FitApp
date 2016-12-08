package com.m306.fitapp.fitapp.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.m306.fitapp.fitapp.Controller.DBController;
import com.m306.fitapp.fitapp.Controller.Dialog;
import com.m306.fitapp.fitapp.Controller.FitnessplanContract;
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
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Exercise exercise = Exercices.get(position);

        holder.ExerciceName.setText(exercise.Name);

        String text; if (exercise.Repetition == 1) text = " Wiederholung"; else text = " Wiederholungen";
        holder.ExerciceRepetition.setText(String.valueOf(exercise.Repetition + text));

        holder.Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Dialog("Übung bearbeiten", "Geben Sie einen neuen Namen ein:", "Geben Sie die Anzahl Wiederholungen ein:", exercise.Name, String.valueOf(exercise.Repetition), Context, "Änderungen gespeichert.") {
                    @Override
                    public void save() {
                        if (!Name.equals(exercise.Name)){
                            DBController.update(exercise.ID, Name, FitnessplanContract.ExerciseEntry.TABLE_NAME, FitnessplanContract.ExerciseEntry.COLUMN_NAME_NAME, FitnessplanContract.ExerciseEntry._ID);
                            exercise.Name = Name;
                        }
                        if (Repetitions != exercise.Repetition){
                            DBController.update(exercise.ID, String.valueOf(Repetitions), FitnessplanContract.ExerciseEntry.TABLE_NAME, FitnessplanContract.ExerciseEntry.COLUMN_NAME_WIEDERHOLUNGEN, FitnessplanContract.ExerciseEntry._ID);
                            exercise.Repetition = Repetitions;
                        }
                        notifyDataSetChanged();
                    }
                }.show();
            }
        });

        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBController.delete(exercise.ID, FitnessplanContract.ExerciseEntry.TABLE_NAME, FitnessplanContract.ExerciseEntry._ID);
                Exercices.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return Exercices.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView ExerciceName;
        public TextView ExerciceRepetition;
        public ImageView Edit;
        public ImageView Delete;

        public ViewHolder(View itemView) {
            super(itemView);
            ExerciceName = (TextView)itemView.findViewById(R.id.exercise_name);
            ExerciceRepetition = (TextView)itemView.findViewById(R.id.exercise_info);
            Edit = (ImageView)itemView.findViewById(R.id.edit_exercise);
            Delete = (ImageView)itemView.findViewById(R.id.delete_exercise);
        }
    }
}
