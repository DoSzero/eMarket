package com.example.eMarket;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,View.OnClickListener {

    private SignInButton signInButton;
    GoogleApiClient mGoogleApiClient;

    // Button singOutButton;
    private TextView statusTextView;

    private  static  final String TAG="SingInActivity";
    private  static  final int RC_SIGN_IN =9001;


    //https://www.youtube.com/watch?v=SXlidHy-Tb8&t=179s
    //https://www.youtube.com/watch?v=Duz_0XkWP2I&t=574s
    SignInButton button;  // google standart naming in play-services-auth
    FirebaseAuth mAuth;

    private EditText editTextEmail, editTextPassword;
    private Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView btn=findViewById(R.id.textViewSignUp);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(LoginActivity.this,"Some WRONG!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        // Initialization TextView
        statusTextView = (TextView) findViewById(R.id.status_textview);
        // Initialization Google Button
        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(this);

        /*
        singOutButton = (Button) findViewById(R.id.singOutBtton);
        singOutButton.setOnClickListener;
        */

        // Initialization for Button Login
        btnlogin =(Button) findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(this);
        // Initialization for EditText
        editTextEmail = (EditText) findViewById(R.id.editTextPassword);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        // Firebase auth
        mAuth = FirebaseAuth.getInstance();
        //singOutButton = (Button) findViewById(R.id.singOutBtton);
        //singOutButton.setOnClickListener;

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sign_in_button:
                singIn();
                break;
                /*
                * case R.id.singOutButton:
                * singOut();
                * break;
                * */
            case R.id.btnlogin:
                // method
                userLogin();
                break;
        }
    }

    // Method Login  for user functionality
    private void userLogin() {
        String  email  = editTextEmail.getText().toString().trim();
        String  password = editTextPassword.getText().toString().trim();
        /*
        // Is user empty ?
        if (email.isEmpty()){
            editTextEmail.setError("Email is required !! ");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please enter valid email!");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()){
            editTextPassword.setError("Please enter right Password !");
            editTextPassword.requestFocus();
            return;
        }
        // Password length
        if (password.length()<5){
            editTextPassword.setError("Minimum of length of password should be 5 symbols! ");
            editTextPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
           if (task.isSuccessful()){
               //redirect to (FROM Login TO main (Fragments ))
               //finish();
               startActivity(new Intent(LoginActivity.this,MainActivity.class));
           }  else {
               Toast.makeText(LoginActivity.this,"Error mAuth", Toast.LENGTH_LONG).show();
           }
            }
        });
        */

        // Entry without database
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    // Version in video but old method
    private void singIn(){
        Intent singInIntend = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(singInIntend,RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result return from launching the Intend from GoogleSingInApi.getSingInIntend(...);
        if (requestCode ==RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG,"handleSignInResult:"+ result.isSuccess());
        if (result.isSuccess()){
            //Singed in WONDERFUL , show authenticated UI
            GoogleSignInAccount acct = result.getSignInAccount();
            statusTextView.setText("\n" +"Welcome"+ "\n" + acct.getDisplayName() );
            //result.setTextSize(40); значение в sp
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unreasonable error has occurred and Google APIs wont be available
        Log.d(TAG,"onConnectionFail");
    }

    // Button Exit from Google notification
    private void singOut () {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                statusTextView.setText("Sing OUT ");
            }
        });
    }


}
