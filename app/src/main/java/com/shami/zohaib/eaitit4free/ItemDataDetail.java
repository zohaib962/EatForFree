package com.shami.zohaib.eaitit4free;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.shami.zohaib.eaitit4free.adapters.ItemsAdapter;

import java.util.ArrayList;

public class ItemDataDetail extends AppCompatActivity {
    private RecyclerView recyclerView;

    private ItemsAdapter mItemsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_data_detail);

        Intent intent=getIntent();

        if(intent!=null)
        {

            ArrayList<ItemData> mList=intent.getParcelableArrayListExtra("mylist");

            mItemsAdapter=new ItemsAdapter(mList);

            recyclerView = (RecyclerView) findViewById(R.id.itemDataRecyclerView);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(mItemsAdapter);

        }

    }
}
