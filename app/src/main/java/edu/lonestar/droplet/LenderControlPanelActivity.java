package edu.lonestar.droplet;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Vector;

import edu.lonestar.droplet.util.Disaster;
import edu.lonestar.droplet.util.DisasterAdapter;
import edu.lonestar.droplet.util.DownloadDeamon;
import edu.lonestar.droplet.util.HorizontalListView;

public class LenderControlPanelActivity extends AppCompatActivity {

    public static HorizontalListView disasterListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lender_control_panel);
        disasterListView = findViewById(R.id.disasterScroller);
        disasterListView.setAdapter(new DisasterAdapter(vectorToArrayList(DownloadDeamon.disasterVector), getApplicationContext()));
    }

    public static ArrayList<Disaster> vectorToArrayList(Vector<Disaster> vector){
        if (vector == null){return null;}
        return new ArrayList<Disaster>(vector);
    }


}
