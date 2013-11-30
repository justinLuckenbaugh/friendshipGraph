/*
 * Justin Luckenbaugh
 * Austin Tamutus
 */

package apps;

import structures.*;
import java.io.*;
import java.util.*;

public class Friends{
	
	/*
	 * Three ArrayLists are created:  "map" for obtaining the index number of a given name, "people" for storing all of the information of 
	 * the people, and "vertexes" for storing the   
	 */
	ArrayList<String> map = new ArrayList<String>();
	ArrayList<Person> people = new ArrayList<Person>();
	ArrayList<Node<Integer>> vertexes = new ArrayList<Node<Integer>>();
	
	static Scanner stdin = new Scanner(System.in);
	static char getOption(){
		System.out.println("Choose one of the following:  ");
		
		System.out.print("'s' = Students at a school, ");
		System.out.print("'i' = shortest intro chain, ");
		System.out.print("'c' = cliques at a school, ");
		System.out.print("'x' = connectors, or ");
		System.out.print("q = quit.\n=>");
				
		char response = stdin.next().toLowerCase().charAt(0);
		while (response!='s' && response!='i' && response!='c' && response !='x' && response !='q'){
			System.out.println("But you have to pick 's', 'i', 'c', 'x' or 'q'!");
			response = stdin.next().toLowerCase().charAt(0);
		}
		return response;
	}
	
	public static void main(String[] args)
		throws  FileNotFoundException
		{
		
		Friends mainGraph = new Friends();
		System.out.println("Welcome to the Friendship Graphs project of Justin Luckenbaugh and Austin Tamutus.");
		System.out.println("We sincerely hope you enjoy grading our magnificent program!");
		System.out.println("First, you will need to type the name of the text file that you wish to use as input.");
		File inputFile = new File(stdin.next());
		mainGraph.build(inputFile);
		
		
		char option = getOption();
		while((option = getOption())!='q'){
			if(option=='s'){
				//Call the "students at school" method
			}
			if(option=='i'){
				//Call the "shortest intro chain" method 
			}
			if(option=='c'){
				//Call the "cliques at a school" method
			}
			if(option=='x'){
				//Call the "connectors" method
			}			
		}
		
		stdin.close();
	}
	
	/*
	 * Builds the friendship graph!
	 * Reads the input file through a scanner named fileReader
	 * Takes the integer that begins the document as the number of people to add
	 * As many times as there are people, calls the method addPerson for each subsequent line
	 * The rest of the lines are interpreted as edges, and the method "addEdge" for every subsequent line.S
	 */
	public void build(File input)
		throws FileNotFoundException
		{
		Scanner fileReader = new Scanner(input);
		int numPeople = fileReader.nextInt();
		fileReader.nextLine();
		for(int i = 0; i<numPeople ; i++){
			addVertex(fileReader.nextLine());
		}
		while(fileReader.hasNextLine()){
			addEdge(fileReader.nextLine());
		}
		
		fileReader.close();
	}

	/*
	 * Adds a person to the friendship graph "vertexes"
	 * How it works:  
	 * 		Uses two structures, the Node<T> and the Person.  This Node is parameterized with a Person, and has a link to another Node.
	 * 		Input = one line from the original document, which must represent a person's information (i.e. NOT a friendship)
	 * 		Creates a scanner that reads the input line, pInfo, using the delimiter "|"
	 *		Reads the first String of the line and assigns that to name
	 *		Checks the next string.  Appends a new Node<Integer> to the end of the vertexes list.  
	 *			If it's y, then it assigns the /next/ string as the value for "school".  Otherwise, school remains null
	 */
	private void addVertex(String pInfo){
		Scanner pReader = new Scanner(pInfo);
		pReader.useDelimiter("|");
		String name = pReader.next();
		if (pReader.next()=="y"){
			String school = pReader.next();
			people.add(new Person(name, school));
			map.add(name);
			this.vertexes.add(new Node<Integer>(map.indexOf(name), null));
		}
		else{
			people.add(new Person(name));
			map.add(name);
			this.vertexes.add(new Node<Integer>(map.indexOf(name), null));
		}
		
		pReader.close();
		return;
	}
	
	private void addEdge(String fInfo){
		Scanner fReader = new Scanner(fInfo);
		fReader.useDelimiter("|");
		String f1 = fReader.next();
		String f2 = fReader.next();
		int f1_index = map.indexOf(f1), f2_index = map.indexOf(f2);
				
		for (Node<Integer> p = vertexes.get(f1_index); p.data!=f2_index; p = p.link){
			 if(p.link==null){
				 p.addLink(new Node<Integer>(f2_index, null));
				 break;
			 }
		}
		
		fReader.close();
		return;
	}
	
	public void connectors(){
		/*
		 * Complete this method
		 */
		return;
	}
	
	public void school(){
		/*
		 * Complete this method
		 */
		return;
	}

	public void shortestChain(){
		/*
		 * Complete this method
		 */
		return;
	}
	
	public void cliques(){
		/*
		 * Complete this method
		 */
		return;
	}
	
}