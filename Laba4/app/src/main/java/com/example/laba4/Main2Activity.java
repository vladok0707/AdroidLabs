package com.example.laba4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private TextView id;
    private Button email,location,number,link;
    private Contact contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView id =(TextView) findViewById(R.id.id);
        email = (Button) findViewById(R.id.email);
        location = (Button) findViewById(R.id.location);
        number = (Button) findViewById(R.id.number);
        link = (Button) findViewById(R.id.link);
        Bundle arguments = getIntent().getExtras();

        if(arguments!=null){
            contact = (Contact) arguments.getSerializable(Contact.class.getSimpleName());
            id.setText(contact.getId());
            email.setText(contact.getEmail());
            location.setText(contact.getLocation());
            number.setText(contact.getNumber());
            link.setText(contact.getLink());
        }

    }
    public void openEmail(View view)
    {   Intent EmailIntent = new Intent(Intent.ACTION_SEND);
        EmailIntent.setType("plain/text");
        EmailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { contact.getEmail() });
        EmailIntent.putExtra(Intent.EXTRA_SUBJECT, "Привет");
        EmailIntent.putExtra(Intent.EXTRA_TEXT, "Как дела?");
        startActivity(Intent.createChooser(EmailIntent, ""));

    }
    public void openLocation(View view)
    {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + contact.getLocation());
        Intent locationIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        locationIntent.setPackage("com.google.android.apps.maps");
        startActivity(locationIntent);
    }
    public void openNumber(View view)
    {
        Intent numberIntent = new Intent(Intent.ACTION_DIAL);
        numberIntent.setData(Uri.parse("tel:" + contact.getNumber()));
        startActivity(numberIntent);
    }
    public void openLink(View view)
    {
        String url = contact.getLink();
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;
        final Intent linkIntent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url));
        startActivity(linkIntent);
    }


}
