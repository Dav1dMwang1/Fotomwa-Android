package com.example.dijonkariz.fotomwa.network;

import com.example.dijonkariz.fotomwa.model.Brand;
import com.example.dijonkariz.fotomwa.model.Category;
import com.example.dijonkariz.fotomwa.model.ImageModel;
import com.example.dijonkariz.fotomwa.model.Message;
import com.example.dijonkariz.fotomwa.model.Order;
import com.example.dijonkariz.fotomwa.model.Product;
import com.example.dijonkariz.fotomwa.model.User;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface GetInterface {
    String URL = "https://fotomwa.herokuapp.com/";
//    Get HomePage
    @GET("/")
    Call<String> getHomePage();
//    Login User
    @GET("/auth/login")
    Call<User> loginUser();

//    Fetch All Categories
    @GET("/admin/categories")
    Call<List<Category>> getAllCategories(@Header("Authorization") String authHeader);
//    Call<List<Category>> getAllCategories();
//    Fetch All Brands
    @GET("/admin/brands")
    Call<List<Brand>> getAllBrands();
//    Fetch All Products
    @GET("/admin/products")
    Call<List<Product>> getAllProducts();
//    Fetch All User Orders
    @GET("")
    Call<List<Order>> getAllOrders();
//    Fetch One User Order
    @GET("")
    Call<List<Order>> getOrder();
//    Fetch All User Notifications
    @GET("")
    Call<List<Message>> getAllNotifications();
//    Fetch One User Notification
    @GET("")
    Call<List<Message>> getNotification();
//    Fetch Users Photos
    @GET("")
    Call<List<ImageModel>> getAllPhotos();
//    Fetch One User Photo
    @GET("")
    Call<List<ImageModel>> getPhoto();
}
