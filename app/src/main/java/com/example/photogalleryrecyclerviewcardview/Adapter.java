package com.example.photogalleryrecyclerviewcardview;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ListItemHolder> {

    private MainActivity activity;
    private ArrayList<Item> itemList;

    public Adapter(MainActivity mainActivity, ArrayList<Item> itemList) {

        this.activity = mainActivity;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public Adapter.ListItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);

        return new ListItemHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ListItemHolder listItemHolder, int position) {
        Item item = itemList.get(position);
        listItemHolder.mTitle.setText(item.getTitle());
    }

    @Override
    public int getItemCount() {

        return itemList.size();
    }


    public interface OnItemClickListener {
    }

    public class ListItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

       TextView mTitle;


        public ListItemHolder(@NonNull View itemView) {
            super(itemView);

            mTitle = (TextView)
                    itemView.findViewById(R.id.textViewTitle);

        }

        @Override
        public void onClick(View v) {

            //mMainActivity.showItem(getAdapterPosition());
        }
    }
}
