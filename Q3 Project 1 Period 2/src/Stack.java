import java.util.ArrayList;

public class Stack<T> {

	ArrayList<T> queueList;
	
	public Stack() {
		queueList = new ArrayList<T>();
	}
	
	public void push(T o){
		queueList.add(o);
	}
	
	public T pop(){
	    if(queueList.size() == 0){
            return null;
        }
        else{
            return queueList.remove(queueList.size()-1);
        }
    }
	
	public T peek(){
		if(queueList.size() == 0){
	           return null; 
	    }
		return queueList.get(queueList.size()-1);
	}
	
	public int search(int o){
		for(int i = 0; i < queueList.size(); i++) {
			if(queueList.get(i).equals(o)){
				return i;
			}
		}
		return -1;
	}
	
	public boolean isEmpty() {
		if(queueList.size() == 0) {
			return true;
		}
		return false;
	}
	
	public int size(){
		return queueList.size();
	}
	
}
