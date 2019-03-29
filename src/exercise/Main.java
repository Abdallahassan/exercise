package exercise;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Main {
	
	final static String datapath = "data/info.txt";
	final static String logpath = "data/log.txt";
	
	private static String get_timestamp() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()) + ": ";
	}

	public static void main(String[] args) throws IOException {
		File file = new File(datapath);
		File log = new File(logpath);
		if (!log.exists()) {
			log.getParentFile().mkdirs();
			log.createNewFile();
		}
		PrintWriter logwriter = new PrintWriter(new BufferedWriter(new FileWriter(log, true)));
		logwriter.println(get_timestamp() + "Started program.");
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			file.createNewFile();
		} else {
			Scanner s = new Scanner(file);
			System.out.println("Showing old data:");
			while (s.hasNextLine()) {
				System.out.println(s.nextLine());
			}
			logwriter.println(get_timestamp() + "Showed previous data.");
			s.close();
		}
		PrintWriter filewriter = new PrintWriter(file, "UTF-8");
		Scanner s = new Scanner(System.in);
		System.out.println("Write data line for line to save (write :quit to close):");
		logwriter.println(get_timestamp() + "Reading new data.");
		String in;
		while(s.hasNextLine()) {
			in = s.nextLine();
			if (in.equals(":quit")) {
				break;
			} else {
				filewriter.println(in);
			}
		}
		logwriter.println(get_timestamp() + "Finsihed reading new data.");
		filewriter.close();
		s.close();
		logwriter.println(get_timestamp() + "Closing program.");
		logwriter.close();
	}

}
