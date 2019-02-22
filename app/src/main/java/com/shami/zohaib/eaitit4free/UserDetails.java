package com.shami.zohaib.eaitit4free;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.shami.zohaib.eaitit4free.Models.ResutratantModel;
import com.shami.zohaib.eaitit4free.Models.WeekModel;
import com.shami.zohaib.eaitit4free.Utils.Constants;
import com.shami.zohaib.eaitit4free.adapters.ResturantAdapter;

import java.util.ArrayList;
import java.util.List;

public class UserDetails extends AppCompatActivity {


    TextView userTV;
    RecyclerView mlistView;

    ResturantAdapter mAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        userTV=(TextView)findViewById(R.id.fn);

       userTV.setText(Constants.mMainData.getUserData().getEmail());

        List<ResutratantModel> mlist=new ArrayList<ResutratantModel>();

        if(Constants.mMainData.getPreviousOrders()!=null) {

            for (int i = 0; i < Constants.mMainData.getPreviousOrders().size(); i++) {
                mlist.add(new ResutratantModel(
                        Constants.mMainData.getPreviousOrders().get(i).getResturantName(),
                        Constants.mMainData.getPreviousOrders().get(i).getResturantLogo(),
                        Constants.mMainData.getPreviousOrders().get(i).getOrderData()
                        )
                );
            }


            mAdapter = new ResturantAdapter(mlist, this, new ResturantAdapter.mClickListeners() {
                @Override
                public void clickZemaListener(WeekModel weekModel) {

                    Intent intent = new Intent(UserDetails.this, ItemDataDetail.class);
                    intent.putParcelableArrayListExtra("mylist", (ArrayList<? extends Parcelable>) weekModel.getItemData());
                    startActivity(intent);
                }
            });


            mlistView = (RecyclerView) findViewById(R.id.orderlistview);

            mlistView.setLayoutManager(new LinearLayoutManager(this));
            mlistView.setAdapter(mAdapter);

        }
    }
}
