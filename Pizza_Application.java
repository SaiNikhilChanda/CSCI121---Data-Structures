//Importing all necessary methods
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Creating the class with a JFrame and necessary listeners
public class Pizza_Application extends JFrame implements ActionListener{
//Adding my 4 global variables that will be used as testing values to preform the right actions
    public static boolean sizeIfClicked = false;
    public static int numToppings = 0;
    public static double cost = 0;
    public static double baseCost = 0;
// Setting up layout to include sections of programs embedded in a BorderLayout, these layouts will all be put in separate areas of the Border Layout
    Panel pizzaFrame = new Panel(new GridLayout(3,1));
    Panel settings = new Panel(new GridLayout(2,1));
    Panel bottomLayout = new Panel(new GridLayout(2,1));
    Panel welcomeFrame = new Panel(new FlowLayout());
    Panel question = new Panel(new FlowLayout());

//Creating the basic labels and their fonts
    JLabel welcome_text = new JLabel("Welcome to BigY Pizza Builder", SwingConstants.CENTER);
    Font headerFont = new Font("Arial", Font.BOLD, 20);

    JLabel pizza_size = new JLabel("What size pizza would you like to purchase?", SwingConstants.CENTER);
    Font regularFont = new Font("Arial", Font.PLAIN, 14);

    JLabel costMessage = new JLabel("The total cost is " + Pizza_Application.cost, SwingConstants.CENTER);

    JLabel errorMessage = new JLabel("Please select a pizza size", SwingConstants.CENTER);
    Font errorFont = new Font("Arial",Font.BOLD, 18);


//This design button will be used to control the transition from pizza size selection to topping selection
    JButton designPizza = new JButton("Design My Pizza!");

//This method will handle all the buttons for the toppings add all labels to their frames and sections
    public Pizza_Application(){
        super("BigY Pizza Application");
        //Setting the fonts for all the text and setting visibility off for conditional labels: the error message and cost message
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcome_text.setFont(headerFont);
        pizza_size.setFont(regularFont);

        errorMessage.setFont(errorFont);
        errorMessage.setVisible(false);

        costMessage.setFont(regularFont);
        costMessage.setVisible(false);

        //Adding the conditional messages to the bottom of frame
        bottomLayout.add(costMessage);
        bottomLayout.add(errorMessage);
        add(bottomLayout, BorderLayout.SOUTH);

        //Adding the intro section to the top of the frame to include welcome message, pizza size selection, and the continue button
        welcomeFrame.add(welcome_text);
        pizzaFrame.add(welcomeFrame);
        pizzaFrame.add(pizza_size);
        question.add(designPizza);
        pizzaFrame.add(question);
        add(pizzaFrame, BorderLayout.NORTH);

        //Creating the settings section to modify the toppings or confirm pizza, which will be used later as they are not visible yet
        JButton cancelOptions = new JButton("Clear Options");
        JButton confirmPizza = new JButton("Confirm Pizza");
        cancelOptions.setVisible(false);
        confirmPizza.setVisible(false);

        //Adding elements into settings section
        settings.add(cancelOptions);
        settings.add(confirmPizza);

        add(settings, BorderLayout.EAST);

        //Initiation for adding all the buttons for pizza size question, utilizing for loops to automate button making process

        //An array to contain all the text for each radio button, a button group to put all the buttons together, and an array for
        //computer to understand cost for each size
        String[] sizes = {"Small $5", "Medium $10", "Large $15", "Super $20"};
        ButtonGroup sizeSelection = new ButtonGroup();
        Integer[] pizzaCost = {5, 10, 15, 20};

        //The for loop goes through each string in the sizes array and creates a radio button with that text
        for(String size : sizes) {
            JRadioButton button = new JRadioButton(size);
            sizeSelection.add(button);
            question.add(button);

            /*
            When a button is clicked, the global testing variable sizeIfClicked is true to allow thr user to continue making pizza
            An if statement checks whether the text corresponding to the button click to the index of the string in the sizes array
            the point of this is to know the index of the button that was clicked so then the program knows what the numerical cost
            associated with the button click is. Now the program knows the price, it displays it using costMessage
            */
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sizeIfClicked = true;
                    errorMessage.setVisible(false);
                    String text = button.getText();
                    for(int j = 0; j < sizes.length; j++) {
                        if (text.equals(sizes[j])) {
                            Pizza_Application.baseCost = pizzaCost[j];
                            Pizza_Application.cost = Pizza_Application.baseCost;
                        }
                    }
                    costMessage.setText("The total cost is $" + Pizza_Application.cost);
                    costMessage.setVisible(true);
                }
            });

            /*
            When the user clicks designPizza, it checks to see if the user has clicked a size yet using the ifSizeClicked boolean.
            If the user has clicked a button, it will hide all the unnecessary buttons to continue to the topping selection. This
            is done to reduce the clutter, later on will add ability to change size using a back button
             */
            designPizza.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(sizeIfClicked) {
                        errorMessage.setVisible(false);
                        button.setVisible(false);
                        designPizza.setVisible(false);
                        cancelOptions.setVisible(true);
                        confirmPizza.setVisible(true);

                    }else{
                        errorMessage.setVisible(true);
                    }
                }
            });
        }




//Setting up pizza customization section----------------------------------------------------------

        /*
        The point of this section is to handel creating all the buttons for each topping in the allToppings array.
        Using the same for loop concept, the program will make button groups for each topping all falling under one big
        button group called toppingOptions. Each button group will have a right, left, and whole button to allow the user
        to choose where to put their toppings in case of allergies or food restrictions.
         */
        String[] allToppings = {"Pepperoni", "Sausage", "Salami", "Chicken", "Onion", "Peppers", "Broccoli", "Spinach", "Mushrooms", "Jalapenos", "Pineapple"};
        ButtonGroup [] toppingOptions = new ButtonGroup[allToppings.length];

        Panel toppings = new Panel(new GridLayout(allToppings.length + 1,4));


        /*
        These sections are meant for the extra cheese. It is left out from the other options because there is no additional
        cost for extra cheese. They are set hidden but will show up when the designButton button is clicked. After that,
        they are added them to the toppings panel
         */
        ButtonGroup cheeseOption = new ButtonGroup();

        JLabel cheese = new JLabel("Extra Cheese");
        JRadioButton yesCheese = new JRadioButton("Yes");
        JRadioButton noCheese = new JRadioButton("No");
        JLabel space = new JLabel(" ");

        cheeseOption.add(yesCheese);
        cheeseOption.add(noCheese);

        cheese.setVisible(false);
        yesCheese.setVisible(false);
        noCheese.setVisible(false);
        space.setVisible(false);

        toppings.add(cheese);
        toppings.add(yesCheese);
        toppings.add(noCheese);
        toppings.add(space);

        /*
        This for loop creates all the buttons for each topping corresponding to its label while adding the buttons to a
        button group. Within this for loop are 3 different button action events which will monitor the number of toppings
        the user has selected and will pop up an error message and delete all toppings selected if the user exceeds
        3 toppings. The code for the left, right, and whole buttons are the same so only need to examine one to understand
         */

        for(int i = 0; i < allToppings.length; i++){
            JLabel label = new JLabel(allToppings[i]);
            label.setVisible(false);

            toppingOptions[i] = new ButtonGroup();

            JRadioButton left = new JRadioButton("Left", false);
            left.setVisible(false);
            JRadioButton right = new JRadioButton("Right", false);
            right.setVisible(false);
            JRadioButton whole = new JRadioButton("Whole", false);
            whole.setVisible(false);

            left.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(Pizza_Application.numToppings == 3) {
                        for (ButtonGroup toppingOption : toppingOptions) {
                            toppingOption.clearSelection();
                        }
                        errorMessage.setText("You can only select 3 toppings. Please select your toppings again.");
                        errorMessage.setVisible(true);
                        Pizza_Application.numToppings = 0;
                        costMessage.setText("The total cost is $" + Pizza_Application.baseCost);
                    } else if (Pizza_Application.numToppings == 2){
                        Pizza_Application.cost = Pizza_Application.cost + .25;
                        Pizza_Application.numToppings++;
                        costMessage.setText("The total cost is $" + Pizza_Application.cost);
                    }else{
                        errorMessage.setVisible(false);
                        Pizza_Application.numToppings++;
                        Pizza_Application.cost = Pizza_Application.cost + .50;
                        costMessage.setText("The total cost is $" + Pizza_Application.cost);
                    }

                }
            });

            right.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(Pizza_Application.numToppings == 3) {
                        for (ButtonGroup toppingOption : toppingOptions) {
                            toppingOption.clearSelection();
                        }
                        errorMessage.setText("You can only select 3 toppings. Please select your toppings again.");
                        errorMessage.setVisible(true);
                        costMessage.setText("The total cost is $" + Pizza_Application.baseCost);
                        Pizza_Application.numToppings = 0;
                    } else if (Pizza_Application.numToppings == 2){
                        Pizza_Application.cost = Pizza_Application.cost + .25;
                        Pizza_Application.numToppings++;
                        costMessage.setText("The total cost is $" + Pizza_Application.cost);
                    }else{
                        errorMessage.setVisible(false);
                        Pizza_Application.numToppings++;
                        Pizza_Application.cost = Pizza_Application.cost + .50;
                        costMessage.setText("The total cost is $" + Pizza_Application.cost);
                    }
                }
            });

            whole.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(Pizza_Application.numToppings == 3) {
                        for (ButtonGroup toppingOption : toppingOptions) {
                            toppingOption.clearSelection();
                        }
                        errorMessage.setText("You can only select 3 toppings. Please select your toppings again.");
                        errorMessage.setVisible(true);
                        Pizza_Application.numToppings = 0;
                        costMessage.setText("The total cost is $" + Pizza_Application.baseCost);
                    }else if(Pizza_Application.numToppings == 2){
                        Pizza_Application.cost = Pizza_Application.cost + .25;
                        costMessage.setText("The total cost is $" + Pizza_Application.cost);
                        Pizza_Application.numToppings++;
                    }else{
                        errorMessage.setVisible(false);
                        Pizza_Application.numToppings++;
                        Pizza_Application.cost = Pizza_Application.cost + .50;
                        costMessage.setText("The total cost is $" + Pizza_Application.cost);
                    }
                }
            });

            toppingOptions[i].add(left);
            toppingOptions[i].add(right);
            toppingOptions[i].add(whole);

            toppings.add(label);
            toppings.add(right);
            toppings.add(left);
            toppings.add(whole);

            //This designPizza action event allows all the topping settings to be shown
            designPizza.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    errorMessage.setVisible(false);
                    label.setVisible(true);
                    right.setVisible(true);
                    left.setVisible(true);
                    whole.setVisible(true);
                    cheese.setVisible(true);
                    yesCheese.setVisible(true);
                    noCheese.setVisible(true);
                    space.setVisible(true);
                }

            });
            /*
            These 2 action event functions are for each of the settings button
            - The cancelOptions resets the options for the toppings so the user can select new options if needed
            -The confirmPizza will bring a new screen telling the total cost of the pizza and say thank you
             */
            cancelOptions.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Pizza_Application.numToppings = 0;
                    for (ButtonGroup toppingOption : toppingOptions) {
                        toppingOption.clearSelection();
                        costMessage.setText("The total cost is $" + Pizza_Application.baseCost);
                    }
                }
            });
            confirmPizza.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame endingMessage = new JFrame();
                    endingMessage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    JPanel textBox = new JPanel(new GridLayout(2,1,20,30));
                    final short WIDTH = 500;
                    final short HEIGHT = 200;
                    JLabel finalMessage1 = new JLabel("Thank you for Shopping at BigY Pizza!" , SwingConstants.CENTER);
                    JLabel finalMessage2 = new JLabel("Your total cost is $" + Pizza_Application.cost, SwingConstants.CENTER);
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
        add(toppings, BorderLayout.WEST);


    }
//This is the initiation of the entire program
    public static void main(String[] args){
        final short WIDTH = 645;
        final short HEIGHT = 500;
        Pizza_Application frame = new Pizza_Application();
        frame.setSize(WIDTH,HEIGHT);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}