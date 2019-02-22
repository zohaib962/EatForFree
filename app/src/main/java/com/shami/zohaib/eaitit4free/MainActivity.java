package com.shami.zohaib.eaitit4free;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<UserOrder> arraylist;

    ImageView bannerImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences prefs = getSharedPreferences("onlyForBanner", MODE_PRIVATE);
        String restoredText = prefs.getString("name", "");

        bannerImageView=findViewById(R.id.picc);
        Glide.with(this).load(restoredText).into(bannerImageView);

        Intent intent=getIntent();
//        try {
//            Bundle bundle = getIntent().getExtras();
//             arraylist = bundle.getParcelableArrayList("ordrlist");
//
//        }
//        catch (Exception e)
//        {
//            Toast.makeText(this,"No Order Details Found!",Toast.LENGTH_LONG).show();
//        }



    }

    public void openContactUs(View view)
    {
        String url = "http://www.eat-it-4free.co.uk/cms/contact-us";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
    public void openWebsite(View view)
    {
        String url = "http://www.eat-it-4free.co.uk";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

    }

    public void goToLogin(View view)
    {
        Intent intent=new Intent(MainActivity.this,UserDetails.class);
//        if(arraylist!=null) {
//            Bundle bundle = new Bundle();
//            bundle.putParcelableArrayList("ordrlist", arraylist);
//            intent.putExtras(bundle);
//        }
//        intent.putExtra("username",username);

        startActivity(intent);

    }
    public void clearSP(View view)
    {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor=pref.edit();
        editor.clear();
        editor.commit();
        Intent intent=new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
