package com.example.listview;

public class User {
private String url;
    private double prix;

private int quantity;
    private  String name;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User(String name, double prix, String url,int quantity) {
        this.url=url;
        this.name = name;
this.quantity=quantity;
        this.prix = prix;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





    public double getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
}
