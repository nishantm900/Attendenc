package com.example.raj.attendenceregister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new MyThread().start();
    }

    public class MyThread extends Thread {
        public void run() {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                Toast.makeText(SplashActivity.this, e.toString(), Toast.LENGTH_LONG).show();
            } finally {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
}

