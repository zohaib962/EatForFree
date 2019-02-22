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
 * Created by Zohaib on 1/22/2018.
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.MyViewHolder>{


    private List<ItemData> mlist;
    public ItemsAdapter(List<ItemData> list)
    {
        mlist=list;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.order_list_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.item.setText(mlist.get(position).getMenuName());
        holder.price.setText(mlist.get(position).getMenuPrice());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView item,price;

        public MyViewHolder(View itemView) {
            super(itemView);
            item=(TextView)itemView.findViewById(R.id.orderItemTV);
            price=(TextView)itemView.findViewById(R.id.orderPriceTV);
        }
    }

}
