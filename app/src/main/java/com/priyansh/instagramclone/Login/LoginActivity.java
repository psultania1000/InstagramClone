package com.priyansh.instagramclone.Login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.priyansh.instagramclone.Home.HomeFragment;
import com.priyansh.instagramclone.Home.MainActivity;
import com.priyansh.instagramclone.R;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    //Firebase Objects
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private Context mContext;
    private ProgressBar mProgressBar;
    private TextView mTextViewPleaseWait;
    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private Button mButtonRegister;
    private TextView mTextViewRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d(TAG, "onCreate: Started");
        setupActivityWidgets();
        setupFirebaseAuth();
        //checkForSignIn();
        init();
    }

    /**
     * Setting up all the widgets
     */
    private void setupActivityWidgets() {
        mContext = this;
        mProgressBar = findViewById(R.id.progressBarInLogin);
        mEditTextEmail = findViewById(R.id.editTextEmail);
        mEditTextPassword = findViewById(R.id.editTextPassword);
        mButtonRegister = findViewById(R.id.buttonLogin);
        mTextViewPleaseWait = findViewById(R.id.textViewPleaseWait);
        mTextViewRegister = findViewById(R.id.textViewRegister);
        mProgressBar.setVisibility(View.GONE);
        mTextViewPleaseWait.setVisibility(View.GONE);
    }

    private boolean isStringNull(String s) {
        Log.d(TAG, "isStringNull: Checking Strings");
        return s.equals("");
    }

    /**
     * Here Everything about firebase is happening from instantiating firebaseAuth
     * to add and removing authStateListener on it inside onStart and onStop method
     */

    private void init() {
        Log.d(TAG, "init: Trying Logging in");
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: attempting login");
                String email = mEditTextEmail.getText().toString();
                String password = mEditTextPassword.getText().toString();
                if (isStringNull(email) && isStringNull(password)) {
                    Toast.makeText(mContext, "Fields can't be empty", Toast.LENGTH_SHORT).show();
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mTextViewPleaseWait.setVisibility(View.VISIBLE);
                    mFirebaseAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener((Activity) mContext, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {
                                        Log.d(TAG, "onComplete: SignIn w/ email failed", task.getException());
                                        Toast.makeText(mContext, "Login error", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(mContext, "Login Successful", Toast.LENGTH_SHORT).show();
                                        Log.d(TAG, "Successful Login" );
                                    }
                                    mProgressBar.setVisibility(View.GONE);
                                    mTextViewPleaseWait.setVisibility(View.GONE);
                                }
                            });
                }

            }
        });



        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, RegisterActivity.class);
                startActivity(intent);
            }
        });


        if (mFirebaseAuth.getCurrentUser()!=null) {
            Intent intent = new Intent(mContext, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void setupFirebaseAuth() {
        Log.d(TAG, "setupFirebaseAuth: Setting Up FirebaseAuth");
        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                if (currentUser != null) {
                    //User is signed in
                    Log.d(TAG, "onAuthStateChanged: Signed In w/ UI: "+currentUser);
                } else {
                    //User is signed out
                    Log.d(TAG, "onAuthStateChanged: User is signed out");
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }
}
