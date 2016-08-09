package com.example.igor.restaurantapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.igor.restaurantapp.App.AppController;
import com.example.igor.restaurantapp.Model.RestorantMenu;
import com.example.igor.restaurantapp.Service.MyService;
import com.squareup.picasso.Picasso;

/**
 * Created by Igor on 08-Aug-16.
 */
public class MeniItemDetail extends Activity {
    RestorantMenu meniObj;

    ImageView meniImg;
    TextView meniName;
    TextView mienDesctiption;
    TextView meniTime;
    private Picasso imageLoader;


    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restorantmeni_detail);
        doInject();

    }

    private void doInject() {
        Intent i = getIntent();
        meniObj = (RestorantMenu) i.getParcelableExtra("meniObj");


        //imageLoader = Picasso.with(MeniItemDetail.this);
        meniImg = (ImageView) findViewById(R.id.meniImg);
        meniName = (TextView) findViewById(R.id.meni_name);
        imageLoader = Picasso.with(MeniItemDetail.this);
        //bookDesctiption = (TextView) findViewById(R.id.book_description);
        meniTime = (TextView) findViewById(R.id.meni_time);
       // addFavorite = (Button) findViewById(R.id.add_favorite);
       // removeFavorite = (Button) findViewById(R.id.remove_favorite);
       // favBookTask = new FavBookTask(MeniItemDetail.this);
        //getFavBookStateTask = new GetFavBookStateTask(MeniItemDetail.this);

        //imageLoader.load(getResources().getString(R.string.book_image_real) + bookObj.getId()).
        //        placeholder(R.mipmap.ic_person_black_24dp)
        //        .error(R.mipmap.ic_power_settings_new_black_24dp)
        //        .into(bookImg);

        imageLoader.load(meniObj.getThumbnailUrl())
                .placeholder(R.drawable.ic_menu_gallery)
                .error(R.drawable.ic_menu_send)
                .fit()
                .into(meniImg);
        meniName.setText(meniObj.getTitle());
       // bookDesctiption.setText(bookObj.getDescription());
        meniTime.setText(meniObj.getTime().toString());
        setTitle(meniObj.getTitle());
      //  addFavorite.setVisibility(View.GONE);
     //   removeFavorite.setVisibility(View.GONE);
       // setFavBookBtn();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_restaurant_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);

        switch(item.getItemId())
        {
            case R.id.item1:
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", 0);

                Intent intent = new Intent(getApplicationContext(),DisplayAdimin.class);
                intent.putExtras(dataBundle);

                startActivity(intent);
                return true;

         /*   case R.id.item2:
                Intent intent1 = new Intent(getApplicationContext(),RestaurantHome.class);
                startActivity(intent1);
                return true;
         */
            default:
                return super.onOptionsItemSelected(item);
        }
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
