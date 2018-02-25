package edu.lonestar.droplet;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText usernameInput = findViewById(R.id.usernameField);
        final EditText passwordInput = findViewById(R.id.passwordField);
        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();

                try {
                    URL request = new URL("http://droplet.eventhorizonwebdesign.com/api.php?filter=name,eq," +
                            username + "&filter=password,eq," + password);

                    HttpURLConnection con = (HttpURLConnection) request.openConnection();
                    con.setRequestMethod("GET");

                    DataOutputStream output = (DataOutputStream) con.getOutputStream();


                } catch (Exception e) {
                    Snackbar snack = Snackbar.make(view, "Something went wrong with the HTTP requests.", Snackbar.LENGTH_SHORT);
                    e.printStackTrace();
                }
            }
        });

    }
}

