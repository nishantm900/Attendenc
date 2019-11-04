package com.example.raj.attendenceregister;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class StudentLoginActivity extends AppCompatActivity {
    Button signbutton;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    private EditText emails, passwords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_student_login);
        emails = findViewById(R.id.editText3);
        passwords = findViewById(R.id.editText4);
        signbutton = findViewById(R.id.signinButton);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void doOnClick(View view) {

        final String email = emails.getText().toString().trim();
        String password = passwords.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(StudentLoginActivity.this, "EMAIL CANNOT BE EMPTY.... \n PLZ FILL THE EMAIL FIELD.....", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(StudentLoginActivity.this, "PASSWORD CANNOT BE EMPTY.... \n PLZ FILL THE PASSWORD FIELD.....", Toast.LENGTH_LONG).show();
            return;
        }
        progressDialog.setMessage("Siging In.....");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "SignIn Successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(StudentLoginActivity.this, StudentInformationActivity.class);
                    intent.putExtra("Name", "Email");
                    intent.putExtra("Email", email);
                    firebaseAuth.signOut();
                    startActivity(intent);
                    progressDialog.dismiss();
                } else {
                    Toast.makeText(StudentLoginActivity.this, "Oops!! Wrong credentials", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
            }
        });

    }


}

