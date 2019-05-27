package com.example.photogalleryrecyclerviewcardview;

import android.net.Uri;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.net.URI;



public class Item implements Serializable {

    private String mTitle;
    private Uri mImageUri;
    private String mDescription;
    private String image;
    private static final String JSON_TITLE = "title";
    private static final String JSON_DESCRIPTION = "description";
    private static final String JSON_IMAGE  = "image";



    public Item(JSONObject jsonObject) throws JSONException {

        mTitle = jsonObject.getString(JSON_TITLE);
        mDescription = jsonObject.getString(JSON_DESCRIPTION);
        image = jsonObject.getString(JSON_IMAGE);

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

    public String getmDescription()

    {
        return mDescription;
    }


    public void setDescription(String mDescription) {

        this.mDescription = mDescription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public JSONObject convertToJSON() throws JSONException {


        JSONObject jsonObject = new JSONObject();

        jsonObject.put(JSON_TITLE, mTitle);
        jsonObject.put(JSON_DESCRIPTION, mDescription);
        jsonObject.put(JSON_IMAGE,image);


        return  jsonObject;
    }

}
