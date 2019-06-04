package deu.healthtracker;

public class BMI {

    Person person;
    private double calculatedBMI;
    private String weightCategory;

    public BMI(Person person) {
        this.person = person;
        this.calculatedBMI=calculateBMI(person);
        this.weightCategory=calculateCategory(calculatedBMI);
    }

    public BMI() {
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public double getCalculatedBMI() {
        return calculatedBMI;
    }

    public void setCalculatedBMI(double calculatedBMI) {
        this.calculatedBMI = calculatedBMI;
    }

    public String getWeightCategory() {
        return weightCategory;
    }

    public void setWeightCategory(String weightCategory) {
        this.weightCategory = weightCategory;
    }

    public double calculateBMI(Person person){
        return person.getWeight()/Math.pow(person.getHeight(),2);
    }
    public double calculateBMI(int Height,double Weight){
        return Weight/(Math.pow(Height,2)/10000);
    }

    public String calculateCategory(double BMI){

        if (BMI<=18.5){
            return "Underweight";
        }
        else if (BMI>18.5 && BMI<25){
            return "Normal Weight";
        }
        else if (BMI>=25 && BMI<30){
            return "Over Weight";
        }
        else if (BMI>=30){
            return "Obesity";
        }else return null;

    }





}
