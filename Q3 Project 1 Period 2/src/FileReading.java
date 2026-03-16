import java.io.File;
import java.io.FileNotFoundException;
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
	
	
	public static String[][] readTextMap(String filename){
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
			String[][] map = new String[parts[0]*parts[2]+1][parts[1]];
			//
			//
			for(int i = 0; i < parts.length; i++) {
				map[0][i] = firstLine[i];
			}
			map[0][3] = "";
			//
			int row = 1;
			while (scanner.hasNextLine() && row < map.length) {       //only works with file having one map
				String line = scanner.nextLine();
				String[] l = line.split(" ");
				
				for(int i = 0; i < l.length; i++) {
					map[row][i] = l[i];
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
	
	public static String[][] readCoordMap(String filename){
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
			String[][] map = new String[parts[0]*parts[1]*parts[2]+1][4];
			//
			//
			for(int i = 0; i < parts.length; i++) {
				map[0][i] = firstLine[i];
			}
			map[0][3] = "";
			//
			for(int r = 1; r < map.length; r++) {
				for(int c = 0; c < map[r].length; c++) {
					map[r][c] = "";
				}
			}
			
			//
			int row = 1;
			while (scanner.hasNextLine() && row < parts[0]*parts[1]*parts[2]+1) {       //only works with file having one map
				String line = scanner.nextLine();
				String[] l = line.split(" ");
				if(line.isEmpty()) {    // stop reading if no more coordinates in the map
					row = parts[0]*parts[1]*parts[2];
				}
				
				for(int i = 0; i < l.length; i++) {
					map[row][i] = l[i];
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
	
	
	public static boolean checkValid(String[][] map) {
		
		boolean wolverine = false;
		boolean buck = false; 
		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map[r].length; c++) {
				if(map[r][c].equals("W")) {
					wolverine = true;
				}
				if(map[r][c].equals("$")) {
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
	
	
	public static void printMap(String[][] textmap) {
		if (textmap == null) {              
		        System.out.println("Map is null");
		        return;
		}
		for(int r = 0; r < textmap.length; r++) {
			String row = "";
			for(int c = 0; c < textmap[r].length; c++) {
				row += textmap[r][c] + " ";
			}
			System.out.println(row);
		}
		System.out.println(" ");
	}
	
	
	public static void main(String[] args) {
		
		String[][] textmap = readTextMap(easyM);
		printMap(textmap);
		
		String[][] coordmap = readCoordMap(noSolutionC);
		printMap(coordmap);
		
		boolean isValid = checkValid(coordmap);
        System.out.println("Valid setup? " + isValid);
		
	
	}
}
