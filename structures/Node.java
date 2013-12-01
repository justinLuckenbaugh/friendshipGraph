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
	
	public void insertRight(Node<T> insertion){
		if(this.link!=null){
			insertion.link=this.link;
		}
		this.link = insertion;
		return;
	}
	
	public void removeRight(){
		if(this.link!=null){
			this.link=this.link.link;
		}
		return;
	}
}