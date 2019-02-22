package com.shami.zohaib.eaitit4free;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zohaib on 1/6/2018.
 */

public class MainData implements Parcelable {

    private UserData userData;
    private List<PreviousOrder> previousOrders;

    public MainData(UserData userData, List<PreviousOrder> previousOrders) {
        this.userData = userData;
        this.previousOrders = previousOrders;
    }


    public MainData(UserData userData) {
        this.userData = userData;
    }
    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public List<PreviousOrder> getPreviousOrders() {
        return previousOrders;
    }

    public void setPreviousOrders(List<PreviousOrder> previousOrders) {
        this.previousOrders = previousOrders;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.userData, flags);
        dest.writeList(this.previousOrders);
    }

    protected MainData(Parcel in) {
        this.userData = in.readParcelable(UserData.class.getClassLoader());
        this.previousOrders = new ArrayList<PreviousOrder>();
        in.readList(this.previousOrders, PreviousOrder.class.getClassLoader());
    }

    public static final Parcelable.Creator<MainData> CREATOR = new Parcelable.Creator<MainData>() {
        @Override
        public MainData createFromParcel(Parcel source) {
            return new MainData(source);
        }

        @Override
        public MainData[] newArray(int size) {
            return new MainData[size];
        }
    };
}
