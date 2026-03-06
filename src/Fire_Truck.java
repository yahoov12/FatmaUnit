import java.util.ArrayList;
public class Fire_Truck extends Fire_Vehicle{
	static protected String type="T";
	private int numFighters;

	public Fire_Truck(int id,int waterAmount,double eventCost, double yearsExperience, int numFighters) {
		super(id,waterAmount,eventCost,yearsExperience);
		this.numFighters=numFighters;
		this.List_Of_Figthers = new ArrayList <Fire_Fighter>();
	}
	public void addFighter(Fire_Fighter f) {
		if (this.List_Of_Figthers.size() < numFighters) {
			this.List_Of_Figthers.add(f); 
		}
	}
	public void addCommander(Fire_Commander c) {
		for (int i = 0; i < List_Of_Figthers.size(); i++) {
			if (List_Of_Figthers.get(i) instanceof Fire_Commander) {
				System.out.println("There is a Commander on Truck alredy!");
				return; 
			}
		}
		List_Of_Figthers.add(c);
	}
	public int getNוumberOfAssignedFighters() {
		int counter =0;
		for (int i = 0; i < List_Of_Figthers.size(); i++) {
			if(!(List_Of_Figthers.get(i) instanceof Fire_Commander))
				counter++;
		}
		return counter;
	
	}
	
	public int getNumFigthers() {
		return this.numFighters;
	}
}
