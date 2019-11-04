package com.example.raj.attendenceregister;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("REALLY  EXIT ?....");
        builder.setTitle("EXIT....");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new MyListener());
        builder.setNegativeButton("CANCEL", null);
        builder.show();
    }

    public void fn(View view) {
        if (view.getId() == R.id.teacherButton) {
            Intent intent = new Intent(MainActivity.this, TeacherLoginActivity.class);
            startActivity(intent);
        } else {
            Intent i = new Intent(MainActivity.this, StudentLoginActivity.class);
            startActivity(i);
        }
    }

    public class MyListener implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }
    }
}