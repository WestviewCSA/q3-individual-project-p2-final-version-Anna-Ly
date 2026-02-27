import java.util.ArrayList;

public class Stack {

	ArrayList<Integer> queueList = new ArrayList<Integer>();
	private static int index = 0;
	
	public static void push(ArrayList<Integer> queue, int o){
		queue.add(o);
		index++;
	}
	
	public int pop(ArrayList<Integer> queue){
		return queue.remove(index);
	}
	
	public int peek(){
		return queueList.get(index);
	}
	
	public static void search(){
		
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
