package models;

public class User extends Person {
    protected double height; // In cm
    protected double weight; // In kg
    protected String fitnessGoal; // e.g., "Weight Loss", "Muscle Gain"
    protected int progressID;
    protected int statisticID;

    public User(int id, String name, String email, String password, double height, double weight, String fitnessGoal, int progressID, int statisticID) {
        super(id, name, email, password);
        this.height = height;
        this.weight = weight;
        this.fitnessGoal = fitnessGoal;
        this.progressID = progressID;
        this.statisticID = statisticID;
    }

    @Override
    public String getPersonType() {
        return "Member";
    }

    // Getters
    
    public int getProgressID() {
        return progressID;
    }
    
    public int getStatisticID() {
        return statisticID;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public String getFitnessGoal() {
        return fitnessGoal;
    }

    // Setters

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setFitnessGoal(String fitnessGoal) {
        this.fitnessGoal = fitnessGoal;
    }

}
