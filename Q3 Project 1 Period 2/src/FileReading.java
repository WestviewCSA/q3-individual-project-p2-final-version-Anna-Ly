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
	
	public static boolean isMap; 
	
	
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
			isMap = true;
			return map;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("IncompleteMapException");
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static CoordPoint[][] readCoordMap(String filename){
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
			isMap = false;
			return map;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("IncompleteMapException");
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static CoordPoint[][][] CoordToMap(CoordPoint[][] map){
		isMap = true;
		return null;	
	}
	
	public static CoordPoint[][] MapToCoord(CoordPoint[][][] map){ 
		isMap = false; // work on these after queue and stack map are done
		return null;	
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
	        System.out.println("Map is null");
	        return;
		}
		
		for(int r = 0; r < textmap.length; r++) {
			String row = "";
			for(int c = 0; c < textmap[r].length; c++) {
				if(!textmap[r][c].symbol().equals("")) {
					row += textmap[r][c].symbol() + " ";
				}
			}
			
			if(row.length() > 0) {
				System.out.println(row);
			}
		}
		System.out.println("");
	}
	
	public static void main(String[] args) {
		
		CoordPoint[][][] textmap = readTextMap(mediumM);
		printMap(textmap);
		CoordPoint[][] coordmap = readCoordMap(mediumC);
		printCoord(coordmap);
		
		
		boolean isValid = checkValid(coordmap);
        System.out.println("Valid setup? " + isValid);
        
        QueueMap qMap = new QueueMap(textmap);
        printMap(qMap.map());
        //printMap(qMap.sol());
        
		/*
        OptimalPath map = new OptimalPath(textmap); // works
        printMap(map.map());
        printMap(map.sol());
       */
	}
}
