package com.example.laba3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.ListView;
import android.widget.Toast;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ArrayAdapter;
import android.os.Bundle;
import android.widget.EditText;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    private CalendarView calendarView;
    private EditText notesText;
    private Date nowDate;
    private List<ItemCalendar> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notesText = (EditText) findViewById(R.id.notesText);
        open();
        calendarView=(CalendarView) findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                nowDate = new Date(year,month,dayOfMonth);
                try{
                    notesText.setText(findNote(nowDate,notes).getText());

                }catch (Exception ex){
                    notesText.setText("");
                }
            }
        });
    }


    public void save(View view){
            String text = notesText.getText().toString();
            ItemCalendar note = new ItemCalendar(nowDate, text);
            try{ findNote(nowDate,notes).setText(text);}
            catch (Exception ex) {notes.add(note);}
        boolean result = JSONHelper.exportToJSON(this, notes);
        if(result){
            Toast.makeText(this, "Данные сохранены", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Не удалось сохранить данные", Toast.LENGTH_LONG).show();}




    }
    public void delete(View view){
        notesText.setText("");
        try{
            notes.remove(notes.indexOf((findNote(nowDate,notes))));
            boolean result = JSONHelper.exportToJSON(this, notes);
            if(result){
                Toast.makeText(this, "Удалены", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this, "Не удалось удалить данные", Toast.LENGTH_LONG).show();
            }
        } catch (Exception ex){
            Toast.makeText(this, "Не удалось удалить данные", Toast.LENGTH_LONG).show();
        }

    }
    private void open(){
        notes = JSONHelper.importFromJSON(this);
        if(notes!=null){
            Toast.makeText(this, "Данные восстановлены", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Не удалось восстановить данные", Toast.LENGTH_LONG).show();
            notes = new ArrayList<>();
        }
    }
    public ItemCalendar findNote(
            Date Date, List<ItemCalendar> notes) {
            for (ItemCalendar s : notes) {
                if (s.getDate().equals(Date)) {
                    return s;
                }
            }
        return null;
    }

}
