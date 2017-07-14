package com.example.archimede.ecommerce2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.AsyncListUtil;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.archimede.ecommerce2.data.CartProductAdapter;
import com.example.archimede.ecommerce2.data.Category;
import com.example.archimede.ecommerce2.data.CategoryAdapter;
import com.example.archimede.ecommerce2.data.EcommerceOpenHelper;
import com.example.archimede.ecommerce2.data.OnAdapterItemClickListener;
import com.example.archimede.ecommerce2.data.Product;
import com.example.archimede.ecommerce2.data.User;
import com.example.archimede.ecommerce2.data.UserRequest;
import com.example.archimede.ecommerce2.network.EcommerceService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements OnAdapterItemClickListener, SharedPreferences.OnSharedPreferenceChangeListener{

    private RecyclerView rView;
    private SharedPreferences preferences;
    private List<Category> categoryList = new ArrayList<>();

    private CategoryTask mTask = null;
    private EcommerceOpenHelper mDB;

    private ProgressBar progressBarCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDB = new EcommerceOpenHelper(this);
        setContentView(R.layout.activity_main);
        progressBarCategory = (ProgressBar)findViewById(R.id.progressBarCategory);

        preferences = getSharedPreferences("ecommerce",MODE_PRIVATE);

        boolean first_launch = preferences.getBoolean("firstUser",true);

        rView = (RecyclerView)findViewById(R.id.objs_recycler_view);

//        mTask = new CategoryTask();
//        mTask.execute((Void) null);

        if (first_launch){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("firstUser",true);
            editor.apply();

            Snackbar mySnackbar = Snackbar.make(rView,
                    "Benvenuto ! Questa è la prima volta che apri questa applicazione", 10000);
            mySnackbar.show();
        }


        GridLayoutManager layout = new GridLayoutManager(this, 2);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rView.setLayoutManager(mLayoutManager);

        categoryList = mDB.getAllCategories();


//        for (int i = 0; i < 21; i++) {
//            categoryList.add(new Category("Title", "Image", "Description"));
//        }

        CategoryAdapter categoryAdapter = new CategoryAdapter(categoryList, this);
        rView.setAdapter(categoryAdapter);
    }

    @Override
    public void OnItemClick(int position) {
        Log.d("Posizione item: ", String.valueOf(position));
        Intent productIntent = new Intent(this, ProductListActivity.class);
        startActivity(productIntent);

    }

    @Override
    public void OnItemBuyClick(int position) {

    }

    @Override
    public void OnItemBookmarkClick(int position) {

    }

    @Override
    public void OnRemoveCartItem(int position) {

    }

    @Override
    protected void onPause() {
        super.onPause();

        preferences = getSharedPreferences("ecommerce",MODE_PRIVATE);

        preferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        preferences = getSharedPreferences("ecommerce",MODE_PRIVATE);

        preferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Snackbar mySnackbar = Snackbar.make(rView,
                "Property changed: " + key, Snackbar.LENGTH_LONG);
        mySnackbar.show();
    }

    public void buttonOnClick(View view) {
        Intent i = new Intent(this, CartActivity.class);
        startActivity(i);

//        Intent productIntent = new Intent(this, ProductListActivity.class);
//        startActivity(productIntent);

//        preferences = getSharedPreferences("ecommerce",MODE_PRIVATE);
//
//        boolean b = preferences.getBoolean("firstUser",true);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putBoolean("firstUser",!b);
//        editor.apply();
    }

    public class CategoryTask extends AsyncTask < Void, Void, List<Category>> {

        @Override
        protected List<Category> doInBackground(Void... params) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://wispy-feather-82.getsandbox.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            EcommerceService service = retrofit.create(EcommerceService.class);
            Call<List<Category>> listCall = service.listCategory();

            try {
                Response<List<Category>> listResponse = listCall.execute();

                if (listResponse.isSuccessful()) {
                    for (Category category : listResponse.body()) {
                        mDB.AddOrUpdate(category);
                    }
                    return mDB.getAllCategories();
                }
            }catch (IOException e){
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(List<Category> categories) {

            if (categories == null){
                return;
            }

            progressBarCategory.setVisibility(View.VISIBLE);
            categoryList = categories;
            CategoryAdapter categoriesAdapter = new CategoryAdapter(categoryList, MainActivity.this);
            rView.setAdapter(categoriesAdapter);
        }
    }
}
