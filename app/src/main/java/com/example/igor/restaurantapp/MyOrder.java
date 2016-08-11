package com.example.igor.restaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.igor.restaurantapp.Adapter.CustomListAdapter;
import com.example.igor.restaurantapp.Model.RestorantMenu;

import java.util.ArrayList;
import java.util.List;

public class MyOrder extends AppCompatActivity {

    RestorantMenu meniObj;

    private ListView listViewMyOrder;
    private CustomListAdapter adapter;
    private List<RestorantMenu> restorantMenuList = new ArrayList<RestorantMenu>();
    private TextView textViewSumaPrice;
    private int price;
    private int sumaprice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_order);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       //   setSupportActionBar(toolbar);

        ActionBar actionBar =  getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Intent i=getIntent();
        meniObj = i.getParcelableExtra("meniObj");
        restorantMenuList.add(meniObj);
        listViewMyOrder = (ListView) findViewById(R.id.myOrderList);
        adapter = new CustomListAdapter(this, restorantMenuList);
        listViewMyOrder.setAdapter(adapter);

        sumaprice=0;
        for(int j = 0; j < adapter.getCount() ; j++) {
            price =Integer.parseInt(String.valueOf(meniObj.getPrice().charAt(0)));
            sumaprice +=price;
        }
        textViewSumaPrice=(TextView) findViewById(R.id.sumaprice);
        textViewSumaPrice.setText("SumaPrice: "+Integer.toString(sumaprice));

    }

    /** Called when the activity is about to become visible. */
    @Override
    protected void onStart() {
        super.onStart();

    }

    /** Called when the activity has become visible. */
    @Override
    protected void onResume() {
        super.onResume();

    }

    /** Called when another activity is taking focus. */
    @Override
    protected void onPause() {
        super.onPause();

    }

    /** Called when the activity is no longer visible. */
    @Override
    protected void onStop() {
        super.onStop();

    }

    /** Called just before the activity is destroyed. */
    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_itemdetail_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.home) {
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        }else
        {
            this.finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }



    public void onBackPressed() {
        //  super.onBackPressed();
        MyOrder.this.finish();
    }


}
