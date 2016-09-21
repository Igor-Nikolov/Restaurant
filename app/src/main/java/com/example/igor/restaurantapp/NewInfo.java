package com.example.igor.restaurantapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.igor.restaurantapp.Adapter.CustomListAdapter;
import com.example.igor.restaurantapp.App.AppController;
import com.example.igor.restaurantapp.Database.MyMenuItemsDAO;
import com.example.igor.restaurantapp.Model.RestorantMenu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewInfo extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // Log tag
    private static final String TAG = NewInfo.class.getSimpleName();

    // Menu json url
    private static  String url = "https://api.myjson.com/bins/2r35g";
    private ProgressDialog pDialog;
    private List<RestorantMenu> restorantMenuList = new ArrayList<RestorantMenu>();
    private ListView listView;
    private CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar =  getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);



        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter(this, restorantMenuList,false);
        listView.setAdapter(adapter);

    /*    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RestorantMenu meni = restorantMenuList.get(position);

                Intent i = new Intent(getApplicationContext(),NewInfoItemDetail.class);
                i.putExtra("meniObj", meni);
                NewInfo.this.startActivityForResult(i,1);
            }
        });*/

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        //AKO IMA INTERNET DA SE ZEMAT OD WEB
        if (netInfo != null && netInfo.isConnected()) {

            final MyMenuItemsDAO myDatabase = new MyMenuItemsDAO(NewInfo.this);
            myDatabase.open();
            myDatabase.deleteAll();

            // Creating volley request obj
            JsonArrayRequest movieReq = new JsonArrayRequest(url,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Log.d(TAG, response.toString());
                            hidePDialog();

                            // Parsing json
                            for (int i = 0; i < response.length(); i++) {
                                try {

                                    JSONObject obj = response.getJSONObject(i);
                                    RestorantMenu restorantMenu = new RestorantMenu();
                                    restorantMenu.setId(obj.getLong("id"));
                                    restorantMenu.setTitle(obj.getString("title"));
                                    restorantMenu.setThumbnailUrl(obj.getString("image"));
                                    restorantMenu.setRating(((Number) obj.get("rating"))
                                            .doubleValue());
                                    restorantMenu.setPrice(obj.getString("price"));

                                    // Genre is json array
                                    JSONArray genreArry = obj.getJSONArray("genre");
                                    ArrayList<String> genre = new ArrayList<String>();
                                    for (int j = 0; j < genreArry.length(); j++) {
                                        genre.add((String) genreArry.get(j));
                                    }
                                    restorantMenu.setGenre(genre);

                                    // adding restorantMenu to menu array
                                    restorantMenuList.add(restorantMenu);
                                    myDatabase.insert(restorantMenu);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                            // notifying list adapter about data changes
                            // so that it renders the list view with updated data
                            adapter.notifyDataSetChanged();
                            myDatabase.close();

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                    hidePDialog();

                }
            });

            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(movieReq);
        }
        //NEMA INTERNET ZEMI OD BAZA
        else{
            hidePDialog();
            Toast.makeText(NewInfo.this,"No INTERNET !",Toast.LENGTH_LONG).show();
            MyMenuItemsDAO myDatabase = new MyMenuItemsDAO(NewInfo.this);
            myDatabase.open();
            List<RestorantMenu> lista  = myDatabase.getAllItems();
            for(RestorantMenu item : lista ){
                restorantMenuList.add(item);
            }
            myDatabase.close();
            adapter.notifyDataSetChanged();
        }

    }
    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {
            Toast.makeText(NewInfo.this,"Povikavte fiskalna smetka !",Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_send) {
            Toast.makeText(NewInfo.this,"Go povikavte kelnerot !",Toast.LENGTH_LONG).show();
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
     /*   DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
        NewInfo.this.finish();
      //  Intent myIntent = new Intent(getApplicationContext() , SelectMenu.class);
      //  startActivity(myIntent);
    }

}