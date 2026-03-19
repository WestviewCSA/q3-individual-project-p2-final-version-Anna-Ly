
public class QueueMap {
	
	private Queue<CoordPoint> queue;
	private Queue<CoordPoint> visited;
	private CoordPoint[][][] text;     // this guy is the output and can be altered to show the path 
	private int rowW;
	private int colW;
	private int levelW;
	private int dimensionR, dimensionC, dimensionL;
	
	public QueueMap(CoordPoint[][][] textmap){
		queue = new Queue<CoordPoint>();
		visited = new Queue<CoordPoint>();
		dimensionR = textmap[0].length;
		dimensionC = textmap[0][0].length;      // initialization of dimensions
		dimensionL = textmap.length;
		
		text = new CoordPoint[dimensionL][dimensionR][dimensionC];
		
		for(int l = 0; l < text.length; l++) {
			for(int r = 0; r < text[l].length; r++) {
				for(int c = 0; c < text[l][r].length; c++) {
					
					text[l][r][c] = textmap[l][r][c];
				}
				
			}
		}
		
		findWr(0);
	}
	
	public void findWr(int level){                             //finding W on the map for the first thing in the queue
		for(int r = 0; r < text[level].length; r++) {
			for(int c = 0; c < text[level][r].length; c++) {
				if(text[level][r][c].symbol().equals("W")) {
					rowW = r;
					colW = c;
					levelW = level;
				}
			}
		}
		System.out.println("In level " + levelW + ", W is at row " + rowW + ", column " + colW);
	}
	
	public boolean findPath() { 
		
		CoordPoint start = text[levelW][rowW][colW];
		queue.enqueue(start);
		visited.enqueue(start);
		
		while (!queue.isEmpty()) {
			CoordPoint current = queue.dequeue();
			
			int l = current.layer();
			int r = current.row();
			int c = current.column();
			String sym = current.symbol();
			
			if(sym.equals("$")) {
				return true;
			}
			if(!sym.equals("W") && !sym.equals("$")) {
				current.setSymbol("+");
			}
			
			if (l < 0 || l >= dimensionL || r < 0 || r >= dimensionR || c < 0 || c >= dimensionC) { // boundaries
				return false;
			}
			else {
				
				int newR = r;
				int newC = c;
				
				if(!hasVisited(l, newR, newC)) {
					
					String next = text[l][newR][newC];
					if(next.equals(".") || next.equals("$") || next.equals("|")) {
						CoordPoint nextPoint = text[l][newR][newC];
					}
				}
					
			}
			
			
		    if(sym.equals("|")) {
		        if (l+1 < dimensionL) {
		            findWr(l+1);
					if(!hasVisited(levelW, rowW, colW)) {
						CoordPoint nextStart = text[levelW][rowW][colW];
						queue.enqueue(nextStart);
						visited.enqueue(nextStart);
					}
		        }
		    }  
		}
		
		return false;
	}
	
	
	public boolean hasVisited(int l, int r, int c) {
		for(CoordPoint p : visited) {
			if(p.layer() == l && p.row() == r && p.column() == c) {
				return true;
			}
		}
		return false;
	}
	
	
	public CoordPoint[][][] map(){
		if(text == null) {
			return null;
		}
		return text;
	}
	
	public CoordPoint[][][] sol(){
		findPath();
		if(text == null) {
			return null;
		}
		return text;
	}
	
}
