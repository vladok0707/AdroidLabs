package com.example.laba2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    String[] data = {"Малоподвижный образ жизни", "Обычная физнагрузка", "Интенсивная нагрузка"};
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    private Button btnDisplay;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        addListenerOnButton();


    }

    private void addListenerOnButton() {
        radioSexGroup = (RadioGroup) findViewById(R.id.radioGroup);
        btnDisplay = (Button) findViewById(R.id.OK);

        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioSexGroup.getCheckedRadioButtonId();
                radioSexButton = (RadioButton) findViewById(selectedId);
                int selected = spinner.getSelectedItemPosition();
                TextView result = (TextView) findViewById(R.id.result);
                result.setText((int) GetResult(selectedId,selected));
            }

        });
    }

    private float GetResult(int RadioButtonID, int SpinnerSelectedID) {
        float result = 0;
        float BMR;
        TextView kg = (TextView) findViewById(R.id.Kg);
        float kgs = 0;
        String text = kg.getText().toString();
        if (!text.isEmpty())
            try {
                kgs = Float.parseFloat(text);
                // it means it is double
            } catch (Exception e1) {
                // this means it is not double
                e1.printStackTrace();
            }
        TextView height = (TextView) findViewById(R.id.height);
        float heights = 0;
        text = kg.getText().toString();
        if (!text.isEmpty())
            try {
                heights = Float.parseFloat(text);
                // it means it is double
            } catch (Exception e1) {
                // this means it is not double
                e1.printStackTrace();
            }
        TextView age = (TextView) findViewById(R.id.height);
        float ages = 0;
        text = kg.getText().toString();
        if (!text.isEmpty())
            try {
                ages = Float.parseFloat(text);
                // it means it is double
            } catch (Exception e1) {
                // this means it is not double
                e1.printStackTrace();
            }
        if (RadioButtonID == 0) {
            BMR = (float) (655.0955 + (9.5634 * kgs) + (1.8496 * heights) - (4.6756 * ages));
        } else {
            BMR = (float) (66.4730 + (13.7516 * kgs) + (5.0033 * heights) - (6.7550 * ages));
        }
        if (SpinnerSelectedID == 0) {
            result = (float) (BMR * 1.2);
        } else if (SpinnerSelectedID == 1) {
            result = (float) (BMR * 1.55);
        } else {
            result = (float) (BMR * 1.9);
        }
        return result;

    }
}
