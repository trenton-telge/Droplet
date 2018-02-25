package edu.lonestar.droplet.util;


import android.util.Log;

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

    public Disaster(String json){
        json = json.substring(json.indexOf(":")+2);
        this.id = Integer.parseInt(json.substring(0, json.indexOf(",")));
        json=json.substring(json.indexOf(":")+3);
        this.artist = (json.substring(0, json.indexOf("\"")));
        //height
        json=json.substring(json.indexOf(":")+2);
        this.year = Integer.parseInt(json.substring(0, json.indexOf(",")));
        //Width
        json=json.substring(json.indexOf(":")+2); // start to count after the colon
        this.height = Integer.parseInt(json.substring(0, json.indexOf(",")));// start at the prvious value, ini to 0 and count on
        //artist

}
