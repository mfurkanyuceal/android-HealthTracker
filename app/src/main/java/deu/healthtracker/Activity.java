package deu.healthtracker;

public class Activity {

    private int id;
    private int pid;
    private String date;
    private double bloodPressure;
    private double bloodSugar;
    private double heartRate;
    private double weight;


    public Activity(String date, double bloodPressure, double bloodSugar, double heartRate, double weight) {
        this.date = date;
        this.bloodPressure = bloodPressure;
        this.bloodSugar = bloodSugar;
        this.heartRate = heartRate;
        this.weight = weight;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(double bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public double getBloodSugar() {
        return bloodSugar;
    }

    public void setBloodSugar(double bloodSugar) {
        this.bloodSugar = bloodSugar;
    }

    public double getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(double heartRate) {
        this.heartRate = heartRate;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
