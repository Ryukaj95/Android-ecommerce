package com.example.archimede.ecommerce2.data;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.archimede.ecommerce2.R;
import com.example.archimede.ecommerce2.ShoppingCart;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{

    private List<Product> mDataset;
    private Context context;
    private OnAdapterItemClickListener listener;
    private NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.ITALY);
    ShoppingCart sc = ShoppingCart.getInstance();

    public ProductAdapter(List<Product> mDataset, Context context) {
        this.mDataset = mDataset;
        this.context = context;
        this.listener = (OnAdapterItemClickListener) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.products_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);

        vh.name = (TextView) v.findViewById(R.id.textViewName);
        vh.desc= (TextView) v.findViewById(R.id.textViewDescription);
        vh.price= (TextView) v.findViewById(R.id.textViewPrice);
        vh.productImage= (ImageView) v.findViewById(R.id.imageViewProduct);
        vh.buyButton= (Button) v.findViewById(R.id.ButtonBuy);
        vh.favorite= (ImageButton) v.findViewById(R.id.ButtonFavorite);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.OnItemClick((int)v.getTag());
                }
            }
        });

        vh.favorite.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.OnItemBookmarkClick((int)v.getTag());
                }
            }
        });

        vh.buyButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.OnItemBuyClick((int)v.getTag());
                }
            }
        });

        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(mDataset.get(position).getTitle());
        holder.desc.setText(mDataset.get(position).getDescription());
        holder.price.setText(nf.format(mDataset.get(position).getPrice()));
        holder.productImage.setTag(position);
        holder.itemView.setTag(position);
        holder.favorite.setTag(position);
        holder.buyButton.setTag(position);
        if (sc.isAvailable(mDataset.get(position), 1)){
            holder.buyButton.setBackgroundColor(Color.GRAY);
        } else {
            holder.buyButton.setBackgroundColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        public ImageView productImage;
        public TextView name;
        public TextView desc;
        public TextView price;
        public Button buyButton;
        public ImageButton favorite;



        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public Product getProduct(int index){
        if (mDataset.size() >= index){
            return mDataset.get(index);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }



}
