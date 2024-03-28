//Blood Data Class that captures the blood type and RhFactor
class BloodData{
    private BloodType bloodType;
    private String RhFactor;

    //Overloaded constructor for Blood Data
    public BloodData(BloodType bloodType, String RhFactor) {
        this.bloodType = bloodType;
        this.RhFactor = RhFactor;
    }

    //Default constructor for when user doesn't have the data yet, returns O+
    public BloodData(){
        this.bloodType = BloodType.O;
        this.RhFactor = "+";
    }

    //These are just get and set methods for both Blood Type and RhFactor
    public BloodType getBloodType() {return bloodType;}

    public void setBloodType(BloodType bloodType) {this.bloodType = bloodType;}

    public String getRhFactor() {return RhFactor;}

    public void setRhFactor(String rhFactor) {RhFactor = rhFactor;}

    //This is used in TestBloodData to combine the blood type and RhFactor into 1 string
    public String displayBloodData(){return String.join("", String.valueOf(bloodType), RhFactor);}
}