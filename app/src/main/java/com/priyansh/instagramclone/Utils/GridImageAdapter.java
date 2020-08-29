package com.priyansh.instagramclone.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.priyansh.instagramclone.R;

import java.util.ArrayList;

public class GridImageAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private int mLayoutResource;
    private String mAppend;
    private ArrayList<String> mImageURLs;

    public GridImageAdapter(Context mContext, int mLayoutResource, String mAppend, ArrayList<String> mImageURLs) {
        super(mContext, mLayoutResource, mImageURLs);
        this.mContext = mContext;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mLayoutResource = mLayoutResource;
        this.mAppend = mAppend;
        this.mImageURLs = mImageURLs;
    }

    private static class ViewHolder {
        SquareImageView mImageView;
        ProgressBar mProgressBar;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        /**
         * viewHolder build pattern
         */
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(mLayoutResource, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.mImageView = convertView.findViewById(R.id.gridViewImageView);
            viewHolder.mProgressBar = convertView.findViewById(R.id.gridImageProgressBar);
            //viewHolder.mProgressBar.setVisibility(View.GONE);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String imgURL = getItem(position);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(mAppend + imgURL, viewHolder.mImageView, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                if (viewHolder.mProgressBar != null) {
                    viewHolder.mProgressBar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                if (viewHolder.mProgressBar != null) {
                    viewHolder.mProgressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                if (viewHolder.mProgressBar != null) {
                    viewHolder.mProgressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                if (viewHolder.mProgressBar != null) {
                    viewHolder.mProgressBar.setVisibility(View.GONE);
                }
            }
        });

        return convertView;
    }
}
