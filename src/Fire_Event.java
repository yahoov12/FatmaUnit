import java.util.ArrayList;

public class Fire_Event implements Comparable{
	
	private int Level;
	protected ArrayList <Fire_Vehicle> List_Of_Assigned_Vehicle;
	protected String Location;
	
	public Fire_Event(int level,String location) {
		this.Level= level;
		this.Location= location;
		this.List_Of_Assigned_Vehicle = new ArrayList <Fire_Vehicle>();

	}
	
	public int numOfFighters() {
		int totalFighters =0;
		for (int i = 0; i < List_Of_Assigned_Vehicle.size(); i++) {
			totalFighters+= (List_Of_Assigned_Vehicle.get(i)).getNוumberOfAssignedFighters();
			
		}
		return totalFighters;
	}
	public void addVehicle(Fire_Vehicle v) {
		List_Of_Assigned_Vehicle.add(v);
	}
	
	
	public int compareTo(Object other) {
		if(this.Level>((Fire_Event)other).Level)
			return 1;
		if(this.Level<((Fire_Event)other).Level)
			return -1;
		return 0;
	
	}
	
	public int getLevel () {
		return this.Level;
	}

}
