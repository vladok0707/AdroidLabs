package com.example.laba2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private int selecretitemradio = 0;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner) findViewById(R.id.activity_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.actyvity_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void onRadioButtonClicked(View view) {
        // если переключатель отмечен
        boolean checked = ((RadioButton) view).isChecked();
        // Получаем нажатый переключатель
        switch (view.getId()) {
            case R.id.Male:
                if (checked) {
                    selecretitemradio = 0;
                }
                break;
            case R.id.Female:
                if (checked) {
                    selecretitemradio = 1;
                }
                break;
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.OK:
                double BMR;
                EditText first = (EditText) findViewById(R.id.age);
                EditText second = (EditText) findViewById(R.id.weight);
                EditText third = (EditText) findViewById(R.id.height);
                TextView res = (TextView) findViewById(R.id.result);
                if (selecretitemradio == 1) {
                    BMR = 655.0955 + (9.5634 * ParseDouble(second))
                            + (1.8496 * ParseDouble(third))
                            - (4.6756 * ParseDouble(first));
                } else {
                    BMR = 66.4730 + (13.7516 * ParseDouble(second))
                            + (5.0033 * ParseDouble(third))
                            - (6.7550 * ParseDouble(first));
                }
                res.setText("Ваша суточная норма (Ккал): " + BMR * SpinnerX(spinner));
        }
    }


    private double ParseDouble(EditText e) {
        double res=0;
        try {
            res = Double.parseDouble(e.getText().toString());
        } catch (Exception ex) {
            //Toast.makeText(this, "Not correct data, return 0", Toast.LENGTH_SHORT).show();
            e.setText("0");
            return 0;
        }

        return res;
    }


    private double SpinnerX(Spinner s) {
        switch (s.getId()) {
            case 0:
                return 1.2;
            case 1:
                return 1.55;
            case 2:
                return 1.9;
            default: return 1.2;
        }


    }
}
