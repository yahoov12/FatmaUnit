
public class Fire_Fighter implements Comparable{
	protected String Name;
	private int id;
	protected int Age;
	protected double yearsExperience;
	protected int Salary;
	protected boolean Avalible;

	public Fire_Fighter(String name, int Id,int age,double yearsExperience) {
		this.Name= name;
		this.id= Id;
		this.Age= age;
		this.yearsExperience= yearsExperience;
		this.Salary=1000;
		this.Avalible = true;
	}
	public Fire_Fighter(String name, int Id,int age) {
		this.Name= name;
		this.id= Id;
		this.Age= age;
		this.yearsExperience= 0;
		this.Salary=0;
		this.Avalible = true;
	}
	public void setSalary(int bonus) {
		this.Salary= Salary+bonus;
	}
	
	public int compareTo(Object other) {
		if(this.yearsExperience>((Fire_Fighter)other).yearsExperience)
			return 1;
		if(this.yearsExperience<((Fire_Fighter)other).yearsExperience)
			return -1;
		return 0;
	}
	
	
	
	
	/*public String toString() {
		return this.Name;
	}
*/
}
