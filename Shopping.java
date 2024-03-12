//importing all necessary methods
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/*
Creating the Item class which will manage the name and cost of the item.
Will contain a constructor and get methods
*/
class Item {
    String itemName;
    double price;

    public Item(String itemName, double price){
        this.itemName = itemName;
        this.price= price;
    }

    public String getItemName(){return itemName;}
    public double getPrice(){return price;}
}

/*
Creating the ItemOrder class which will add the quantity to the item.
Will contain a constructor, get methods, and the cost function and bulk cost in cases of bulk orders
 */
class ItemOrder{
    Item item;
    int quantity;

    public ItemOrder(Item item, int quantity){
        this.item = item;
        this.quantity = quantity;
    }

    public Item getItem(){return item;}
    public int getQuantity(){return quantity;}

    public double calculateCost(){return item.getPrice() * quantity;}

    public double bulkCost(){
        if(quantity >= 5){
            return calculateCost()*.5;
        }else if(quantity >= 2){
            return calculateCost()*.75;
        }
        else return calculateCost();
    }
}

/*
Creating the ShoppingCart class which will create an array for all the ItemOrder instances.
Will contain a constructor for the array, a get method, and functions for the table
This table will have function where it manages all the columns (name, price, quantity) and will line up all items in their cells
There are also add, remove, total cost, and discount amount functions that are used in the groceryShopping file
*/
class ShoppingCart extends DefaultTableModel{

    ArrayList<ItemOrder> groceryList = new ArrayList<ItemOrder>();

    public ShoppingCart(ArrayList<ItemOrder> groceryList){
        this.groceryList = groceryList != null ? groceryList : new ArrayList<>();
    }

    public int getRowCount(){return groceryList != null ? groceryList.size() : 0;}

    public int getColumnCount(){return 3;}

    public Object getValueAt(int row, int column){
        ItemOrder order = groceryList.get(row);
        Item item = order.getItem();
        return switch (column) {
            case 0 -> item.getItemName();
            case 1 -> item.getPrice();
            case 2 -> order.getQuantity();
            default -> null;
        };
    }

    public String getColumnName(int column){
        return switch (column) {
            case 0 -> "Item";
            case 1 -> "Price";
            case 2 -> "Quantity";
            default -> null;
        };
    }

    public void addOrder(ItemOrder order) {
        groceryList.add(order);
        fireTableDataChanged();
    }

    public void removeOrder(int rowIndex){
        groceryList.remove(rowIndex);
        fireTableDataChanged();
    }


    public double totalPrice(){
        double total = 0;
        for(ItemOrder order: groceryList){
            total += order.bulkCost();
        }
        return Math.round(total * 100) / 100.0;
    }

    public double discountAmount(){
        double discount = 0;
        for(ItemOrder order : groceryList){
            discount += order.calculateCost() - order.bulkCost();
        }
        return Math.round(discount * 100) / 100.0;
    }
}
