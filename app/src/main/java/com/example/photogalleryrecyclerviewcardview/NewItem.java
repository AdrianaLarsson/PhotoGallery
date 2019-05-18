package com.example.photogalleryrecyclerviewcardview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class NewItem extends AppCompatActivity {

    Item mItem = new Item();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_item);


        final EditText editTextTitle = (EditText) findViewById(R.id.editTextTitle);


        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String itemToAdd = editTextTitle.getText().toString();
               Intent intent = new Intent();


                Item item = new Item();

                item.setTitle(editTextTitle.
                        getText().toString());

                finish();

            }
        });


    }

    public void createNewNote(Item item){


        mItem = item;
    }
}
