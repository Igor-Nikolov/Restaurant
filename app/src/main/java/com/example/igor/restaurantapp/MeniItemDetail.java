package com.example.igor.restaurantapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.igor.restaurantapp.Model.RestorantMenu;
import com.example.igor.restaurantapp.Service.MyService;
import com.squareup.picasso.Picasso;

/**
 * Created by Igor on 08-Aug-16.
 */
public class MeniItemDetail extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    RestorantMenu meniObj;

    ImageView meniImg;
    TextView meniName;
    TextView mienDesctiption;
    TextView meniPrice;
    Button add_to_order;
    private Picasso imageLoader;


    /**
     * Called when the activity is first created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restorantmeni_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        ImageButton tipimg = (ImageButton)findViewById(R.id.imageButton8);


        tipimg.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Toast.makeText(MeniItemDetail.this, "Thank you for Tiping", Toast.LENGTH_LONG).show();
            }
        });
        doInject();


    }

    private void doInject() {
        Intent i = getIntent();
        meniObj = (RestorantMenu) i.getParcelableExtra("meniObj");


        //imageLoader = Picasso.with(MeniItemDetail.this);
        meniImg = (ImageView) findViewById(R.id.meniImg);
        meniName = (TextView) findViewById(R.id.meni_name);
        imageLoader = Picasso.with(MeniItemDetail.this);
        meniPrice = (TextView) findViewById(R.id.meni_time);
        add_to_order = (Button) findViewById(R.id.add_to_order);
        //bookDesctiption = (TextView) findViewById(R.id.book_description);
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
        meniPrice.setText("Price: " + meniObj.getPrice().toString()+"$");
        setTitle(meniObj.getTitle());
        // bookDesctiption.setText(bookObj.getDescription());
        //  addFavorite.setVisibility(View.GONE);
        //   removeFavorite.setVisibility(View.GONE);
        // setFavBookBtn();
        add_to_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), MyOrder.class);
                i.putExtra("meniObj", meniObj);
                startActivity(i);
            }
        });

    }

    /**
     * Called when the activity is about to become visible.
     */
    @Override
    protected void onStart() {
        super.onStart();

    }

    /**
     * Called when the activity has become visible.
     */
    @Override
    protected void onResume() {
        super.onResume();

    }

    /**
     * Called when another activity is taking focus.
     */
    @Override
    protected void onPause() {
        super.onPause();

    }

    /**
     * Called when the activity is no longer visible.
     */
    @Override
    protected void onStop() {
        super.onStop();

    }

    /**
     * Called just before the activity is destroyed.
     */
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

        if (id == R.id.home) {
            Intent i = new Intent(getApplicationContext(),SelectMenu.class);
            startActivity(i);
        }else if (id == R.id.myOrderMenuItem) {
            Intent i = new Intent(getApplicationContext(),MyOrder.class);
            startActivity(i);
        }
        else if (id == R.id.nav_share) {

        }
        else if (id == R.id.nav_send) {

        }
        else {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keycode, event);
    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        MeniItemDetail.this.finish();
    }

}
