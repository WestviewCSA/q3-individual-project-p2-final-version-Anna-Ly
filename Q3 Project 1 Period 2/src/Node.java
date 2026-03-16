class Node<T>{
    private T data;
    Node next; //null if no next Node
    Node prev; //null if no previous Node
    
    public Node(T t) {
        data = t;
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T t) {
        data = t;
    }
    
    public Node getNext(){
        return next;
    }
    
    public void setNext(Node n){
        next = n;
    }
}