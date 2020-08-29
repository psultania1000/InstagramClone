package com.priyansh.instagramclone.Profile;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.priyansh.instagramclone.R;
import com.priyansh.instagramclone.Utils.UniversalImageLoader;

public class EditProfileFragment extends Fragment {
    private static final String TAG = "EditProfileFragment";
    private ImageView profileImage;
    private ImageView mImageViewBackButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editprofile, container, false);
        profileImage = view.findViewById(R.id.profile_photo);
        mImageViewBackButton = view.findViewById(R.id.backArrow);
        //Back Arrow to navigating back to profile Activity
        mImageViewBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Navigating back to profile activity");
                getActivity().finish();
            }
        });
        setProfileImage();
        return view;
    }



    private void setProfileImage() {
        Log.d(TAG, "setProfileImage: Setting Profile Image");
        String imageURL = "https://www.notebookcheck.net/fileadmin/Notebooks/News/_nc3/Android_Logo.jpg";
        UniversalImageLoader.setImage(imageURL, profileImage, null, "");
    }
}
