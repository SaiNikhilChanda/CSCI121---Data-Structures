//Creating deliveryPizza class
public class DeliveryPizza extends Pizza{
    short deliveryFee;
    String deliveryAddress;

    //create constructor
    public DeliveryPizza(String[] toppingList, short numToppings, String deliveryAddress){
        super(toppingList, numToppings);
        this.deliveryAddress = deliveryAddress;
        if(price > 18){
            this.deliveryFee = 3;
            price += 3;
        }else{
            this.deliveryFee = 5;
            price += 5;
        }
    }

    //this creates the description for the pizza
    public String deliveryPizzaDescription(){
        return String.join(". ", toString(), "The address is " + deliveryAddress);
    }
}
