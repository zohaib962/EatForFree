package com.shami.zohaib.eaitit4free;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

public class ForgotPassword extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String >  {


    EditText mEnterMail;

    ProgressDialog progress;

    String url=" http://api.eat-it-4free.co.uk/api.php?action=forgotpassword&apisecretkey=NNTnnQEgzpDbcbudjQyz&apiuserkey=WebAPIE4F";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        mEnterMail=(EditText)findViewById(R.id.editusername);

    }

    private boolean isValidEmaillId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    public void sendMail(View view)
    {

        if(mEnterMail.getText().toString().length()>=0)
        {
            sendMail();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Please Enter Email Address",Toast.LENGTH_SHORT).show();
        }



    }

    public final static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    int tries=1;
    void sendMail()
    {

        if(isValidEmail(mEnterMail.getText().toString()))
        {
            LoaderManager mLoaderManager=getLoaderManager();
            mLoaderManager.initLoader(tries, null, this);
            tries++;

        }
        else
        {
            Toast.makeText(getApplicationContext(),"Please Enter valid Email",Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        progress= ProgressDialog.show(ForgotPassword.this, "",
                "Loading. Please wait...", true);
        return new com.shami.zohaib.eaitit4free.Loaders.ForgotPassword(this,url,mEnterMail.getText().toString());
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {

        progress.dismiss();
        try {
            JSONObject jsonObject=new JSONObject(data);

            if(jsonObject.has("forgotpassword"))
            {
                Toast.makeText(getApplicationContext(),"Forgot has been Sent Via Email",Toast.LENGTH_SHORT).show();
                finish();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Fail to reset Password",Toast.LENGTH_SHORT).show();

            }


        } catch (JSONException e) {

            Toast.makeText(getApplicationContext(),"Fail to reset Password",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}
