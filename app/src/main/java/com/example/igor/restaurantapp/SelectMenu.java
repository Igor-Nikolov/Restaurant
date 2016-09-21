package com.example.igor.restaurantapp;

/**
 * Created by Igor on 18-Sep-16.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.igor.restaurantapp.Database.DBHelper;
import com.example.igor.restaurantapp.Service.MyService;

import java.util.ArrayList;

public class SelectMenu extends Activity {
    String msg = "Android : ";
    public final static String EXTRA_MESSAGE = "MESSAGE";
    private ListView obj;
    DBHelper mydb;

    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_menu_layout);

        ImageButton imagebtn1 = (ImageButton)findViewById(R.id.imageButton1);
        ImageButton imagebtn2 = (ImageButton)findViewById(R.id.imageButton2);
        ImageButton imagebtn3 = (ImageButton)findViewById(R.id.imageButton3);
        ImageButton imagebtn4 = (ImageButton)findViewById(R.id.imageButton4);


        imagebtn1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent myIntent = new Intent(getApplicationContext() , MainActivity.class);
                myIntent.putExtra("DesertUrl","https://api.myjson.com/bins/10p0c");
                startActivity(myIntent);
            }
        });
        imagebtn2.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent myIntent = new Intent(getApplicationContext() , MainActivity.class);
                myIntent.putExtra("DesertUrl","https://api.myjson.com/bins/4ydbg");
                startActivity(myIntent);
            }
        });
        imagebtn3.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent myIntent = new Intent(getApplicationContext() , MainActivity.class);
                myIntent.putExtra("DesertUrl","https://api.myjson.com/bins/4jd70");
                startActivity(myIntent);
            }
        });
        imagebtn4.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent myIntent = new Intent(getApplicationContext() , MainActivity.class);
                myIntent.putExtra("DesertUrl","https://api.myjson.com/bins/18mi4");
                startActivity(myIntent);
            }
        });


    }
    /** Called when the activity is about to become visible. */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(msg, "The onStart() event");
    }

    /** Called when the activity has become visible. */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(msg, "The onResume() event");
    }

    /** Called when another activity is taking focus. */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(msg, "The onPause() event");
    }

    /** Called when the activity is no longer visible. */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(msg, "The onStop() event");
    }

    /** Called just before the activity is destroyed. */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(msg, "The onDestroy() event");
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_itemdetail_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.home) {
            Intent i = new Intent(getApplicationContext(),Home.class);
            startActivity(i);
        }else if (id == R.id.myOrderMenuItem) {
            Intent i = new Intent(getApplicationContext(),MyOrder.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    // Method to start the service
    public void startService(View view) {
        startService(new Intent(getBaseContext(), MyService.class));
    }

    // Method to stop the service
    public void stopService(View view) {
        stopService(new Intent(getBaseContext(), MyService.class));
    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keycode, event);
    }
}