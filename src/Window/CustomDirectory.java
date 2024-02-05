package Window;

import java.io.File;

public class CustomDirectory {
	private String initialPath = "";
	private Boolean recursivePath = false;
	public int fileCount = 0;
	public int dirCount = 0;
	
	public CustomDirectory(String path, Boolean subFolder) {
		super();
		this.initialPath = path;
		this.recursivePath = subFolder;
	}
	
	public void list() {
		this.listDirectory(this.initialPath);
	}
	
	private void listDirectory(String dir) {
		File file = new File(dir);
		File[] files = file.listFiles();
		if (files != null) {
			for (int i = 0; i <files.length; i++) {
				if (files[i].isDirectory() == true) {
					System.out.println("Dossier: " + files[i].getAbsolutePath());
					this.dirCount++;
				} else {
					System.out.println("  Fichier: " + files[i].getName());
					this.fileCount++;
				}
				if (files[i].isDirectory() == true && this.recursivePath == true) {
					this.listDirectory(files[i].getAbsolutePath());
				}
			}
		}
	}
	
}
