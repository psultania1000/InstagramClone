package com.priyansh.instagramclone.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.priyansh.instagramclone.Login.LoginActivity;
import com.priyansh.instagramclone.R;
import com.priyansh.instagramclone.Utils.BottomNavigationViewHelper;
import com.priyansh.instagramclone.Utils.SectionPagerAdapter;
import com.priyansh.instagramclone.Utils.UniversalImageLoader;

import java.security.PrivateKey;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int ACTIVITY_NUM = 0;
    private Context mContext;

    //Firebase Objects
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Starting");

        mContext = this;
        setupFirebaseAuth();

        initImageLoader();
        setupBottomNavigationView();
        setupViewPager();
    }



    /**
     * BottomNavigationView setup
     */
    public void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting up bottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(MainActivity.this, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        //Highlighting current menu icon by changing the icon to ic_selected_icon
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setIcon(R.drawable.ic_selected_house);
        menuItem.setChecked(true);
        Intent intent = getIntent();
        //Getting the number of previous activity
        int prev_activity_num = intent.getIntExtra("ACTIVITY_NUM", 0);
        //De-highlighting previous menu icon by changing the icon to ic_icon
        menuItem = menu.getItem(ACTIVITY_NUM);
        if (ACTIVITY_NUM != prev_activity_num) {
            switch (prev_activity_num) {
                case 0:
                    menuItem.setIcon(R.drawable.ic_house);
                    break;
                case 1:
                    menuItem.setIcon(R.drawable.ic_search);
                    break;
                case 2:
                    menuItem.setIcon(R.drawable.ic_circle);
                    break;
                case 3:
                    menuItem.setIcon(R.drawable.ic_alert);
                    break;
                case 4:
                    menuItem.setIcon(R.drawable.ic_android);
                    break;
                default:
                    Toast.makeText(this, "Something went wrong in "+TAG, Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Function of setupViewPager is adding 3 tabs/Fragments in HomeActivity :-
     * Camera
     * Home
     * Messages
     */
    public void setupViewPager() {
        SectionPagerAdapter sectionPagerAdapter = new SectionPagerAdapter(getSupportFragmentManager());
        sectionPagerAdapter.addFragment(new CameraFragment()); //Index: 0
        sectionPagerAdapter.addFragment(new HomeFragment()); //Index: 1
        sectionPagerAdapter.addFragment(new MessagesFragment()); //Index: 2
        ViewPager viewPager = findViewById(R.id.container);
        viewPager.setAdapter(sectionPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_camera);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_insta_logo_invisible_bg);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_messages);
    }

    private void initImageLoader() {
        UniversalImageLoader imageLoader = new UniversalImageLoader(mContext);
        ImageLoader.getInstance().init(imageLoader.getConfig());
    }


    /**
     * Here Everything about firebase is happening from instantiating firebaseAuth
     * to add and removing authStateListener on it inside onStart and onStop method
     */


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
                checkCurrentUser(currentUser);
            }
        };
    }

    /**
     * Check to see if the @param 'user' is logged in
     * @param user
     */

    private void checkCurrentUser(FirebaseUser user) {
        Log.d(TAG, "checkCurrentUser: Checking if user is logged in");

        if (user == null) {
            //User is not signed in
            Intent intent = new Intent(mContext, LoginActivity.class);
            startActivity(intent);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
        checkCurrentUser(mFirebaseAuth.getCurrentUser());
    }

    @Override
    protected void onStop() {
        super.onStop();
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }

}