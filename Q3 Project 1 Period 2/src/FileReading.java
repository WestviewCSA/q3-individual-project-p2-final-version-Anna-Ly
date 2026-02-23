import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReading {
	
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
			String[][] map = new String[parts[0]][parts[1]];
			//
			//
			int row = 0;
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
			String[][] map = new String[parts[0]][parts[1]];
			//
			//
			int row = 0;
			while (scanner.hasNextLine() && row < map.length) {       //only works with file having one map
				String line = scanner.nextLine();
				String[] l = line.split(" ");
				
				for(int i = 0; i < l.length; i++) {
					map[row][i] = l[i]+" "+row+" "+i+" "+parts[2];
					//System.out.println(map[row][i]);
				}
				row++;
			}
			
			return map;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		
		String easyM = "easyMap.txt";
		String[][] textmap = readTextMap(easyM);
		
		for(int r = 0; r < textmap.length; r++) {
			String row = "";
			for(int c = 0; c < textmap[0].length; c++) {
				row += textmap[r][c] + " ";
			}
			System.out.println(row);
		}
		
		System.out.println(" ");
		
		String easyC = "easyMap.txt";
		String[][] coordmap = readCoordMap(easyC);
		
		for(int r = 0; r < coordmap.length; r++) {
			for(int c = 0; c < coordmap[0].length; c++) {
				System.out.println(coordmap[r][c]);
			}
		}
		
	
	}
}
