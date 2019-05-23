package com.example.photogalleryrecyclerviewcardview;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public  class NewItem extends AppCompatActivity {

    ArrayList<Item> itemArrayList = new ArrayList<>();

    Item mItem;
    ImageView imageViewEdit;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private  Adapter mAdapter;


    private RecyclerView recyclerView;
    private JSONSerialLizer mSeriallizer;
    private ArrayList<Item> itemList;

    private MainActivity mainActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_item);


        mSeriallizer = new JSONSerialLizer("PhotoGalleryRecyclerViewCardView.json",
                getApplicationContext());

        try {
            itemList = mSeriallizer.load();

        } catch (Exception e) {
            itemList = new ArrayList<Item>();


            Log.i("Error loading items: ", "", e);

        }


        mItem = new Item();

        final EditText editTextTitle = (EditText) findViewById(R.id.textViewTitle);

        final EditText description = (EditText) findViewById(R.id.description);

        String itemToAdd = editTextTitle.getText().toString();
        String itemToAddD = description.getText().toString();
        mItem.setTitle(itemToAdd);

        mItem.setDescription(itemToAddD);

        imageViewEdit = (ImageView) findViewById(R.id. imageViewEdit);

        imageViewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent imagePicker = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(imagePicker, 1);
            }
        });

        Button saveBtn = (Button) findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveItems();
            }
        });

        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String itemToAdd = editTextTitle.getText().toString();
                String itemToAddD = description.getText().toString();

                mItem.setTitle(itemToAdd);

                mItem.setDescription(itemToAddD);

                itemList.add(mItem);
                itemList.add(mItem);

                //mainActivity.addItem(mItem);

                //intent.putExtra("ItemArrayList", itemArrayList);
                //v.getContext().startActivity(intent);



               // item.setTitle(editTextTitle.
                 //       getText().toString());

                saveItems();

                finish();

            }

        });



    }



    public void SelectPhotoMethod(View view) {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            Uri selectedImage = imageReturnedIntent.getData();

            mItem.setImageUri(selectedImage);
            imageViewEdit.setImageURI(selectedImage);
        }
    }

    public void saveItems(){
        try{

            mSeriallizer.save(itemList);
            Toast.makeText(this, "SAVES", Toast.LENGTH_SHORT).show();
            Log.i("SAVE newItem", itemList.get(0).getTitle());


        }catch(Exception e){
            Log.e("Error Saving Notes","", e);
            Toast.makeText(this, "Error Saving Notes", Toast.LENGTH_SHORT).show();

        }


    }
    //it saves when the app is on pause
    protected void onPause(){
        super.onPause();

        saveItems();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
