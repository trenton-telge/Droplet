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

/**
 * Created by Alienware on 2/25/2018.
 */

public class TransactionRequestDaemon {

    Context c;
    String argsAppendix;
    public static Vector<Transaction> transactionVector = new Vector<>();

    public TransactionRequestDaemon(String argsAppendix, ListView l, Context c){
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
                URL url = new URL("http://droplet.eventhorizonwebdesign.com/api.php/transactions/" + argsAppendix);
                Log.e("REQ", "http://droplet.eventhorizonwebdesign.com/api.php/transactions/" + argsAppendix);
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
                transactionVector = new Vector<>();
                for (String s : strings) {
                    Log.e("TRANSACTION FOUND", s);
                    transactionVector.addElement(new Transaction(s));
                }
            } catch (Exception e) {
                resultb = false;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            this.listView.setAdapter(new TransactionAdapter(vectorToArrayList(transactionVector), c));
        }
    }
    private static ArrayList<Transaction> vectorToArrayList(Vector<Transaction> vector){
        if (vector == null){return null;}
        return new ArrayList<Transaction>(vector);
    }
}
