package com.shami.zohaib.eaitit4free.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shami.zohaib.eaitit4free.PreviousOrder;
import com.shami.zohaib.eaitit4free.R;

import java.util.List;

/**
 * Created by Zohaib on 1/1/2018.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    Context mContext;
    List<PreviousOrder> mPreviousOrder;
    onClickListeners mClickListener;

    public OrderAdapter(onClickListeners mClickListeners, Context context, List<PreviousOrder> previousOrders)
    {

        mContext=context;
        mPreviousOrder=previousOrders;
        mClickListener=mClickListeners;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.resturant_item, parent, false);

        return new ViewHolder(row);
        
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.resturantTV.setText(mPreviousOrder.get(position).getResturantName());
        Glide.with(mContext).load(mPreviousOrder.get(position).getResturantLogo()).into(holder.resturantIV);

    }

    
    @Override
    public int getItemCount() {
        return mPreviousOrder.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {


        ImageView resturantIV;
        TextView resturantTV;
        
        public ViewHolder(View itemView) {
            super(itemView);

            resturantIV=(ImageView)itemView.findViewById(R.id.resutrantIV);
            resturantTV=(TextView)itemView.findViewById(R.id.resturantNameTV);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mClickListener.callBack(getAdapterPosition());
                }
            });
//
//            mViewMore.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    mClickListener.callBack(getAdapterPosition());
//                }
//            });

        }
    
    
    
    }


    public interface  onClickListeners
    {
        public void callBack(int position);
    }

    
}
