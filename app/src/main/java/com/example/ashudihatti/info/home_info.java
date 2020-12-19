package com.example.ashudihatti.info;

import java.util.ArrayList;
import java.util.List;

public class home_info {

    String product_id;
    String product_name;
    String product_Article_No;
    String product_Category;
    String product_Sub_Category;
    String product_Sub_Category_type;
    String product_Label;
    List<String> product_Color;
    String product_Price;
    String product_Discount_Price;
    String product_field_type;
    String product_Description;
    String product_Specification;
    String product_Rating;
    String product_Stock;
    List<String> product_Image;

    public home_info(String product_id, String product_name, String product_Article_No, String product_Category, String product_Sub_Category, String product_Sub_Category_type, String product_Label, List<String> product_Color, String product_Price, String product_Discount_Price, String product_field_type, String product_Description, String product_Specification, String product_Rating, String product_Stock, List<String> product_Image) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_Article_No = product_Article_No;
        this.product_Category = product_Category;
        this.product_Sub_Category = product_Sub_Category;
        this.product_Sub_Category_type = product_Sub_Category_type;
        this.product_Label = product_Label;
        this.product_Color = product_Color;
        this.product_Price = product_Price;
        this.product_Discount_Price = product_Discount_Price;
        this.product_field_type = product_field_type;
        this.product_Description = product_Description;
        this.product_Specification = product_Specification;
        this.product_Rating = product_Rating;
        this.product_Stock = product_Stock;
        this.product_Image = product_Image;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_Article_No() {
        return product_Article_No;
    }

    public String getProduct_Category() {
        return product_Category;
    }

    public String getProduct_Sub_Category() {
        return product_Sub_Category;
    }

    public String getProduct_Sub_Category_type() {
        return product_Sub_Category_type;
    }

    public String getProduct_Label() {
        return product_Label;
    }

    public List<String> getProduct_Color() {
        return product_Color;
    }

    public String getProduct_Price() {
        return product_Price;
    }

    public String getProduct_Discount_Price() {
        return product_Discount_Price;
    }

    public String getProduct_field_type() {
        return product_field_type;
    }

    public String getProduct_Description() {
        return product_Description;
    }

    public String getProduct_Specification() {
        return product_Specification;
    }

    public String getProduct_Rating() {
        return product_Rating;
    }

    public String getProduct_Stock() {
        return product_Stock;
    }

    public List<String> getProduct_Image() {
        return product_Image;
    }
}
