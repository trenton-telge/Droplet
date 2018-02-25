package edu.lonestar.droplet.util;

/**
 * Created by Alienware on 2/25/2018.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;

import edu.lonestar.droplet.MainActivity;

/**
 * Created by Alienware on 2/25/2018.
 */

public class AsyncTaskFindNameByID  extends AsyncTask<Integer, String, String> {
    private final static String TAG = "AsyncTaskLoadImage";
    private TextView imageView;
    public AsyncTaskFindNameByID(TextView imageView) {
        this.imageView = imageView;
    }
    @Override
    protected String doInBackground(Integer... params) {
        try {
            URL url = new URL("http://droplet.eventhorizonwebdesign.com/api.php/users/?filter=id,eq," + params[0]);
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
            return possible.firstName + " " + possible.lastName;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(String s) {
        imageView.setText(s);
    }
}