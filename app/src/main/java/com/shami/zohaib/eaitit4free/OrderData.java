package com.shami.zohaib.eaitit4free;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Zohaib on 1/6/2018.
 */

public class OrderData implements Parcelable {
    private String week;
    private String orderNumber;
    private String orderTotal;
    private String orderDateAdd;
    private String orderPaymentStatus;
    private String orderStatus;
    private String deliveryType;
    private String orderPaymentMethod;
    private List<ItemData> itemData;
    private CustomerData customerData;

    public OrderData(String week, String orderNumber, String orderTotal, String orderDateAdd, String orderPaymentStatus, String orderStatus, String deliveryType, String orderPaymentMethod, List<ItemData> itemData, CustomerData customerData) {
        this.week = week;
        this.orderNumber = orderNumber;
        this.orderTotal = orderTotal;
        this.orderDateAdd = orderDateAdd;
        this.orderPaymentStatus = orderPaymentStatus;
        this.orderStatus = orderStatus;
        this.deliveryType = deliveryType;
        this.orderPaymentMethod = orderPaymentMethod;
        this.itemData = itemData;
        this.customerData = customerData;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(String orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getOrderDateAdd() {
        return orderDateAdd;
    }

    public void setOrderDateAdd(String orderDateAdd) {
        this.orderDateAdd = orderDateAdd;
    }

    public String getOrderPaymentStatus() {
        return orderPaymentStatus;
    }

    public void setOrderPaymentStatus(String orderPaymentStatus) {
        this.orderPaymentStatus = orderPaymentStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getOrderPaymentMethod() {
        return orderPaymentMethod;
    }

    public void setOrderPaymentMethod(String orderPaymentMethod) {
        this.orderPaymentMethod = orderPaymentMethod;
    }

    public List<ItemData> getItemData() {
        return itemData;
    }

    public void setItemData(List<ItemData> itemData) {
        this.itemData = itemData;
    }

    public CustomerData getCustomerData() {
        return customerData;
    }

    public void setCustomerData(CustomerData customerData) {
        this.customerData = customerData;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.week);
        dest.writeString(this.orderNumber);
        dest.writeString(this.orderTotal);
        dest.writeString(this.orderDateAdd);
        dest.writeString(this.orderPaymentStatus);
        dest.writeString(this.orderStatus);
        dest.writeString(this.deliveryType);
        dest.writeString(this.orderPaymentMethod);
        dest.writeTypedList(this.itemData);
        dest.writeParcelable(this.customerData, flags);
    }

    protected OrderData(Parcel in) {
        this.week = in.readString();
        this.orderNumber = in.readString();
        this.orderTotal = in.readString();
        this.orderDateAdd = in.readString();
        this.orderPaymentStatus = in.readString();
        this.orderStatus = in.readString();
        this.deliveryType = in.readString();
        this.orderPaymentMethod = in.readString();
        this.itemData = in.createTypedArrayList(ItemData.CREATOR);
        this.customerData = in.readParcelable(CustomerData.class.getClassLoader());
    }

    public static final Parcelable.Creator<OrderData> CREATOR = new Parcelable.Creator<OrderData>() {
        @Override
        public OrderData createFromParcel(Parcel source) {
            return new OrderData(source);
        }

        @Override
        public OrderData[] newArray(int size) {
            return new OrderData[size];
        }
    };
}
