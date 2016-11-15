package com.m306.fitapp.fitapp.Controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;
import java.util.ArrayList;
import java.util.Arrays;
import com.m306.fitapp.fitapp.R;

public abstract class Dialog extends android.app.Dialog{

    private Button Cancel;
    private Button Save;
    private String Info;
    private int DialogType;
    private String DefaultValue;
    private Context ActivityContext;

    public NumberPicker Picker;
    public NumberPicker PickerDeci;
    public NumberPicker PickerCenti;
    public EditText Input;


    public Dialog(String info, String defaultValue, Context context) {
        super(context);
        Info = info;
        DefaultValue = defaultValue;
        ActivityContext = context;

        setContentView(R.layout.dialog);
        Input = (EditText) findViewById(R.id.value);
        Input.setSelectAllOnFocus(true);
        Input.setText(DefaultValue);
        Input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focused) {
                if (focused) getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
        });

        ((TextView)findViewById(R.id.info)).setText(Info);

        Save = (Button)findViewById(R.id.save);
        Save.setText("OK");
        Cancel = (Button)findViewById(R.id.cancel);
        Cancel.setText("CANCEL");

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
                Toast.makeText(ActivityContext, "Fitnessplan wurde erstellt.", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
    }

    public void updateAdapter(RecyclerView.Adapter adapter, int position){
        adapter.notifyItemChanged(position);
    }

    public abstract void save();
}