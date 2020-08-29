package com.priyansh.instagramclone.Utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.priyansh.instagramclone.Alert.LikesActivity;
import com.priyansh.instagramclone.Home.MainActivity;
import com.priyansh.instagramclone.Profile.ProfileActivity;
import com.priyansh.instagramclone.R;
import com.priyansh.instagramclone.Search.SearchActivity;
import com.priyansh.instagramclone.Share.ShareActivity;

public class BottomNavigationViewHelper {
    private static final String TAG = "BottomNavigationViewHel";

    /*
     * Not using setupBottomNavigationView function due to some problems in the
     * {@link BottomNavigationViewEx}.
     * It might be possible that since this class is using legacy design support library
     * it is not fully compatible with SDK>28 as it uses Material instead of design
     * But we will continue to use this java class for other functions/methods created below
     * this method
     */

    public static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx) {
        Log.d(TAG, "setupBottomNavigationView: setting up bottomNavigationView \t" + bottomNavigationViewEx);
        /*
         * Instead of using these 4 line of following code we can also use
         * app:labelVisibilityMode="unlabeled" in xml file
         * Both will give exact result
         * TODO(1) Create another 5 icon with fill color blue and create animation or something where on click the NavigationBottomMenu color of icon will change to blue and it will revert back to gray on click of other menu.
         */
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(false);
    }

    public static void enableNavigation(final Context context, final BottomNavigationViewEx view) {
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_house:
                        Intent intent1 = new Intent(context, MainActivity.class); //ACTIVITY_NUM = 0
                        intent1.putExtra("ACTIVITY_NUM", 0);
                        context.startActivity(intent1);
                        break;
                    case R.id.ic_search:
                        Intent intent2 = new Intent(context, SearchActivity.class); //ACTIVITY_NUM = 1
                        intent2.putExtra("ACTIVITY_NUM", 1);
                        context.startActivity(intent2);
                        break;
                    case R.id.ic_circle:
                        Intent intent3 = new Intent(context, ShareActivity.class); //ACTIVITY_NUM = 2
                        intent3.putExtra("ACTIVITY_NUM", 2);
                        context.startActivity(intent3);
                        break;
                    case R.id.ic_alert:
                        Intent intent4 = new Intent(context, LikesActivity.class); //ACTIVITY_NUM = 3
                        intent4.putExtra("ACTIVITY_NUM", 3);
                        context.startActivity(intent4);
                        break;
                    case R.id.ic_android:
                        Intent intent5 = new Intent(context, ProfileActivity.class); ////ACTIVITY_NUM = 5
                        intent5.putExtra("ACTIVITY_NUM", 4);
                        context.startActivity(intent5);
                        break;
                    default:
                        Toast.makeText(context, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });
    }

}
