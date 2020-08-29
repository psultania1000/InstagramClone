package com.priyansh.instagramclone.Profile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.priyansh.instagramclone.R;
import com.priyansh.instagramclone.Utils.BottomNavigationViewHelper;
import com.priyansh.instagramclone.Utils.GridImageAdapter;
import com.priyansh.instagramclone.Utils.UniversalImageLoader;

import java.util.ArrayList;
import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {


    private static final String TAG = "ProfileActivity";
    private static final int ACTIVITY_NUM = 4;
    private ImageView mImageViewProfilePhoto;
    private Context mContext;
    private GridView mGridView;
    private static final int NUMBER_OF_GRID_COLUMN = 3;

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d(TAG, "onCreate: Started");
        mContext = this;
        setupBottomNavigationView();
        setupToolbar();
        setupActivityWidgets();
        setupProfilePhoto();
        setupTempGrid();
    }

    private void setupTempGrid() {
        ArrayList<String> imgURLs = new ArrayList<>();
        imgURLs.add("https://www.notebookcheck.net/fileadmin/Notebooks/News/_nc3/Android_Logo.jpg");
        imgURLs.add("https://www.computersciencedegreehub.com/wp-content/uploads/2016/02/what-is-coding-1024x683.jpg");
        imgURLs.add("https://thumbor.forbes.com/thumbor/960x0/https%3A%2F%2Fblogs-images.forbes.com%2Fforbestechcouncil%2Ffiles%2F2019%2F01%2Fcanva-photo-editor-8-7.jpg");
        imgURLs.add("https://s17026.pcdn.co/wp-content/uploads/sites/9/2018/01/Coding.jpeg");
        imgURLs.add("https://www.notebookcheck.net/fileadmin/Notebooks/News/_nc3/Android_Logo.jpg");
        imgURLs.add("https://www.computersciencedegreehub.com/wp-content/uploads/2016/02/what-is-coding-1024x683.jpg");
        imgURLs.add("https://thumbor.forbes.com/thumbor/960x0/https%3A%2F%2Fblogs-images.forbes.com%2Fforbestechcouncil%2Ffiles%2F2019%2F01%2Fcanva-photo-editor-8-7.jpg");
        imgURLs.add("https://s17026.pcdn.co/wp-content/uploads/sites/9/2018/01/Coding.jpeg");
        imgURLs.add("https://www.notebookcheck.net/fileadmin/Notebooks/News/_nc3/Android_Logo.jpg");
        imgURLs.add("https://www.computersciencedegreehub.com/wp-content/uploads/2016/02/what-is-coding-1024x683.jpg");
        imgURLs.add("https://thumbor.forbes.com/thumbor/960x0/https%3A%2F%2Fblogs-images.forbes.com%2Fforbestechcouncil%2Ffiles%2F2019%2F01%2Fcanva-photo-editor-8-7.jpg");
        imgURLs.add("https://s17026.pcdn.co/wp-content/uploads/sites/9/2018/01/Coding.jpeg");
        imgURLs.add("https://www.notebookcheck.net/fileadmin/Notebooks/News/_nc3/Android_Logo.jpg");
        imgURLs.add("https://www.computersciencedegreehub.com/wp-content/uploads/2016/02/what-is-coding-1024x683.jpg");
        imgURLs.add("https://thumbor.forbes.com/thumbor/960x0/https%3A%2F%2Fblogs-images.forbes.com%2Fforbestechcouncil%2Ffiles%2F2019%2F01%2Fcanva-photo-editor-8-7.jpg");
        imgURLs.add("https://s17026.pcdn.co/wp-content/uploads/sites/9/2018/01/Coding.jpeg");
        imgURLs.add("https://www.notebookcheck.net/fileadmin/Notebooks/News/_nc3/Android_Logo.jpg");
        imgURLs.add("https://www.computersciencedegreehub.com/wp-content/uploads/2016/02/what-is-coding-1024x683.jpg");
        imgURLs.add("https://thumbor.forbes.com/thumbor/960x0/https%3A%2F%2Fblogs-images.forbes.com%2Fforbestechcouncil%2Ffiles%2F2019%2F01%2Fcanva-photo-editor-8-7.jpg");
        imgURLs.add("https://s17026.pcdn.co/wp-content/uploads/sites/9/2018/01/Coding.jpeg");
        imgURLs.add("https://www.notebookcheck.net/fileadmin/Notebooks/News/_nc3/Android_Logo.jpg");
        imgURLs.add("https://www.computersciencedegreehub.com/wp-content/uploads/2016/02/what-is-coding-1024x683.jpg");
        imgURLs.add("https://thumbor.forbes.com/thumbor/960x0/https%3A%2F%2Fblogs-images.forbes.com%2Fforbestechcouncil%2Ffiles%2F2019%2F01%2Fcanva-photo-editor-8-7.jpg");
        imgURLs.add("https://s17026.pcdn.co/wp-content/uploads/sites/9/2018/01/Coding.jpeg");
        imgURLs.add("https://www.notebookcheck.net/fileadmin/Notebooks/News/_nc3/Android_Logo.jpg");
        imgURLs.add("https://www.computersciencedegreehub.com/wp-content/uploads/2016/02/what-is-coding-1024x683.jpg");
        imgURLs.add("https://thumbor.forbes.com/thumbor/960x0/https%3A%2F%2Fblogs-images.forbes.com%2Fforbestechcouncil%2Ffiles%2F2019%2F01%2Fcanva-photo-editor-8-7.jpg");
        imgURLs.add("https://s17026.pcdn.co/wp-content/uploads/sites/9/2018/01/Coding.jpeg");
        imgURLs.add("https://www.notebookcheck.net/fileadmin/Notebooks/News/_nc3/Android_Logo.jpg");
        imgURLs.add("https://www.computersciencedegreehub.com/wp-content/uploads/2016/02/what-is-coding-1024x683.jpg");
        imgURLs.add("https://thumbor.forbes.com/thumbor/960x0/https%3A%2F%2Fblogs-images.forbes.com%2Fforbestechcouncil%2Ffiles%2F2019%2F01%2Fcanva-photo-editor-8-7.jpg");
        imgURLs.add("https://s17026.pcdn.co/wp-content/uploads/sites/9/2018/01/Coding.jpeg");
        imgURLs.add("https://www.notebookcheck.net/fileadmin/Notebooks/News/_nc3/Android_Logo.jpg");
        imgURLs.add("https://www.computersciencedegreehub.com/wp-content/uploads/2016/02/what-is-coding-1024x683.jpg");
        imgURLs.add("https://thumbor.forbes.com/thumbor/960x0/https%3A%2F%2Fblogs-images.forbes.com%2Fforbestechcouncil%2Ffiles%2F2019%2F01%2Fcanva-photo-editor-8-7.jpg");
        imgURLs.add("https://s17026.pcdn.co/wp-content/uploads/sites/9/2018/01/Coding.jpeg");
        imgURLs.add("https://www.notebookcheck.net/fileadmin/Notebooks/News/_nc3/Android_Logo.jpg");
        imgURLs.add("https://www.computersciencedegreehub.com/wp-content/uploads/2016/02/what-is-coding-1024x683.jpg");
        imgURLs.add("https://thumbor.forbes.com/thumbor/960x0/https%3A%2F%2Fblogs-images.forbes.com%2Fforbestechcouncil%2Ffiles%2F2019%2F01%2Fcanva-photo-editor-8-7.jpg");
        imgURLs.add("https://s17026.pcdn.co/wp-content/uploads/sites/9/2018/01/Coding.jpeg");
        imgURLs.add("https://www.notebookcheck.net/fileadmin/Notebooks/News/_nc3/Android_Logo.jpg");
        imgURLs.add("https://www.computersciencedegreehub.com/wp-content/uploads/2016/02/what-is-coding-1024x683.jpg");
        imgURLs.add("https://thumbor.forbes.com/thumbor/960x0/https%3A%2F%2Fblogs-images.forbes.com%2Fforbestechcouncil%2Ffiles%2F2019%2F01%2Fcanva-photo-editor-8-7.jpg");
        imgURLs.add("https://s17026.pcdn.co/wp-content/uploads/sites/9/2018/01/Coding.jpeg");

        setupImageGrid(imgURLs);


    }

    private void setupImageGrid(ArrayList<String> imgURLs) {

        int gridWidth = getResources().getDisplayMetrics().widthPixels;
        int imageWidth = gridWidth/NUMBER_OF_GRID_COLUMN;
        mGridView.setColumnWidth(imageWidth);




        GridImageAdapter gridImageAdapter = new GridImageAdapter(mContext, R.layout.layout_grid_imageview, "", imgURLs);
        mGridView.setAdapter(gridImageAdapter);
    }

    private void setupProfilePhoto() {
        Log.d(TAG, "setupProfilePhoto: Setting up profile photo");
        String imageURL = "https://www.notebookcheck.net/fileadmin/Notebooks/News/_nc3/Android_Logo.jpg";
        //You can send mProgressBar/null as 3rd Parameter below
        UniversalImageLoader.setImage(imageURL, mImageViewProfilePhoto, mProgressBar, "");
    }

    private void setupActivityWidgets() {
        Log.d(TAG, "setupActivityWidgets: Setting Up Widgets");
        mImageViewProfilePhoto = findViewById(R.id.profile_image);
        mProgressBar = findViewById(R.id.progress_circular);
        mProgressBar.setVisibility(View.GONE);
        mGridView = findViewById(R.id.gridView);
    }

    /**
     * BottomNavigationView setup
     */
    private void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting up bottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(ProfileActivity.this, bottomNavigationViewEx);
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

    private void setupToolbar() {
        Toolbar toolbar =  findViewById(R.id.profileToolbar);
        setSupportActionBar(toolbar);

        ImageView imageView = findViewById(R.id.profileMenu);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Navigating to account settings.");
                Intent intent = new Intent(ProfileActivity.this, AccountSettingsActivity.class);
                startActivity(intent);
            }
        });
    }


}
