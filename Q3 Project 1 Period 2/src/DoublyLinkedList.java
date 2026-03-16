import java.util.ArrayList; 
public class DoublyLinkedList {

    protected Node head;
    protected Node tail;
    protected int size;

    /**
     * Constructs an empty DoublyLinkedList.
     * The list should initialize with head = null, tail = null,
     * and size = 0. No exceptions should be thrown.
     */
    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Returns the number of elements currently stored in the list.
     * Should return 0 when the list is empty.
     * Must accurately reflect additions and removals.
     */
    public int size() {
        return size;
    }

    /**
     * Appends the given node to the end of the list.
     * Must correctly update:
     * - tail reference
     * - head reference if list was empty
     * - next and prev links
     * - size
     */
    public void add(Node n) {
        if(size == 0){
            n.next = head;
            head = n;
        }
        else{
            tail.next = n;
        }
        
        size++;
        tail = n;
        head.prev = null;
        tail.next = null;
    }

    /**
     * Inserts the given node at the specified index.
     * Valid index values are from 0 to size inclusive.
     * If index is invalid, the list should not change.
     * Must correctly adjust next and prev references,
     * update head or tail if necessary, and increment size.
     */
    public void add(int index, Node n) {
        if(index <= size|| index >=0){
	        if(index == 0){
	            n.next = head;
	            head = n;
	        }
	        if(index == size){
	            tail.next = n;
	            tail = n;
	        }
	        else{
	            Node ptr = head;
	            for(int i = 0; i < index -1; i++){
	                ptr = ptr.next;
	            }
	            n.next = ptr.next;
	            ptr.next = n;
	        }
	        head.prev = null;
	        tail.next = null;
	        size++;
	    }
    }

    /**
     * Returns the node located at the specified index.
     * Valid index values are from 0 to size - 1.
     * Returns null if the index is invalid.
     */
    public Node get(int index) {
        if(index >= 0 || index < size-1){
            if(index == 0){
                return head;
            }
            Node n;
            Node start = head; 
            for(int i = 0; i < index -1; i++){
                start = start.next;
            }
            n = start.next;
            return n;
        }
        
        return null;
    }

    /**
     * Removes and returns the node at the specified index.
     * Valid index values are from 0 to size - 1.
     * Returns null if the index is invalid.
     * Must correctly relink surrounding nodes,
     * update head or tail if necessary,
     * and decrement size.
     */
    public Node remove(int index) {
        if(index >= size || index < 0 || head == null){
            return null;
        }
        if(index <= size || index >= 0){
            Node removed;
            if(index == 0){
                removed = head;
                head = head.next;
            }
            else{
                Node current = head;
                for(int i = 0; i < index -1; i++){
                    current = current.next;
                }
                
                removed = current.next;
                current.next = removed.next;
                
                if(removed == tail){
                    tail = current;
                }
            }
            
            size--;
            
            return removed;
        }
        return null;
    }

    /**
     * Returns a string representation of the list in the format:
     * [elem1, elem2, elem3]
     * Elements must appear in correct order from head to tail.
     */
    public String toString() {
        String list = "[";
        Node current = head;
        for(int i = 0; i < size; i++){
            list += current.getData() + ", ";
            current = current.next;
        }
        list = list.substring(0, list.length()-2) + "]";
        return list;
    }
}