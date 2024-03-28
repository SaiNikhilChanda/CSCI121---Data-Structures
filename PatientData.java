//Importing necessary classes
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

//This class will manage the table that is used in the PatientTest program that displayes all data in a table format
public class PatientData extends DefaultTableModel {
    ArrayList<Patient> patientList;

    //basic constructor
    public PatientData(ArrayList<Patient> patientList){
        this.patientList = patientList != null ? patientList : new ArrayList<>();
    }

    //Get methods for dimensions of table
    public int getRowCount(){return patientList != null ? patientList.size() : 0;}

    public int getColumnCount(){return 4;}

    //This method gets all the data from patient object and puts each one to its respective column
    public Object getValueAt(int row, int column){
        Patient patient = patientList.get(row);
        BloodData bloodData1 = patient.getBloodData();
        BloodType bloodType = bloodData1.getBloodType();

        return switch (column) {
            case 0 -> patient.getPatientID();
            case 1 -> patient.getAge();
            case 2 -> bloodType;
            case 3 -> bloodData1.getRhFactor();
            default -> null;
        };
    }

    //This method labels the column names
    public String getColumnName(int column){
        return switch (column) {
            case 0 -> "Patient ID";
            case 1 -> "Patient Name";
            case 2 -> "Patient Blood Type";
            case 3 -> "Patient RhFactor";
            default -> null;
        };
    }

    //allows a patient to be added to table and update the table
    public void addPatient(Patient patient){
        patientList.add(patient);
        fireTableDataChanged();
    }

    //allows to remove patient row when you click on the row
    public void removePatient(int rowIndex){
        patientList.remove(rowIndex);
        fireTableDataChanged();
    }
}
