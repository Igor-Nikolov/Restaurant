package com.example.igor.restaurantapp.Adapter;

/**
 * Created by Igor on 07-Aug-16.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.igor.restaurantapp.Database.MyOrderDAO;
import com.example.igor.restaurantapp.Model.RestorantMenu;
import com.example.igor.restaurantapp.MyOrder;
import com.example.igor.restaurantapp.R;
import com.example.igor.restaurantapp.App.AppController;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<RestorantMenu> restorantMenuItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    private boolean showBtnDelete;

    public CustomListAdapter(Activity activity, List<RestorantMenu> restorantMenuItems,boolean showBtnDelete) {
        this.activity = activity;
        this.restorantMenuItems = restorantMenuItems;
        this.showBtnDelete = showBtnDelete;
    }

    @Override
    public int getCount() {
        return restorantMenuItems.size();
    }

    @Override
    public Object getItem(int location) {
        return restorantMenuItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void clear() {
        restorantMenuItems.clear();
        notifyDataSetChanged();
    }

    public void delete(Long id) {
            int position = -1;
            for(int i = 0; i<restorantMenuItems.size(); i++){
                if(restorantMenuItems.get(i).getId() == id)
                    position = i;
            }
            if(position != -1){
                restorantMenuItems.remove(position);
                MyOrderDAO myDatabase = new MyOrderDAO(activity);
                myDatabase.open();
                myDatabase.delete(id);
                myDatabase.close();
                notifyDataSetChanged();
            }

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView rating = (TextView) convertView.findViewById(R.id.rating);
        TextView genre = (TextView) convertView.findViewById(R.id.genre);
        TextView price = (TextView) convertView.findViewById(R.id.price);
        final ImageButton delBtn = (ImageButton) convertView.findViewById(R.id.delOrderItem);

        // getting movie data for the row
        final RestorantMenu currentMenuItem = restorantMenuItems.get(position);

        // thumbnail image
        thumbNail.setImageUrl(currentMenuItem.getThumbnailUrl(), imageLoader);

        // title
        title.setText(currentMenuItem.getTitle());

        // rating
        rating.setText("Rating: " + String.valueOf(currentMenuItem.getRating()));

        // genre
        String genreStr = "";
        if(currentMenuItem.getGenre() !=null){
            for (String str : currentMenuItem.getGenre()) {
                genreStr += str + ", ";
            }
            genreStr = genreStr.length() > 0 ? genreStr.substring(0,
                    genreStr.length() - 2) : genreStr;
        }

        genre.setText(genreStr);

        // release price
        price.setText(String.valueOf(currentMenuItem.getPrice()) + "$");

        if(showBtnDelete == true){
            delBtn.setVisibility(View.VISIBLE);
            delBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    delete(currentMenuItem.getId());
                }
            });
        }
        return convertView;
    }

}
