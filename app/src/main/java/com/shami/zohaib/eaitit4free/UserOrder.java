package com.shami.zohaib.eaitit4free;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Zohaib on 1/1/2018.
 */

public class UserOrder implements Parcelable {

    public String orderNumber;
    public String resturantName;
    public String imageUrl;



    public UserOrder(String orderNumber, String resturantName, String imageUrll) {
        this.orderNumber = orderNumber;
        this.resturantName = resturantName;

        this.imageUrl=imageUrll;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(orderNumber);
        parcel.writeString(resturantName);
        parcel.writeString(imageUrl);
    }
    public UserOrder(Parcel in) {

        orderNumber = in.readString();
        resturantName=in.readString();
        imageUrl=in.readString();
    }

    public static final Parcelable.Creator<UserOrder> CREATOR = new Parcelable.Creator<UserOrder>() {
        public UserOrder createFromParcel(Parcel in) {
            return new UserOrder(in);
        }

        public UserOrder[] newArray(int size) {
            return new UserOrder[size];
        }
    };

}
