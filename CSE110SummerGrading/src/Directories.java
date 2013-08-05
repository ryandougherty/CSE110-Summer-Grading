public class Directories {
	private final static String WINDOWS_DESKTOP = System
			.getProperty("user.dir");
	private final static String WINDOWS_DESKTOP_GRADE_FOLDER = System
			.getProperty("user.dir") + "/grades/";
	private final static String WINDOWS_DESKTOP_CSE110_HTML_FILE = System
			.getProperty("user.dir") + "/CSE110.html";
	private final static String WINDOWS_DESKTOP_FINAL_TXT_FILE = System
			.getProperty("user.dir") + "/final.txt";
	private final static String MAC_DESKTOP = System.getProperty("user.dir");
	private final static String MAC_DESKTOP_GRADE_FOLDER = System.getProperty("user.dir") + "/grades/";
	private final static String MAC_DESKTOP_CSE110_HTML_FILE = System.getProperty("user.dir") + "/CSE110.html";
	private final static String MAC_DESKTOP_FINAL_TXT_FILE = System.getProperty("user.dir") + "/final.txt";

	public static String getPathToDesktop() {
		String osName = System.getProperty("os.name");
		if (osName.startsWith("Windows")) {
			return WINDOWS_DESKTOP;
		} else {
			return MAC_DESKTOP;
		}
	}

	public static String getPathToGradesFolder() {
		String osName = System.getProperty("os.name");
		if (osName.startsWith("Windows")) {
			return WINDOWS_DESKTOP_GRADE_FOLDER;
		} else {
			return MAC_DESKTOP_GRADE_FOLDER;
		}
	}

	public static String getPathToHTMLFile() {
		String osName = System.getProperty("os.name");
		if (osName.startsWith("Windows")) {
			return WINDOWS_DESKTOP_CSE110_HTML_FILE;
		} else {
			return MAC_DESKTOP_CSE110_HTML_FILE;
		}
	}

	public static String getPathToFinalTxtFile() {
		String osName = System.getProperty("os.name");
		if (osName.startsWith("Windows")) {
			return WINDOWS_DESKTOP_FINAL_TXT_FILE;
		} else {
			return MAC_DESKTOP_FINAL_TXT_FILE;
		}
	}
}