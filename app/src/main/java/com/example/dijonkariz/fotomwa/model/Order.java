package com.example.dijonkariz.fotomwa.model;
// TODO: Add Content for values and attributes for an Order

import java.util.ArrayList;

public class Order {
    private String brand_name, product_type, order_description, order_status;
    private int amount;
    private int processing_time;
    private ArrayList<ImageModel> order_images;

    public Order() {}

    public Order(String brand_name, String product_type) {
        this.brand_name = brand_name;
        this.product_type = product_type;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getOrder_description() {
        return order_description;
    }

    public void setOrder_description(String order_description) {
        this.order_description = order_description;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getProcessing_time() {
        return processing_time;
    }

    public void setProcessing_time(int processing_time) {
        this.processing_time = processing_time;
    }

    public ArrayList<ImageModel> getOrder_images() {
        return order_images;
    }

    public void setOrder_images(ArrayList<ImageModel> order_images) {
        this.order_images = order_images;
    }
}
