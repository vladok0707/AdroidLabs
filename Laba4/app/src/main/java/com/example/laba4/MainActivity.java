package com.example.laba4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<Contact> adapter;
    private EditText idText, emailText, locationText, numberText, linkText;
    private List<Contact> contacts;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idText = (EditText) findViewById(R.id.idText);
        emailText = (EditText) findViewById(R.id.emailText);
        locationText = (EditText) findViewById(R.id.locationText);
        numberText = (EditText) findViewById(R.id.numberText);
        linkText = (EditText) findViewById(R.id.linkText);

        contacts = new ArrayList<>();
        listView = (ListView) findViewById(R.id.list);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contacts);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(getApplication(), Main2Activity.class);
                intent.putExtra(Contact.class.getSimpleName(), contacts.get(position));
                startActivity(intent);
            }
        });
    }

    public void addContact(View view){
        String id = idText.getText().toString();
        String email = emailText.getText().toString();
        String location = locationText.getText().toString();
        String number = numberText.getText().toString();
        String link = linkText.getText().toString();
        Contact contact = new Contact(id,email,location,number,link);
        contacts.add(contact);
        adapter.notifyDataSetChanged();

    }


    public void save(View view){

        boolean result = JSONHelper.exportToJSON(this, contacts);
        if(result){
            Toast.makeText(this, "Данные сохранены", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Не удалось сохранить данные", Toast.LENGTH_LONG).show();
        }
    }
    public void open(View view){
        contacts = JSONHelper.importFromJSON(this);
        if(contacts!=null){
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contacts);
            listView.setAdapter(adapter);
            Toast.makeText(this, "Данные восстановлены", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Не удалось открыть данные", Toast.LENGTH_LONG).show();
        }
    }
}
