
public class OptimalPath {
	
	private CoordPoint[][][] text;     // this guy is the output and can be altered to show the path 
	private int rowW;
	private int colW;
	private int levelW;
	private int l, r, c;
	private int dimensionR, dimensionC, dimensionL;
	
	public OptimalPath(CoordPoint[][][] textmap){
		dimensionR = textmap[0].length;
		dimensionC = textmap[0][0].length;      // initialization of dimensions
		dimensionL = textmap.length;
		
		/*System.out.println(dimensionR);
		System.out.println(dimensionC);
		System.out.println(dimensionL);
		*/
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
	
	public String findWr(int level){                             //finding W on the map for the first thing in the queue
		for(int r = 0; r < text[level].length; r++) {
			for(int c = 0; c < text[level][r].length; c++) {
				if(text[level][r][c].symbol().equals("W")) {
					rowW = r;
					colW = c;
					levelW = level;
				}
			}
		}
		return "In level " + levelW + ", W is at row " + rowW + ", column " + colW;
	}
	
	public boolean findPath(int l, int r, int c) {
		
		if (l < 0 || l >= dimensionL || r < 0 || r >= dimensionR || c < 0 || c >= dimensionC) { // boundaries
			return false;
		}

	    String sym = text[l][r][c].symbol();

	    if(sym.equals("$")) {
	        return true;
	    }

	    if(!sym.equals(".") && !sym.equals("W") && !sym.equals("|")) {
	        return false;
		}

	    if(!sym.equals("W")) {
	        text[l][r][c].setSymbol("+");
	    }
	    
	    if(!sym.equals("|")) {
	        if (levelW+1 < dimensionL) {
	            findWr(levelW+1);
	            if (findPath(levelW, rowW, colW)) {
	                return true;
	            }
	        }
	    }
	    
	    if(findPath(l, r-1, c)) { //north
	        return true;
		}
	    if(findPath(l, r+1, c)) { //south
	        return true;
		}
	    if(findPath(l, r, c+1)) { //east
	        return true;
	    }
	    if(findPath(l, r, c-1)) { //west
	        return true;
	    }
	    
	    if(!sym.equals("W")) { 
	        text[l][r][c].setSymbol(".");
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
		findPath(levelW, rowW, colW);
		if(text == null) {
			return null;
		}
		return text;
	}
	
	
}
