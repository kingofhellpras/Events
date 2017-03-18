package com.example.hp_pc.events.login;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp_pc.events.Events;
import com.example.hp_pc.events.R;
import com.example.hp_pc.events.data.DbHelper;

import static com.example.hp_pc.events.data.EventContract.UsersEntry.COLUMN_EMAIL;
import static com.example.hp_pc.events.data.EventContract.UsersEntry.COLUMN_PASS;
import static com.example.hp_pc.events.data.EventContract.UsersEntry.USER_TABLE;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private Button login, register;
    private EditText etEmail, etPass;
    private DbHelper mDbHelper;
    private Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mDbHelper = new DbHelper(this);
        session = new Session(this);
        login = (Button)findViewById(R.id.btnLogin);
        register = (Button)findViewById(R.id.btnReg);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etPass = (EditText)findViewById(R.id.etPass);
        login.setOnClickListener(this);
        register.setOnClickListener(this);

        if(session.loggedin()){
            startActivity(new Intent(LoginActivity.this,Events.class));
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnLogin:
                login();
                break;
            case R.id.btnReg:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
            default:

        }
    }

    private void login(){
        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();

        if(getUser(email,pass)){
            session.setLoggedin(true);
            Intent i=new Intent(LoginActivity.this,Events.class);
            i.putExtra("email",email);
            startActivity(i);
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "Wrong email/password",Toast.LENGTH_SHORT).show();
        }
    }

    public boolean getUser(String email, String pass) {
        String selectQuery = "select * from  " + USER_TABLE + " where " +
                COLUMN_EMAIL + " = " + "'" + email + "'" + " and " + COLUMN_PASS + " = " + "'" + pass + "'";

        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {

            return true;
        }
        cursor.close();
        db.close();

        return false;
    }
}