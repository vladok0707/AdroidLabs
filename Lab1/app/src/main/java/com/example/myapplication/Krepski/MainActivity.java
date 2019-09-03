package com.example.myapplication.Krepski;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.LogWriter;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int count = 0; count<10; count++){
            Log.d("MainActivity","Counter" + count);

        }
        displayText();
    }

    private void displayText() {
        TextView tw = findViewById(R.id.NewText);
        TextFunc f = new TextFunc();
        tw.setText(f.getValue());
    }
}
