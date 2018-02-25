package edu.lonestar.droplet.util;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;

import edu.lonestar.droplet.LoginActivity;
import edu.lonestar.droplet.MainActivity;

/**
 * Created by Alienware on 2/24/2018.
 */

public class LoginDaemon {
    private static String username;
    private static String password;
    private Context c;
    public LoginDaemon(String username, String password, Context c){
        LoginDaemon.username = username;
        LoginDaemon.password = password;
        this.c = c;
    }

    public void refresh()
    {
        new LoginDaemon.RefreshDataTask().execute(new Object());
    }


    class RefreshDataTask extends AsyncTask<Object, Object, Void> {
        boolean resultb = false;

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
                Log.e("RESULT OF API CALL", result);
                User possible = new User(result);
                if (Objects.equals(possible.password, password)){
                    MainActivity.loggedUser = possible;
                    resultb = true;
                } else {
                    //TODO show error
                    resultb = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(resultb){
                LoginActivity.logInSuccess(c);
            }
        }
    }
}
