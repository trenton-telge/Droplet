package edu.lonestar.droplet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.lonestar.droplet.util.DisasterDownloadDeamon;
import edu.lonestar.droplet.util.LoginDaemon;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText usernameInput = findViewById(R.id.usernameField);
        final EditText passwordInput = findViewById(R.id.passwordField);
        final Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();

                try {
                    new LoginDaemon(username, password, LoginActivity.this).refresh();
                } catch (Exception e) {
                    Snackbar.make(view, "You could not be logged in", Snackbar.LENGTH_SHORT);
                    e.printStackTrace();
                }
            }
        });
    }
    public static void logInSuccess(Context c){
        if (MainActivity.loggedUser.isVictim) {
            Intent myIntent = new Intent(c, VictimControlPanelActivity.class);
            c.startActivity(myIntent);
        } else {
            new DisasterDownloadDeamon().refresh();
            Intent myIntent = new Intent(c, LenderControlPanelActivity.class);
            c.startActivity(myIntent);
        }
    }
    public static void logInFailure(Context c){
        //TODO toast login failure
    }
}

