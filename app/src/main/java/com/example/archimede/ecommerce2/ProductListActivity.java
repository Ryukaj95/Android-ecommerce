package com.example.archimede.ecommerce2;

import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.archimede.ecommerce2.data.EcommerceOpenHelper;
import com.example.archimede.ecommerce2.data.OnAdapterItemClickListener;
import com.example.archimede.ecommerce2.data.Product;
import com.example.archimede.ecommerce2.data.ProductAdapter;

import java.util.List;

public class ProductListActivity extends BaseActivity implements OnAdapterItemClickListener {

    private RecyclerView rView;
    private List<Product> productList;
    private EcommerceOpenHelper mDB;
    private ProductAdapter productAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        mDB = new EcommerceOpenHelper(this);

        rView = (RecyclerView)findViewById(R.id.products_recycler_view);

        GridLayoutManager layout = new GridLayoutManager(this, 1);
        rView.setLayoutManager(layout);

        int categoryID = getIntent().getIntExtra("categoryID", -1);

        productList = mDB.getAllProducts(1);
        productAdapter = new ProductAdapter(productList, this);
        rView.setAdapter(productAdapter);

    }

    @Override
    public void OnItemClick(int position) {

    }

    @Override
    public void OnItemBuyClick(final int position) {
        final ShoppingCart sc = ShoppingCart.getInstance();
        final Product product = productList.get(position);
        Snackbar mySnackbar;

        if (sc.isAvailable(product, 1)){
            if (sc.getCart().contains(product)){
                mySnackbar = Snackbar.make(rView,
                        "Aggiunto al carrello. Presenti: " + String.valueOf(
                                sc.getProduct(productAdapter.getProduct(position)).getQuantity() + 1), 3000);
                mySnackbar.setAction("annulla", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("Item add action: ", "annullato");
                        sc.decreaseAnArticle(product);
                        productAdapter.notifyItemChanged(position);
                    }
                });

            } else {
                mySnackbar = Snackbar.make(rView,
                        "Aggiunto al carrello un nuovo prodotto", 2000);
                mySnackbar.setAction("annulla", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("Item buy action: ", "annullato");
                        sc.removeProduct(product);
                        productAdapter.notifyItemChanged(position);
                    }
                });
            }
            sc.addProduct(product);
            productAdapter.notifyItemChanged(position);

        } else {
            mySnackbar = Snackbar.make(rView,
                    "L'oggetto non è attualmente disponibile", 2000);
        }

        mySnackbar.show();
    }

    @Override
    public void OnItemBookmarkClick(int position){
        if(productList.get(position).isBookmark()){
            Log.d("Bookmark action: ", "rimosso");
            productList.get(position).setBookmark(false);

        }else
        {
            Log.d("Bookmark action: ", "aggiunto");
            productList.get(position).setBookmark(true);
        }
    }

    @Override
    public void OnRemoveCartItem(int position) {

    }
}