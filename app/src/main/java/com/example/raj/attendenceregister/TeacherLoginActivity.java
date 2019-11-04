package com.example.raj.attendenceregister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class TeacherLoginActivity extends AppCompatActivity {
    EditText userId, password;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);
        userId = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        button = findViewById(R.id.button3);

    }

    public void doOnClick(View view) {
        String user = userId.getText().toString().trim();
        String pass = password.getText().toString().trim();
        if (user.equals("nishant") && pass.equals("1234")) {
            Intent intent = new Intent(TeacherLoginActivity.this, TeacherActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(TeacherLoginActivity.this, "PASSWORD WAS WRONG.... PLZ TRY AGAIN", Toast.LENGTH_LONG).show();
        }
    }
}
