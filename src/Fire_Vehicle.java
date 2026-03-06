import java.util.ArrayList;

abstract public class Fire_Vehicle implements Comparable{
	private int id;
	private int waterAmount;
	protected double eventCost;
	protected double yearsExperience;
	protected boolean Avalible;
	public ArrayList <Fire_Fighter> List_Of_Figthers;
	
	public Fire_Vehicle(int id,int waterAmount,double eventCost, double yearsExperience) {
		this.id=id;
		this.waterAmount=waterAmount;
		this.eventCost=eventCost;
		this.yearsExperience=yearsExperience;
		this.Avalible = true;
	}
	abstract public void addFighter(Fire_Fighter f);
	abstract public void addCommander(Fire_Commander c);
	
	public int compareTo(Object other) {
		if(this.waterAmount>((Fire_Vehicle)other).waterAmount)
			return 1;
		if(this.waterAmount<((Fire_Vehicle)other).waterAmount)
			return -1;
		return 0;
	}
	
	public int getwaterAmount () {
		return this.waterAmount;
	}
	
	abstract public int getNוumberOfAssignedFighters();
	
}
