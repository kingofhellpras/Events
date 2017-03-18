package com.example.hp_pc.events.login;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp_pc.events.R;
import com.example.hp_pc.events.data.DbHelper;
import com.example.hp_pc.events.data.EventContract;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private Button reg;
    private TextView tvLogin;
    private EditText etEmail, etPass;
    private DbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mDbHelper = new DbHelper(this);
        reg = (Button) findViewById(R.id.btnReg);
        tvLogin = (TextView) findViewById(R.id.tvLogin);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPass);
        reg.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnReg:
                register();
                break;
            case R.id.tvLogin:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
                break;
            default:

        }
    }

    private void register() {
        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)) {

            // Gets the database in write mode
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(EventContract.UsersEntry.COLUMN_EMAIL, email);
            values.put(EventContract.UsersEntry.COLUMN_PASS, pass);
            long id = db.insert(EventContract.UsersEntry.TABLE_NAME, null, values);
            if (id > 0) {
                Toast.makeText(this, "User Registration Successfull!!", Toast.LENGTH_SHORT).show();
                Intent login=new Intent(RegisterActivity.this,LoginActivity.class);
                login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(login);
            } else Toast.makeText(this, "Registration Failed!!", Toast.LENGTH_SHORT).show();


        } else displayToast("Username/password field empty");
    }

    private void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}