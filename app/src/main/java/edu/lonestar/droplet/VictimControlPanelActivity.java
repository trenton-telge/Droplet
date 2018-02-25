package edu.lonestar.droplet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import edu.lonestar.droplet.util.TransactionRequestDaemon;

public class VictimControlPanelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victim_control_panel);
        TextView name = findViewById(R.id.nameplate);
        name.setText(String.format("%s %s", MainActivity.loggedUser.firstName, MainActivity.loggedUser.lastName));
        new TransactionRequestDaemon("?filter[]=id,eq," + MainActivity.loggedUser.ID + "&filter[]=id,eq," + MainActivity.loggedUser.ID + "&satisfy=any", (ListView) findViewById(R.id.victimTransactionList), this.getApplicationContext());

    }
}
