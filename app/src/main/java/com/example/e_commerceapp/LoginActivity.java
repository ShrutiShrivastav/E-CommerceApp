package com.example.e_commerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText et_Lemail,et_Lpass;
    Button btnLogin;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_Lemail = findViewById(R.id.loginemail);
        btnLogin = findViewById(R.id.login);
        et_Lpass= findViewById(R.id.loginpass);
        fAuth= FirebaseAuth.getInstance();

        //listener for login button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = et_Lemail.getText().toString().trim();
                String password = et_Lpass.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    et_Lemail.setError("Please enter an email address");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    et_Lpass.setError("Please enter a password");
                    return;
                }
                else if (password.length() < 8) {
                    et_Lpass.setError("Password is too weak");
                }

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Successfully Logged in!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), CartActivity.class));
                        } else {
                            Toast.makeText(LoginActivity.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}
