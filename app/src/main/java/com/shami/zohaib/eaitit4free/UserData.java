package com.shami.zohaib.eaitit4free;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Zohaib on 1/6/2018.
 */

public class UserData implements Parcelable {

    private String userId;
    private String userName;
    private String firstName;
    private String email;
    private String status;

    public UserData(String userId, String userName, String firstName, String email, String status) {
        this.userId = userId;
        this.userName = userName;
        this.firstName = firstName;
        this.email = email;
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userId);
        dest.writeString(this.userName);
        dest.writeString(this.firstName);
        dest.writeString(this.email);
        dest.writeString(this.status);
    }

    protected UserData(Parcel in) {
        this.userId = in.readString();
        this.userName = in.readString();
        this.firstName = in.readString();
        this.email = in.readString();
        this.status = in.readString();
    }

    public static final Parcelable.Creator<UserData> CREATOR = new Parcelable.Creator<UserData>() {
        @Override
        public UserData createFromParcel(Parcel source) {
            return new UserData(source);
        }

        @Override
        public UserData[] newArray(int size) {
            return new UserData[size];
        }
    };
}
