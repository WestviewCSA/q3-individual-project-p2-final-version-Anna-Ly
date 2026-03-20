import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class FileReading {
	
	public static String easyM = "easyMap.txt";
	public static String mediumM = "mediumMap.txt";
	public static String hardM = "hardMap.txt";
	public static String noSolutionM = "noSolutionMap.txt";
	
	public static String easyC1 = "easyMapC.txt";
	public static String easyC2 = "easyMapC2.txt";
	public static String mediumC = "mediumMapC.txt";
	public static String hardC = "hardMapC.txt";
	public static String noSolutionC = "noSolutionMapC.txt";
	
	private static boolean inputM;
	private static boolean outputM;
	private static int method;
	
	
	public static CoordPoint[][][] readTextMap(String filename){
		File file = new File(filename);
		
	
		try (Scanner scanner = new Scanner(file)) {
			String dimensionLine = scanner.nextLine();
			String[] firstLine = dimensionLine.split(" ");
			////
			int[] parts = new int[firstLine.length];          //initalization of 2d array
			for(int i = 0; i < parts.length; i++) {
				parts[i] = Integer.parseInt(firstLine[i]);
				//System.out.println(parts[i]);
			}
			CoordPoint[][][] map = new CoordPoint[parts[2]][parts[0]][parts[1]]; //layer, row, col

			int layer = 0;
			int row = 0;
			while (scanner.hasNextLine() && layer < map.length) {       //only works with file having one map
				String line = scanner.nextLine();
				if (line.isEmpty()) {
                    continue;
                }
				String[] l = line.split(" ");
				
				
				for(int i = 0; i < l.length; i++) {
					map[layer][row][i] = new CoordPoint(layer, row, i, l[i]);
					//System.out.println(map[row][i]);
				}
				row++;
				if(row == map[layer].length) {
					row = 0;
					layer++;
				}
			}
			return map;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("IncompleteMapException");
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static CoordPoint[][] readCoordMap(String filename){ //no longer being used
		File file = new File(filename);
		
		try (Scanner scanner = new Scanner(file)) {
			String dimensionLine = scanner.nextLine();
			String[] firstLine = dimensionLine.split(" ");
			////
			int[] parts = new int[firstLine.length];          //initalization of 2d array
			for(int i = 0; i < parts.length; i++) {
				parts[i] = Integer.parseInt(firstLine[i]);
				//System.out.println(parts[i]);
			}
			CoordPoint[][] map = new CoordPoint[parts[0]*parts[1]*parts[2]][4];
			//
			
			for(int r = 0; r < map.length; r++) {
				for(int c = 0; c < map[r].length; c++) {
					map[r][c] = new CoordPoint("");
				}
			}
			
			//
			int row = 0;
			while (scanner.hasNextLine() && row < parts[0]*parts[1]*parts[2]) {       //only works with file having one map
				String line = scanner.nextLine();
				if (line.isEmpty()) {
					row = parts[0]*parts[1]*parts[2]-1;
                }
				String[] l = line.split(" ");
				
				for(int i = 0; i < l.length; i++) {
					map[row][i] = new CoordPoint(row, i, l[i]);
					//System.out.println(map[row][i]);
				}
				
				row++;
			}
			return map;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("IncompleteMapException");
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static CoordPoint[][][] CoordToMap(String filename){
		File file = new File(filename);
		
		try (Scanner scanner = new Scanner(file)) {
			String dimensionLine = scanner.nextLine();
			String[] firstLine = dimensionLine.split(" ");
			////
			int[] parts = new int[firstLine.length];          //initalization of 2d array
			for(int i = 0; i < parts.length; i++) {
				parts[i] = Integer.parseInt(firstLine[i]);
				//System.out.println(parts[i]);
			}
			CoordPoint[][][] map = new CoordPoint[parts[2]][parts[0]][parts[1]]; //layer, row, col
			
			for(int l = 0; l < map.length; l++) {
				for(int r = 0; r < map[l].length; r++) {
					for(int c = 0; c < map[l][r].length; c++) {
						map[l][r][c] = new CoordPoint(l, r, c, ".");
					}
				}
			}
			
			
			boolean done = false;
			while (scanner.hasNextLine() && !done) {       //only works with file having one map
				String line = scanner.nextLine();
				if (line.isEmpty()) {
                    done = true;
                }
				else {
					String[] l = line.split(" ");
					String symbolL = l[0];
					//System.out.println(l[0]);
					int rowL = Integer.parseInt(l[1]);
					int colL = Integer.parseInt(l[2]);
					int layerL = Integer.parseInt(l[3]);
				
					map[layerL][rowL][colL] = new CoordPoint(layerL, rowL, colL, symbolL);
				}
			}
			return map;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("IncompleteMapException");
			e.printStackTrace();
		}
		return null;
	}
	
	public static CoordPoint[][] MapToCoord(CoordPoint[][][] map){ 
		
		CoordPoint[][] coord = new CoordPoint[map.length*map[0].length*map[0][0].length][4];
		int row = 0;
		for(int l = 0; l < map.length; l++) {
			for(int r = 0; r < map[l].length; r++) {
				for(int c = 0; c < map[l][r].length; c++) {
					CoordPoint p = map[l][r][c];
					if(p.symbol().equals("+")) {
						coord[row][0] = new CoordPoint(p.symbol());
						coord[row][1] = new CoordPoint(Integer.toString(p.row()));
						coord[row][2] = new CoordPoint(Integer.toString(p.column())); 
						coord[row][3] = new CoordPoint(Integer.toString(p.layer()));
						row++;
					}
				}
			}
		}
		if(row == 0) {
			return null;
		}
		return coord;	
	}
	
	public static boolean checkValid(CoordPoint[][] map) {
		
		boolean wolverine = false;
		boolean buck = false; 
		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map[r].length; c++) {
				if(map[r][c].symbol().equals("W")) {
					wolverine = true;
				}
				if(map[r][c].symbol().equals("$")) {
					buck = true;
				}
			}
			
		}
		
		if(wolverine && buck) {
			return true;
		}
		else {
			return false; 
		}
	}
	
	
	public static void printMap(CoordPoint[][][] textmap) {
		if (textmap == null) {              
		        System.out.println("Map is null");
		        return;
		}
		for(int l = 0; l < textmap.length; l++) {
			for(int r = 0; r < textmap[l].length; r++) {
				String row = "";
				for(int c = 0; c < textmap[l][r].length; c++) {
					if(!textmap[l][r][c].symbol().equals("")) {
						row += textmap[l][r][c].symbol() + " ";
					}
				}
					if(row.length() > 0) {
						System.out.println(row);
					}
			}
		}
		System.out.println("");
	}
	
	
	public static void printCoord(CoordPoint[][] textmap) {
		if (textmap == null) {              
			System.out.println("No solution path found");
	        return;
		}
		
		for(int r = 0; r < textmap.length; r++) {
			String row = "";
			for(int c = 0; c < textmap[r].length; c++) {
				if(textmap[r][c] != null && !textmap[r][c].symbol().equals("")) {
					row += textmap[r][c].symbol() + " ";
					//System.out.println(textmap[r][c].symbol());
				}
			}
			
			if(row.length() > 0) {
				System.out.println(row);
			}
		}
		System.out.println("");
	}
	
	public static void main(String[] args) {
		int startTime = (int) System.currentTimeMillis();
		
		inputM = true;          // is input a Map?
		outputM = true;        // will output be a Map?
		method = 2;             // 0 - stack , 1 - queue, 2 - optimal
		CoordPoint[][][] textmap;
		String filename = hardM;
		
		if(!inputM) {
			textmap = CoordToMap(filename);
		}
		else {
			textmap = readTextMap(filename);
		}
		
		if(method == 0) {
			Stack(textmap, outputM);
		}
		else if(method == 1) {
			Queue(textmap, outputM);
		}
		else if(method == 2) {
			Optimal(textmap, outputM);
		}
		
		
		int endTime =  (int) System.currentTimeMillis();
		double duration = (double)(endTime - startTime)/1000;
		
		System.out.println("Total Runtime: " + duration + " seconds");
		/*
		CoordPoint[][][] textmap = readTextMap(mediumM);
		printMap(textmap);
		CoordPoint[][] coordmap = readCoordMap(mediumC);
		printCoord(coordmap);
		
		
		boolean isValid = checkValid(coordmap);
        System.out.println("Valid setup? " + isValid);
       */
	}
	
	public static void Stack(CoordPoint[][][] textmap, boolean outputM) {
		StackMap sMap = new StackMap(textmap); //works
        //printMap(sMap.map());
        
        if(outputM) {
        	printMap(sMap.sol());
        }
        else {
        	CoordPoint[][] maptocoord = MapToCoord(sMap.sol());
        	printCoord(maptocoord);
        }
	}
	
	public static void Queue(CoordPoint[][][] textmap, boolean outputM) {
		QueueMap qMap = new QueueMap(textmap); //works
        //printMap(qMap.map());
	       if(outputM) {
	        	printMap(qMap.sol());
	        }
	        else {
	        	CoordPoint[][] maptocoord = MapToCoord(qMap.sol());
	        	printCoord(maptocoord);
	        }
	}
	
	public static void Optimal(CoordPoint[][][] textmap, boolean outputM) {
		OptimalPath map = new OptimalPath(textmap); // works
        //printMap(map.map());
	    if(outputM) {
	        printMap(map.sol());
	    }
	    else {
	        CoordPoint[][] maptocoord = MapToCoord(map.sol());
	        printCoord(maptocoord);
	    }
	}
}
