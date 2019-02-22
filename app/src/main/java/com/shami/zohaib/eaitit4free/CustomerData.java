package com.shami.zohaib.eaitit4free;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Zohaib on 1/6/2018.
 */

public class CustomerData  implements Parcelable{

    private String deliveryFirstName;
    private String deliveryLastName;
    private String deliveryEmail;
    private String deliveryAddress;

    public CustomerData(String deliveryFirstName, String deliveryLastName, String deliveryEmail, String deliveryAddress) {
        this.deliveryFirstName = deliveryFirstName;
        this.deliveryLastName = deliveryLastName;
        this.deliveryEmail = deliveryEmail;
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryFirstName() {
        return deliveryFirstName;
    }

    public void setDeliveryFirstName(String deliveryFirstName) {
        this.deliveryFirstName = deliveryFirstName;
    }

    public String getDeliveryLastName() {
        return deliveryLastName;
    }

    public void setDeliveryLastName(String deliveryLastName) {
        this.deliveryLastName = deliveryLastName;
    }

    public String getDeliveryEmail() {
        return deliveryEmail;
    }

    public void setDeliveryEmail(String deliveryEmail) {
        this.deliveryEmail = deliveryEmail;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(deliveryFirstName);
        parcel.writeString(deliveryLastName);
        parcel.writeString(deliveryEmail);
        parcel.writeString(deliveryAddress);
    }

    public CustomerData(Parcel in) {

        deliveryFirstName = in.readString();
        deliveryLastName=in.readString();
        deliveryEmail=in.readString();
        deliveryAddress=in.readString();
    }

    public static final Parcelable.Creator<CustomerData> CREATOR = new Parcelable.Creator<CustomerData>() {
        public CustomerData createFromParcel(Parcel in) {
            return new CustomerData(in);
        }

        public CustomerData[] newArray(int size) {
            return new CustomerData[size];
        }
    };


}

