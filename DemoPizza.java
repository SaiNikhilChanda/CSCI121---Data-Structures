import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class DemoPizza extends JFrame implements ItemListener {
    public static short numToppings = 0;
    public static boolean deliveryChoice;

    Panel pizzaPanel = new Panel(new GridLayout(1, 2));
    Panel designPanel = new Panel(new GridLayout(3,1));
    Panel deliveryOptions = new Panel(new GridLayout(2, 1));
    Panel deliveryPanel = new Panel(new GridLayout(3,1));
    Panel deliveryAddressEntry = new Panel(new GridLayout(2, 1));


    JLabel welcomeText = new JLabel("Design Your Pizza Here", SwingConstants.CENTER);

    Font headerFont = new Font("Times New Roman", Font.BOLD, 20);
    Font descriptionFont = new Font("Times New Roman", Font.PLAIN, 12);

    JLabel toppingDesc = new JLabel("Type your topping here", SwingConstants.CENTER);
    JTextField toppingField = new JTextField();

    JButton addTopping = new JButton("Add Topping");

    JLabel deliveryText = new JLabel("Would you like delivery?");
    ButtonGroup ifDelivery = new ButtonGroup();

    JRadioButton yesDelivery = new JRadioButton("Yes");
    JRadioButton noDelivery = new JRadioButton("No");

    JTextField enterAddress = new JTextField("Enter Street Address");
    JTextField enterZipCode = new JTextField("Enter Zip Code");

    JButton confirmPizza = new JButton("Confirm Pizza");


    public DemoPizza(){
        welcomeText.setFont(headerFont);
        add(welcomeText, BorderLayout.NORTH);

        toppingDesc.setFont(descriptionFont);
        addTopping.setFont(descriptionFont);
        deliveryText.setFont(descriptionFont);

        designPanel.add(toppingDesc);
        designPanel.add(toppingField);
        designPanel.add(addTopping);


        ifDelivery.add(yesDelivery);
        ifDelivery.add(noDelivery);

        deliveryPanel.add(deliveryText);

        deliveryOptions.add(yesDelivery);
        deliveryOptions.add(noDelivery);

        deliveryPanel.add(deliveryOptions);


        deliveryAddressEntry.add(enterAddress);
        deliveryAddressEntry.add(enterZipCode);

        deliveryPanel.add(deliveryAddressEntry);
        deliveryAddressEntry.setVisible(false);

        add(confirmPizza, BorderLayout.SOUTH);

        pizzaPanel.add(deliveryPanel);
        pizzaPanel.add(designPanel);

        add(pizzaPanel);
        String [] toppingList = new String[10];
        addTopping.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String topping = toppingField.getText();
                if(topping.equals("Quit")){
                    toppingField.setVisible(false);
                    addTopping.setVisible(false);
                } else if (DemoPizza.numToppings == 10) {
                    toppingField.setText("Sorry can only add 10 toppings");
                } else{
                    toppingList[DemoPizza.numToppings] = topping;
                    DemoPizza.numToppings ++;
                }
            }
        });

        yesDelivery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deliveryAddressEntry.setVisible(true);
                DemoPizza.deliveryChoice = true;
            }
        });

        noDelivery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deliveryAddressEntry.setVisible(false);
                DemoPizza.deliveryChoice = false;
            }
        });

        confirmPizza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(DemoPizza.deliveryChoice){
                    String completeAddress = String.join(", ", enterAddress.getText(), enterZipCode.getText());
                    DeliveryPizza pizza = new DeliveryPizza(toppingList, DemoPizza.numToppings,completeAddress);

                    JFrame endingMessage = new JFrame();
                    endingMessage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    JPanel textBox = new JPanel(new GridLayout(2,1,20,30));
                    final short WIDTH = 500;
                    final short HEIGHT = 400;
                    JLabel finalMessage1 = new JLabel("Thank you for Shopping!" , SwingConstants.CENTER);
                    JTextArea finalMessage2 = new JTextArea(pizza.deliveryPizzaDescription());
                    finalMessage2.setLineWrap(true);
                    finalMessage2.setWrapStyleWord(true);
                    finalMessage1.setFont(headerFont);
                    finalMessage2.setFont(headerFont);
                    textBox.add(finalMessage1);
                    textBox.add(finalMessage2);
                    endingMessage.add(textBox);
                    endingMessage.setSize(WIDTH,HEIGHT);
                    endingMessage.setVisible(true);
                }else{
                    Pizza pizza = new Pizza(toppingList, numToppings);
                    JFrame endingMessage = new JFrame();
                    endingMessage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    JPanel textBox = new JPanel(new GridLayout(2,1,20,30));
                    final short WIDTH = 500;
                    final short HEIGHT = 400;
                    JLabel finalMessage1 = new JLabel("Thank you for Shopping!" , SwingConstants.CENTER);
                    JTextArea finalMessage2 = new JTextArea(pizza.toString());
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
