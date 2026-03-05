import java.util.ArrayList;

public class Queue {
	
	private ArrayList<Integer> queueList = new ArrayList<Integer>();
	private static int index;
	
	public Queue() {
		index = 0;
	}
	
	public static void enqueue(ArrayList<Integer> queue, int o){
		queue.add(o);
		index++;
	}
	
	public static void dequeue(ArrayList<Integer> queue){
		queue.remove(0);
		index--;
	}
	
	public int peek(){
		return queueList.get(index);
	}
	
	public boolean isEmpty(ArrayList<Integer> queue) {
		if(queue.size() == 0) {
			return true;
		}
		return false;
	}
	
	public int size(ArrayList<Integer> queue){
		return queue.size();
	}

}
