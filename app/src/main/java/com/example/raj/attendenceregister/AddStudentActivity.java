package com.example.raj.attendenceregister;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AddStudentActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    Button b5;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    CheckBox cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8;
    FirebaseDatabase firebaseDatabase;
    private EditText et_user_id, et_name, et_roll_no, et_dept, et_phone, et_email, et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        et_user_id = findViewById(R.id.editText5);
        et_name = findViewById(R.id.editText6);
        et_roll_no = findViewById(R.id.editText7);
        et_dept = findViewById(R.id.editText8);
        et_phone = findViewById(R.id.editText9);
        et_email = findViewById(R.id.editText10);
        et_password = findViewById(R.id.editText11);
        b5 = findViewById(R.id.button5);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        if (firebaseAuth.getCurrentUser() != null) {
            Toast.makeText(AddStudentActivity.this, "ALREADY SIGN IN....", Toast.LENGTH_LONG).show();
            firebaseAuth.signOut();
        }
        cb1 = findViewById(R.id.checkBox1);
        cb2 = findViewById(R.id.checkBox2);
        cb3 = findViewById(R.id.checkBox3);
        cb4 = findViewById(R.id.checkBox4);
        cb5 = findViewById(R.id.checkBox5);
        cb6 = findViewById(R.id.checkBox6);
        cb7 = findViewById(R.id.checkBox7);
        cb8 = findViewById(R.id.checkBox8);

    }

    public void fnRegister(View view) {
        Log.e("Entering ", "In fn");

        String userid = et_user_id.getText().toString().trim();
        String name = et_name.getText().toString().trim();
        String rollno = et_roll_no.getText().toString().trim();
        String dept = et_dept.getText().toString().trim();
        String phone = et_phone.getText().toString().trim();
        String email = et_email.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        //checking checkboxes

        ArrayList<Integer> subject = new ArrayList<Integer>(8);
        for (int i = 0; i < 8; i++)
            subject.add(0);
        if (!cb1.isChecked())
            subject.add(0, -1);
        if (!cb2.isChecked())
            subject.add(1, -1);
        if (!cb3.isChecked())
            subject.add(2, -1);
        if (!cb4.isChecked())
            subject.add(3, -1);
        if (!cb5.isChecked())
            subject.add(4, -1);
        if (!cb6.isChecked())
            subject.add(5, -1);
        if (!cb7.isChecked())
            subject.add(6, -1);
        if (!cb8.isChecked())
            subject.add(7, -1);
        int sum = 0;
        for (int i = 0; i < 8; i++)
            sum = sum + subject.get(i);
        if (sum == -8) {
            Toast.makeText(AddStudentActivity.this, "PLEASE SELECT SUBJECTS", Toast.LENGTH_LONG).show();
            return;
        }
        String arr[] = new String[]{userid, name, rollno, dept, phone, email, password};
        for (int i = 0; i < 7; i++) {
            if (TextUtils.isEmpty(arr[i])) {
                Toast.makeText(AddStudentActivity.this, "BLOCK CANNOT BE EMPTY", Toast.LENGTH_LONG).show();
                return;
            }
        }
        if (password.length() < 6) {
            Toast.makeText(AddStudentActivity.this, "PASSWORD CANNOT BE CONTAIN MIN 6 CHARACTERS", Toast.LENGTH_LONG).show();
            return;
        }

        Log.e("Entering ", "In fn2");
        Toast.makeText(AddStudentActivity.this, "VALIDATION SUCCESSFULLY DONE....", Toast.LENGTH_LONG).show();

        progressDialog.setMessage("REGISTERING USER");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();

        Log.e("ENTERING", "IN FN3");
        try {
            final StudentInformation stdinfo = new StudentInformation(name, rollno, dept, phone, email, password, subject);
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(AddStudentActivity.this, "USER REGISTER SUCCESSFULLY DONE....", Toast.LENGTH_LONG).show();
                        saveInformation(stdinfo);
                        progressDialog.dismiss();
                    } else {
                        Toast.makeText(AddStudentActivity.this, "USER REGISTER FAILED.....", Toast.LENGTH_LONG).show();
                        Log.e("Exception is", task.getException().toString());
                        progressDialog.dismiss();

                    }

                }
            });


        } catch (Exception e) {

        }


    }

    private void saveInformation(StudentInformation stdinfo) {
        try {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            databaseReference = FirebaseDatabase.getInstance().getReference();
            databaseReference.child(user.getUid()).setValue(stdinfo);

        } catch (Exception e) {
            Log.e("Exception is", e.toString());


        }
        firebaseAuth.signOut();

    }

}