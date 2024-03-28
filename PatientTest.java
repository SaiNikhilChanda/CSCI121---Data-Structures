//import all necessary methods
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PatientTest extends JFrame{
    //Creating frames for GUI
    Panel patientFrame = new Panel(new GridLayout(2,1 ));
    Panel patientInfo = new Panel(new GridLayout(5,2));


    //Setting up all the fonts
    Font headerFont = new Font("Times New Roman", Font.BOLD, 20);
    Font regFont = new Font("Times New Roman", Font.PLAIN, 14);

    //Creating welcome text and its font
    JLabel welcomeText = new JLabel("Manage Patient Blood Data", SwingConstants.CENTER);

    //Setting up Patient ID text field and description Label
    JLabel patientIDdesc = new JLabel("Please enter Patient ID:");
    JTextField patientIDText = new JTextField();

    //Setting up Patient Name text field
    JLabel patientAgeDesc = new JLabel("Please enter Patient Age:");
    JTextField patientAgeText = new JTextField();

    //Setting up Description Text for Blood Type and RhFactor
    JLabel bloodTypeSelect = new JLabel("Select Blood Type:");
    JLabel RhFactorSelect = new JLabel("Select RhFactor:");

    //Setting up Button to add Patient Data to table
    JButton addPatientData = new JButton("Add Patient Data");
    JButton removePatientData = new JButton("Remove Patient Data");

    public PatientTest(){
        //Title for the program
        super("Blood Data Patient Program");

        //Adding welcomeText to Frame
        welcomeText.setFont(headerFont);
        patientFrame.add(welcomeText);

        //Adding PatientID text field to Frame
        patientIDdesc.setFont(regFont);
        patientInfo.add(patientIDdesc);
        patientIDText.setFont(regFont);
        patientInfo.add(patientIDText);

        //Adding Patient Name text field to frame
        patientAgeDesc.setFont(regFont);
        patientInfo.add(patientAgeDesc);
        patientAgeText.setFont(regFont);
        patientInfo.add(patientAgeText);

        //Adding BloodType label to frame
        bloodTypeSelect.setFont(regFont);
        patientInfo.add(bloodTypeSelect);

        //Setting up and adding the Blood Type Combo Box
        BloodType[] bloodTypes = {BloodType.O, BloodType.B, BloodType.A, BloodType.AB};
        JComboBox<BloodType> bloodTypeJComboBox = new JComboBox<>(bloodTypes);
        bloodTypeJComboBox.setFont(regFont);
        patientInfo.add(bloodTypeJComboBox);

        //Adding RhFactor label to frame
        RhFactorSelect.setFont(regFont);
        patientInfo.add(RhFactorSelect);

        //Setting up and adding the RhFactor Combo Box
        String[] RhFactors = {"+", "-"};
        JComboBox<String> RhFactorJComboBox = new JComboBox<>(RhFactors);
        RhFactorJComboBox.setFont(regFont);
        patientInfo.add(RhFactorJComboBox);

        //Adding the addPatientData and removePatientData button to patientFrame
        patientInfo.add(addPatientData);
        patientInfo.add(removePatientData);

        //Adding blood selection to patientFrame
        patientFrame.add(patientInfo);

        //Adding patientFrame to the GUI
        add(patientFrame, BorderLayout.NORTH);

        //creating the array list that will be used in the Patient Data class
        ArrayList<Patient> patientList = new ArrayList<>();
        PatientData patientData = new PatientData(patientList);

        //creating table in the frame and giving scroll option
        JTable patientDataTable = new JTable(patientData);
        add(new JScrollPane(patientDataTable), BorderLayout.CENTER);

        //when user clicks addPatient, the values selected or typed will be all inputted to make a patient object and will add the patient to the array to show
        //up on table
        addPatientData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int patientID = Integer.parseInt(patientIDText.getText());
                short patientName = Short.parseShort(patientAgeText.getText());
                BloodData bloodData = new BloodData((BloodType)bloodTypeJComboBox.getSelectedItem(), (String)RhFactorJComboBox.getSelectedItem());

                Patient patient = new Patient(patientID, patientName, bloodData);
                patientData.addPatient(patient);
            }
        });

        //When a user clicks on a patient and clicks the rmeove patient button, it will remove that patient and all its attributes from the array and table
        removePatientData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = patientDataTable.getSelectedRow();
                if (selectedRow != 1){
                    patientData.removePatient(selectedRow);
                }
            }
        });


    }

    public static void main(String[] args){
        final short WIDTH = 500;
        final short HEIGHT = 500;
        PatientTest frame = new PatientTest();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
    }
}
