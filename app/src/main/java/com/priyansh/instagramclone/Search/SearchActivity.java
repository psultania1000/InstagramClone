package com.priyansh.instagramclone.Search;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.priyansh.instagramclone.R;
import com.priyansh.instagramclone.Utils.BottomNavigationViewHelper;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG = "SearchActivity";
    private static final int ACTIVITY_NUM = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Started");

        setupBottomNavigationView();
    }

    /**
     * BottomNavigationView setup
     */
    public void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting up bottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(SearchActivity.this, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        //Highlighting current menu icon by changing the icon to ic_selected_icon
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setIcon(R.drawable.ic_selected_search);
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
                    Toast.makeText(this, "Something went wrong in " + TAG, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
