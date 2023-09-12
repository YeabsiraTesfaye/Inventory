package com.example.inventory;

public class Items {
 
    // variables for storing our data.
    private String name, Description, id;
    Integer count, buy, sell;
 
    public Items() {
        // empty constructor
        // required for Firebase.
    }
 
    // Constructor for all variables.
    public Items(String name, String Description, Integer count, Integer buy, Integer sell, String id) {
        this.name = name;
        this.Description = Description;
        this.count = count;
        this.buy = buy;
        this.sell = sell;
        this.id = id;
    }
 
    // getter methods for all variables.
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getDescription() {
        return Description;
    }
 
    // setter method for all variables.
    public void setDescription(String Description) {
        this.Description = Description;
    }
 
    public Integer getCount() {
        return count;
    }
 
    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getBuy(){return buy;}

    public void setBuy(Integer buy) {
        this.buy = buy;
    }

    public Integer getSell() {
        return sell;
    }
    public void setSell(Integer sell){
        this.sell = sell;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}