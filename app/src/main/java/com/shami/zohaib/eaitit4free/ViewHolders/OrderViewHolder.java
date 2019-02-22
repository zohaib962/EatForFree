package com.shami.zohaib.eaitit4free.ViewHolders;

import android.view.View;
import android.widget.TextView;

import com.shami.zohaib.eaitit4free.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

/**
 * Created by Zohaib on 1/21/2018.
 */

public class OrderViewHolder extends ChildViewHolder{

    public TextView week;
    public TextView mDate;
    public TextView payment;
    public TextView price;
    public OrderViewHolder(View itemView) {
        super(itemView);

        week=(TextView)itemView.findViewById(R.id.weekTV);
        mDate=(TextView)itemView.findViewById(R.id.dateAndTimeTV);
        payment=(TextView)itemView.findViewById(R.id.paymentTV);
        price=(TextView)itemView.findViewById(R.id.amountTV);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }




}
