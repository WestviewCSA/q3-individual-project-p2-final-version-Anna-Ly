
public class QueueMap {
	
	private Queue<String> queue;
	private Queue<String> visited;
	private String[][] text;
	private int rowW;
	private int colW;
	private int r, c;
	private int dimensionR, dimensionC, dimensionL;
	
	public QueueMap(String[][] textmap){
		queue = new Queue<String>();
		visited = new Queue<String>();
		dimensionR = Integer.parseInt(textmap[0][0]);
		dimensionC = Integer.parseInt(textmap[0][1]);      // omg its initialization its the dimensions of the map!!!
		dimensionL = Integer.parseInt(textmap[0][2]);
		
		for(int r = 1; r < textmap.length; r++) {
			for(int c = 0; c < textmap[r].length; c++) {  //just to remove that pesky pesky size dimension thingy
				text[r-1][c] = textmap[r][c];
			}
		}
	}
	
	public void findWr(){                             //finding W on the map for the first thing in the queue
		for(int r = 0; r < text.length; r++) {
			for(int c = 0; c < text[r].length; c++) {
				if(text[r][c].equals("W")) {
					rowW = r;
					colW = c;
				}
			}
		}
		r = rowW;
		c = colW;
	}
	
	public void path(int r, int c) { // this needs uh what do you call it recursive nods nods i will do it i will do it
		queue.enqueue(text[r][c]);
		if(!text[r][c].equals("$")) {
			return;
		}
		
		if(r-1 >= 0) {       //North
			if(text[r-1][c].equals(".")) {
				path(r-1, c);
			}
		}
		if(r+1 <= dimensionR-1) { //South
			if(text[r+1][c].equals(".")) {
				path(r+1, c);
			}
		}
		if(c+1 <= dimensionC-1) { //East
			
		}
		if(c-1 >= 0) { //West
				
		}
	}
	
	public boolean inQueue(int r, int c) {
		//i just realized that these coord points need to be an object to store any data alongside the symbol. 
		//Its 1am im sleeping. Ill fix this later 
		return false;
	}
	
	
}
