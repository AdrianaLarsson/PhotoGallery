package com.example.photogalleryrecyclerviewcardview;

import android.net.Uri;
import android.widget.ImageView;

import java.net.URI;

public class Item {

    private String mTitle;
    private Uri mImageUri;


    public Item(){}

    public Uri getImageUri()

    {
        return mImageUri;
    }


    public void setImageUri(Uri imageUri) {

        this.mImageUri = imageUri;
    }


    public String getTitle()

    {
        return mTitle;
    }


    public void setTitle(String mTitle) {

        this.mTitle = mTitle;
    }


}