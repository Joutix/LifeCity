package com.example.lifecity.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifecity.MainActivity;
import com.example.lifecity.R;
import com.example.lifecity.ui.home.AccueilActivity;
import com.example.lifecity.ui.inscription.InscriptionActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText mEmail, mPassword;
    TextView mCreate;
    Button mLogin;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmail = findViewById(R.id.loginMail);
        mPassword = findViewById(R.id.loginPassword);
        mCreate = findViewById(R.id.loginNoAccount);
        mLogin = findViewById(R.id.loginValidButton);

        mAuth = FirebaseAuth.getInstance();

        mLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                    if(TextUtils.isEmpty(email)){
                        mEmail.setError("Email est demandé");
                        return;
                    }
                    if(TextUtils.isEmpty(password)){
                        mPassword.setError("Password est demandé");
                        return;
                    }
                    if(password.length() < 6){
                        mPassword.setError("Password avec plus de >= 6 Caractères");
                        return;
                    }

                    // Création de l'utilisateur dans firebase
                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Utilisateur Connecté", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            }
                            else{
                                Toast.makeText(LoginActivity.this, "Erreur ! : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
        });

        mCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), InscriptionActivity.class));
            }
        });
    }
}
