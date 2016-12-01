package com.m306.fitapp.fitapp.Controller;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;

import com.m306.fitapp.fitapp.R;

public abstract class Dialog extends android.app.Dialog{

    public String Name;
    public int Repetitions;

    /**
     * Constructor to make a Fitnessplan Dialog
     * @param info
     * @param defaultValue
     * @param context
     */
    public Dialog(String header, String defaultValue, final Context context) {
        super(context);

        setContentView(R.layout.fintessplan_dialog);
        final EditText planNameInput = (EditText) findViewById(R.id.fitnessplan_name);
        planNameInput.setSelectAllOnFocus(true);
        planNameInput.setText(defaultValue);
        ((TextView) findViewById(R.id.plan_header)).setText(header);
        planNameInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focused) {
                if (focused)
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
        });

        Button Save = setUpButtons();
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Name = planNameInput.getText().toString();
                save();
                Toast.makeText(context, "Fitnessplan wurde erstellt.", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

//        Button Save = (Button) findViewById(R.id.savePlan);
//        Save.setText("OK");
//        Button Cancel = (Button) findViewById(R.id.cancel);
//        Cancel.setText("CANCEL");
//
//        Cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dismiss();
//            }
//        });
//        Save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                savePlan();
//                Toast.makeText(context, "Fitnessplan wurde erstellt.", Toast.LENGTH_SHORT).show();
//                dismiss();
//            }
//        });

    }

    /**
     * Constructor to make a Exercise Dialog
     * @param nameInfo
     * @param repetitionsInfo
     * @param nameDefaultValue
     * @param repetitionDefaultValue
     * @param context
     */
    public Dialog(String header, String nameInfo, String repetitionsInfo, String nameDefaultValue, String repetitionDefaultValue, final Context context) {
        super(context);

        setContentView(R.layout.exercise_dialog);

        final EditText exerciseNameInput = (EditText) findViewById(R.id.exercise_name);
        final EditText exerciseRepetitionInput = (EditText) findViewById(R.id.exercise_repetition);


        exerciseNameInput.setSelectAllOnFocus(true);

        exerciseNameInput.setText(nameDefaultValue);
        exerciseRepetitionInput.setText(repetitionDefaultValue);

        ((TextView) findViewById(R.id.exercise_header)).setText(header);
        ((TextView) findViewById(R.id.exercise_name_info)).setText(nameInfo);
        ((TextView) findViewById(R.id.exercise_repetition_info)).setText(repetitionsInfo);

        exerciseNameInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focused) {
                if (focused)
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
        });

        Button Save = setUpButtons();
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Name = exerciseNameInput.getText().toString();
                    Repetitions = Integer.valueOf(exerciseRepetitionInput.getText().toString());
                    save();
                    Toast.makeText(context, "Übung wurde erstellt.", Toast.LENGTH_SHORT).show();
                    dismiss();
                } catch (Exception e){
                    Toast.makeText(context, "Bitte geben Sie bei Wiederholungen nur Zahlen ein.", Toast.LENGTH_LONG).show();
                }

            }
        });

//        Button Save = (Button) findViewById(R.id.savePlan);
//        Save.setText("OK");
//        Button Cancel = (Button) findViewById(R.id.cancel);
//        Cancel.setText("CANCEL");
//
//        Cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dismiss();
//            }
//        });
//        Save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                savePlan();
//                Toast.makeText(context, "Übung wurde erstellt.", Toast.LENGTH_SHORT).show();
//                dismiss();
//            }
//        });

    }

    private Button setUpButtons(){
        Button Save = (Button) findViewById(R.id.save);
        Save.setText("OK");
        Button Cancel = (Button) findViewById(R.id.cancel);
        Cancel.setText("CANCEL");

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return Save;
    }

    public abstract void save();
}