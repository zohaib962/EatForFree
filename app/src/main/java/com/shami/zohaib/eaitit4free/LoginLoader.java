package com.shami.zohaib.eaitit4free;

import android.content.AsyncTaskLoader;
import android.content.Context;

/**
 * Created by Zohaib on 12/31/2017.
 */

public class LoginLoader extends AsyncTaskLoader<String> {

    //for login
    String murl;
    String musername;
    String muserpass;

    // for registration
    String memaill;
    String mConfirmEmail;
    String mpass;
    String mConfirmPass;

    int mMode;

    public LoginLoader(Context context, String urll,String username,String pass,int mode) {
        super(context);
        murl=urll;
        musername=username;
        muserpass=pass;
        mMode=mode;
    }

    public LoginLoader(Context context,String urll,String emaill,String cnfrmEmail,String pass,String cnfrmPass,int mode)
    {
        super(context);
        murl=urll;
        memaill=emaill;
        mConfirmEmail=cnfrmEmail;
        mpass=pass;
        mConfirmPass=pass;
        mMode=mode;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public String loadInBackground() {
        if (murl == null) {
            return null;
        }


        if(mMode==1) {
            String result = QueryUtils.fetchData(murl, musername, muserpass);
            return result;
        }
        else if(mMode==2)
        {
            String result = QueryUtils.fetchDataForRegistration(murl, memaill,mConfirmEmail,mpass,mConfirmPass);
            return result;
        }
        else
        {
            return null;
        }
    }

}
