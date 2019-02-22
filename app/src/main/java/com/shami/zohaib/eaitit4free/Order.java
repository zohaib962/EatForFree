package com.shami.zohaib.eaitit4free;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Zohaib on 1/1/2018.
 */

public class Order implements Parcelable {


    public String orderNumber;
    public String resturantName;
    public String paymnetMethod;
    public String orderStatus;
    public String orderCash;

    public Order(String orderNumber, String resturantName, String paymnetMethod, String orderStatus, String orderCash) {
        this.orderNumber = orderNumber;
        this.resturantName = resturantName;
        this.paymnetMethod = paymnetMethod;
        this.orderStatus = orderStatus;
        this.orderCash = orderCash;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getResturantName() {
        return resturantName;
    }

    public String getPaymnetMethod() {
        return paymnetMethod;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public String getOrderCash() {
        return orderCash;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(orderNumber);
        parcel.writeString(resturantName);
        parcel.writeString(paymnetMethod);
        parcel.writeString(orderStatus);
        parcel.writeString(orderCash);
    }

    public Order(Parcel in) {

        orderNumber = in.readString();
        resturantName=in.readString();
    }

    public static final Parcelable.Creator<Order> CREATOR = new Parcelable.Creator<Order>() {
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        public Order[] newArray(int size) {
            return new Order[size];
        }
    };


}
