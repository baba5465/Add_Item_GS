package com.baba.additem.Model;

public class ItemDetails {
    String itemname,itemprice,imageurl,category,itemid;

    public ItemDetails(String itemname, String itemprice, String imageurl, String category, String itemid) {
        this.itemname = itemname;
        this.itemprice = itemprice;
        this.imageurl = imageurl;
        this.category = category;
        this.itemid = itemid;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemprice() {
        return itemprice;
    }

    public void setItemprice(String itemprice) {
        this.itemprice = itemprice;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }
}
