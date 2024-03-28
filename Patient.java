//Creating Patient class that includes patient ID, age, and the blood data
public class Patient{
    int patientID;
    short age;
    BloodData bloodData;

    //Basic constructor for Patient object
    public Patient(int patientID, short age, BloodData bloodData){
        this.patientID = patientID;
        this.age = age;
        this.bloodData = bloodData;
    }


    //all get methods
    public int getPatientID() {
        return patientID;
    }

    public short getAge() {
        return age;
    }

    public BloodData getBloodData() {
        return bloodData;
    }
}
