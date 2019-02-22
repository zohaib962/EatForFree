package com.shami.zohaib.eaitit4free.Models;

import android.os.Parcel;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by Zohaib on 1/21/2018.
 */

public class ResutratantModel extends ExpandableGroup<WeekModel> {


    private String mResturatName;
    private String mResturantIcon;
    private List<WeekModel> mWeekList;


    public ResutratantModel(String title,String icon, List<WeekModel> items) {
        super(title, items);
        mResturantIcon=icon;
    }

    public String getmResturatName() {
        return mResturatName;
    }

    public void setmResturatName(String mResturatName) {
        this.mResturatName = mResturatName;
    }

    public String getmResturantIcon() {
        return mResturantIcon;
    }

    public void setmResturantIcon(String mResturantIcon) {
        this.mResturantIcon = mResturantIcon;
    }

    public List<WeekModel> getmWeekList() {
        return mWeekList;
    }

    public void setmWeekList(List<WeekModel> mWeekList) {
        this.mWeekList = mWeekList;
    }

    protected ResutratantModel(Parcel in) {
        super(in);
    }
}
