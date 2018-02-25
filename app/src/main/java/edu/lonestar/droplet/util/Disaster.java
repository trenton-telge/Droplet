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
        json = json.substring(json.indexOf(":") + 2);
        this.id = Integer.parseInt(json.substring(0, json.indexOf(",")));

        json = json.substring(json.indexOf(":") + 3);
        this.active = (Boolean.parseBoolean(json.substring(0, json.indexOf("\""))));

        json = json.substring(json.indexOf(":") + 2);
        this.imageurl = valueOf(json.substring(0, json.indexOf(",")));

        json = json.substring(json.indexOf(":") + 2); // start to count after the colon
        this.title = valueOf(json.substring(0, json.indexOf(",")));// start at the prvious value, ini to 0 and count on
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public boolean getActive()
    {
        return active;
    }

    public void getActive(boolean active)
    {
        this.active = active;
    }

    public String getImageurl()
    {
        return imageurl;
    }

    public void getImageurl(String imageurl)
    {
        this.imageurl = imageurl;
    }

    public String getTitle()
    {
        return title;
    }

    public void getTitle(String title)
    {
        this.title = title;
    }

}
