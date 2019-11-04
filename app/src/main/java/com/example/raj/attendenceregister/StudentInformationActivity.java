package com.example.raj.attendenceregister;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class StudentInformationActivity extends AppCompatActivity {
    StudentInformation studentInformation;
    String value = "";
    String name = "";
    TextView textViewName, textViewRollNo, textViewDepartment, textContact;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_information);
        studentInformation = new StudentInformation();

        studentInformation = new StudentInformation();
        studentInformation.setName("NISHANT M");
        final ProgressDialog pd = new ProgressDialog(this, ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage("FETCHING INFORMATION....");
        pd.setCancelable(false);
        pd.show();

        Intent intent = getIntent();
        name = intent.getStringExtra("Name");
        Log.e("name is", name);
        if (name.equals("Email"))
            value = intent.getStringExtra("Email").trim();
        else
            value = intent.getStringExtra("RollNo");
        Log.e("email is", value);
        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = database.getReference();
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    try {
                        // Toast.makeText(StudentInformationActivity.this, "Hello bro Again", Toast.LENGTH_SHORT).show();
                        Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                        for (DataSnapshot child : children) {
                            StudentInformation studentInformation1 = child.getValue(StudentInformation.class);
                            //check for subjects
                            if (name.equals("Email") && studentInformation1.getEmail().equalsIgnoreCase(value))
                                studentInformation = studentInformation1;
                            else if (name.equals("RollNo") && studentInformation1.getRollno().equalsIgnoreCase(value))
                                studentInformation = studentInformation1;
                        }
                        pd.dismiss();
                        fn();
                    } catch (Exception e) {
                        Log.e("Exception is", e.toString());
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        } catch (Exception e) {
            Log.e("Exception is", e.toString());
        }

    }

    void fn() {
        try {
            if (studentInformation.getName().equals("SANDEEP") && name.equals("0002")) {
                Toast.makeText(getApplicationContext(), "Roll No. not found", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(StudentInformationActivity.this, SelectRollNoActivity.class);
                startActivity(intent);
                finish();
            }
        } catch (Exception e) {

        }
        textViewName = findViewById(R.id.textView14);
        textViewRollNo = findViewById(R.id.textView17);
        textViewDepartment = findViewById(R.id.textView18);
        textContact = findViewById(R.id.textView20);
        listView = findViewById(R.id.listView3);
        //Toast.makeText(StudentInformationActivity.this, "fn is called", Toast.LENGTH_SHORT).show();
        textViewName.setText(studentInformation.getName());
        textViewRollNo.setText(studentInformation.getRollno());
        textViewDepartment.setText(studentInformation.getDepartment());
        textContact.setText(studentInformation.getPhone());
        ArrayList<Integer> al = studentInformation.getSubjects();
        ArrayList<String> subjectList = new ArrayList<String>();
        int count = 0;
        int countTot = 0;
        for (int i = 0; i < 7; i++) {
            int val = al.get(i);
            if (val != -1) {
                count = count + val % 1000;
                String str = "CS0" + (i + 1) + "   ----   " + val % 1000 + " / ";
                val = val / 1000;
                countTot = countTot + val % 1000;
                str = str + val % 1000;
                subjectList.add(str);
            }
        }
        if (countTot == 0)
            countTot = 1;
        Toast.makeText(StudentInformationActivity.this, "Your Total Attendance is--" + (count * 100) / countTot + "%", Toast.LENGTH_LONG).show();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, subjectList);
        listView.setAdapter(arrayAdapter);
    }
}








