package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by gongbailiang on 7/26/15.
 */
public class Category implements Serializable {
    private int ID;
    private String name = new String();
    private int value;  // the index (1 for MainDishes, 2 for Salad, 3 for Dessert)
    ArrayList<Dish> dishes = new ArrayList<Dish>();

    public Category(){

    }
    public Category(int value, String name){
        this.value = value;
        this.name = name;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setValue(int value){
        this.value = value;
    }

    public int getID(){
        return this.ID;
    }
    public String getName(){
        return this.name;
    }
    public int getValue(){
        return this.value;
    }
    @Override
    public String toString(){
        return this.name;
    }
}
