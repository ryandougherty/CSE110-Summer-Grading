import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class CSE110Summer {
	public static void main(String[] args) throws IOException {
		File input = new File(Directories.getPathToHTMLFile());
		ExtractHTML.extractHTMLFromFile(input);
		/* num times */
		final int ASSIGNMENT_NUM_TIMES = 8, LAB_NUM_TIMES = 20, EXAM_NUM_TIMES = 4, QUIZ_NUM_TIMES = 15;
		/* indexing */
		final int ASSIGNMENT_INDEX = 0, LAB_INDEX = 1, EXAM_INDEX = 2, QUIZ_INDEX = 3;
		/* percentages */
		final double ASSIGNMENT_PERCENTAGE = 30, EXAM_PERCENTAGE = 40, LAB_PERCENTAGE = 15, QUIZ_PERCENTAGE = 15;
		/* total points */
		final int ASSIGNMENT_TOTAL = 20, LAB_TOTAL = 10, EXAM_TOTAL = 100, QUIZ_TOTAL = 10;
		
		final int ASSIGNMENT_STARTING_INDEX = 0;
		final int LAB_STARTING_INDEX = ASSIGNMENT_STARTING_INDEX+ASSIGNMENT_NUM_TIMES;
		final int EXAM_STARTING_INDEX = LAB_STARTING_INDEX+LAB_NUM_TIMES;
		final int QUIZ_STARTING_INDEX = EXAM_STARTING_INDEX+EXAM_NUM_TIMES;
		ArrayList<Integer> numToRemove = new ArrayList<Integer>();
		ArrayList<ArrayList<Double>> grades = new ArrayList<ArrayList<Double>>();
		ArrayList<Double> averages = new ArrayList<Double>();
		File dir = new File(Directories.getPathToGradesFolder());
		if (!dir.exists())
			dir.mkdir();
		for (int i = 0; i<=QUIZ_INDEX; i++) {
			grades.add(new ArrayList<Double>());
		}
		String finalOutput = "";
		try {
			int j = 0;
			numToRemove.add(3); // assignments 
			numToRemove.add(15);// labs
			numToRemove.add(2); // exams
			numToRemove.add(9); // quizzes
			for (File f : dir.listFiles()) {
					double total = 0.0;
					inputGrades(grades.get(ASSIGNMENT_INDEX), ASSIGNMENT_NUM_TIMES, j, ASSIGNMENT_STARTING_INDEX);
					inputGrades(grades.get(LAB_INDEX), LAB_NUM_TIMES, j, LAB_STARTING_INDEX);
					inputGrades(grades.get(EXAM_INDEX), EXAM_NUM_TIMES, j, EXAM_STARTING_INDEX);
					inputGrades(grades.get(QUIZ_INDEX), QUIZ_NUM_TIMES, j, QUIZ_STARTING_INDEX);
					int i = 0;
					for (ArrayList<Double> a : grades) {
						Collections.sort(a);
						removeFromArray(a, numToRemove.get(i));
						averages.add(average(a));
						i++;
					}
					total += ASSIGNMENT_PERCENTAGE*averages.get(ASSIGNMENT_INDEX)/(ASSIGNMENT_TOTAL);
					total += LAB_PERCENTAGE*averages.get(LAB_INDEX)/LAB_TOTAL;
					total += EXAM_PERCENTAGE*averages.get(EXAM_INDEX)/EXAM_TOTAL;
					total += QUIZ_PERCENTAGE*averages.get(QUIZ_INDEX)/QUIZ_TOTAL;
					String grade = evaluateGrade(total);
					String filename = f.getName().replace(".txt", "");
					DecimalFormat format = new DecimalFormat("0.000");
					if (filename.length() < 5)
						filename += "\t";
					if (total < 10.0) 
						grade = " " + grade;
					finalOutput += filename + "   \t" + format.format(total) + "    " + grade + "\n";
					for (ArrayList<Double> a : grades) {
						a.clear();
					}	
					averages.clear();
					j++;
			}
			File file = new File(Directories.getPathToFinalTxtFile());
			BufferedWriter output = new BufferedWriter(new FileWriter(file));
			output.write(finalOutput);
			output.close();
			println("Final.txt successfully written");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void removeFromArray(ArrayList<Double> array, int num) {
		Collections.sort(array);
		if (array.size() > num) {
			for (int i=0; i<num; i++) {
				array.remove(0);
			}
		}
	}

	@SuppressWarnings("unused")
	public static void inputGrades(ArrayList<Double> array, int numTimes, int n, int startingIndex) throws NumberFormatException, IOException {
		File dir = new File(Directories.getPathToGradesFolder());
		Scanner scan;
		try {
			File f = dir.listFiles()[n];
			BufferedReader br = new BufferedReader(new FileReader(f));
			if (br.readLine() != null) {
				scan = new Scanner(f);
				for (int i=0; i<startingIndex; i++) {
					String _ = scan.nextLine();
				}
			
				int numTimes1 = 0;
				double input = Double.parseDouble(scan.nextLine());
				while (numTimes1 < numTimes) {
					array.add(input);
					input = Double.parseDouble(scan.nextLine());
					numTimes1++;
				}
			}
			br.close();
		} finally {
		}
	}
	
	public static String evaluateGrade(double total) {
		if (total >= 97.0) {
			return "A+";
		} else if (total >= 90.0) {
			return "A";
		} else if (total >= 87.0) {
			return "B+";
		} else if (total >= 80.0) {
			return "B";
		} else if (total >= 77.0) {
			return "C+";
		} else if (total >= 70.0) {
			return "C";
		} else if (total >= 60.0) {
			return "D";
		} else {
			return "E";
		}
	}
	
	public static double average(ArrayList<Double> array) {
		double sum = 0.0;
		for (int i = 0; i<array.size(); i++) {
			sum += array.get(i);
		}
		return (double)sum/(double)array.size();
	}
	
	public static boolean isAlpha(String name) {
	    return name.matches("[a-zA-Z]+");
	}
	
	public static <T> void println(T a) {
		System.out.println(a);
	}

	public static <T> void print(T a) {
		System.out.print(a);
	}
}