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
    private static ArrayList<Item> itemList;


    private ImageView imageViewEdit;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }

    //set upp the recycler, layout and the adapter
    public void setUpTheRecycler(){

        itemList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mAdapter = new Adapter(this,itemList);

        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

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

    @Override
    protected void onResume() {
        super.onResume();

    }

    public static void addItem(Item item){
        itemList.add(item);


        mAdapter.notifyDataSetChanged();
    }


}