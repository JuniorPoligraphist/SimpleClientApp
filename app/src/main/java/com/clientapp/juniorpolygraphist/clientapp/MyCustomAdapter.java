package com.clientapp.juniorpolygraphist.clientapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Junior Polygraphist on 23.05.2017.
 */

public class MyCustomAdapter extends BaseAdapter {

    private ArrayList<String> mListItems;
    private LayoutInflater mLayoutInflater;

    public MyCustomAdapter(Context context, ArrayList<String> arrayList) {

        mListItems = arrayList;

        //get the layout inflater
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mListItems.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    //get the position id of the item from the list
    public long getItemId(int i) {
        return 0;
    }

    @Override

    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.list_item_view, null);
        }

        String stringItem = mListItems.get(position);
        if (stringItem != null) {

            TextView itemName = (TextView) view.findViewById(R.id.list_item_text_view);

            if (itemName != null) {
                //set the item name on the TextView
                itemName.setText(stringItem);
            }
        }

        return view;
    }
}