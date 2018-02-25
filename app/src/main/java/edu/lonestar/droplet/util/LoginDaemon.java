package edu.lonestar.droplet.util;

import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Alienware on 2/24/2018.
 */

public class LoginDaemon {
    private static String username;
    private static String password;

    public LoginDaemon(String username, String password){
        LoginDaemon.username = username;
        LoginDaemon.password = password;
    }

    public void refresh()
    {
        new LoginDaemon.RefreshDataTask().execute(new Object());
    }
    static class RefreshDataTask extends AsyncTask<Object, Object, Void> {

        @Override
        protected Void doInBackground(Object... objects) {
            try {
                URL url = new URL("http://droplet.eventhorizonwebdesign.com/api.php/users/?filter=username,eq," + username);
                URLConnection urlConnection = url.openConnection();//url from string
                InputStream is = urlConnection.getInputStream();    //creating inputstream from url connection
                InputStreamReader isr = new InputStreamReader(is);  // create buffer from inputstream

                int numCharsRead;   //declare position
                char[] charArray = new char[1024];  //set buffer size
                StringBuilder sb = new StringBuilder();//Initialize string builder
                while ((numCharsRead = isr.read(charArray)) > 0) {  //while the file is not ended
                    sb.append(charArray, 0, numCharsRead);  // append the next 1024 bytes
                }
                String result = sb.toString();  //set the result string to fully build appendix
                //Vector String to store json lists and
                result = result.substring(result.indexOf("],"), result.length());
                Log.e("", result);
                //TODO set logged in user
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
