//import all necessary methods
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class groceryShopping extends JFrame{
    //these global variables are for the total cart and discount
    public static double discount = 0;
    public static double total = 0;

    /*Setting up initial frame for Grocery List
        - This includes
            -frame and all the sections
            -All text fields and buttons
            -All font styles
     */
    Panel JFrameInitial = new Panel(new GridLayout(2, 1));
    Panel itemFrame = new Panel(new GridLayout(4, 1));
    Panel costFrame = new Panel(new GridLayout(1, 3));

    JLabel welcomeText = new JLabel("Make your Grocery List");
    Font headerFont = new Font("Times New Roman", Font.BOLD, 20);

    JTextField itemText = new JTextField("Item Name");
    JTextField costText = new JTextField("Item Cost");
    JTextField quantityText = new JTextField("Item Quantity");

    JButton addItem = new JButton("Add Item to Cart");
    JLabel totalCost = new JLabel("Total Cost: $" + groceryShopping.total, SwingConstants.CENTER);
    JButton removeItem = new JButton("Remove Item");

    JLabel discountText = new JLabel("You have saved $" + groceryShopping.discount, SwingConstants.CENTER);

    public groceryShopping() {

        //adding the text fields and header to the item adding section, this is where the user types in all the item info (name, cost, quantity)
        welcomeText.setFont(headerFont);
        itemFrame.add(welcomeText);
        itemFrame.add(itemText);
        itemFrame.add(costText);
        itemFrame.add(quantityText);

        add(itemFrame);

        //adding all the add + remove buttons and total cart tracker
        costFrame.add(addItem);
        costFrame.add(removeItem);
        costFrame.add(totalCost);

        add(costFrame);

        //adding the two frames to the layout
        JFrameInitial.add(itemFrame);
        JFrameInitial.add(costFrame);
        add(JFrameInitial, BorderLayout.NORTH);

        //adding the discount text
        add(discountText, BorderLayout.SOUTH);

        //initializing the array for the items, using the class ShoppingCart
        ArrayList<ItemOrder> itemOrders = new ArrayList<>();
        ShoppingCart cart = new ShoppingCart(itemOrders);

        //making a table to allow for user to see all information about items
        JTable groceryTable = new JTable(cart);
        add(new JScrollPane(groceryTable), BorderLayout.CENTER);

        //These two items are sample items so user can see the layout of the table
        Item item1 = new Item("Milk", 3.99);
        Item item2 = new Item("Bread", 5.49);

        ItemOrder order1 = new ItemOrder(item1, 3);
        ItemOrder order2 = new ItemOrder(item2, 2);

        cart.addOrder(order1);
        cart.addOrder(order2);

        //This block of code is used multiple times and is meant for updating the total cost and discount amount
        groceryShopping.total = cart.totalPrice();
        totalCost.setText("Total Cost: " + groceryShopping.total);
        groceryShopping.discount = cart.discountAmount();
        discountText.setText("You have saved $" + groceryShopping.discount);

        /*This manages the adding function for the item, using the Item and ItemOrder class.
        This will also add all the information to the table and update the cost and discount amount as needed
         */
        addItem.addActionListener(e -> {
           String itemName = itemText.getText();
           double itemCost = Double.parseDouble(costText.getText());
           int itemQuantity = Integer.parseInt(quantityText.getText());


            Item item = new Item(itemName, itemCost);
            ItemOrder order = new ItemOrder(item, itemQuantity);

            cart.addOrder(order);

            groceryShopping.total = cart.totalPrice();
            totalCost.setText("Total Cost: " + groceryShopping.total);

            groceryShopping.discount = cart.discountAmount();
            discountText.setText("You have saved $" + groceryShopping.discount);

            itemText.setText("Item Name");
            costText.setText("Item Cost");
            quantityText.setText("Item Quantity");
        });


        /*This manages the removing function for the item, using the Item and ItemOrder class.
        This will also remove all the information on the table for that item and update the cost and discount amount as needed
         */
        removeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = groceryTable.getSelectedRow();
                if (selectedRow != -1){
                    cart.removeOrder(selectedRow);
                }
                groceryShopping.total = cart.totalPrice();
                totalCost.setText("Total Cost: " + groceryShopping.total);
                groceryShopping.discount = cart.discountAmount();
                discountText.setText("You have saved $" + groceryShopping.discount);
            }
        });

    }

    //This initiates the code and creates a new frame
    public static void main(String[] args){
        final short WIDTH = 500;
        final short HEIGHT = 500;
        groceryShopping frame = new groceryShopping();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
    }

}

