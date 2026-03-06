
public class Fire_Commander extends Fire_Fighter implements Comparable {
	protected int Rank;


	public Fire_Commander(String name, int Id, int Age, Double yearsExperience, int commandLevel) {
		super(name, Id, Age, yearsExperience);
		if(yearsExperience<3)
			throw new ImpossibleFireCommandorYearsOfExperienceException();
		this.Rank = commandLevel;
		this.setSalary(1000);	
	}
	
	public int compareTo(Object other) {
		if(this.Rank>((Fire_Commander)other).Rank)
			return 1;
		if(this.Rank<((Fire_Commander)other).Rank)
			return -1;
		return 0;
	}

}
