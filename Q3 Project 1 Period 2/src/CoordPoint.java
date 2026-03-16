
public class CoordPoint {
	
	private int row, column;
	private String symbol;
	
	public CoordPoint(int r, int c, String s) {
		row = r;
		column = c;
		symbol = s;
	}
	
	public int row() {
		return row;
	}
	
	public int column() {
		return column;
	}
	
	public String symbol() { //applies to dimensions, ., $, @, and |
		return symbol;
	}
}
