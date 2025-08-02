package lib;


import java.util.ArrayList;


public class ShoppingCart {
    

    private final ArrayList<CartItem> items;
    private final PricingService pricingService;
    private final ProductCatalog productCatalog;



    public ShoppingCart(PricingService  pricingService,ProductCatalog productCatalog){
      this.items = new ArrayList<>();
      this.pricingService = pricingService;
      this.productCatalog = productCatalog;
      checkRep();
    }
    public void addItem(String productID,int quantity){
        Product product = productCatalog.findById(productID);
        if (product == null) {
            return ;
        }

        for (CartItem item : items) {
            if (item.getProduct().equals(product)) {
                item.increaseQuantity(quantity);
                checkRep();
                return;
            }
        }

        items.add(new CartItem(product, quantity));
        checkRep();
    }
    public void removeItem(String productId){
       for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProduct().getProductId().equals(productId)) {
                items.remove(i);
                checkRep();
                break;
            }
       }
    }

    public double getTotalPrice(){

        double tatol = 0;
        for (CartItem item : items) {
            tatol += pricingService.calculateItemPrice(item);
        }
        return tatol;
    }


    public int getItemCount(){
        return items.size();
    }

    public void clearCart(){
        items.clear();
        checkRep();
    }
    private void checkRep(){
        if (items == null) {
            throw new RuntimeException("item is null ");
        }
        ArrayList<Product> seen = new ArrayList<>();

        for (CartItem item : items) {
            if (item == null) {
                throw new RuntimeException("item is null ");
   
            }

            Product product = item.getProduct();
            if (seen.contains(product)) {
                throw new RuntimeException(" duplicate product in cart");
            
            }
            seen.add(product);
        }
    }
}
