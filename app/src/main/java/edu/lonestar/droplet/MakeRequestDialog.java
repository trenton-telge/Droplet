package edu.lonestar.droplet;

import android.app.Activity;
import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mashape.unirest.http.Unirest;

import java.io.IOException;

/**
 * Created by Alienware on 2/25/2018.
 */

public class MakeRequestDialog extends Dialog {
    public Activity c;
    public Dialog d;
    public Button yes, no;

    public MakeRequestDialog(Activity a) {
        super(a);
        this.c = a;
        d = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_create_request);
        final EditText amountView = findViewById(R.id.amountView);
        Button requestButton = findViewById(R.id.requestButton);
        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int amount = Integer.parseInt(amountView.getText().toString());
                new PostTask().execute(amount);
                Toast toast = Toast.makeText(c, "Request Submitted", Toast.LENGTH_SHORT);
                toast.show();
                d.dismiss();
            }
        });
    }

    private class PostTask extends AsyncTask<Integer, String, String> {
        @Override
        protected String doInBackground(Integer... data) {
            // Create a new HttpClient and Post Header
            Unirest.post("http://droplet.eventhorizonwebdesign.com/api.php/transaction")
                    .header("accept", "application/json")
                    .field("amount", data[0])
                    .field("toid", MainActivity.loggedUser.ID)
                    .field("statusid", 1);
            return null;
        }
    }

}
