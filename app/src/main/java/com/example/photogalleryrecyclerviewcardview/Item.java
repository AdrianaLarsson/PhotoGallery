package com.example.photogalleryrecyclerviewcardview;

import android.net.Uri;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;

public class Item {

    private String mTitle;
    private Uri mImageUri;
    private String mDescription;
    private static final String JSON_TITLE = "title";
    private static final String JSON_DESCRIPTION = "description";



    public Item(JSONObject jsonObject) throws JSONException {

        mTitle = jsonObject.getString(JSON_TITLE);
        mDescription = jsonObject.getString(JSON_DESCRIPTION);


    }
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

    public JSONObject convertToJSON() throws JSONException {


        JSONObject jsonObject = new JSONObject();

        jsonObject.put(JSON_TITLE, mTitle);
        jsonObject.put(JSON_DESCRIPTION, mDescription);



        return  jsonObject;
    }

}