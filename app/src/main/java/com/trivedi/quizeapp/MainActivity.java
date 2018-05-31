package com.trivedi.quizeapp;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
  //  DatabaseHelper db;
    static CustomAdapter adapter;
    ArrayList<DataClass> quoteArray;
    //static MyUIHandler mHandler;
    static final int NUM_ITEMS = 10;
    DemoActivity.PlanetFragmentPagerAdapter planetFragmentPagerAdapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_pager);

       /* db = MyApplication.getDataBase();

        new Thread(){
            public void run(){
                quoteArray=db.getQuote();
                MainActivity.this.mHandler.sendEmptyMessage(1);
            }
        }.start();
        listView = (ListView)findViewById(R.id.listView);
        quoteArray=new ArrayList<DataClass>();

        CustomAdapter adapter = new CustomAdapter(MainActivity.this,R.layout.row_item,quoteArray);
        listView.setAdapter(adapter);


    }
    class MyUIHandler extends Handler {
        @Override
        public void handleMessage(Message msg)
        {
            if(msg.what==1){
                Log.e("Total quote is", "quotes:"+quoteArray.size());
                MainActivity.this.adapter = new CustomAdapter(MainActivity.this, R.layout.row_item, quoteArray);
                listView.setAdapter(MainActivity.this.adapter);
           //     pd.dismiss();
            }

        }
    }
}
*/
        this.listView = (ListView) findViewById(R.id.listView);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        ArrayList<DataClass> quotes = databaseAccess.getQuotes();
        databaseAccess.close();

        adapter = new CustomAdapter(MainActivity.this, R.layout.row_item, quotes);
        this.listView.setAdapter(adapter);

    }
}