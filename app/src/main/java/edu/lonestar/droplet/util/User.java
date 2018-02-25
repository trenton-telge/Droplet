package edu.lonestar.droplet.util;

/**
 * Created by ckoehler on 2/24/18.
 */

public class User {

    public int iD;
    public String firstName;
    public String lastName;
    public String userName;
    public String password;
    public int ssn;
    public boolean isVictim;
    public String addressLine1;
    public String addressLine2;
    public Date dateOfBirth;
    public String email;
    public int disasterID;

    public User(int idIn,
                String firstIn,
                String lastIn,
                String usernameIn,
                String passwordIn,
                int ssnIn,
                boolean isVictimIn,
                String addressLine1In,
                String addressLine2In,
                Date dateOfBirthIn,
                String emailIn,
                int disasterIDIn
                ){
        iD = idIn;
        firstName = firstIn;
        lastName = lastIn;
        userName = usernameIn;
        password = passwordIn;
        ssn = ssnIn;
        isVictim = isVictimIn;
        addressLine1 = addressLine1In;
        addressLine2 = addressLine2In;
        dateOfBirth = dateOfBirthIn;
        email = emailIn;
        disasterID = disasterIDIn;
    }
}
