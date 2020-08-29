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
import com.priyansh.instagramclone.Home.MainActivity;
import com.priyansh.instagramclone.R;
import com.priyansh.instagramclone.Utils.FirebaseMethods;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";

    //Firebase Objects
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private Context mContext;
    private ProgressBar mProgressBar;
    private TextView mTextViewPleaseWait;
    private EditText mEditTextEmail;
    private EditText mEditTextName;
    private EditText mEditTextPassword;
    private Button mButtonRegister;
    private TextView mTextViewSignIn;
    private FirebaseMethods mFirebaseMethods;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Log.d(TAG, "onCreate: Started");
        setupActivityWidgets();
        setupFirebaseAuth();
        init();
    }

    /**
     * Setting up all the widgets
     */
    private void setupActivityWidgets() {
        mContext = this;
        mProgressBar = findViewById(R.id.progressBarInRegister);
        mEditTextEmail = findViewById(R.id.editTextEmailRegister);
        mEditTextPassword = findViewById(R.id.editTextPasswordRegister);
        mButtonRegister = findViewById(R.id.buttonRegister);
        mEditTextName = findViewById(R.id.editTextNameRegister);
        mTextViewSignIn = findViewById(R.id.linkSignIn);
        mTextViewPleaseWait = findViewById(R.id.textViewPleaseWaitRegister);
        mFirebaseMethods = new FirebaseMethods(mContext);

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

                mProgressBar.setVisibility(View.VISIBLE);
                mTextViewPleaseWait.setVisibility(View.VISIBLE);
                Log.d(TAG, "onClick: attempting login");
                String email = mEditTextEmail.getText().toString();
                String password = mEditTextPassword.getText().toString();
                String name = mEditTextName.getText().toString();
                if (isStringNull(email) && isStringNull(password) && isStringNull(name)) {
                    Toast.makeText(mContext, "Fields can't be empty", Toast.LENGTH_SHORT).show();
                } else {
                    //Registered User is Method created in Object Class FirebaseMethods
                    mFirebaseMethods.registerUser(email, password, name);
                }
                mProgressBar.setVisibility(View.GONE);
                mTextViewPleaseWait.setVisibility(View.GONE);
            }
        });
        if (mFirebaseAuth.getCurrentUser()!=null) {
            Intent intent = new Intent(mContext, MainActivity.class);
            startActivity(intent);
            finish();
        }

        mTextViewSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
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
