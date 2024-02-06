package Window;

import java.io.File;

public class CustomDirectory {
	private File initialFile;
	private Boolean recursivePath = false;
	public int fileCount = 0;
	public int dirCount = 0;
	
	public CustomDirectory(File directory, Boolean subFolder) {
		this.initialFile = directory;
		this.recursivePath = subFolder;
	}
	
	public void list() {
		this.listDirectory(this.initialFile);
	}
	
	private void listDirectory(File dir) {
		initialFile = dir;
		File[] files = initialFile.listFiles();
		if (files != null) {
			for (int i = 0; i <files.length; i++) {
				if (files[i].isDirectory() == true) {
					System.out.println("Dossier: " + files[i].getAbsolutePath());
					this.dirCount++;
				} else {
					System.out.println("  Fichier: " + files[i].getAbsolutePath());
					this.fileCount++;
				}
				if (files[i].isDirectory() == true && this.recursivePath == true) {
					this.listDirectory(files[i]);
				}
			}
		}
	}
	
}
