package edu.lonestar.droplet.util;

import android.content.Loader;
import android.os.AsyncTask;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;
import android.util.Log;

import edu.lonestar.droplet.LenderControlPanelActivity;
import edu.lonestar.droplet.LoaderActivity;


/**
 * Created by telgetr on 2/24/18.
 */

public class DownloadDeamon
{
    public static Vector<Disaster> disasterVector = new Vector<>();
    public void refresh()
    {
        new RefreshDataTask().execute(new Object());
    }
    static class RefreshDataTask extends AsyncTask<Object, Object, Void> {

        @Override
        protected Void doInBackground(Object... objects){
            try {
                // Get http response, include try catch for handle exception
                URL url = new URL("http://droplet.eventhorizonwebdesign.com/api.php/disasters");
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
                result = result.substring(result.indexOf("ds\":"), result.length());
                String f = "],";
                String[] strings = result.split(f);

                //  for loop to store in unfiltered vector
                disasterVector = new Vector<>();
                for (String s : strings) {
                    Log.e("DISASTER FOUND", s);
                    disasterVector.addElement(new Disaster(s));
                }
            }


            catch(Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            LenderControlPanelActivity.disasterListView.invalidate();
        }
    }

}
