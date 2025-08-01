package Discout;

import lib.CartItem;

public class DefaultPriceStrategy implements DiscountStrategy {
    @Override
    public double calculatePrice(CartItem item) {
       return 0;
    }
    
}
