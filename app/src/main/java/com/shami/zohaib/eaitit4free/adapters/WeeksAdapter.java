package com.shami.zohaib.eaitit4free.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shami.zohaib.eaitit4free.OrderData;
import com.shami.zohaib.eaitit4free.R;

import java.util.List;

/**
 * Created by Shami on 1/5/2018.
 */

public class WeeksAdapter extends RecyclerView.Adapter<WeeksAdapter.ViewHolder> {


    List<OrderData> orderDataList;
    Context mContext;
    onClickListeners mClickListener;

    public WeeksAdapter(onClickListeners mClickListeners,Context context,List<OrderData> list)
    {
        mClickListener=mClickListeners;
        mContext=context;
        orderDataList=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.week_item, parent, false);

        return new ViewHolder(row);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mWeek.setText(orderDataList.get(position).getWeek());

    }


    @Override
    public int getItemCount() {
        return orderDataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView mWeek;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mClickListener.callBack(getAdapterPosition());
                }
            });

            mWeek=(TextView) itemView.findViewById(R.id.week);

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

