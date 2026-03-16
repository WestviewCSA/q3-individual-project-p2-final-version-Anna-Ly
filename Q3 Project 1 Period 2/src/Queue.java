import java.util.ArrayList;

public class Queue<T> {
	
	private ArrayList<T> queue; 
	
	public Queue() {
		queue = new ArrayList<T>();
	}
	
	public void enqueue(T o){
		queue.add(o);
	}
	
	public T dequeue(){
		if(queue.size() == 0) {
			return null;
		}
		return queue.remove(0);
	}
	
	public T peek(){
		if(queue.size() == 0) {
			return null;
		}
		return queue.get(0);
	} 
	
	public boolean isEmpty() {
		if(queue.size() == 0) {
			return true;
		}
		return false;
	}
	
	public int size(){
		return queue.size();
	}


}
