package com.decodehelloworld.naarirakshak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity {
    Button b1;
    EditText t1, t2;
    TextView SignUp;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        b1 = (Button) findViewById(R.id.button);
        t1 = (EditText) findViewById(R.id.editTextTextEmailAddress);
        t2 = (EditText) findViewById(R.id.editTextTextPassword);
        SignUp = (TextView) findViewById(R.id.textView4);




        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getInstance().getCurrentUser();
                if (mFirebaseUser != null) {
                    Toast.makeText(SignIn.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(SignIn.this, Combined.class);
                    startActivity(i);
                } else {
                    Toast.makeText(SignIn.this, "Please login to continue", Toast.LENGTH_SHORT).show();
                }
            }
        };

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = t1.getText().toString();
                String password = t2.getText().toString();
                if (email.isEmpty()) {
                    t1.setError("Please enter email id");
                    t1.requestFocus();
                } else if (password.isEmpty()) {
                    t2.setError("Please enter your password");
                    t2.requestFocus();
                } else if (email.isEmpty() && password.isEmpty()) {
                    Toast.makeText(SignIn.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
                } else if (!(email.isEmpty() && password.isEmpty())) {
                    mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(SignIn.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignIn.this, "Logged in successfully !", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignIn.this, Combined.class));
                            } else {
                                Toast.makeText(SignIn.this, "Login error try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(SignIn.this, "Error occured", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignIn.this, register.class));
                }
            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(SignIn.this, register.class);
                startActivity(j);
            }
        });

        }



}

