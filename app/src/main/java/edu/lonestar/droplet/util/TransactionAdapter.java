package edu.lonestar.droplet.util;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.lonestar.droplet.FulfillRequestDialog;
import edu.lonestar.droplet.MainActivity;
import edu.lonestar.droplet.R;

/**
 * Created by Alienware on 2/25/2018.
 */

public class TransactionAdapter extends ArrayAdapter<Transaction> implements View.OnClickListener {

    private ArrayList<Transaction> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        ConstraintLayout background;
        TextView amountView;
        TextView statusView;
        TextView timeView;
        TextView partyView;
    }

    public TransactionAdapter(ArrayList<Transaction> data, Context context) {
        super(context, R.layout.transaction_list_item, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Transaction dataModel=(Transaction) object;

        switch (v.getId())
        {
            case R.id.transactionBackground:
                new FulfillRequestDialog(new MainActivity(), dataModel).show();
                //TODO open view of requests by dataModel.id
                break;
        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Transaction dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        TransactionAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new TransactionAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.transaction_list_item, parent, false);
            viewHolder.background = (ConstraintLayout) convertView.findViewById(R.id.transactionBackground);
            viewHolder.amountView = (TextView) convertView.findViewById(R.id.amountView);
            viewHolder.statusView = (TextView) convertView.findViewById(R.id.statusView);
            viewHolder.timeView = convertView.findViewById(R.id.timeView);
            viewHolder.partyView = convertView.findViewById(R.id.otherPartyView);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (TransactionAdapter.ViewHolder) convertView.getTag();
            result=convertView;
        }

        lastPosition = position;

        viewHolder.amountView.setText(String.format("$%d", dataModel.amount));
        if (dataModel.toID == MainActivity.loggedUser.ID) {
            new AsyncTaskFindNameByID(viewHolder.partyView).execute(dataModel.fromID);
        } else if (dataModel.fromID == MainActivity.loggedUser.ID) {
            new AsyncTaskFindNameByID(viewHolder.partyView).execute(dataModel.toID);
        }
        switch (dataModel.statusID){
            case 1:
                viewHolder.statusView.setText("requested");
                break;
            case 2:
                viewHolder.statusView.setText("pending");
                viewHolder.statusView.setTextColor(Color.parseColor("#A5BED1"));
                viewHolder.amountView.setTextColor(Color.parseColor("#A5BED1"));
                break;
            case 3:
                viewHolder.statusView.setText("rejected");
                break;
            case 4:
                viewHolder.statusView.setText("accepted");
                viewHolder.statusView.setTextColor(Color.parseColor("#75C780"));
                viewHolder.amountView.setTextColor(Color.parseColor("#75C780"));
                break;
            case 5:
                viewHolder.statusView.setText("paid");
                viewHolder.statusView.setTextColor(Color.parseColor("#D67B70"));
                viewHolder.amountView.setTextColor(Color.parseColor("#D67B70"));
                break;
        }
        viewHolder.background.setOnClickListener(this);
        viewHolder.background.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }

}
