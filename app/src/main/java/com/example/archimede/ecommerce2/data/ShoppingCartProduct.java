package com.example.archimede.ecommerce2.data;

/**
 * Created by archimede on 14/07/17.
 */

public class ShoppingCartProduct extends Product {

    int quantity = 1;

    public ShoppingCartProduct(Product product, int quantity) {

        super(product);
        this.quantity = quantity;
    }

    public ShoppingCartProduct(Product product){
        super(product);
        this.quantity = 1;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void addOneProduct(){
        this.quantity += 1;
    }

    public void modifyQuantity(int quantity){
        this.quantity += quantity;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + quantity;
        return result;
    }

    @Override
    public String toString() {
        return "ShoppingCartProduct{" +
                "quantity=" + quantity +
                '}';
    }
}
