package com.bean;

import lombok.Data;

/**
 * Created by XULE on 2018/12/1.
 */
@Data
public class User {

    private  String username;
    private   String password;
    private  String NN;
    private  String yy;

    public void setPassword(String password) {

        this.password = password;
    }
    public void setUsername(String username) {

        this.username = username;
    }

//    public String getUsername() {
//        return username;
//    }

//    public String getPassword() {
//        return password;
//    }

    public void setNN(String NN) {
        this.NN = NN;
    }

//    public String getNN() {
//        return NN;
//    }

    public void setYy(String yy) {
        this.yy = yy;
    }

//    public String getYy() {
//        return yy;
//    }
}
