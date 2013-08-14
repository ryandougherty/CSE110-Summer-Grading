import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ExtractHTML {
	public static void extractHTMLFromFile(File filepath) throws IOException {
		File dir = new File(Directories.getPathToGradesFolder());
		if (!dir.exists())
			dir.mkdir();
		ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
		
		Document doc = Jsoup.parse(new File(Directories.getPathToHTMLFile()), "UTF-8");
		for (Element element : doc.select("tr")) {
			StringBuilder str = new StringBuilder();
			for (Element td : element.select("td")) {
				str.append(td.text() + " ");
			}
			array.add(new ArrayList<String>(Arrays.asList(str.toString().split("\n"))));
		}
		for (ArrayList<String> arr : array) {
			Object[] array1 = arr.toArray();
			String string = array1.toString();
			array1 = string.split(" ");
			array.set(0, new ArrayList<String>(Arrays.asList((String[])array1)));
		}
		for (ArrayList<String> arr : array) {
			String stringToWrite = "";
			for (String str : arr) {
				str = str.replace(" ", "\n");
				stringToWrite += str + "\n";
			}
			String filename = "";
			if (Character.isLetter(stringToWrite.charAt(0))) {
				String firstname = stringToWrite.substring(stringToWrite.indexOf("\n")+1);
				filename = firstname.substring(0, firstname.indexOf("\n"));
			}
			for (int i=0; i<4; i++) {
				stringToWrite = stringToWrite.substring(stringToWrite.indexOf('\n')+1);
			} try {
				File file = new File(Directories.getPathToGradesFolder() + filename + ".txt");
				BufferedWriter output = new BufferedWriter(new FileWriter(file));
				output.write(stringToWrite);
				if (file.getName().equals(".txt") || file.getName().startsWith("[")) {
					file.delete();
				}
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static boolean isAlpha(String name) {
	    return name.matches("[a-zA-Z]+");
	}
}
