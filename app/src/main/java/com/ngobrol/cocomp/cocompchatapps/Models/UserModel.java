package com.ngobrol.cocomp.cocompchatapps.Models;

/**
 * Created by noverio.joe on 3/23/17.
 */

public class UserModel {

    private String userID;
    private String userDeviceID;
    private String username;
    private String pin;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserDeviceID() {
        return userDeviceID;
    }

    public void setUserDeviceID(String userDeviceID) {
        this.userDeviceID = userDeviceID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

}
