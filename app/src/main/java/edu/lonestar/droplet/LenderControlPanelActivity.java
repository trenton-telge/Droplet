package edu.lonestar.droplet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Vector;

import edu.lonestar.droplet.util.Disaster;
import edu.lonestar.droplet.util.DisasterAdapter;
import edu.lonestar.droplet.util.DisasterDownloadDeamon;
import edu.lonestar.droplet.util.HorizontalListView;
import edu.lonestar.droplet.util.TransactionRequestDaemon;

public class LenderControlPanelActivity extends AppCompatActivity {

    public static HorizontalListView disasterListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lender_control_panel);
        disasterListView = findViewById(R.id.disasterScroller);
        disasterListView.setAdapter(new DisasterAdapter(vectorToArrayList(DisasterDownloadDeamon.disasterVector), getApplicationContext()));
        new TransactionRequestDaemon("?filter[]=id,eq," + MainActivity.loggedUser.ID + "&filter[]=id,eq," + MainActivity.loggedUser.ID + "&satisfy=any", (ListView) findViewById(R.id.lenderTransactionList), this.getApplicationContext());
    }

    public static ArrayList<Disaster> vectorToArrayList(Vector<Disaster> vector){
        if (vector == null){return null;}
        return new ArrayList<Disaster>(vector);
    }


}
