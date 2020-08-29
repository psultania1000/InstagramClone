package com.priyansh.instagramclone.Utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FirebaseMethods  {

    private static final String TAG = "FirebaseMethods";

    //Firebase Objects

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private Context mContext;
    private String mUserID;
    public FirebaseMethods(Context context) {
        mContext = context;
        mFirebaseAuth = FirebaseAuth.getInstance();

        if (mFirebaseAuth.getCurrentUser() != null) {
            mUserID = mFirebaseAuth.getCurrentUser().getUid();
        }
    }

    /**
     * Register new user to Firebase Authentication
     * @param email
     * @param password
     * @param username
     */
    public void registerUser (String email, String password, String username) {
        mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) mContext, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "onComplete: " + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            Toast.makeText(mContext, "Registration Failed", Toast.LENGTH_SHORT).show();
                        } else if (task.isSuccessful()) {
                            mUserID = mFirebaseAuth.getCurrentUser().getUid();
                        }
                    }
                });
    }
}
