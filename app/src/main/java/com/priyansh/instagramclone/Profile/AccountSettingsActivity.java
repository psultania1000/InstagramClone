package com.priyansh.instagramclone.Profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.priyansh.instagramclone.R;
import com.priyansh.instagramclone.Utils.BottomNavigationViewHelper;
import com.priyansh.instagramclone.Utils.SectionStatePagerAdapter;

import java.util.ArrayList;

public class AccountSettingsActivity extends AppCompatActivity {

    public static final String TAG = "AccountSettingsActivity";
    public static final int ACTIVITY_NUM = 4;
    private Context mContext;

    private SectionStatePagerAdapter sectionStatePagerAdapter;
    private ViewPager mViewPager;
    private RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountsettings);
        Log.d(TAG, "onCreate: Started");
        mContext = AccountSettingsActivity.this;
        mViewPager = findViewById(R.id.container);
        mRelativeLayout = findViewById(R.id.relLayout1);

        setupSettingsList();
        setupFragment();
        setupBottomNavigationView();

        //Setting ImageView to navigating back to Profile Activity
        ImageView imageView = findViewById(R.id.backArrow);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Back to 'ProfileActivity'");
                finish();
            }
        });
    }

    private void setupFragment() {
        sectionStatePagerAdapter = new SectionStatePagerAdapter(getSupportFragmentManager());
        sectionStatePagerAdapter.addFragment(new EditProfileFragment(), String.valueOf(R.string.edit_profile));
        sectionStatePagerAdapter.addFragment(new SignOutFragment(), getString(R.string.sign_out));
    }

    private void setupViewPager(int fragmentNumber) {
        mRelativeLayout.setVisibility(View.GONE);
        Log.d(TAG, "setupViewPager: Navigating to fragment #" + fragmentNumber);
        mViewPager.setAdapter(sectionStatePagerAdapter);
        mViewPager.setCurrentItem(fragmentNumber);
    }

    private void setupSettingsList() {
        Log.d(TAG, "setupSettingsList: initialising Account Settings");
        ListView listView = findViewById(R.id.textViewSettings);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(getString(R.string.edit_profile));
        arrayList.add(getString(R.string.sign_out));

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemClick: Navigation to fragment#:" + i);
                setupViewPager(i);
            }
        });
    }

    /**
     * BottomNavigationView setup
     */
    private void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting up bottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(AccountSettingsActivity.this, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        //Highlighting current menu icon by changing the icon to ic_selected_icon
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setIcon(R.drawable.ic_selected_android);
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
}
