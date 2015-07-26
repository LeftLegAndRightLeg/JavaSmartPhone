package model;

import java.io.Serializable;

/**
 * Created by gongbailiang on 7/26/15.
 */
public class User implements Serializable{
    private String userName;
    private String userID;
    private String passWord;
    private String IP;
    public User(){

    }
    public User(String userName, String passWord){
        this.userName = userName;
        this.passWord = passWord;
    }
    public User(String userName, String passWord, String IP){
        this.userName = userName;
        this.passWord = passWord;
        this.IP = IP;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public void setUserID(String userID){
        this.userID = userID;
    }
    public void setPassWord(String passWord){
        this.passWord = passWord;
    }
    public void setIP(String IP){
        this.IP = IP;
    }

    public String getUserName(){
        return this.userName;
    }
    public String getUserID(){
        return this.userID;
    }
    public String getPassWord(){
        return this.passWord;
    }
    public String getIP(){
        return this.IP;
    }
    @Override
    public String toString(){
        return this.userName + " " + this.passWord;
    }
}
