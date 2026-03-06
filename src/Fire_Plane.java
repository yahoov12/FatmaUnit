import java.util.ArrayList;

public class Fire_Plane extends Fire_Vehicle{
	static protected String type="P";
	private int minHeight;
	
	
	
	public Fire_Plane(int id,int waterAmount,double eventCost, double yearsExperience, int minHeight) {
		super(id,waterAmount,eventCost,yearsExperience);
		if(minHeight>50)
			throw new ImpossibleFirePlaneMinHeightException();
		this.minHeight=minHeight;
		this.List_Of_Figthers = new ArrayList <Fire_Fighter>();

	}
	public void addFighter(Fire_Fighter f) {
		for (int i = 0; i < List_Of_Figthers.size(); i++) {
			if ((List_Of_Figthers.get(i) instanceof Fire_Fighter)&&!(List_Of_Figthers.get(i) instanceof Fire_Commander)) {
				System.out.println("There is a Fire Fighter on Plane alredy!");
				return; 
			}
		}
		List_Of_Figthers.add(f);
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
	
	public int getMinHeight () {
		return this.minHeight;
	}

	
	
	

}
