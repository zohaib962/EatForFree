package com.shami.zohaib.eaitit4free.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shami.zohaib.eaitit4free.Models.ResutratantModel;
import com.shami.zohaib.eaitit4free.Models.WeekModel;
import com.shami.zohaib.eaitit4free.R;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.List;

/**
 * Created by Zohaib on 1/21/2018.
 */

public class ResturantAdapter extends ExpandableRecyclerViewAdapter<ResturantAdapter.ResturantViewHolder,ResturantAdapter.OrderViewHolder> {

    List<ResutratantModel> mList;
    Context mContext;
    mClickListeners clickListeners;

    public ResturantAdapter(List<ResutratantModel> mList,Context context,mClickListeners mClickListeners) {

        super(mList);
        this.mList=mList;
        this.mContext=context;
        this.clickListeners=mClickListeners;
    }

    @Override
    public ResturantViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.resturant_detail,parent,false);
        return new ResturantViewHolder(view);
    }

    @Override
    public OrderViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
       View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.week_order_detail,parent,false);
       return new OrderViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(OrderViewHolder holder, final int flatPosition, final ExpandableGroup group, final int childIndex) {

//        WeekModel mCurrentItem=mList.get(flatPosition).getItems().get(childIndex);
//
//        holder.week.setText(mCurrentItem.getWeek());
//        holder.price.setText(mCurrentItem.getOrderTotal());
//        holder.payment.setText(mCurrentItem.getOrderPaymentMethod());
//        holder.mDate.setText(mCurrentItem.getOrderDateAdd());
//
//        holder.week.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                clickListeners.clickZemaListener(flatPosition,childIndex);
//            }
//        });

    final WeekModel mWeekModel=((ResutratantModel)group).getItems().get(childIndex);

    holder.week.setText(mWeekModel.getWeek());
    holder.mDate.setText(mWeekModel.getOrderDateAdd());
    holder.payment.setText(mWeekModel.getOrderPaymentMethod());
    holder.price.setText(mWeekModel.getOrderTotal());

    holder.mContianer.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            clickListeners.clickZemaListener(mWeekModel);
        }
    });


    }


    @Override
    public void onBindGroupViewHolder(ResturantViewHolder holder, int flatPosition, ExpandableGroup group) {

        ResutratantModel mCurrentResturant=((ResutratantModel)group);

        holder.resturantName.setText(group.getTitle());
        Glide.with(mContext).load(mCurrentResturant.getmResturantIcon()).into(holder.resturantPic);


    }


    public interface  mClickListeners
    {
        void clickZemaListener(WeekModel weekModel);
    }


    public class ResturantViewHolder extends GroupViewHolder
    {

        TextView resturantName;
        ImageView resturantPic;


        public ResturantViewHolder(View itemView) {
            super(itemView);

            resturantName=(TextView)itemView.findViewById(R.id.newResturantName);
            resturantPic=(ImageView)itemView.findViewById(R.id.resturantPicIV);

        }



    }



    public class OrderViewHolder extends ChildViewHolder {

        public TextView week;
        public TextView mDate;
        public TextView payment;
        public TextView price;
        public CardView mContianer;

        public OrderViewHolder(View itemView) {
            super(itemView);

            week=(TextView)itemView.findViewById(R.id.weekTV);
            mDate=(TextView)itemView.findViewById(R.id.dateAndTimeTV);
            payment=(TextView)itemView.findViewById(R.id.paymentTV);
            price=(TextView)itemView.findViewById(R.id.amountTV);

            mContianer=(CardView)itemView.findViewById(R.id.container);

        }




    }






}
