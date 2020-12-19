package com.example.ashudihatti.info;

public class popular_info {
    String product_name;
    String price;
    int image;
    String discount;
    String quantity;


    public popular_info(String name, int image, String price, String discount, String quantity) {
        this.product_name = name;
        this.price = price;
        this.image = image;
        this.discount = discount;
        this.quantity = quantity;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDiscount() { return discount; }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getQuantity() { return quantity; }

    public void setQuantity(String quantity) { this.quantity = quantity; }
}
