package com.example.hp_pc.events.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.hp_pc.events.R;

public class UserProfile extends AppCompatActivity {

    TextView tv;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Intent i=getIntent();
        user=i.getStringExtra("user");
        tv= (TextView) findViewById(R.id.user_name);
        tv.setText(user);

    }
}
