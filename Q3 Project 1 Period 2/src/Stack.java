import java.util.ArrayList;

public class Stack {

	ArrayList<Integer> queueList = new ArrayList<Integer>();
	private static int index;
	
	public Stack() {
		index = 0;
	}
	
	public static void push(ArrayList<Integer> queue, int o){
		queue.add(o);
		index++;
	}
	
	public int pop(ArrayList<Integer> queue){
		index--;
		return queue.remove(index);
	}
	
	public int peek(){
		return queueList.get(index);
	}
	
	public int search(int o){
		for(int i = 0; i < queueList.size(); i++) {
			if(queueList.get(i).equals(o)){
				return i;
			}
		}
		return -1;
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
