
public class OptimalPath {
	
	private CoordPoint[][][] text;     // this guy is the output and can be altered to show the path 
	private int rowW;
	private int colW;
	private int r, c;
	private int dimensionR, dimensionC, dimensionL;
	
	public OptimalPath(CoordPoint[][][] textmap){
		dimensionR = text[0].length;
		dimensionC = text[0][0].length;      // initialization of dimensions
		dimensionL = text.length;
		
		text = new CoordPoint[dimensionL][dimensionR][dimensionC];
	}
	
	public String findWr(int level){                             //finding W on the map for the first thing in the queue
		for(int r = 0; r < dimensionL*level; r++) {
			for(int c = 0; c < text[r].length; c++) {
				if(text[r][c].symbol().equals("W")) {
					rowW = r;
					colW = c;
				}
			}
		}
		return "W is at row " + rowW + ", column " + colW;
	}
	
	public boolean findPath(int r, int c) {

	    String sym = text[r][c].symbol();

	    if(sym.equals("$")) {
	        return true;
	    }

	    if(!sym.equals(".") && !sym.equals("W")) {
	        return false;
		}

	    if(!sym.equals("W")) {
	        text[r][c].setSymbol("+");
	    }
	    
	    if(findPath(r-1, c)) { //north
	        return true;
		}
	    if(findPath(r+1, c)) { //south
	        return true;
		}
	    if(findPath(r, c+1)) { //east
	        return true;
	    }
	    if(findPath(r, c-1)) { //west
	        return true;
	    }
	    
	    if(!sym.equals("W")) { 
	        text[r][c].setSymbol(".");
	    }
	    
	    return false;
	}
	
	
	public CoordPoint[][] map(){
		if(text == null) {
			return null;
		}
		return text;
	}
	
	public CoordPoint[][] sol(){
		findPath(rowW, colW);
		if(text == null) {
			return null;
		}
		return text;
	}
	
	
}
