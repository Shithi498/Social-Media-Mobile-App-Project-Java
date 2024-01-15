package com.example.social_media_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.social_media_app.Model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {
    EditText Name,email,pass,Cpass;
    TextView healthcare,register;
    Button btn,back;
    FirebaseAuth auth;
    FirebaseDatabase database;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Name=findViewById(R.id.rName);
        email=findViewById(R.id.rEmail);
        pass=findViewById(R.id.rPass);
        Cpass=findViewById(R.id.rCPass);
        healthcare=findViewById(R.id.rtextView);

        btn=findViewById(R.id.rbutton);
        back=findViewById(R.id.button4);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = Name.getText().toString();
                String email1 = email.getText().toString();
                String password=pass.getText().toString();
                auth.createUserWithEmailAndPassword(email1,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            UserModel user = new UserModel(name,email1, password);
                            String id = task.getResult().getUser().getUid();
                            database.getReference().child("User").child(id).setValue(user);
                            Toast.makeText(RegistrationActivity.this, "User data saved", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                        }
                    }
                });

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


     


              


    }
   
              
    }
