package edu.lonestar.droplet.util;

import android.util.Log;

import static java.lang.String.valueOf;

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
        json = json.substring(json.indexOf("["), json.length());
        json = json.replace("[[", "[");
        Log.e("JSON", json);
        this.transactionID = Integer.parseInt(json.substring(1, json.indexOf(",")));
        Log.e("TRANSACTIONID", json.substring(1, json.indexOf(",")));
        json = json.substring(json.indexOf(",") + 1);
        Log.e("JSON", json);

        this.toID = Integer.parseInt(json.substring(1, json.indexOf(",")));
        Log.e("TOID", json.substring(1, json.indexOf(",")));
        json = json.substring(json.indexOf(",") + 1);
        Log.e("JSON", json);

        this.fromID = Integer.parseInt(json.substring(1, json.indexOf(",")));
        Log.e("FROMID", json.substring(1, json.indexOf(",")));
        json = json.substring(json.indexOf(",") + 1);
        Log.e("JSON", json);

        this.amount = Integer.parseInt(json.substring(1, json.indexOf(",")));
        Log.e("AMOUNT", json.substring(1, json.indexOf(",")));
        json = json.substring(json.indexOf(",") + 1);
        Log.e("JSON", json);

        this.statusID = Integer.parseInt(json.substring(1, json.indexOf(",")));
        Log.e("STATUSID", json.substring(1, json.indexOf(",")));
        json = json.substring(json.indexOf(",") + 1);
        Log.e("JSON", json);


        this.daysToRepay = Integer.parseInt(json.substring(1, json.indexOf(",")));
        Log.e("DAYSTOREPAY", json.substring(1, json.indexOf(",")));
        json = json.substring(json.indexOf(",") + 1);
        Log.e("JSON", json);

        this.interestRate = Double.parseDouble(json.substring(1, json.indexOf(",")));
        Log.e("INTERESTRATE", json.substring(1, json.indexOf(",")));
        json = json.substring(json.indexOf(",") + 1);
        Log.e("JSON", json);
    }
}

