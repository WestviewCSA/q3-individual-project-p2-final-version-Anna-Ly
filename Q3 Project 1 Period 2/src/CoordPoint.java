
public class CoordPoint {
	
	private int row, column, layer;
	private String symbol;
	private CoordPoint prev;
	
	public CoordPoint(String s) {
		symbol = s;
	}
	
	public CoordPoint(int r, int c, String s) {
		row = r;
		column = c;
		symbol = s;
	}
	
	public CoordPoint(int l, int r, int c, String s) {
		row = r;
		column = c;
		layer = l;
		symbol = s;
	}
	
	public int layer() {
		return layer;
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
	
	public void setSymbol(String s) {
		symbol = s;
	}
	
	public void setPrev(CoordPoint p) {
		prev = p;
	}
	
	public CoordPoint getPrev() {
		return prev;
	}
	
	
}
