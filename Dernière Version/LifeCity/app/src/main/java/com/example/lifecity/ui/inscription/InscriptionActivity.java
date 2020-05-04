package com.example.lifecity.ui.inscription;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.TextUtilsCompat;

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
import com.example.lifecity.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class InscriptionActivity extends AppCompatActivity {
    EditText mEmail, mPassword, mNom, mPrenom;
    TextView mLogin;
    Button mRegister;
    FirebaseAuth mAuth;
    CheckBox mAgree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        mEmail = findViewById(R.id.inscriptionEmail);
        mPassword = findViewById(R.id.inscriptionPassword);
        mNom = findViewById(R.id.inscriptionNom);
        mPrenom = findViewById(R.id.inscriptionPrenom);
        mAgree = findViewById(R.id.inscriptionCheckBox);
        mAuth = FirebaseAuth.getInstance();
        mRegister = findViewById(R.id.inscriptionValidButton);
        mLogin = findViewById(R.id.inscription);

        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        mRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                if(mAgree.isChecked()){
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
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(InscriptionActivity.this, "Utilisateur Crée", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            }
                            else{
                                Toast.makeText(InscriptionActivity.this, "Erreur ! : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    mAgree.setError("Soyez consentant !");
                    return;
                }
            }
        });

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

    }
}
