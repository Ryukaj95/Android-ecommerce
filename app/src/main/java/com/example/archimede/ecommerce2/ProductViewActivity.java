package com.example.archimede.ecommerce2;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.archimede.ecommerce2.data.OnAdapterItemClickListener;
import com.example.archimede.ecommerce2.data.Product;
import com.example.archimede.ecommerce2.data.ProductAdapter;
import com.squareup.picasso.Picasso;

public class ProductViewActivity extends AppCompatActivity implements OnAdapterItemClickListener {

    private TextView name;
    private TextView shortDesc;
    private TextView longDesc;
    private RatingBar rating;
    private Button buyButton;
    private ImageButton bookmark;
    private ImageView image;
    private TextView price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);

        name = (TextView)findViewById(R.id.textViewItemName);
        shortDesc = (TextView)findViewById(R.id.textViewItemDescription);
        longDesc = (TextView)findViewById(R.id.textViewItemLongDesc);
        rating = (RatingBar)findViewById(R.id.ratingBarItem);
        buyButton = (Button)findViewById(R.id.buttonItemBuy);
        bookmark = (ImageButton)findViewById(R.id.buttonItemFavorite);
        image = (ImageView)findViewById(R.id.imageViewItemProduct);
        price = (TextView)findViewById(R.id.textViewItemPrice);

        Product product = new Product("Titolo","Descrizione",30.50, "http://writingexercises.co.uk/images2/randomimage/boat.jpg");

        Picasso.with(this).load(product.getImagePath()).into(image);
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

    }
}
