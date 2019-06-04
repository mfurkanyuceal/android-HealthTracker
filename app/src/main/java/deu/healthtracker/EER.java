package deu.healthtracker;

public class EER {

    private Person person;
    private double PA;
    private double EER;

    public EER(Person person) {
        this.person = person;
        this.PA = calculatePA(person);
        this.EER=calculateEER(person);
    }


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public double getPA() {
        return PA;
    }

    public void setPA(double PA) {
        this.PA = PA;
    }

    public double getEER() {
        return EER;
    }

    public void setEER(double EER) {
        this.EER = EER;
    }

    public double calculateEER(Person person) {

        switch (person.getSex()) {
            case "Male":
                return (662 - (9.53 * person.getAge()) + calculatePA(person) * ((15.91 * person.getWeight()) + (539.6 * person.getHeight())/100));
            case "Female":
                return (354 - (6.91 * person.getAge()) + calculatePA(person) * ((9.36 * person.getWeight()) + (726 * person.getHeight())/100));
            default:
                return 0;
        }

    }

    public double calculatePA(Person person) {

        if (person.getSex().equalsIgnoreCase("male")) {
            switch (person.getActivity()) {
                case "Sedentary":
                    return 1;

                case "Low Active":
                    return 1.11;

                case "Active":
                    return 1.25;

                case "Very Active":
                    return 1.48;

                default:
                    return 1;
            }
        } else if (person.getSex().equalsIgnoreCase("female")) {
            switch (person.getActivity()) {
                case "Sedentary":
                    return 1;

                case "Low Active":
                    return 1.12;

                case "Active":
                    return 1.27;

                case "Very Active":
                    return 1.45;

                default:
                    return 1;
            }
        } else return 0;


    }

}
