package com.example.archimede.ecommerce2.data;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.archimede.ecommerce2.R;
import com.example.archimede.ecommerce2.ShoppingCart;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by archimede on 12/07/17.
 */

public class CartProductAdapter extends RecyclerView.Adapter<CartProductAdapter.ViewHolder> {

    private Context context;
    private OnAdapterItemClickListener listener;
    private OnCartAdapterUpdate cartListener;
    private NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.ITALY);


    public CartProductAdapter(Context context) {
        this.context = context;
        this.listener = (OnAdapterItemClickListener) context;
        this.cartListener = (OnCartAdapterUpdate) context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        public ImageView imageView;
        public TextView name;
        public TextView price;
        public Button removeButton;
        public TextView productQuantity;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }


    @Override
    public CartProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_product_layout, parent, false);
        final ViewHolder vh = new ViewHolder(v);

        vh.name = (TextView) v.findViewById(R.id.cartArtName);
        vh.price = (TextView) v.findViewById(R.id.cartArtPrice);
        vh.imageView = (ImageView) v.findViewById(R.id.cartArtImage);
        vh.removeButton = (Button) v.findViewById(R.id.cartArtRemoveButton);
        vh.productQuantity = (TextView) v.findViewById(R.id.cartProductQuantity);
//        vh.productQuantity = (NumberPicker) v.findViewById(R.id.cartProductQuantity);
//        vh.productQuantity.setMinValue(1);

        vh.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShoppingCart.getInstance().removeProduct(ShoppingCart.getInstance().getCart().get((Integer)vh.removeButton.getTag()));
                notifyDataSetChanged();
                listener.OnRemoveCartItem((Integer)vh.removeButton.getTag());
            }
        });

        vh.productQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartListener.ChangeQuantity((ShoppingCartProduct) vh.productQuantity.getTag());
            }
        });

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ShoppingCart sc = ShoppingCart.getInstance();
        holder.name.setText(sc.getCart().get(position).getTitle());
        holder.price.setText(nf.format(sc.getCart().get(position).getPrice()));
        Picasso.with(context).load(sc.getCart().get(position).getImagePath()).into(holder.imageView);
        holder.removeButton.setTag(position);
        holder.productQuantity.setTag(sc.getProduct(position));
        holder.productQuantity.setText(""+sc.getProductQuantity(position));
//        holder.productQuantity.setMaxValue(sc.getProduct(position).getAvailable());
//        holder.productQuantity.setValue(sc.getProductQuantity(position));

        holder.productQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartListener.ChangeQuantity(sc.getProduct(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return ShoppingCart.getInstance().getCart().size();
    }
}
