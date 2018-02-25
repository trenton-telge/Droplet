package edu.lonestar.droplet.util;


import android.util.Log;

import static java.lang.String.valueOf;

/**
 * Created by telgetr on 2/24/18.
 */

public class Disaster {
    public int id;
    public boolean active;
    public String imageurl;
    public String title;

    Disaster(int id, boolean active, String imageurl, String title){
        id = id;
        active = active;
        imageurl = imageurl;
        title = title;
    }

    public Disaster(String json) {
        json = json.substring(json.indexOf("["), json.length());
        json = json.replace("[[", "[");
        Log.e("JSON", json);
        this.id = Integer.parseInt(json.substring(1, json.indexOf(",")));
        Log.e("ID", json.substring(1, json.indexOf(",")));
        json = json.substring(json.indexOf(",") + 1);
        Log.e("JSON", json);
        this.active = (Boolean.parseBoolean(json.substring(0, json.indexOf(","))));
        Log.e("ACTIVE", json.substring(1, json.indexOf(",")));
        json = json.substring(json.indexOf(",") + 2);
        Log.e("JSON", json);
        this.imageurl = valueOf(json.substring(0, json.indexOf("\"")));
        Log.e("URL", this.imageurl);
        json = json.substring(json.indexOf("\"") + 3); // start to count after the colon
        Log.e("JSON", json);
        this.title = valueOf(json.substring(0, json.indexOf("\"")));// start at the prvious value, ini to 0 and count on
        Log.e("TITLE", this.title);
    }

}
