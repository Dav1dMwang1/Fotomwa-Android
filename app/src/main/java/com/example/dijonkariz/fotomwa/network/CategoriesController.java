package com.example.dijonkariz.fotomwa.network;

import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.example.dijonkariz.fotomwa.fragments.HomeFragment;
import com.example.dijonkariz.fotomwa.model.Category;
import com.example.dijonkariz.fotomwa.model.User;
import com.example.dijonkariz.fotomwa.network.thiga.APIService;
import com.example.dijonkariz.fotomwa.network.thiga.APIUrl;
import com.example.dijonkariz.fotomwa.network.thiga.Result;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoriesController implements Callback<List<Category>> {
    private static final String TAG = CategoriesController.class.getSimpleName();
    private static Retrofit retrofit = null;
    private OkHttpClient client;
    private static final String BASE_URL = "https://fotomwa.herokuapp.com/";

    public CategoriesController() {}

    public void start() {
        Log.i(TAG, "Making Call to the API");
//        Build OkHTTP3Client
        okhttpClientBuild();
        Gson gson = new GsonBuilder().setLenient().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson)).build();

//        APIService service = retrofit.create(APIService.class);
//
//        User user = new User();
//        user.setEmail("david.mwangi.john@gmail.com");
//
//        Call<Result> call = service.createUser(user.getEmail());
//        call.enqueue(new Callback<Result>() {
//            @Override
//            public void onResponse(Call<Result> call, Response<Result> response) {
////                Toast.makeText(new HomeFragment().getContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
//                System.out.println(response.body().getMessage());
//            }
//
//            @Override
//            public void onFailure(Call<Result> call, Throwable t) {
//                t.printStackTrace();
////                Toast.makeText(new HomeFragment().getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });

//        Gson gson = new GsonBuilder().setLenient().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(client)
//                .addConverterFactory(GsonConverterFactory.create(gson)).build();

        GetInterface getInterface = retrofit.create(GetInterface.class);
        String userEmail = "david.mwangi.john@gmail.com";
        String password = "david1234";

        // Get token
        String authToken = Credentials.basic(userEmail, password);


        String base = userEmail + ":" + password;

        String authHeader = "Basic " + Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);
        Call<List<Category>> callCategories = getInterface.getAllCategories(authHeader);
        callCategories.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
        Log.i(TAG, "Returning Response");
        if (response.isSuccessful()) {
            Log.i(TAG, "Category response body: " + response.body().toString());
            System.out.println("Response Body: " + response.body().toString());
            List<Category> categories = response.body();
            categories.forEach(category -> {
                System.out.println("Category: " + category.toString());
                Log.i(TAG, "Category: " + category.toString());
            });
        } else {
            System.out.println(response.errorBody());
            System.out.println("Status Code: " + response.code());
            System.out.println("Error Message: " + response.message());
            Log.e(TAG, "Error: " + response.code());
        }
    }

    @Override
    public void onFailure(Call<List<Category>> call, Throwable t) {
        Log.e(TAG, "Error: Cannot fetch the Categories");
        t.printStackTrace();
    }

    private void okhttpClientBuild() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor((Interceptor) chain -> {
            Request original = chain.request();
            Request request = original.newBuilder().header("Accept", "application/json")
                    .header("Authorization", "auth-token")
                    .method(original.method(), original.body())
                    .build();
            okhttp3.Response response = chain.proceed(request);
            return response;
        });
        client = httpClient.build();
    }
}
