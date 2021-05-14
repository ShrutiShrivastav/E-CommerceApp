package com.example.e_commerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.TextUtilsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    //password pattern
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{8,}" +               //at least 8 characters
                    "$");

    EditText et_name,et_email,et_pass,et_mobile;
    Button btnRegister;
    TextView tv_login;
    FirebaseAuth fAuth;
    boolean isNameValid, isEmailValid, isMobileValid, isPasswordValid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText et_name = findViewById(R.id.name);
        final EditText et_email = findViewById(R.id.email);
        final EditText et_mobile = findViewById(R.id.mobile);
        final EditText et_pass = findViewById(R.id.pass);

         TextView tv_login = findViewById(R.id.loginpage);
        Button btnRegister = findViewById(R.id.register);
        fAuth= FirebaseAuth.getInstance();


        if(fAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(),CartActivity.class));
            finish();
        }

        //listener for register button
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passwordInput = et_pass.getText().toString().trim();
                String email = et_email.getText().toString().trim();
                String password = et_pass.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    et_email.setError("Please enter an email address");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    et_pass.setError("Please enter a password");
                    return;
                }
                else if (password.length() < 8) {
                    et_pass.setError("Password is too weak");
                }
                else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
                    et_pass.setError("Please enter a valid password");}

                    if (et_mobile.getText().toString().isEmpty()) {
                    et_mobile.setError("Please enter mobile number");
                }
                if (et_name.getText().toString().isEmpty()) {
                    et_name.setError("Please enter your name");
                }

                    //register the user to firebase
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        } else {
                            Toast.makeText(MainActivity.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                });
            }
            });

        // listener for login
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }

}

