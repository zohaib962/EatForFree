package com.shami.zohaib.eaitit4free;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;

import com.shami.zohaib.eaitit4free.Models.WeekModel;
import com.shami.zohaib.eaitit4free.Utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String >{
    private static final String USGS_REQUEST_URL = "http://api.eat-it-4free.co.uk/api.php?action=signin&apisecretkey=NNTnnQEgzpDbcbudjQyz&apiuserkey=WebAPIE4F";

    private String user;
    private String pass;

    private ProgressDialog progress;
    private MainData mainDataObj;
    private UserData userDataObj;
    private PreviousOrder previousOrder;
    private List<PreviousOrder> previousOrderList;
    private WeekModel orderData;
    private List<WeekModel> orderDataList;
    private CustomerData customerDataObj;
    private ItemData itemData;
    private List<ItemData> itemDataList;

   private int tries=1;

    protected String bannerUrl="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        user=pref.getString("username",null);
        pass=pref.getString("password",null);


//
//        new CountDownTimer(2000, 1000) {
//
//            public void onTick(long millisUntilFinished) {
//
//            }
//
//            public void onFinish() {
//                Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
//                intent.putExtra("user",user);
//                intent.putExtra("pass",pass);
//                startActivity(intent);
//                finish();
//            }
//        }.start();


        LoaderManager loaderManager = getLoaderManager();
//
//        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
//        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
//        // because this activity implements the LoaderCallbacks interface).
        loaderManager.initLoader(tries, null, this);
        tries++;
    }

    @Override
    public Loader<String> onCreateLoader(int i, Bundle bundle) {

        progress=ProgressDialog.show(SplashActivity.this, "",
                "Loading. Please wait...", true);
        return new LoginLoader(getApplicationContext(),USGS_REQUEST_URL,user,pass,1);

    }

    @Override
    public void onLoadFinished(Loader<String> loader, String s) {


        progress.dismiss();
        String result1=extractFeatureFromJson(s);

        if(result1==null ||result1.isEmpty())
        {

           // Toast.makeText(getApplicationContext(),"Login Unsuccessful",Toast.LENGTH_LONG).show();

            new CountDownTimer(2000, 1000) {

                public void onTick(long millisUntilFinished) {

                }

                public void onFinish() {
                    Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
                    intent.putExtra("user",user);
                    intent.putExtra("pass",pass);
                    startActivity(intent);
                    finish();
                }
            }.start();

            return;
        }

        MainData myObj=extractOrderDetails(s);
        String temp=user.trim();
        String temp2=result1.trim();
        if(s!=null && temp.equals(temp2) ) {
            Constants.mMainData=mainDataObj;
            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
            SharedPreferences.Editor editor = pref.edit();

            editor.putString("username",user.toString());
            editor.putString("password",pass.toString());
            editor.commit();

            //           intent.putExtra("maindata",mainDataObj);
//           if(myList!=null) {
//               Bundle bundle = new Bundle();
//               bundle.putParcelableArrayList("ordrlist", myList);
//               intent.putExtras(bundle);
//           }
//            intent.putExtra("username",temp);


            new CountDownTimer(2000, 1000) {

                public void onTick(long millisUntilFinished) {

                }

                public void onFinish() {
                    Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                    intent.putExtra("user",user);
                    intent.putExtra("pass",pass);
                    startActivity(intent);
                    finish();
                }
            }.start();



        }
        else
        {
          //  Toast.makeText(getApplicationContext(),"Login Unsuccessful",Toast.LENGTH_LONG).show();

            new CountDownTimer(2000, 1000) {

                public void onTick(long millisUntilFinished) {

                }

                public void onFinish() {
                    Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
                    intent.putExtra("user",user);
                    intent.putExtra("pass",pass);
                    startActivity(intent);
                    finish();
                }
            }.start();

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
            editor.putString("name", "Elena");
            editor.apply();

            JSONObject baseJSONresponse=new JSONObject(userJson);
            JSONObject userdataJson=baseJSONresponse.getJSONObject("userdata");
            bannerUrl=baseJSONresponse.getString("banner_url");
            editor.putString("name", bannerUrl);
            editor.apply();
            String userId=userdataJson.getString("userid");
            String userName=userdataJson.getString("username");
            String firstName=userdataJson.getString("firstname");
            String email=userdataJson.getString("email");
            String status=userdataJson.getString("status");
            userDataObj =new UserData(userId,userName,firstName,email,status);


            JSONArray responseArray=baseJSONresponse.getJSONArray("previousorder");
            previousOrderList=new ArrayList<>();
            for(int i=0;i<responseArray.length();i++)
            {
                JSONObject currentObject=responseArray.getJSONObject(i);
                String resturantName=currentObject.getString("restaurant_name");
                String resturantLogo=currentObject.getString("restaurant_logo");

                JSONArray oderDataArray=currentObject.getJSONArray("orderdata");
                orderDataList=new ArrayList<>();
                for(int j=0;j<oderDataArray.length();j++)
                {
                    JSONObject orderDataJsonObj=oderDataArray.getJSONObject(j);
                    String week=orderDataJsonObj.getString("week");
                    String orderNumber=orderDataJsonObj.getString("order_number");
                    String orderTotal=orderDataJsonObj.getString("order_total");
                    String orderDateAdd=orderDataJsonObj.getString("order_date_add");
                    String orderPaymentStatus=orderDataJsonObj.getString("order_payment_status");
                    String orderStatus=orderDataJsonObj.getString("order_status");
                    String deliveryType=orderDataJsonObj.getString("delivery_type");
                    String orderPaymentMethod=orderDataJsonObj.getString("order_payment_method");

                    JSONObject customerDataJsonObj=orderDataJsonObj.getJSONObject("customerdata");
                    String deliveryFirstName=customerDataJsonObj.getString("delivery_firstname");
                    String deliveryLastName=customerDataJsonObj.getString("delivery_lastname");
                    String deliveryEmail=customerDataJsonObj.getString("delivery_email");
                    String deliveryAddress=customerDataJsonObj.getString("delivery_address");

                    customerDataObj=new CustomerData(deliveryFirstName,deliveryLastName,deliveryEmail,deliveryAddress);



                    JSONArray itemDataJasonArray=orderDataJsonObj.getJSONArray("itemdata");
                    itemDataList=new ArrayList<>();
                    for(int k=0;k<itemDataJasonArray.length();k++)
                    {
                        JSONObject itemDataJsonObj=itemDataJasonArray.getJSONObject(k);
                        String menuName=itemDataJsonObj.getString("menuname");
                        String menuPrice=itemDataJsonObj.getString("menuprice");
                        String qty=itemDataJsonObj.getString("qty");
                        itemData=new ItemData(menuName,menuPrice,qty);
                        itemDataList.add(itemData);

                    }
                    orderData=new WeekModel(week,orderNumber,orderTotal,orderDateAdd,
                            orderPaymentStatus,orderStatus,deliveryType,orderPaymentMethod,itemDataList,customerDataObj);
                    orderDataList.add(orderData);

                }
                previousOrder=new PreviousOrder(resturantName,resturantLogo,orderDataList);
                previousOrderList.add(previousOrder);

            }

            mainDataObj=new MainData(userDataObj,previousOrderList);
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
