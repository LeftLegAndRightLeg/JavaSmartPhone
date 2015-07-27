package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by gongbailiang on 7/26/15.
 */
public class Dish implements Serializable{
    private int ID;
    private String dishName = new String();
    private byte[] image;
    private String[] direction;
    private ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
    private String location = new String();
    private String cookTime = new String();
    private String userName = new String();

    protected Dish(){

    }
    protected Dish(String dishName){
        this.dishName = dishName;
    }

    protected void setID(int ID){
        this.ID = ID;
    }
    protected void setDishName(String dishName){
        this.dishName = dishName;
    }
    protected void setImage(byte[] image){
        this.image = image;
    }
    protected void setDirection(String[] direction){
        this.direction = direction;
    }
    protected void setLocation(String location){
        this.location = location;
    }
    protected void setCookTime(String cookTime){
        this.cookTime = cookTime;
    }
    protected void setUserName(String userName){
        this.userName = userName;
    }

    protected int getID(){
        return this.ID;
    }
    protected String getDishName(){
        return this.dishName;
    }
    protected byte[] getImage(){
        return this.image;
    }
    protected String[] getDirection(){
        return this.direction;
    }
    protected String getLocation(){
        return this.location;
    }
    protected String getCookTime(){
        return this.cookTime;
    }
    protected String getUserName(){
        return this.userName;
    }
    @Override
    public String toString(){
        return this.dishName;
    }
    class Ingredient {
        private String name = new String();
        private String quantity = new String();

        protected Ingredient(){

        }
        protected Ingredient(String name, String quantity){
            this.name = name;
            this.quantity = quantity;
        }
        protected void setName(String name){
            this.name = name;
        }
        protected void setQuantity(String quantity){
            this.quantity = quantity;
        }
        protected String getName(){
            return this.name;
        }
        protected String getQuantity(){
            return this.quantity;
        }
        @Override
        public String toString(){
            return this.name + "  " + this.quantity;
        }
    }
}
