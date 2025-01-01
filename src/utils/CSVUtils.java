package utils;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class CSVUtils {	
	private static void ensureFileExists(String filePath) {
	    File file = new File(filePath);
	    if (!file.exists()) {
	        try {
	            file.createNewFile(); 
	        } catch (IOException e) {
	            System.err.println("Could not create file: " + filePath);
	            e.printStackTrace();
	        }
	    }
	}
	
    public static List<String[]> readFromCSV(String filePath) {
    	ensureFileExists(filePath);
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                data.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }


    public static void writeToCSV(String filePath, List<String[]> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] row : data) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
