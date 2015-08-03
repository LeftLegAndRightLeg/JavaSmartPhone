package adapter;

import android.content.Context;
import android.database.Cursor;
import database.*;
import model.*;
/**
 * Created by gongbailiang on 7/26/15.
 */
public abstract class proxyUserEditor {
    EditUserTable editusrtb;

    public void updateUser(String name, String newpw, Context context) {
        // TODO Auto-generated method stub
        editusrtb = new EditUserTable(context);
        editusrtb.updateRecord(name, newpw);
    }

    public void createUser(String name, String pw) {

    }
    public void checkUser(String userName, String passWord){

    }

    //EditUserDB
    public void saveUsertoDataBase(User user, Context context){
        editusrtb = new EditUserTable(context);
        editusrtb.insertRecord(user.getUserName(), user.getPassWord(), user.getIP());
    }

    public Cursor getUserfromDB(String name, Context context){
        editusrtb = new EditUserTable(context);
        editusrtb.open();
        return editusrtb.getOneRecord(name);
    }

    public void closeDB(){
        editusrtb.close();
    }


}
