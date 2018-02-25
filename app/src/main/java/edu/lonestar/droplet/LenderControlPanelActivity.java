package edu.lonestar.droplet;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class LenderControlPanelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lender_control_panel);
        TextView name = findViewById(R.id.nameplate);
        name.setText(String.format("%s %s", MainActivity.loggedUser.firstName, MainActivity.loggedUser.lastName));
    }

}
