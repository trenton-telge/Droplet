package edu.lonestar.droplet.util;

import android.util.Log;

/**
 * Created by ckoehler on 2/24/18.
 */

public class User {

    public int ID;
    public String firstName;
    public String lastName;
    public String userName;
    public String password;
    public int ssn;
    public boolean isVictim;
    public String addressLine1;
    public String addressLine2;
    public Date dateOfBirth;
    public String email;
    public int disasterID;

    public User(int idIn,
                String firstIn,
                String lastIn,
                String usernameIn,
                String passwordIn,
                int ssnIn,
                boolean isVictimIn,
                String addressLine1In,
                String addressLine2In,
                Date dateOfBirthIn,
                String emailIn,
                int disasterIDIn
                ){
        ID = idIn;
        firstName = firstIn;
        lastName = lastIn;
        userName = usernameIn;
        password = passwordIn;
        ssn = ssnIn;
        isVictim = isVictimIn;
        addressLine1 = addressLine1In;
        addressLine2 = addressLine2In;
        dateOfBirth = dateOfBirthIn;
        email = emailIn;
        disasterID = disasterIDIn;
    }

    public User(String json){
        json = json.replaceAll("]", "");
        json = json.replaceAll("]", "");
        json = json.substring(json.indexOf(":")+3, json.length());
        this.ID = Integer.parseInt(json.substring(0, json.indexOf(",")));
        Log.e("ID", json.substring(0, json.indexOf(",")));
        json = json.substring(json.indexOf("\"")+1, json.length());
        Log.e("JSON", json);
        this.firstName = json.substring(0, json.indexOf(",") -1);
        Log.e("FIRST NAME", this.firstName);
        json = json.substring(json.indexOf("\"")+3, json.length());
        Log.e("JSON", json);
        this.lastName = json.substring(0, json.indexOf(",") -1);
        Log.e("LAST NAME", this.lastName);
        json = json.substring(json.indexOf("\"")+3, json.length());
        Log.e("JSON", json);
        this.userName = json.substring(0, json.indexOf(",") -1);
        Log.e("JSON", json);
        Log.e("USERNAME", this.userName);
        json = json.substring(json.indexOf("\"")+3, json.length());
        this.password = json.substring(0, json.indexOf(",") -1);
        Log.e("JSON", json);
        Log.e("PASSWORD", this.password);
        json = json.substring(json.indexOf(",")+1, json.length());
        Log.e("JSON", json);
        this.ssn = Integer.parseInt(json.substring(0, json.indexOf(",")));
        Log.e("SSN", json.substring(0, json.indexOf(",")));
        json = json.substring(json.indexOf(",")+1, json.length());
        Log.e("JSON", json);
        this.isVictim = Integer.parseInt(json.substring(0, json.indexOf(","))) == 0;
        Log.e("ISVICTIM", json.substring(0, json.indexOf(",")));
        json = json.substring(json.indexOf("\"")+1, json.length());
        Log.e("JSON", json);
        this.addressLine1 = json.substring(0, json.indexOf(",") - 1);
        Log.e("ADDRESS LINE 1", this.addressLine1);
        json = json.substring(json.indexOf("\"") + 3, json.length());
        Log.e("JSON", json);
        this.addressLine2 = json.substring(0, json.indexOf("\""));
        Log.e("ADDRESS LINE 2", this.addressLine2);
        json = json.substring(json.indexOf("\"") +3, json.length());
        Log.e("JSON", json);
        this.dateOfBirth = new Date(0,0,0);//TODO
        Log.e("DOB",  "  ");
        json = json.substring(json.indexOf("\"") + 3, json.length());
        Log.e("JSON", json);
        this.email = json.substring(0, json.indexOf(",") -1);
        Log.e("EMAIL", this.email);
        json = json.substring(json.indexOf("\"") + 2, json.length());
        Log.e("JSON", json);
        this.disasterID = Integer.parseInt(json.substring(0, json.length()-2));
        Log.e("DISASTERID", json.substring(0, json.length()-2));
    }
}
