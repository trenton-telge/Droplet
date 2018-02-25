package edu.lonestar.droplet;

import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import edu.lonestar.droplet.util.TransactionRequestDaemon;

public class VictimControlPanelActivity extends AppCompatActivity {
    Activity a = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victim_control_panel);
        TextView name = findViewById(R.id.nameplate);
        name.setText(String.format("%s %s", MainActivity.loggedUser.firstName, MainActivity.loggedUser.lastName));
        new TransactionRequestDaemon("?filter[]=toid,eq," + MainActivity.loggedUser.ID + "&filter[]=fromid,eq," + MainActivity.loggedUser.ID + "&satisfy=any", (ListView) findViewById(R.id.victimTransactionList), this.getApplicationContext());
        FloatingActionButton fab = findViewById(R.id.addRequestButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MakeRequestDialog(a).show();
            }
        });
    }
}
