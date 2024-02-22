package Window;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class CustomDirectory {
	private File initialFile;
	private Boolean recursivePath = false;
	public int fileCount = 0;
	public int dirCount = 0;
	public int fileInDir = 0;
	public ArrayList<File> arrayFiles = new ArrayList<File>();
	public ArrayList<DefaultMutableTreeNode> arrayNodes = new ArrayList<DefaultMutableTreeNode>();
	
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
				
				// In case of a directory that has child :
				if (f.isDirectory() == true) {
					this.dirCount++;
					int nbrChild = 0;
					File[] tabFile = f.listFiles();
					for (File file : tabFile) { nbrChild += 1; }
					DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(new NodeInfo(f.getName(), (String) f.getPath(), f.getParentFile().getName(), true, nbrChild, false));
					arrayNodes.add(tempNode);
					arrayFiles.add(f);
					
				// In case of an empty directory :
				} else if (f.isDirectory() == true && f.listFiles() == null) {
					this.dirCount++;
					DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(new NodeInfo(f.getName(), (String) f.getPath(), f.getParentFile().getName(), true, 0, true));
					arrayNodes.add(tempNode);
					arrayFiles.add(f);
					
				// In case of a file (not a directory) : 
				} else {
					this.fileCount++;
					DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(new NodeInfo(f.getName(), (String) f.getPath(), f.getParentFile().getName(), false, 0, true));
					arrayNodes.add(tempNode);
					arrayFiles.add(f);
				}
				
				// In case of a directory is not empty then we set the recusivity to true
				if (f.isDirectory() == true && f.listFiles() != null ) {
					this.recursivePath = true;
					if (f.isDirectory() == true && this.recursivePath == true) {
						CustomDirectory tempFile = new CustomDirectory(f, true);
						listD(tempFile);
					}
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
