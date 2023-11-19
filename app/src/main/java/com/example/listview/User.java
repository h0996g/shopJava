package com.example.listview;

public class User {
private String url;
    private int prix;

private int quantity;
    private  String name;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User(String name, int prix, String url,int quantity) {
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





    public int getPrix() {
        return prix;
    }


}
