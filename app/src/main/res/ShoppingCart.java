/**
 * Created by archimede on 12/07/17.
 */

class ShoppingCart {
    private static final ShoppingCart ourInstance = new ShoppingCart();

    static ShoppingCart getInstance() {
        return ourInstance;
    }

    private ShoppingCart() {
    }
}
