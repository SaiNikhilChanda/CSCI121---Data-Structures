//importing all methods
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import static java.util.Arrays.asList;

public class DemoPizza extends JFrame implements ItemListener {
    //Creating 2 global variables to maintain number of toppings what the delivery option is
    public static short numToppings = 0;
    public static boolean deliveryChoice;

    //these panels are used to organize the layout and make it simpler for the user to see information
    Panel pizzaPanel = new Panel(new GridLayout(1, 2));
    Panel designPanel = new Panel(new GridLayout(4,1));
    Panel deliveryOptions = new Panel(new GridLayout(2, 1));
    Panel deliveryPanel = new Panel(new GridLayout(3,1));
    Panel deliveryAddressEntry = new Panel(new GridLayout(2, 1));

    //adding the Header Text
    JLabel welcomeText = new JLabel("Design Your Pizza Here", SwingConstants.CENTER);

    //Initializing fonts
    Font headerFont = new Font("Times New Roman", Font.BOLD, 20);
    Font descriptionFont = new Font("Times New Roman", Font.BOLD, 14);

    //This next section is for adding toppings to the pizza, has a label, a text field, and an addTopping button
    JLabel toppingDesc = new JLabel("Type your topping here", SwingConstants.CENTER);
    JTextField toppingField = new JTextField();

    JButton addTopping = new JButton("Add Topping");
    JButton removeTopping = new JButton("Remove Topping");

    //This section is for managing the delivery option, will ask if you want delivery and will show text fields to add address if the user wants delivery
    JLabel deliveryText = new JLabel("Would you like delivery?");
    ButtonGroup ifDelivery = new ButtonGroup();

    JRadioButton yesDelivery = new JRadioButton("Yes");
    JRadioButton noDelivery = new JRadioButton("No");

    JTextField enterAddress = new JTextField("Enter Street Address");
    JTextField enterZipCode = new JTextField("Enter Zip Code");

    //this button confirms pizza and will show a new screen with the description
    JButton confirmPizza = new JButton("Confirm Pizza");


    public DemoPizza(){
        //setting the font and adding the header to the frame
        welcomeText.setFont(headerFont);
        add(welcomeText, BorderLayout.NORTH);

        //adding the font for all the topping elements
        toppingDesc.setFont(descriptionFont);
        addTopping.setFont(descriptionFont);
        deliveryText.setFont(descriptionFont);
        removeTopping.setFont(descriptionFont);

        //adding topping elements to design panel which contains all elements needed to manage the toppings
        designPanel.add(toppingDesc);
        designPanel.add(toppingField);
        designPanel.add(addTopping);
        designPanel.add(removeTopping);

        //adding the radio buttons for delivery to a button group
        ifDelivery.add(yesDelivery);
        ifDelivery.add(noDelivery);

        //adding all delivery elements to delivery Panel
        deliveryPanel.add(deliveryText);

        deliveryOptions.add(yesDelivery);
        deliveryOptions.add(noDelivery);

        deliveryPanel.add(deliveryOptions);


        deliveryAddressEntry.add(enterAddress);
        deliveryAddressEntry.add(enterZipCode);

        deliveryPanel.add(deliveryAddressEntry);
        deliveryAddressEntry.setVisible(false); //hiding this until the user says yes to delivery

        //adding the confirm button to the bottom of frame
        add(confirmPizza, BorderLayout.SOUTH);

        //adding the 2 main panels to the pizza panel
        pizzaPanel.add(deliveryPanel);
        pizzaPanel.add(designPanel);

        //adding pizza panel to frame
        add(pizzaPanel);

        //this string will contain all toppings and will be used in constructor for Pizza and DeliveryPizza class
        String [] toppingList = new String[10];

        //Action listener and event for adding toppings, where it checks for quit and if yes, hides the topping field and topping manager buttons
        //If user types a topping, then will add the topping to the toppingList array and increase the numToppings unless there are already 10 toppings
        addTopping.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String topping = toppingField.getText();
                if(topping.equals("Quit")){
                    toppingField.setVisible(false);
                    addTopping.setVisible(false);
                    removeTopping.setVisible(false);
                } else if (DemoPizza.numToppings == 10) {
                    toppingField.setText("Sorry can only add 10 toppings");
                } else{
                    toppingList[DemoPizza.numToppings] = topping;
                    DemoPizza.numToppings ++;
                }
            }
        });

        //this remove button will find what index the topping typed was in and essentially remove it from the array
        //if there were no toppings already added, then it will show that no toppings were added
        //will also adjust the numToppings variable if it removes the topping
        removeTopping.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                short i = 0;
                String selectedTopping = toppingField.getText();
                if(numToppings == 0){
                    toppingField.setText("No toppings have been added yet, cannot remove");
                }
                while(numToppings > i){
                    String topping = toppingList[i];
                    if(topping.equals(selectedTopping)){
                        toppingList[asList(toppingList).indexOf(selectedTopping)] = "";
                        DemoPizza.numToppings --;
                        break;
                    }else{
                        i++;
                    }
                }

            }
        });

        //this action will show the delivery address and zip code fields so user can type them in
        yesDelivery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deliveryAddressEntry.setVisible(true);
                DemoPizza.deliveryChoice = true;
            }
        });

        //will hide the delivery address and zip code fields
        noDelivery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deliveryAddressEntry.setVisible(false);
                DemoPizza.deliveryChoice = false;
            }
        });

        //takes in all the data
            //if the user chooses delivery, will make a new DeliveryPizza object and display all info
            //if user chooses no delivery, will make a Pizza object and display all info without delivery
        //will create a new frame saying thank you and showing the description of price, toppings, and delivery address (if needed)
        confirmPizza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String description;
                if(DemoPizza.deliveryChoice){
                    String completeAddress = String.join(", ", enterAddress.getText(), enterZipCode.getText());
                    DeliveryPizza pizza = new DeliveryPizza(toppingList, DemoPizza.numToppings,completeAddress);
                    description = pizza.deliveryPizzaDescription();
                }else{
                    Pizza pizza = new Pizza(toppingList, numToppings);
                    description = pizza.toString();
                }

                JFrame endingMessage = new JFrame();
                endingMessage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JPanel textBox = new JPanel(new GridLayout(2,1,20,30));
                final short WIDTH = 500;
                final short HEIGHT = 400;
                JLabel finalMessage1 = new JLabel("Thank you for Shopping!" , SwingConstants.CENTER);
                JTextArea finalMessage2 = new JTextArea(description);
                finalMessage2.setLineWrap(true);
                finalMessage2.setWrapStyleWord(true);
                finalMessage1.setFont(headerFont);
                finalMessage2.setFont(headerFont);
                textBox.add(finalMessage1);
                textBox.add(finalMessage2);
                endingMessage.add(textBox);
                endingMessage.setSize(WIDTH,HEIGHT);
                endingMessage.setVisible(true);
            }
        });



    }
    public static void main(String[] args){
        final short WIDTH = 800;
        final short HEIGHT = 500;
        DemoPizza frame = new DemoPizza();
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
