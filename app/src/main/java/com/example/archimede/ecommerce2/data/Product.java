package com.example.archimede.ecommerce2.data;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by archimede on 14/06/17.
 */

public class Product {
    private int Id;
    private String description = "Default Description";
    private String title = "Default Title";
    private double price = 3.00;
    private float shipmentPrice = 2;
    private int available = 30;
    private int rating = 3;
    private String imagePath = "";
    private boolean bookmark = false;

    @Override
    public String toString() {
        return "Product{" +
                "Id=" + Id +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", shipmentPrice=" + shipmentPrice +
                ", available=" + available +
                ", rating=" + rating +
                ", imagePath='" + imagePath + '\'' +
                ", bookmark=" + bookmark +
                '}';
    }

    public Product(String description, String title, double price, String imagePath) {
        this.description = description;
        this.title = title;
        this.price = 3.00;
        this.imagePath = imagePath;
    }

    public Product(Product product){
        this.Id = product.getId();
        this.description = product.getDescription();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.imagePath = product.getImagePath();
        this.price = product.getPrice();
        this.shipmentPrice = product.getShipmentPrice();
        this.available = product.getAvailable();
        this.rating = product.getRating();
        this.bookmark = product.getBookmark();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!Product.class.isAssignableFrom(o.getClass())) return false;

        Product product = (Product) o;

        if (Id != product.Id) return false;
        if (Double.compare(product.price, price) != 0) return false;
        if (Float.compare(product.shipmentPrice, shipmentPrice) != 0) return false;
        if (available != product.available) return false;
        if (rating != product.rating) return false;
        if (bookmark != product.bookmark) return false;
        if (description != null ? !description.equals(product.description) : product.description != null)
            return false;
        if (title != null ? !title.equals(product.title) : product.title != null) return false;
        return true;
        //return imagePath != null ? imagePath.equals(product.imagePath) : product.imagePath == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = Id;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (shipmentPrice != +0.0f ? Float.floatToIntBits(shipmentPrice) : 0);
        result = 31 * result + available;
        result = 31 * result + rating;
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        result = 31 * result + (bookmark ? 1 : 0);
        return result;
    }

    public boolean isBookmark() {

        return bookmark;
    }

    public void setBookmark(boolean bookmark) {
        this.bookmark = bookmark;
    }

    public boolean getBookmark() { return bookmark; }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getShipmentPrice() {
        return shipmentPrice;
    }

    public void setShipmentPrice(float shipmentPrice) {
        this.shipmentPrice = shipmentPrice;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isAvailable(){
        if (this.getAvailable() > 0){
            return true;
        }
        return false;
    }

    public boolean canBeAddedToCart(int test_quantity){
        if (this.getAvailable() > test_quantity){
            return true;
        }
        return false;
    }

    public Product() {
    }


}
