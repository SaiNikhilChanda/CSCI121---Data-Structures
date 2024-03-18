//Creating Pizza class to contain all toppings and price
public class Pizza {
    String[] toppingList;
    short numToppings;
    short price;

    //creating constructor and calculating price
     public Pizza(String[] toppingList, short numToppings){
         this.numToppings = numToppings;
         this.toppingList = new String[Math.min(numToppings, 10)];
         System.arraycopy(toppingList, 0, this.toppingList, 0, this.toppingList.length);
         this.price = (short) (14 + 2 * numToppings);
     }

     //creating description text
     public String toString(){
         String toppings = String.join(", ",  toppingList);
         return "The total cost of the pizza is $" + price + ". The toppings you got are: " + toppings;
     }
}