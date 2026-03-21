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
	
	private static String filename;
	private static boolean stack;
	private static boolean queue;
	private static boolean opt;
	private static boolean time;
	private static boolean incoordinate;
	private static boolean outcoordinate;
	private static boolean help;
	
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
			System.out.println("The Wolverine Store is closed.");
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
		
		CoordPoint[][][] textmap;
		filename = "noSolutionMap.txt";
		incoordinate = false;          // is input a Coord?
		outcoordinate = false;        // will output be a Coord?
		stack = true;
		queue = false;       //methods
		opt = false; 
		time = true;     //show runtime or not
		help = false;
		
		for(int i = 0; i < args.length; i++) {
			//System.out.println(args[i]);
		}
		
		
		if(args.length == 1) {
			if(args[0].toLowerCase().equals("yes")) {
				help = true;
			}
			else {
				System.out.println("Not a valid argument");
			}
		}
		else if(args.length == 7) {
			filename = args[0];
			incoordinate = Boolean.parseBoolean(args[1]);          // is input a Coord?
			outcoordinate = Boolean.parseBoolean(args[2]);        // will output be a Coord?
			stack = Boolean.parseBoolean(args[3]);
			queue = Boolean.parseBoolean(args[4]);       //methods
			opt = Boolean.parseBoolean(args[5]); 
			time = Boolean.parseBoolean(args[6]);     //show runtime or not
		}
		else {
			System.out.println("Place your argument as follows");
			System.out.println("<String Filename> <boolean inCoord> <boolean outCoord> <boolean Stack> <boolean Queue> <boolean Optimal Path> <boolean runTime>");
			System.out.println("Boolean are either true and false. Only ONE of the stack, queue, opt must be true. The rest are false.");
			System.out.println(" ");
			System.out.println("Example:");
			System.out.println("mediumMap.txt false false true false false true");
			System.out.println(" ");
			System.out.println("Need more help? Input argument as Yes");
			
			System.exit(0);
		}
		
		
		if(help) {
			System.out.println("Help Center:");
			System.out.println("This program is designed to create a path from the Wolverine to the Wolverine Buck in text-map format or coordinate-based system");
			System.out.println("It takes the input of a text-map format or coordinate-based system and outputs a text-map format or coordinate-based system.");
			System.out.println("It uses several methods, Stack, Queue and Optimal Path.");
			System.out.println(" ");
			System.out.println("Place your argument as follows");
			System.out.println("<String Filename> <boolean inCoord> <boolean outCoord> <boolean Stack> <boolean Queue> <boolean Optimal Path> <boolean runTime>");
			System.out.println("Boolean are either true and false. Only ONE of the stack, queue, opt must be true. The rest are false.");
			System.out.println(" ");
			System.out.println("Filename is the name of the file in text-map format or coordinate-based system being inputted");
			System.out.println("inCoord is if the file is a coordinate-based system. If so, argument should be true and if it is a text-map format, argument is false.");
			System.out.println("outCoord is if the output will be a coordinate-based system. If so, argument should be true and if it is a text-map format, argument is false.");
			System.out.println("Stack, Queue, Optimal Path are the methods for determining the solution. Only make the method you want as true and the rest as false.");
			System.out.println("runTime is if you would like the runtime of the program. If so, argument should be true and if no, argument is false");
			System.out.println(" ");
			System.out.println("Example:");
			System.out.println("mediumMap.txt false false true false false true");
			System.out.println("Input is a text-map and output is a text-map. The path is determined through Stack and it shows the runtime.");
			System.out.println(" ");
			System.out.println("mediumMapC.txt true false false false true false");
			System.out.println("Input is a coordinate-based and output is a text-map. The path is determined through Optimal Path and it does not show the runtime.");
			
			System.exit(0);
		}
		
		if(stack == true && (stack == queue || stack == opt)) {
			System.out.println("Two or more method switches are true. Only one method switch must be true.");
			System.exit(-1);
		}
		if(queue == true && (queue == stack || queue == opt)) {
			System.out.println("Two or more method switches are true. Only one method switch must be true.");
			System.exit(-1);
		}
		if(stack == false && queue == false && opt == false) {
			System.out.println("All method switches are false. One method switch must be true.");
			System.exit(-1);
		}
		
		
		//After initializing all booleans and methods based on the argument
		if(incoordinate) { //Incoordinate
			textmap = CoordToMap(filename); 
		}
		else {
			textmap = readTextMap(filename);
		}
		
		
		int startTime = (int) System.currentTimeMillis();
		
		CoordPoint[][][] map = null;
		if(stack) {
			StackMap sMap = new StackMap(textmap);
			map = sMap.sol();
		}
		else if(queue) {
			QueueMap qMap = new QueueMap(textmap);
			map = qMap.sol();
		} 
		else if(opt) {
			OptimalPath oMap = new OptimalPath(textmap);
			map = oMap.sol();
		}
		
		int endTime =  (int) System.currentTimeMillis();
		double duration = (double)(endTime - startTime)/1000;
		
        if(outcoordinate) { //Outcoordinate
        	CoordPoint[][] maptocoord = MapToCoord(map);
        	printCoord(maptocoord);
        }
        else {
        	printMap(map);
        	
        	CoordPoint[][] maptocoord = MapToCoord(map);  // only checks if there's a path or not
        	if(maptocoord == null) {
        		System.out.println("The Wolverine Store is closed.");
        	}
        }
		
        
		if(time) {
			System.out.println("Total Runtime: " + duration + " seconds");
		}
		System.exit(-1);
		
	}
}
