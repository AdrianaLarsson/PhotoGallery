package com.example.photogalleryrecyclerviewcardview;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class NewItem extends AppCompatActivity {

    ArrayList<Item> itemArrayList = new ArrayList<>();

    Item mItem;
    ImageView imageViewEdit;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    Uri imgUri=Uri.parse("file:///data/data/MYFOLDER/myimage.png");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_item);

        mItem = new Item();

        final EditText editTextTitle = (EditText) findViewById(R.id.textViewTitle);

        imageViewEdit = (ImageView) findViewById(R.id. imageViewEdit);

        imageViewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent imagePicker = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(imagePicker, 1);
            }
        });

        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String itemToAdd = editTextTitle.getText().toString();

                mItem.setTitle(itemToAdd);

                MainActivity.addItem(mItem);

                //intent.putExtra("ItemArrayList", itemArrayList);
                //v.getContext().startActivity(intent);



               // item.setTitle(editTextTitle.
                 //       getText().toString());

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
}
