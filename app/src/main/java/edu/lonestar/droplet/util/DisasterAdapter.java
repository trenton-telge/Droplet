package edu.lonestar.droplet.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import edu.lonestar.droplet.R;

/**
 * Created by Alienware on 2/24/2018.
 */

public class DisasterAdapter extends ArrayAdapter<Disaster> implements View.OnClickListener{

    private ArrayList<Disaster> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView disasterName;
        ImageView disasterImage;
    }

    public DisasterAdapter(ArrayList<Disaster> data, Context context) {
        super(context, R.layout.disaster_grid_item, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Disaster dataModel=(Disaster) object;

        switch (v.getId())
        {
            case R.id.disasterImage:
                //TODO open view of requests by dataModel.id
                break;
        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Disaster dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.disaster_grid_item, parent, false);
            viewHolder.disasterName = (TextView) convertView.findViewById(R.id.disasterName);
            viewHolder.disasterImage = (ImageView) convertView.findViewById(R.id.disasterImage);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        lastPosition = position;

        viewHolder.disasterName.setText(dataModel.title);
        new AsyncTaskLoadImage(viewHolder.disasterImage).execute(dataModel.imageurl);
        viewHolder.disasterImage.setOnClickListener(this);
        viewHolder.disasterImage.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }


}


