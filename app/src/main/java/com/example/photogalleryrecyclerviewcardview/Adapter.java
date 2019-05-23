package com.example.photogalleryrecyclerviewcardview;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ListItemHolder> {

    //private ArrayList<Item> itemList;
    private Context context;
    private List<Item> mItemList;
    private MainActivity mMainActivity;


    public Adapter(MainActivity mainActivity,
                               List<Item> itemlist) {

        mMainActivity = mainActivity;
        mItemList = itemlist;
    }


    @NonNull
    @Override
    public Adapter.ListItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);


        return new ListItemHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final Adapter.ListItemHolder listItemHolder, final int position) {
        Item item = mItemList.get(position);
        listItemHolder.mTitle.setText(item.getTitle());

        listItemHolder.mImage.setImageURI(item.getImageUri());


        listItemHolder.mTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Intent intent = new Intent(v.getContext(), NewItem.class);


                 TextView textView = (TextView) listItemHolder.mTitle.findViewById(R.id.textViewTitle);
                 ImageView imageView = (ImageView) listItemHolder.mImage.findViewById(R.id.imageViewEdit);
                Intent intent = new Intent(mMainActivity,NewItem.class);

                intent.putExtra("textViewTitle", textView.getText().toString());

                Toast.makeText(mMainActivity, "You clicked on image "  + String.valueOf(textView.getText()), Toast.LENGTH_SHORT).show();

                mMainActivity.startActivity(intent);


            }
        });



    }


    @Override
    public int getItemCount() {

        return mItemList.size();
    }


    public class ListItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


       TextView mTitle;
       ImageView mImage;

        public ListItemHolder(@NonNull View itemView) {
            super(itemView);

            mTitle = (TextView)
                    itemView.findViewById(R.id.textViewTitle);

            mImage = (ImageView) itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(this);
            mImage.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {


            Log.i("info", "" + getAdapterPosition());


            Toast.makeText(mMainActivity, "You clicked on image " + " " + String.valueOf(mTitle.getText() + " and is on position " + getAdapterPosition() ), Toast.LENGTH_SHORT).show();




        }
    }

}
