package com.example.dijonkariz.fotomwa.model;
// TODO: Add Content for values and attributes for an Order

public class Order {
    private String order_type;
    private String order_size;
//    private int view_icon;

    public Order(String order_type, String order_size) {
        this.order_type = order_type;
        this.order_size = order_size;
    }

//    public Order(String order_type, String order_size, int view_icon) {
//        this.order_type = order_type;
//        this.order_size = order_size;
//        this.view_icon = view_icon;
//    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getOrder_size() {
        return order_size;
    }

    public void setOrder_size(String order_size) {
        this.order_size = order_size;
    }

//    public int getView_icon() {
//        return view_icon;
//    }
//
//    public void setView_icon(int view_icon) {
//        this.view_icon = view_icon;
//    }
}
