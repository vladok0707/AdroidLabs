package com.example.laba2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener  {
    private int selecretitemradio = 0;
    private int selecretitemactyvity = 0;
    private Button mButton;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.OK);
        mButton.setOnClickListener(this);
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
        switch(view.getId()) {
            case R.id.Male:
                if (checked){
                    selecretitemradio = 0;
                }
                break;
            case R.id.Female:
                if (checked){
                    selecretitemradio = 1;
                }
                break;
        }
    }
    @Override
    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.OK:
                TextView res = (TextView) findViewById(R.id.result);
                res.setText(selecretitemradio + " : " + spinner.getSelectedItemId());
        }
    }

}
