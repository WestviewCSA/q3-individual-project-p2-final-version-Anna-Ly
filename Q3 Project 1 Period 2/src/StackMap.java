
public class StackMap {
	
	private Stack<CoordPoint> stack;
	private Stack<CoordPoint> visited;
	private CoordPoint[][][] text;     // this guy is the output and can be altered to show the path 
	private int rowW;
	private int colW;
	private int levelW;
	private int dimensionR, dimensionC, dimensionL;
	
	public StackMap(CoordPoint[][][] textmap){
		stack = new Stack<CoordPoint>();
		visited = new Stack<CoordPoint>();
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
		//System.out.println("In level " + levelW + ", W is at row " + rowW + ", column " + colW);
	}
	
	public boolean findPath() { 
		
		CoordPoint start = text[levelW][rowW][colW];
		start.setPrev(null);
		stack.push(start);
		
		while (!stack.isEmpty()) {
			CoordPoint current = stack.pop();
			visited.push(current);
			
			int l = current.layer();
			int r = current.row();
			int c = current.column();
			String sym = current.symbol();
			
			if(sym.equals("$")) {
				
			    CoordPoint path = current.getPrev();
			    while(path != null) {
			    	String pathSym = path.symbol();
			    	if(!pathSym.equals("W") && !pathSym.equals("|")) {
						path.setSymbol("+");
					}
			        path = path.getPrev();
			    }
			    
			    
				return true;
			}
			/*
			if(!sym.equals("W") && !sym.equals("|")) {
				current.setSymbol("+");
			}
			*/
				
			
			int[][] directions = {{-1, 0},{1, 0},{0, 1},{0, -1}}; //N, S, E, W
			
			for(int i = 0; i < directions.length; i++) {
				int newR = r + directions[i][0];
				int newC = c + directions[i][1];
				
				if(l+1 < dimensionL && newR >= dimensionR && !hasVisited(l + 1, 0, c)) {
					String next = text[l+1][0][newC].symbol();
					if(next.equals(".") || next.equals("$") || next.equals("|")) {
						CoordPoint nextPoint = text[l+1][0][newC];
						nextPoint.setPrev(current);
						stack.push(nextPoint);
						//System.out.println(nextPoint.row());
					}
				} 
				if (newR < 0 || newR >= dimensionR || newC < 0 || newC >= dimensionC) { // boundaries
					continue;
				}
				if(!hasVisited(l, newR, newC)) {
					String next = text[l][newR][newC].symbol();
					if(next.equals(".") || next.equals("$") || next.equals("|")) {
						CoordPoint nextPoint = text[l][newR][newC];
						nextPoint.setPrev(current);
						stack.push(nextPoint);
					}
				}
			}
			
			
			
			
		    if(sym.equals("|")) {
		        if (l+1 < dimensionL) {
		            findWr(l+1);
		            CoordPoint nextStart = text[levelW][rowW][colW];
		            nextStart.setPrev(current);
		            stack.push(nextStart);
		        }
		    }  
		}
		
		return false;
	}
	
	
	public boolean hasVisited(int l, int r, int c) {
		boolean inVisited = false;
		boolean inStack = false;
		Stack<CoordPoint> stacktemp = new Stack<>();
		
		while (!visited.isEmpty()) {
			CoordPoint p = visited.pop();
			if(p.layer() == l && p.row() == r && p.column() == c) {
				inVisited = true;
			}
			stacktemp.push(p);
		}
		
		while (!stacktemp.isEmpty()) {
		    visited.push(stacktemp.pop());
		}
		
		
		while (!stack.isEmpty()) {
			CoordPoint p = stack.pop();
			if(p.layer() == l && p.row() == r && p.column() == c) {
				inStack = true;
			}
			stacktemp.push(p);
		}
		
		while (!stacktemp.isEmpty()) {
		    stack.push(stacktemp.pop());
		}
		
		if(inVisited || inStack) {
			return true;
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
