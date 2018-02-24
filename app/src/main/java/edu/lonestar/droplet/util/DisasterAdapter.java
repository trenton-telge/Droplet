package edu.lonestar.droplet.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import edu.lonestar.droplet.R;

/**
 * Created by Alienware on 2/24/2018.
 */

public class DisasterAdapter extends BaseAdapter {
    private Context context;
    private final String[] mobileValues;

    public DisasterAdapter(Context context, String[] mobileValues) {
        this.context = context;
        this.mobileValues = mobileValues;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.disaster_grid_item, null);

            // set value into textview
            TextView textView = (TextView) gridView.findViewById(R.id.disasterImage);
            //TODO set text to disaster name
            // textView.setText(mobileValues[position]);

            // set image based on selected text
            ImageView imageView = (ImageView) gridView.findViewById(R.id.disasterName);

            String mobile = mobileValues[position];

                //TODO set image to downloaded picture
            // imageView.setImageResource(R.drawable.windows_logo);

        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return mobileValues.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}