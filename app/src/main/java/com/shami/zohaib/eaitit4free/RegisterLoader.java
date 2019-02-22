package com.shami.zohaib.eaitit4free;

import android.content.AsyncTaskLoader;
import android.content.Context;

/**
 * Created by Zohaib on 12/31/2017.
 */

public class RegisterLoader extends AsyncTaskLoader<String > {

    // for registration
    String mfname;
    String mlname;
    String mmobile;
    String memaill;
    String mpass;
    String mgender;


    public RegisterLoader(Context context,String urll,String fname,
                       String lname,String mobile,String emaill,String pass, String gender)
    {
        super(context);
        mfname=fname;
        mlname=lname;
        mmobile=mobile;
        memaill=emaill;
        mpass=pass;
        mgender=gender;

    }
    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public String loadInBackground() {
       return null;
    }
}
