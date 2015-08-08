package com.mamarecipe.exception;


/**
 * Created by Jeremiah on 7/22/15.
 */
public class ExHandler implements IExHandler{
    public void fix(Exception e){
        e.printStackTrace();
    }
}
