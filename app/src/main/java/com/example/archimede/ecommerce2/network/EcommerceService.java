package com.example.archimede.ecommerce2.network;

import com.example.archimede.ecommerce2.data.Category;
import com.example.archimede.ecommerce2.data.Product;
import com.example.archimede.ecommerce2.data.User;
import com.example.archimede.ecommerce2.data.UserRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by archimede on 05/07/17.
 */

public interface EcommerceService {

//    http://ecommerce.getsandbox.com/products
    @Headers("Content-Type: application/json")
    @GET("products")
    Call<List<Product>> listProduct();

    @Headers("Content-Type: application/json")
    @GET("categories")
    Call<List<Category>> listCategory();

    @POST("login")
    Call<User> login(@Body UserRequest user);



//    @GET("users/{user}/repos")
//    Call<List<Repo>> listRepos(@Path("user") String user);
}
