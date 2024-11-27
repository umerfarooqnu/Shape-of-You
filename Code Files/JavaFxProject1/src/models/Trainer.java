package models;

public class Trainer extends Person {
	protected String specialization;
	protected String experience;
	protected boolean availablity;
	
	public Trainer(int id, String name, String email, String password, String specialization, String experience, boolean availablity) {
		super(id, name, email, password);
		
		this.specialization = specialization;
		this.experience = experience;
		this.availablity = availablity;
	}

    @Override
    public String getPersonType() {
        return "Trainer";
    }
    
    public String getSpecialization() {
        return specialization;
    }
    
    public String getExperience() {
        return experience;
    }
    
    public boolean getAvailablity() {
        return availablity;
    }
}
