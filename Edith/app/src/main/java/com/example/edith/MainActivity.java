package com.example.edith;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText mEmail, mPassword;
    Button login;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        login  = findViewById(R.id.btn_login);

        login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                String lemail = mEmail.getText().toString();
                String lpassword = mPassword.getText().toString();

                mAuth.signInWithEmailAndPassword(lemail,lpassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    gotoHome();
                                }
                                else{
                                    // Toast.makeText(MainActivity.this, task.getException().getMessage(),
                                    //       Toast.LENGTH_SHORT).show();

                                    Toast.makeText(MainActivity.this, "Incorrect email or password please try again",
                                            Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

            }

        }
        );


    }



    public void gotoHome(){

        Intent j = new Intent(this, HomeActivity.class);
        startActivity(j);

    }

    public void gotoSignUp(View view){

        Intent j = new Intent(this, SignUp.class);
        startActivity(j);

    }


}
