package com.decodehelloworld.naarirakshak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {
    EditText t1, t2, t3, t4;
    Button b1;
    TextView Signin;
    FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mFirebaseAuth = FirebaseAuth.getInstance();
        t1 = findViewById(R.id.editTextTextPersonName);
        t2 = findViewById(R.id.editTextTextEmailAddress2);
        t3 = findViewById(R.id.editTextTextPassword2);
        t4 = findViewById(R.id.editTextPhone);
        b1 = findViewById(R.id.button3);
        Signin = findViewById(R.id.textView10);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = t1.getText().toString();
                String email = t2.getText().toString();
                String password = t3.getText().toString();
                String phone = t4.getText().toString();
                if (email.isEmpty()) {
                    t2.setError("Please enter email id");
                    t2.requestFocus();
                } else if (password.isEmpty()) {
                    t3.setError("Please enter your password");
                    t3.requestFocus();
                } else if (name.isEmpty()) {
                    t1.setError("Please enter your name");
                    t1.requestFocus();
                } else if (phone.isEmpty()) {
                    t4.setError("Please enter your phone number");
                } else if (name.isEmpty() && phone.isEmpty() && email.isEmpty() && password.isEmpty()) {
                    Toast.makeText(register.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
                } else if (!(email.isEmpty() && password.isEmpty())) {
                    mFirebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(register.this, "registration Unsuccessful", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(register.this, Combined.class));
                            }
                        }
                    });
                } else {
                    Toast.makeText(register.this, "Error Occured", Toast.LENGTH_SHORT).show();

                }
            }
        });
        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(register.this, SignIn.class);
                startActivity(i);
            }
        });


    }
}