package com.example.archimede.ecommerce2;

import com.example.archimede.ecommerce2.data.Product;
import com.example.archimede.ecommerce2.data.ShoppingCartProduct;

import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by archimede on 12/07/17.
 */

public class ShoppingCart {

    private List<ShoppingCartProduct> cart;

    private static final ShoppingCart ourInstance = new ShoppingCart();

    public static ShoppingCart getInstance() {
        return ourInstance;
    }

    private ShoppingCart() {
        this.cart = new ArrayList<>();
    }

    public List<ShoppingCartProduct> getCart() {
        return cart;
    }


    public void addProduct(Product product){
        if (cart.contains(product)) {
            int indexProduct = cart.indexOf(product);
            getProduct(indexProduct).modifyQuantity(1);

        } else {
            cart.add(new ShoppingCartProduct(product));

        }
    }

    public void decreaseAnArticle(Product product){
        int indexProduct = cart.indexOf(product);
        getProduct(indexProduct).modifyQuantity(-1);
    }

    public void removeProduct(Product product){
        if (cart.contains(product)){
            cart.remove(product);
        }
    }

    public void emptyCart(){
        cart.clear();
    }

    public double getTotalCart(){
        double totalPrice = 0.00;
        for (ShoppingCartProduct product : cart){
            totalPrice += product.getPrice()*product.getQuantity();
        }

        return totalPrice;
    }

    public int getProductQuantity(int position){
        return this.cart.get(position).getQuantity();
    }


    public boolean isAvailable(Product product,int qty){

        int indexProduct = cart.indexOf(product);
        ShoppingCartProduct p = getProduct(indexProduct);
        if (p!=null){
            return p.getAvailable() >= p.getQuantity()+qty;
        }else{
            return product.getAvailable() >= qty;
        }
    }

    public ShoppingCartProduct getProduct(int position){
        if (!this.getCart().isEmpty() && position != -1 ){
            return this.cart.get(position);
        }
        return null;
    }

    public ShoppingCartProduct getProduct(Product product){
        if (cart.contains(product)){
            return getProduct(cart.indexOf(product));
        }
        return null;
    }

}
