package logging;

import java.io.*;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import element.Tile;

public final class Logging {
	private Logging() {
	}

	private static final String Filename = "log.txt";

	/**
	 * X and Y coordinate tiles that were cleaned
	 */
	public static void logCleaning(int x, int y) {
		String message = " Cleaned tiles: (" + x + ", " + y + ")";
		writeToFile(Filename, message);
	}

	/**
	 * Logs discovered floor plan by the clean sweep
	 */
	public static void logDiscoveredCell(String cell) {
		writeToFile(Filename, cell);
	}
	/**
	 * Date and Time
	 */
	public static void dateofMovement(String CurrentDate) {
        Calendar date = Calendar.getInstance();
        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH)+1;
        int day = date.get(Calendar.DAY_OF_MONTH);

        CurrentDate="";
  
            CurrentDate += year + "" + month + "" + day;
        }

	/**
	 * Current tile location showing with X and Y coordinate
	 */
	public static void logMovement(int x, int y) {
		String message = "Current location at tile: " + x + ", " + y;
		writeToFile(Filename, message);
	}

	public static void logMovement(Tile t) {
		String message = "Current location at tile: " + t.getX() + ", " + t.getX();
		writeToFile(Filename, message);
	}

	public static void logPath(Tile t) {
		String message = "Shortest Path: " + t.getX() + ", " + t.getX();
		writeToFile(Filename, message);
	}

	public static void logStart() {
		String message = "CleanSweep has started.";
		writeToFile(Filename, message);
	}

	public static void logFinish() {
		String message = "CleanSweep has finished.";
		writeToFile(Filename, message);
	}

	public static void logRecharge() {
		String message = "CleanSweep has finished recharging.";
		writeToFile(Filename, message);
	}

	public static void logReturn() {
		String message = "CleanSweep is currently returning to charging station.";
		writeToFile(Filename, message);
	}

	public static void logPath(List<Tile> path) {
		for (Tile t : path) { Logging.logMovement(t);
		}
	}


	private static void writeToFile(String fileName, String message) {
		FileWriter fw;
		BufferedWriter bw = null;

		try {
			createDirectory();
			File outputFile = new File("tracking", fileName);

			if (outputFile.createNewFile()) {
				System.out.println("New file created: " + fileName);
			}

			fw = new FileWriter(outputFile.getPath(), true);
			bw = new BufferedWriter(fw);

			System.out.println(message);
			bw.write(message);
			bw.write(System.getProperty("line.separator"));
		} catch (IOException e) {
			System.out.println("Error when attempting to write to file: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (Exception ex) {
			}
		}
	}

	private static void createDirectory() {
		File trackingDirectory = new File("tracking");

		// creates new directory if there no directory
		if (!trackingDirectory.exists()) {
			trackingDirectory.mkdir();
			System.out.println("tracking directory created");
		}
	}
}
