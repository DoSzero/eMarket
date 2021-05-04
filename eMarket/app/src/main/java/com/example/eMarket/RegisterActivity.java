package com.example.eMarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView baner2 ;
    private TextView  logo;
    private EditText  editTextFullName, editTextEmail, editTextPassword, editTextRepeatPassword ;
    private Button btnRegisterUser;
    private ProgressBar progressBar ;

    // FirebaseAuth firebaseAuth;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView btn=findViewById(R.id.alreadyHaveAccount);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });

        // Initialize auth
        mAuth = FirebaseAuth.getInstance();
        // Initialize logo
        logo = (TextView) findViewById(R.id.logo);
        logo.setOnClickListener((View.OnClickListener) this);
        // Initialize Button
        btnRegisterUser = (Button) findViewById(R.id.btnRegisterUser);
        btnRegisterUser.setOnClickListener(this);
        // Initialize TextView
        editTextFullName = (EditText)findViewById(R.id.editTextFullName);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        editTextRepeatPassword = (EditText)findViewById(R.id.editTextRepeatPassword);
        // Initialize progressBar
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.logo:
                startActivity(new Intent(this,LoginActivity.class));
                break;
            case R.id.btnRegisterUser:
                btnRegisterUser();
                break;
        }
    }

    //method
    private void btnRegisterUser() {
        // Create String Variables
        final String fullName = editTextFullName.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String repeat_password = editTextRepeatPassword.getText().toString().trim();


        // Validate
        if (fullName.isEmpty()){
            // png icon for validation (Vlad PS )
            editTextFullName.setError("FullName is required! ");
            editTextFullName.requestFocus();
            return;
        }
        // Validate Email , but we need add provide  ".com" or some else
        if (email.isEmpty()){
            // png icon for validation (Vlad PS )
            editTextEmail.setError("Age is required! ");
            editTextEmail.requestFocus();
            return;
        }
        //Patterns its Android Utils
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please provide valid email! ");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            editTextPassword.setError("Password is required! ");
            editTextPassword.requestFocus();
            return;
        }
        // Password length
        if (password.length()<5){
            editTextPassword.setError("Min password length must be 5 symbols! ");
            editTextPassword.requestFocus();
            return;
        }
        // Password does not match
        if(!repeat_password.equals(password)){
            editTextRepeatPassword.setError("The password does not match!");
            editTextRepeatPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        // Create in table data base "Firebase"
        mAuth.createUserWithEmailAndPassword(email,password)
              .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            User user = new User (fullName, email);
                            //Call objects from Firebase
                            //FirebaseAuth.getInstance();
                            FirebaseDatabase.getInstance().getReference("User")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setPriority(user).addOnCompleteListener(new OnCompleteListener<Void>() {

                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    // Task been completed (registered !!!) = Visible
                                    if(task.isSuccessful()){
                                        // 1) Activity 2) Text 3) int or some instrument
                                        Toast.makeText(RegisterActivity.this,"User has been REG corrected! ", Toast.LENGTH_LONG).show();
                                       progressBar.setVisibility(View.VISIBLE);
                                       //progressBar.setVisibility(View.GONE);

                                        //Redirect to Login Layout (Activity_login)
                                    } else {
                                        // FAIL !!!  MIT ALARM!!
                                        Toast.makeText(RegisterActivity.this,"Failed to register!",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });

                      } else {
                            // FAIL !!!
                            Toast.makeText(RegisterActivity.this,"Failed to register!",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                      }
                    }
              });
    }
}