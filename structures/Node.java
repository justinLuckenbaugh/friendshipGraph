package structures;

/*
 * A node has a person and a link to another node
 */
public class Node<T>{
	public T data;
	public Node<T> link;
	
	public Node(T data, Node<T> link){
		this.data = data;
		this.link = link;
	}
	
	public void addLink(Node<T> link){
		this.link = link;
		return;
	}
	
}