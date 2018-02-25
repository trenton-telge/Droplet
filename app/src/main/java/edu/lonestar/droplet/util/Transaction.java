package edu.lonestar.droplet.util;

import android.util.Log;

/**
 * Created by ckoehler on 2/24/18.
 */

public class Transaction {

    public int transactionID;
    public int toID;
    public int fromID;
    public int amount;
    public int statusID;
    public Date dateAccessed;
    public int daysToRepay;
    public double interestRate;

    public Transaction(int transactionIDIn,
                       int toIDIn,
                       int fromIDIn,
                       int amountIn,
                       int statusIDIn,
                       Date dateAccessedIn,
                       int daysToRepayIn,
                       double interestRateIn){
        transactionID = transactionIDIn;
        toID = toIDIn;
        fromID = fromIDIn;
        amount = amountIn;
        statusID = statusIDIn;
        dateAccessed = dateAccessedIn;
        daysToRepay = daysToRepayIn;
        interestRate = interestRateIn;
    }

    public Transaction(String json){
        json = json.replaceAll("]", "");
        json = json.replaceAll("]", "");
        json = json.substring(json.indexOf(":")+3, json.length());
        this.transactionID = Integer.parseInt(json.substring(0, json.indexOf(",")));
        Log.e("ID", json.substring(0, json.indexOf(",")));
        json = json.substring(json.indexOf("\"")+1, json.length());
        Log.e("JSON", json);
    }
}
