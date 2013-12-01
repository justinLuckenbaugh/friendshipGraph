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
	ArrayList<Node<Integer>> vertices = new ArrayList<Node<Integer>>();
	
	static Scanner stdin = new Scanner(System.in);
	static char getOption(){
		System.out.println("Choose one of the following:  ");
		
		System.out.print("'p' = Print the graph, ");
		System.out.print("'s' = Students at a school, ");
		System.out.print("'i' = shortest intro chain, ");
		System.out.print("'c' = cliques at a school, ");
		System.out.print("'x' = connectors, or ");
		System.out.print("q = quit.\n=>");
				
		char response = stdin.next().toLowerCase().charAt(0);
		while (response!='p' && response!='s' && response!='i' && response!='c' && response !='x' && response !='q'){
			System.out.println("But you have to pick 'p', 's', 'i', 'c', 'x' or 'q'!");
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
		
		char option;
		while((option = getOption())!='q'){
			if(option=='p'){
				//Print the whole graph
				mainGraph.printGraph(mainGraph.map, mainGraph.people, mainGraph.vertices);
			}
			if(option=='s'){
				//Call the "students at school" method
				System.out.println("Enter the name of the school you want to see.");
				stdin.nextLine();
				String subset = stdin.nextLine();
				mainGraph.school(subset);
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
		pReader.useDelimiter("\\|");
		String name = pReader.next().toLowerCase();
		String studies = pReader.next().toLowerCase();
		if (studies.equals("y")){
			String school = pReader.next().toLowerCase();
			people.add(new Person(name, school));
			map.add(name);
			this.vertices.add(new Node<Integer>(map.indexOf(name), null));
		}
		else{
			people.add(new Person(name));
			map.add(name);
			this.vertices.add(new Node<Integer>(map.indexOf(name), null));
		}
		
		pReader.close();
		return;
	}
	
	/*
	 * mutually adds edges between the two people mentioned in a properly formatted line of text (i.e. "personA|personB")
	 * 
	 */
	private void addEdge(String fInfo){
		Scanner fReader = new Scanner(fInfo);
		fReader.useDelimiter("\\|");
		String f1 = fReader.next();
		String f2 = fReader.next();
		int f1_index = map.indexOf(f1), f2_index = map.indexOf(f2);
				
		for (Node<Integer> p = vertices.get(f1_index); p!=null && p.data!=f2_index; p = p.link){
			if(p.link==null || f2_index>p.link.data){
				p.insertRight(new Node<Integer>(f2_index, null));
				break;
			}
		}
		for (Node<Integer> p = vertices.get(f2_index); p!=null && p.data!=f1_index; p = p.link){
			 if(p.link==null ||f1_index>p.link.data){
				 p.insertRight(new Node<Integer>(f1_index, null));
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
	
	public void school(String schoolName){
		/*
		 * Complete this method
		 */
		System.out.print("The friendship graph of " + schoolName + " is:\n");
		schoolName=schoolName.toLowerCase();
		ArrayList<Node<Integer>> subVertices = new ArrayList<Node<Integer>>();
		for(int i = 0, j = 0; i < vertices.size(); i++){
			if(schoolName.equals(people.get(i).school)){
				subVertices.add(new Node<Integer>(i, null));
				for(Node<Integer> p = subVertices.get(j), t = vertices.get(i); t.link!=null; t=t.link ){
					Person friend = people.get(t.link.data);
					if(schoolName.equals(friend.school)){
						p.insertRight(new Node<Integer>(t.link.data, null));
						p=p.link;
					}
				}
				j++;
			}
		}
		printGraph(this.map, this.people, subVertices);
		return;
	}

		/*
		 * shortestChain returns a list of integers that constitutes the shortest path, in order of traversal
		 * It is non-recursive so that a running list of vertices visited can be visible for the entire duration of the method.
		 * Instead, for loops with pointers are used. 
		 * 
		 */
	public ArrayList<Integer> shortestChain(ArrayList<Node<Integer>> vertices, int a, int b){
		/*
		 * Complete this method
		 */
		ArrayList<Integer> path = new ArrayList<Integer>();
		ArrayList<Integer> visits = new ArrayList<Integer>();
		/*
		for(p=vertices.get(a); ; ){
			if(a==b){
				return null;
			}
		}
		*/
		
		return path;
	}
	
	public void cliques(){
		/*
		 * Complete this method
		 */
		return;
	}
	
		/*
		 * printGraph takes three input ArrayLists:  a map, a list of the people, and the vertices' linked lists
		 * 		This allows it to operate on subgraphs which are only transiently generated by the various methods of Friends
		 * 		For each vertex, this method prints the person who that bucket represents, then lists all of the people 
		 * 			that are friends with that person.
		 * 		 
		 */
	public void printGraph(ArrayList<String> map, ArrayList<Person> people, ArrayList<Node<Integer>> vertices){
		for(int i = 0; i<vertices.size(); i++){
			Integer value = vertices.get(i).data;
			System.out.print(value + " (" + people.get(value) + ") : ");
			for (Node<Integer> p = vertices.get(i).link; p!=null; p=p.link){
				System.out.print(p.data + " (" + people.get(p.data) + ")");
				if(p.link!=null){
					System.out.print(", ");
					if(p.link.link==null){
						System.out.print("and ");
					}
				}
			}
			System.out.print("\n");
		}	
		return;
	}
	
	
	
}