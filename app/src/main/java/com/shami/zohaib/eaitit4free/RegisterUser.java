package com.shami.zohaib.eaitit4free;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.shami.zohaib.eaitit4free.Models.WeekModel;
import com.shami.zohaib.eaitit4free.Utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class RegisterUser extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{

    private EditText mEmail;
    private EditText mCondirmEmail;
    private EditText mPassword;
    private EditText mConfirmPassword;
    protected String bannerUrl="";
    private UserData userDataObj;
    ProgressDialog progress;
    private List<PreviousOrder> previousOrderList;
    private List<WeekModel> orderDataList;
    private CustomerData customerDataObj;
    private ItemData itemData;
    private List<ItemData> itemDataList;
    private WeekModel orderData;
    private PreviousOrder previousOrder;
    private MainData mainDataObj;

    private static final String USGS_REQUEST_URL = "http://api.eat-it-4free.co.uk/api.php?action=signup&apisecretkey=NNTnnQEgzpDbcbudjQyz&apiuserkey=WebAPIE4F";
    private static final int EARTHQUAKE_LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mEmail=(EditText)findViewById(R.id.email);
        mCondirmEmail=(EditText)findViewById(R.id.confirmemail);
        mPassword=(EditText)findViewById(R.id.enterPassword);
        mConfirmPassword=(EditText)findViewById(R.id.rePassword);

    }
    private boolean isValidEmaillId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    public boolean checkFields()
    {

        if(mEmail.getText().length()<=0)
        {


            Toast.makeText(getApplicationContext(),"Please Enter Your Email",Toast.LENGTH_SHORT);
            return false;
        }
        else
        {
            if(!isValidEmaillId(mEmail.getText().toString().trim())){
                Toast.makeText(getApplicationContext(), "In-Valid Email Address .", Toast.LENGTH_SHORT).show();
                return false;

            }
        }

        if(mCondirmEmail.getText().length()<=0)
        {

            Toast.makeText(getApplicationContext(),"Please Re-Enter Your Email ",Toast.LENGTH_SHORT);
            return false;
        }
        if(mPassword.getText().length()<=0)
        {

            Toast.makeText(getApplicationContext(),"Please Enter Your Password",Toast.LENGTH_SHORT);
            return false;
        }

        if(mConfirmPassword.getText().length()<=0)
        {

            Toast.makeText(getApplicationContext(),"Please Re Enter Your Password to Confirm",Toast.LENGTH_SHORT);
            return false;
        }

        return true;
    }



    public void registerUser(View view)
    {
//        Intent intent=new Intent(RegisterUser.this,LoginActivity.class);
//        startActivity(intent);

        if(checkFields())
        {
            register();
        }



//        LoaderManager loaderManager = getLoaderManager();
//
//        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
//        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
//        // because this activity implements the LoaderCallbacks interface).
//        loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
    }

    int contry=1;
    void register()
    {
        if(mConfirmPassword.getText().toString().trim().equals(mPassword.getText().toString().trim()))
        {
                LoaderManager loaderManager = getLoaderManager();
                loaderManager.initLoader(contry, null, this);
                contry++;

        }
        else
        {
            Toast.makeText(getApplicationContext(),"Password Doesnot Matched",Toast.LENGTH_SHORT).show();
            mPassword.setText("");
            mConfirmPassword.setText("");
        }

    }
    public void openLoginActivity(View view)
    {
        Intent intent=new Intent(RegisterUser.this,LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public Loader<String> onCreateLoader(int i, Bundle bundle) {

        progress=ProgressDialog.show(RegisterUser.this, "",
                "Loading. Please wait...", true);
            return new LoginLoader(getApplicationContext(), USGS_REQUEST_URL, mEmail.getText().toString(),mCondirmEmail.getText().toString(),
                    mPassword.getText().toString(),mConfirmPassword.getText().toString(), 2);

    }

    @Override
    public void onLoadFinished(Loader<String> loader, String s) {
        progress.dismiss();
        try {
            JSONObject baseJSONresponse=new JSONObject(s);

            if(baseJSONresponse.has("success"))
            {



                String result1=extractFeatureFromJson(s);

                if(result1==null ||result1.isEmpty())
                {

                    Toast.makeText(getApplicationContext(),"Login Unsuccessful",Toast.LENGTH_LONG).show();
                    return;
                }

                MainData myObj=extractOrderDetails(s);
                String temp=mEmail.getText().toString().trim();
                String temp2=result1.trim();
                if(s!=null && temp.equals(temp2) ) {
                    Constants.mMainData=mainDataObj;
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
                    SharedPreferences.Editor editor = pref.edit();

                    editor.putString("username",mEmail.getText().toString());
                    editor.putString("password",mPassword.getText().toString());
                    editor.commit();
                }




                Intent intent=new Intent(RegisterUser.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
            else
            {
                Toast.makeText(getApplicationContext(),"Failed To Register",Toast.LENGTH_SHORT).show();
            }


        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(),"Failed To Register",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }


    public MainData extractOrderDetails(String userJson)
    {


        ArrayList<UserOrder> myList=new ArrayList<UserOrder>();


        try
        {
            SharedPreferences.Editor editor = getSharedPreferences("onlyForBanner", MODE_PRIVATE).edit();


            JSONObject baseJSONresponse=new JSONObject(userJson);
            JSONObject userdataJson=baseJSONresponse.getJSONObject("userdata");
            bannerUrl=baseJSONresponse.getString("banner_url");
            editor.putString("name", bannerUrl);
            editor.apply();

            String userId=userdataJson.getString("userid");
            String userName=userdataJson.getString("username");
            String email=userdataJson.getString("email");
            String status=userdataJson.getString("status");
            userDataObj =new UserData(userId,userName,"",email,status);


            previousOrderList=new ArrayList<>();

            mainDataObj=new MainData(userDataObj);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            return null;
        }
        catch (Exception e)
        {

            e.printStackTrace();
            return null;
        }

        return mainDataObj;

    }
    public static String extractFeatureFromJson(String userJson)
    {
        try
        {

            JSONObject baseJSONresponse=new JSONObject(userJson);
            JSONObject currentObject=baseJSONresponse.getJSONObject("userdata");
            String userName=currentObject.getString("username");

            return userName;
        }
        catch (JSONException e)
        {

            return null;
        }

    }




}
