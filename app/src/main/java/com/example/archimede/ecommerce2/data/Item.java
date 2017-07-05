package com.example.archimede.ecommerce2.data;

/**
 * Created by archimede on 05/07/17.
 */

public class Item{
    private int ID;
    private String description;
    private String title;
    private float price;
    private float shipmentPrice;
    private int available;
    private int rating;
    private String imagePath;

    public Item(int ID, String description, String title, float price, float shipmentPrice, int available, int rating, String imagePath) {
        this.ID = ID;
        this.description = description;
        this.title = title;
        this.price = price;
        this.shipmentPrice = shipmentPrice;
        this.available = available;
        this.rating = rating;
        this.imagePath = imagePath;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public float getPrice() {
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

    @Override
    public String toString() {
        return "Item{" +
                "ID=" + ID +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", shipmentPrice=" + shipmentPrice +
                ", available=" + available +
                ", rating=" + rating +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (ID != item.ID) return false;
        if (Float.compare(item.price, price) != 0) return false;
        if (Float.compare(item.shipmentPrice, shipmentPrice) != 0) return false;
        if (available != item.available) return false;
        if (rating != item.rating) return false;
        if (description != null ? !description.equals(item.description) : item.description != null)
            return false;
        if (title != null ? !title.equals(item.title) : item.title != null) return false;
        return imagePath != null ? imagePath.equals(item.imagePath) : item.imagePath == null;

    }

    @Override
    public int hashCode() {
        int result = ID;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + (shipmentPrice != +0.0f ? Float.floatToIntBits(shipmentPrice) : 0);
        result = 31 * result + available;
        result = 31 * result + rating;
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        return result;
    }
}
