package structures;

public class Person{
	
	String name;
	String school;
	
	/*
	 * Constructor for when the person is a student and has a school listed
	 */
	public Person(String name, String school){
		this.name = name;
		this.school = school;
	}
	
	/*
	 * Constructor for when the person is not a student (just leaves "school" as null)
	 */
	public Person(String name){
		this.name = name;
	}
	
}