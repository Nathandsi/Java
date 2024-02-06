package Window;

import java.io.File;
import java.util.ArrayList;

public class CustomDirectory {
	private File initialFile;
	private Boolean recursivePath = false;
	public int fileCount = 0;
	public int dirCount = 0;
	public ArrayList<File> arrayFiles = new ArrayList<File>();
	
	public CustomDirectory(File directory, Boolean subFolder) {
		this.initialFile = directory;
		this.recursivePath = subFolder;
	}
	
	public void listD() {
		this.listDirectory(this.initialFile);
	}
	
	private void listDirectory(File dir) {
		initialFile = dir;
//		System.out.println("On rentre dans ce dossier pour lister les éléments : " + initialFile.getName());
		File[] files = initialFile.listFiles();
		if (files != null) {
			for (int i = 0; i <files.length; i++) {
				if (files[i].isDirectory() == true) {
	//				System.out.println("Dossier: " + files[i].getAbsolutePath());
					this.dirCount++;
					arrayFiles.add(files[i]);
				} else {
	//			System.out.println("  Fichier: " + files[i].getAbsolutePath());
					this.fileCount++;
					arrayFiles.add(files[i]);
				}
				if (files[i].isDirectory() == true && this.recursivePath == true) {
					this.listDirectory(files[i]);
				}
			}
		}
	}
	
	public ArrayList<File> showFiles() {
		return this.arrayFiles;
	}
	
}
