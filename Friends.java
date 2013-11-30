import java.util.Scanner;

public class Friends{
	
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
	
	public static void main(String[] args){
		Friends mainGraph = new Friends();
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
	}

}