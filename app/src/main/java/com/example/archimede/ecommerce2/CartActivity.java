package com.example.archimede.ecommerce2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.archimede.ecommerce2.data.CartProductAdapter;
import com.example.archimede.ecommerce2.data.Category;
import com.example.archimede.ecommerce2.data.CategoryAdapter;
import com.example.archimede.ecommerce2.data.OnAdapterItemClickListener;
import com.example.archimede.ecommerce2.data.OnCartAdapterUpdate;
import com.example.archimede.ecommerce2.data.Product;
import com.example.archimede.ecommerce2.data.ShoppingCartProduct;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by archimede on 12/07/17.
 */

public class CartActivity extends AppCompatActivity implements OnAdapterItemClickListener, OnCartAdapterUpdate {

    private RecyclerView rView;
    private NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.ITALY);
    public TextView totalPrice;
    private CartProductAdapter cartProductAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        totalPrice =(TextView) findViewById(R.id.cartTotalPrice);
        totalPrice.setText(nf.format(ShoppingCart.getInstance().getTotalCart()));

        rView = (RecyclerView)findViewById(R.id.cart_recycler_view);

        cartProductAdapter = new CartProductAdapter(this);
        rView.setAdapter(cartProductAdapter);

        GridLayoutManager layout = new GridLayoutManager(this, 1);
        rView.setLayoutManager(layout);
    }

    @Override
    public void OnItemClick(int position) {

    }

    @Override
    public void OnItemBuyClick(int position) {

    }

    @Override
    public void OnItemBookmarkClick(int position) {

    }

    @Override
    public void OnRemoveCartItem(int position) {
        totalPrice.setText(nf.format(ShoppingCart.getInstance().getTotalCart()));
    }

    @Override
    public void ChangeQuantity(final ShoppingCartProduct product) {
        final EditText editText= new EditText(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        editText.setLayoutParams(lp);
        editText.setGravity(Gravity.CENTER);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Changing product's quantity");
        builder.setMessage("Choose the quantity: ");
        builder.setView(editText);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int modQuantity = Integer.parseInt(editText.getText().toString());

                if (modQuantity > 0 && modQuantity <= product.getAvailable() ){
                    product.setQuantity(modQuantity);
                    cartProductAdapter.notifyDataSetChanged();
                    refreshTotalPrice();
                    dialog.dismiss();
                } else {
                    if ( modQuantity < 0 ){
                        Snackbar mySnackbar = Snackbar.make(rView ,
                                "Inserire una quantità maggiore di 0", 4500);
                        mySnackbar.show();
                    } else {
                        if ( modQuantity > product.getAvailable()){
                            Snackbar mySnackbar = Snackbar.make(rView ,
                                    "Non sono disponibili sufficenti prodotti, massimo "+product.getAvailable(), 4500);
                            mySnackbar.show();
                        } else {
                            Snackbar mySnackbar = Snackbar.make(rView ,
                                    "Errore ! Selezionare una quantità !", 4500);
                            mySnackbar.show();
                        }
                    }
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        builder.create();
        builder.show();
    }

    private void refreshTotalPrice(){
        totalPrice.setText(nf.format(ShoppingCart.getInstance().getTotalCart()));
    }
}
