package edu.lonestar.droplet.util;

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
        //TODO
    }
}
