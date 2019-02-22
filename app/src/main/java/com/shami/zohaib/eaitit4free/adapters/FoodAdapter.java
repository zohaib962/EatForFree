package com.shami.zohaib.eaitit4free.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shami.zohaib.eaitit4free.ItemData;
import com.shami.zohaib.eaitit4free.R;

import java.util.List;

/**
 * Created by Shami on 1/5/2018.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {


    List<ItemData> mList;

    public FoodAdapter(List<ItemData> list)
    {
        mList=list;
    }



    @Override
    public FoodAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_list_item, parent, false);


        return new FoodAdapter.ViewHolder(row);

    }

    @Override
    public void onBindViewHolder(FoodAdapter.ViewHolder holder, int position) {

        holder.mItemTitle.setText(mList.get(position).getMenuName());
        holder.mItemQty.setText(mList.get(position).getQty());
        holder.mItemPrice.setText(mList.get(position).getMenuPrice());

    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView mItemTitle;
        TextView mItemQty;
        TextView mItemPrice;

        public ViewHolder(View itemView) {
            super(itemView);

            mItemTitle=itemView.findViewById(R.id.titleTV);
            mItemQty=itemView.findViewById(R.id.qtyTV);
            mItemPrice=itemView.findViewById(R.id.priceTV);


        }



    }



}

