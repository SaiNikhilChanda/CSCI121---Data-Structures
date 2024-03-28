//Import all necessary methods
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TestBloodData extends JFrame{

    //Initializing Combo Boxes for both the Blood Type and RhFactor for simplicity
    public static String bloodGroup = "N/A";

    //Setting up Panels for program
    Panel BloodDataPanel = new Panel(new FlowLayout());
    Panel DataSelection = new Panel(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();

    public TestBloodData(){
        //Creating title for Frame
        super("Blood Data Tester");

        //Setting and adding the header text with a header font style
        JLabel headerText = new JLabel("Use this Program to Enter Blood", SwingConstants.CENTER);
        Font headerFont = new Font("Times New Roman", Font.BOLD,20);
        headerText.setFont(headerFont);
        BloodDataPanel.add(headerText);

        //Creating a default blood data using default constructor
        BloodData defaultBloodData = new BloodData();

        //Setting font that will be used by all other text
        Font regFont = new Font("Times New Roman", Font.PLAIN, 15);

        //Setting up and adding the Blood Type Combo Box
        constraints.gridx = 0;
        constraints.gridy = 0;
        BloodType[] bloodTypes = {BloodType.O, BloodType.B, BloodType.A, BloodType.AB};
        JComboBox<BloodType> bloodTypeJComboBox = new JComboBox<>(bloodTypes);
        bloodTypeJComboBox.setFont(regFont);
        DataSelection.add(bloodTypeJComboBox);

        //Setting up and adding the RhFactor Combo Box
        constraints.gridx = 1;
        constraints.gridy = 0;
        String[] RhFactors = {"+", "-"};
        JComboBox<String> RhFactorJComboBox = new JComboBox<>(RhFactors);
        RhFactorJComboBox.setFont(regFont);
        DataSelection.add(RhFactorJComboBox);

        //Setting up and adding JButton for submitting Blood Data
        constraints.gridx = 2;
        constraints.gridy = 0;
        JButton addData = new JButton("Add Blood Data");
        addData.setFont(regFont);
        DataSelection.add(addData);

        //Setting up and adding Label for default selection
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        JLabel defaultSelection = new JLabel("<html>" + "Default Blood Data: " + "<B>" + defaultBloodData.displayBloodData() + "<B>");
        defaultSelection.setFont(regFont);
        DataSelection.add(defaultSelection, constraints);

        //Setting up and adding user selection field
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        JLabel userSelection = new JLabel("<html>" + "User Blood Data: " + "<B>" + TestBloodData.bloodGroup + "<B>");
        userSelection.setFont(regFont);
        DataSelection.add(userSelection, constraints);

        //Adding the Data selection panel to the main panel
        BloodDataPanel.add(DataSelection);

        add(BloodDataPanel);

        //When the user clicks the add data button, it will grab what was selected from the 2 combo boxes and create a new Blood Data Object
        //and display both the default selection and user selection as the new inputted blood data.
        addData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BloodData bloodData = new BloodData((BloodType)bloodTypeJComboBox.getSelectedItem(), (String)RhFactorJComboBox.getSelectedItem());
                TestBloodData.bloodGroup = bloodData.displayBloodData();
                userSelection.setText("<html>" + "User Blood Data: " + "<B>" + TestBloodData.bloodGroup + "<B>");
                defaultSelection.setText("<html>" + "Default Updated Blood Data: " + "<B>" + TestBloodData.bloodGroup + "<B>");
            }
        });
    }

    //Setting up the frame and size
    public static void main(String[] args){
        final short WIDTH = 375;
        final short HEIGHT = 175;
        TestBloodData frame = new TestBloodData();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
    }
}
