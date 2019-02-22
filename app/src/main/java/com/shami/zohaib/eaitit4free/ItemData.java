package com.shami.zohaib.eaitit4free;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Zohaib on 1/6/2018.
 */

public class ItemData implements Parcelable{
    private String menuName;
    private String menuPrice;
    private String qty;

    public ItemData(String menuName, String menuPrice, String qty) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.qty = qty;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public void setMenuPrice(String menuPrice) {
        this.menuPrice = menuPrice;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getMenuName() {
        return menuName;
    }

    public String getMenuPrice() {
        return menuPrice;
    }

    public String getQty() {
        return qty;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(menuName);
        parcel.writeString(menuPrice);
        parcel.writeString(qty);
    }
    public ItemData(Parcel in) {

        menuName = in.readString();
        menuPrice=in.readString();
        qty=in.readString();
    }

    public static final Parcelable.Creator<ItemData> CREATOR = new Parcelable.Creator<ItemData>() {
        public ItemData createFromParcel(Parcel in) {
            return new ItemData(in);
        }

        public ItemData[] newArray(int size) {
            return new ItemData[size];
        }
    };

}
