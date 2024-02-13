package Window;

import java.io.File;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;

public class CustomDirectory {
	private File initialFile;
	private Boolean recursivePath = false;
	public int fileCount = 0;
	public int dirCount = 0;
	public ArrayList<File> arrayFiles = new ArrayList<File>();
	public ArrayList<DefaultMutableTreeNode> arrayNodes = new ArrayList<DefaultMutableTreeNode>();
	NodeInfo infos;
	
	public CustomDirectory(File directory, Boolean subFolder) {
		this.initialFile = directory;
		this.recursivePath = subFolder;
	}
	
	public void listD(CustomDirectory dir) {
		this.listDirectory(dir);
	}
	
	public void listDirectory(CustomDirectory dir) {
		initialFile = getFile(dir);
		
//		System.out.println("On rentre dans ce dossier pour lister les éléments : " + initialFile.getName());
		File[] files = initialFile.listFiles();
		if (files != null) {
			for (File f : files) {
				if (f.isDirectory() == true) {
	//				System.out.println("Dossier: " + f.getAbsolutePath());
					this.dirCount++;
					DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(new NodeInfo(f.getName(), (String) f.getPath(), true));
					arrayNodes.add(tempNode);
					arrayFiles.add(f);
				} else {
	//			System.out.println("  Fichier: " + f.getAbsolutePath());
					this.fileCount++;
					DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(new NodeInfo(f.getName(), (String) f.getPath(), false));
					arrayNodes.add(tempNode);
					arrayFiles.add(f);
				}
				if (f.isDirectory() == true && this.recursivePath == true) {
					CustomDirectory tempFile = new CustomDirectory(f, true);
					listD(tempFile);
				}
			}
		}
	}
	
	
	public File getFile(CustomDirectory dir) {
		return dir.initialFile;
	}
	
	public ArrayList<File> showFiles() {
	//	System.out.println("nombre de dossiers : " + this.dirCount);
	//	System.out.println("nombre de fichiers : " + this.fileCount);
		return this.arrayFiles;
	}
	
	public ArrayList<DefaultMutableTreeNode> showNodes(){
		return this.arrayNodes;
	}
	
}
