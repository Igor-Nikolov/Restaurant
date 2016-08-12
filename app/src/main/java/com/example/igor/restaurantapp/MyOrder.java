package com.example.igor.restaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.igor.restaurantapp.Adapter.CustomListAdapter;
import com.example.igor.restaurantapp.Database.MyOrderDAO;
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

        FloatingActionButton deleteMyOrderList = (FloatingActionButton) findViewById(R.id.deleteMyOrderList);
        deleteMyOrderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
               //         .setAction("Action", null).show();
                MyOrderDAO myDatabase = new MyOrderDAO(MyOrder.this);
                myDatabase.open();
                myDatabase.deleteAll();
                adapter.clear();
                myDatabase.close();
            }
        });
        Intent i=getIntent();
        meniObj = i.getParcelableExtra("meniObj");

        MyOrderDAO myDatabase = new MyOrderDAO(this);
        myDatabase.open();
       // myDatabase.deleteAll(); za delete na listata i gore dvata reda
      //  myDatabase.close();
        List<RestorantMenu> result = myDatabase.getAllItems();
        if(meniObj != null){
            boolean flag = true;

            for(RestorantMenu meni : result){
                if(meni.getId() == meniObj.getId())
                    flag=false;
            }

            if(flag){
                result.add(meniObj);
                myDatabase.insert(meniObj);
            }
        }

        myDatabase.close();

        //restorantMenuList.add(meniObj);
        listViewMyOrder = (ListView) findViewById(R.id.myOrderList);
        adapter = new CustomListAdapter(this, result,true);
        listViewMyOrder.setAdapter(adapter);

        sumaprice=0;
        for(int j = 0; j < result.size() ; j++) {
            price = Integer.parseInt(result.get(j).getPrice());
            sumaprice += price;
        }

        textViewSumaPrice=(TextView) findViewById(R.id.sumaprice);
        textViewSumaPrice.setText("SumaPrice: "+Integer.toString(sumaprice) + "$");

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
        }else if (id == R.id.myOrderMenuItem) {
            Intent i = new Intent(getApplicationContext(),MyOrder.class);
            startActivity(i);
        }
        else if (id == R.id.nav_share) {

        }
        else if (id == R.id.nav_send) {

        }
        else
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
