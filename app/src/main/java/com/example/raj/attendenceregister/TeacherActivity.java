package com.example.raj.attendenceregister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class TeacherActivity extends AppCompatActivity {
    Button b1, b2, b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button4);

    }

    public void fn(View view) {
        if (view == b1) {
            Intent intent = new Intent(TeacherActivity.this, SubjectListActivity.class);
            // Intent intent = new Intent(this, SubjectListActivity.class);
            startActivity(intent);

        } else if (view == b2) {
            Intent intent = new Intent(TeacherActivity.this, AddStudentActivity.class);
            startActivity(intent);

        } else {
            Intent intent = new Intent(TeacherActivity.this, SelectRollNoActivity.class);
            //Intent intent = new Intent(this, SelectRollNoActivity.class);
            startActivity(intent);
        }
    }
}
