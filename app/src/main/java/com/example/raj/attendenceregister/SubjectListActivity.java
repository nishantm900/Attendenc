package com.example.raj.attendenceregister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SubjectListActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {


            setContentView(R.layout.activity_subject_list);
            listView = findViewById(R.id.listView);
            String arr[] = new String[7];
            for (int i = 0; i < 7; i++) {
                arr[i] = "CS0" + (i + 1);
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, arr);
            listView.setAdapter(arrayAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(SubjectListActivity.this, AttendenceActivity.class);
                    intent.putExtra("position", position + "");
                    startActivity(intent);
                }
            });

        } catch (Exception e) {
            Log.e("Exception is", e.toString());


        }
    }
}