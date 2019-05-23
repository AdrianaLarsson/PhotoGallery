package com.example.photogalleryrecyclerviewcardview;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    private EditText editText;
    private RecyclerView recyclerView;
    private static Adapter mAdapter;
   // private static ArrayList<Item> itemList;
    private static List<Item> itemList;
    private ImageView imageViewEdit;
    private JSONSerialLizer mSeriallizer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //picks upp the methods and class from json so it saves

        mSeriallizer = new JSONSerialLizer("PhotoGalleryRecyclerViewCardView.json",
                getApplicationContext());


        try {

            itemList = mSeriallizer.load();
            Toast.makeText(this, "loads", Toast.LENGTH_SHORT).show();
            Log.e("Load", "" + itemList);


        } catch (Exception e) {


          itemList = new ArrayList<Item>();

            Log.i("Error loading items: ", "", e);
            Toast.makeText(this, "Error dosent loading items", Toast.LENGTH_SHORT).show();

        }


        setUpTheRecycler();
       // Intent intent = new Intent();
        //Bundle extras = intent.getExtras();






        editText  = (EditText) findViewById(R.id.textViewTitle);

        imageViewEdit = (ImageView) findViewById(R.id.imageViewEdit);



        FloatingActionButton floatingActionButtonPlusItemAdd = (FloatingActionButton) findViewById(R.id.floatingActionButtonPlusItemAdd);

        floatingActionButtonPlusItemAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Intent intent = new Intent(v.getContext(), NewItem.class);
                //startActivityForResult(intent, 1);

                Intent intent = new Intent(v.getContext(), NewItem.class);
                startActivityForResult(intent, -1);


            }
        });



        mAdapter.notifyDataSetChanged();
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    //set upp the recycler, layout and the adapter
    public void setUpTheRecycler(){

        //itemList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mAdapter = new Adapter(MainActivity.this, itemList);
        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 0:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    imageViewEdit.setImageURI(selectedImage);
                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                   imageViewEdit.setImageURI(selectedImage);
                }
                break;
        }

    }



    //adds item
    public static void addItem(Item item){
       itemList.add(item);
        mAdapter.notifyDataSetChanged();
    }

    //saves item
    public void saveItems(){
        try{

            mSeriallizer.save(itemList);
            Toast.makeText(this, "SAVES", Toast.LENGTH_SHORT).show();
            Log.e("Save", ""+ itemList);


        }catch(Exception e){

            Log.e("Error Saving Notes","", e);
            Toast.makeText(MainActivity.this, "Error Saving Notes", Toast.LENGTH_SHORT).show();
        }


    }
    //it saves when the app is on pause
    protected void onPause(){
        super.onPause();

        Toast.makeText(this, "on pause", Toast.LENGTH_SHORT).show();
        saveItems();

    }



}