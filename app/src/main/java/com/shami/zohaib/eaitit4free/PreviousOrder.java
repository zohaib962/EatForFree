package com.shami.zohaib.eaitit4free;

import android.os.Parcel;
import android.os.Parcelable;

import com.shami.zohaib.eaitit4free.Models.WeekModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zohaib on 1/6/2018.
 */

public class PreviousOrder implements Parcelable {

    private String resturantName;
    private String resturantLogo;
    private List<WeekModel> orderData;

    public PreviousOrder(String resturantName, String resturantLogo, List<WeekModel> orderData) {
        this.resturantName = resturantName;
        this.resturantLogo = resturantLogo;
        this.orderData = orderData;
    }

    public String getResturantName() {
        return resturantName;
    }

    public void setResturantName(String resturantName) {
        this.resturantName = resturantName;
    }

    public String getResturantLogo() {
        return resturantLogo;
    }

    public void setResturantLogo(String resturantLogo) {
        this.resturantLogo = resturantLogo;
    }

    public List<WeekModel> getOrderData() {
        return orderData;
    }

    public void setOrderData(List<WeekModel> orderData) {
        this.orderData = orderData;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.resturantName);
        dest.writeString(this.resturantLogo);
        dest.writeList(this.orderData);
    }

    protected PreviousOrder(Parcel in) {
        this.resturantName = in.readString();
        this.resturantLogo = in.readString();
        this.orderData = new ArrayList<WeekModel>();
        in.readList(this.orderData, WeekModel.class.getClassLoader());
    }

    public static final Parcelable.Creator<PreviousOrder> CREATOR = new Parcelable.Creator<PreviousOrder>() {
        @Override
        public PreviousOrder createFromParcel(Parcel source) {
            return new PreviousOrder(source);
        }

        @Override
        public PreviousOrder[] newArray(int size) {
            return new PreviousOrder[size];
        }
    };
}
