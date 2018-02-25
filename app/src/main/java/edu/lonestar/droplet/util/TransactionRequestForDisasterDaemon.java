package edu.lonestar.droplet.util;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Vector;

import edu.lonestar.droplet.MainActivity;

/**
 * Created by Alienware on 2/25/2018.
 */

public class TransactionRequestForDisasterDaemon {

    Context c;
    String argsAppendix;
    public static Vector<User> userVector = new Vector<>();
    public static Vector<Transaction> requestVector = new Vector<>();

    public TransactionRequestForDisasterDaemon(String argsAppendix, ListView l, Context c){
        this.argsAppendix = argsAppendix;
        this.c =c;
        new RefreshDataTask(l).execute(argsAppendix);
    }



    class RefreshDataTask extends AsyncTask<Object, Object, Void> {
        boolean resultb = false;
        private ListView listView;
        RefreshDataTask(ListView listView){
            this.listView = listView;
        }

        @Override
        protected Void doInBackground(Object... objects) {
            try {
                URL url = new URL("http://droplet.eventhorizonwebdesign.com/api.php/users/" + argsAppendix);
                URLConnection urlConnection = url.openConnection();//url from string
                InputStream is = urlConnection.getInputStream();    //creating inputstream from url connection
                InputStreamReader isr = new InputStreamReader(is);  // create buffer from inputstream
                Log.e("1", "1");
                int numCharsRead;   //declare position
                char[] charArray = new char[1024];  //set buffer size
                StringBuilder sb = new StringBuilder();//Initialize string builder
                while ((numCharsRead = isr.read(charArray)) > 0) {  //while the file is not ended
                    sb.append(charArray, 0, numCharsRead);  // append the next 1024 bytes
                }
                Log.e("1", "1");
                String result = sb.toString();  //set the result string to fully build appendix
                //Vector String to store json lists and
                result = result.substring(result.indexOf("ds\":"), result.length());
                Log.e("1", "1");
                String f = "],";
                Log.e("RESULT OF API CALL", result);
                String[] strings = result.split(f);

                //  for loop to store in unfiltered vector
                userVector = new Vector<>();
                for (String s : strings) {
                    Log.e("USER IN DISASTER FOUND", s);
                    userVector.addElement(new User(s));
                }
                requestVector = new Vector<>();
                for (User u : userVector){
                    URL url2 = new URL("http://droplet.eventhorizonwebdesign.com/api.php/transactions/" + "?filter[]=toid,eq," + u.ID + "&filter[]=fromid,eq," + u.ID + "&satisfy=any");
                    URLConnection urlConnection2 = url2.openConnection();
                    InputStream is2 = urlConnection2.getInputStream();
                    InputStreamReader isr2 = new InputStreamReader(is2);
                    Log.e("1", "1");
                    charArray = new char[1024];
                    sb = new StringBuilder();
                    while ((numCharsRead = isr2.read(charArray)) > 0) {  //while the file is not ended
                        sb.append(charArray, 0, numCharsRead);  // append the next 1024 bytes
                    }
                    Log.e("1", "1");
                    result = sb.toString();
                    //Vector String to store json lists and
                    result = result.substring(result.indexOf("ds\":"), result.length());
                    Log.e("1", "1");
                    f = "],";
                    Log.e("RESULT OF API CALL", result);
                    strings = result.split(f);

                    //  for loop to store in unfiltered vector
                    for (String s : strings) {
                        Log.e("USER TRANSACTION FOUND", s);
                        requestVector.addElement(new Transaction(s));
                    }
                }
            } catch (Exception e) {
                resultb = false;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            this.listView.setAdapter(new TransactionAdapter(vectorToArrayList(requestVector), c));
        }
    }
    private static ArrayList<Transaction> vectorToArrayList(Vector<Transaction> vector){
        if (vector == null){return null;}
        return new ArrayList<Transaction>(vector);
    }
}
