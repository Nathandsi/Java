package Window;
// Imports
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.tree.DefaultMutableTreeNode;

// Class
public class Window extends JFrame implements ActionListener, WindowListener, ProcessFile{
	// Properties
	private static final long serialVersionUID = 1L;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();   //  Get screen size as Dimension object.
	private JToolBar toolBar;
	private ImageIcon icon;
	private boolean isFullWindow;  // should have fullscreen or not
	private boolean isUnDecorated;  //  should have (close, resize, reduce button) -> false / or should not -> true (will remove those buttons)
	private int h;  // height
	private int w; // weight
	private Color backgroundColor;
	private String closeOperation;  //  string that represents the state of the closing operation, useful for the defineCloseOperation() method.
	private String rootPath = "C:\\Users\\ndurand\\eclipse-workspace\\JavaTraining\\src\\UserFiles";
	private File rootFile = new File(rootPath);
	public ArrayList<File> arrayFile = new ArrayList<File>();
	private ArrayList<String> nameFiles = new ArrayList<>();
	private ArrayList<String> pathFiles = new ArrayList<>();
	private int nbrChildInDir;
	
	private DefaultMutableTreeNode tempNode;
	private DefaultMutableTreeNode rootNode;
	private ArrayList<DefaultMutableTreeNode> arrayNodes = new ArrayList<DefaultMutableTreeNode>();
	private ArrayList<DefaultMutableTreeNode> tempNodeList = new ArrayList<DefaultMutableTreeNode>();
	private JTree tree = new JTree(rootNode, true);
	private File[] tabTempFiles;
	private int tempNbr;
	private DefaultMutableTreeNode containerNode;
	private ArrayList<File> tableauFiles = new ArrayList<File>();
	private ArrayList<File> orderedListFiles = new ArrayList<File>();
	private ArrayList<ArrayList<File>> contentLevel = new ArrayList<ArrayList<File>>();
	private File[] tempArrayFile;
	private ArrayList<Integer> nbrFilesInLevel = new ArrayList<Integer>();
	
	public ArrayList<DefaultMutableTreeNode> tabNodes = new ArrayList<DefaultMutableTreeNode>();
	
	public ArrayList<DefaultMutableTreeNode> tNodes = new ArrayList<DefaultMutableTreeNode>();
	
	// private JComboBox<String> selectionList = new JComboBox<String>();
	
	
	// Constructor
	public Window(boolean isFullWindow, int w, int h, Color backColor, boolean isUnDecorated, String closeOperation) {
		//   -----   Try to apply Look and Feel   -----
		try {UIManager.setLookAndFeel(new NimbusLookAndFeel());} catch (UnsupportedLookAndFeelException e) {
			System.out.println("Unsupported Look and Feel");
			e.printStackTrace();
		}
		
		// Counts the files whithin the root file (none recursive, only the root directory)
		File[] tabTempFiles = rootFile.listFiles();
		for (File tempFile : tabTempFiles) {
			nbrChildInDir += 1;
		}
		// creates the root node 
		rootNode = new DefaultMutableTreeNode(new NodeInfo(rootFile.getName(), rootFile.getPath(), rootFile.getParentFile().getName(), true, nbrChildInDir, false));
		
		// Sets properties
		setFullWindow(isFullWindow);
		setW(w);
		setH(h);
		setBackgroundColor(backColor);
		setUnDecorated(isUnDecorated);
		setCloseOperation(closeOperation);
		checkSize();
		defineCloseOperation();
		// Defines contentPane
		JPanel contentPane = (JPanel)this.getContentPane();
		contentPane.setLayout(new BorderLayout());
		// MenuBar creation
		this.setJMenuBar(createJMenuBar());
		// JTree creation

		tempNbr = rootNode.getChildCount();
		tabTempFiles = rootFile.listFiles();
		
		for (File fichier : tabTempFiles) {
			tableauFiles.add(fichier);
			tempNodeList.add(convertFileToNode(fichier));
		}
	//	System.out.println(tempNodeList);
		
		
	//	Tree tree = new Tree(containerNode, true);
		
		
//		listDirectory(rootFile.listFiles());
//		arrayFile.stream().map(element -> element.getAbsolutePath()).forEach(System.out::println);
		
		if (rootFile.list() != null) {
			/*
			
			// CustomDirectory creation, from the source folder (rootFile), with recursivity set to "true"
			CustomDirectory customDir = new CustomDirectory(rootFile, true);
			// Invoking the "listD()" method from the "CustomDirectory" class that lists all the elements from the folder
			customDir.listD(customDir);
			// Invoking the "showFile()" method that return an ArrayList, streamed and mapped to only keep the name, then println each line (that represents an element)
		    //	 customDir.showFiles().stream().map(element -> element.getName()).forEach(System.out::println);
			
//			customDir.showFiles().stream().map(element -> element.getName()).forEach(DefaultMutableTreeNode::new);
			customDir.showFiles().stream().forEach((element) -> {
				arrayFile.add(element);
				});
			customDir.showNodes().stream().forEach((element) -> {
				arrayNodes.add(element);
				});
			
			
	//	customDir.showNodes().stream().map(element -> element.getUserObject()).forEach(System.out::println);
			ArrayList<Object> myList = new ArrayList<Object>();
			customDir.showNodes().stream().map(element -> element.getUserObject()).forEach( n -> {myList.add(n);});
		//	myList.stream().forEach(System.out::println);
		//	customDir.showFiles().stream().map(element -> element.getParentFile().getName() + " -> " + element.getName()).forEach(System.out::println);
			
		//	System.out.println(arrayNodes);
			
// ********			orderingNodes(arrayFile);
			
		//	searchForDir(rootFile);
			
			
			ArrayList<Object> tableauDeFile = new ArrayList<Object>();
			// Creates and fill an ArrayList<Object> with an ArrayList<File>
			for (File f : arrayFile) {
				tableauDeFile.add(f);
			}
			
			Object[] tableauFile = convertArrayListObjectToArrayObject(tableauDeFile);

			ArrayList<String> tableauDeString;
			
			for (Object f : tableauFile) {
				System.out.println(((File)f).getName() + " --> " + ((File)f).getParentFile());
				
			}
			
			System.out.println(" ");
			
			File[] resultProcessFiles;
			File[] processFiles = convertArrayListFileToArrayFile(arrayFile);
			
			resultProcessFiles = processSiblings(processFiles);
			
			
			switch (processAgain(resultProcessFiles)) {
			case "UNDEFINED" : break;
			case "0" : break;
			case "1" : System.out.println("WE FOUND THE ONE"); break;
			case "2" : System.out.println("WE NEED TO DEAL WITH THOSE"); tempArrayFile = processSiblings(resultProcessFiles); break;
			default : break;
			}
			
			System.out.println(tempArrayFile.length);
			for (File f : tempArrayFile) {
				System.out.println(f.getName());
			}
		//	process(rootFile); .map(f -> f.getName())
			*/
			gettingLevels(rootFile);
			
			contentLevel.stream().forEach((System.out::println));
			
			// contentLevel.stream().forEach(e -> e.stream().forEach(System.out::println));
			
			// File[] tabFi = contentLevel.stream().forEach(g -> g.stream().forEach(() -> convertArrayListFileToArrayFile(e)));
			
			
			
			
			int treeSize = contentLevel.size();
			
			System.out.println("TREE SIZE ----->  " + treeSize);
			
			ArrayList<DefaultMutableTreeNode> tabNodes = new ArrayList<DefaultMutableTreeNode>();
			
			System.out.println("********************************************************");
			
			for (ArrayList<File> e : contentLevel) {
				System.out.println(getNodes(e));
				tabNodes.add(getNodes(e));
				System.out.println("////////////////////////////////////////////////////////");
			}
			DefaultMutableTreeNode[] tabNode = convertArrayListNodeToArrayNode(tabNodes);
			System.out.println("+++++++++++++++++++++++++++++++++");
			
			
			
//			for (int i = 5; i <= 5; i++) {
//				System.out.println("WWWWWWWWWWWWWWWWWWWWWWWWWW");
//			}
			
			
			
			System.out.println(tabNode.length);
			
			DefaultMutableTreeNode[] tableauNode = tabNode;
			
//			for (int i = 0; i <= tabNode.length - 1; i++) {
//				for  (int j = tabNode.length - 1; j >= 0; j--) {
//					System.out.println("i = " + i);
//					System.out.println("j = " + j);
//					System.out.println("tableauNode[i] = " + tableauNode[i]);
//					System.out.println("tableauNode[j] = " + tableauNode[j]);
//					System.out.println("tabNode[i] = " + tabNode[i]);
//					System.out.println("tabNode[j] = " + tabNode[j]);
//					System.out.println("-----  CONVERTION ICI  -----");
//					tableauNode[j] = tabNode[i];
//					System.out.println("-----FIN DE CONVERTION ICI-----");
//					System.out.println("i = " + i);
//					System.out.println("j = " + j);
//					System.out.println("tableauNode[i] = " + tableauNode[i]);
//					System.out.println("tableauNode[j] = " + tableauNode[j]);
//					System.out.println("tabNode[i] = " + tabNode[i]);
//					System.out.println("tabNode[j] = " + tabNode[j]);
//				}
//			}
//			for (DefaultMutableTreeNode node : tableauNode) {
//				System.out.println(node);
//			}
			
			for (ArrayList<File> arrayNode : contentLevel) {
				System.out.println(arrayNode);
			}
			System.out.println("BEFORE CONVERTION");
			reverseList(contentLevel);
			System.out.println("AFTER CONVERTION");
			for (ArrayList<File> arrayNode : contentLevel) {
				System.out.println(arrayNode);
			}
			
			
			ArrayList<DefaultMutableTreeNode> theRootNode = getTheNode(contentLevel);
			
			System.out.println("5555555555555555555555555555555555555555555555555555");
			System.out.println(theRootNode);
			
			/*
			DefaultMutableTreeNode[] arrayRootNode = convertArrayListNodeToArrayNode(theRootNode);
			
			for (int i = 0; i < arrayRootNode.length; i++) {
				if (i >= arrayRootNode.length) {
					
				} else if (i < arrayRootNode.length -1) {
					arrayRootNode[i+1].add(arrayRootNode[i]);
				}

			}
			*/
			
			for (DefaultMutableTreeNode n : theRootNode) {
				System.out.println("parent : " + n.getUserObject());
				if (n.getUserObject() != null) {
					addChildToParentNode((DefaultMutableTreeNode)n.getParent(), n);
				}
			}
			DefaultMutableTreeNode[] arrayRootNode = convertArrayListNodeToArrayNode(theRootNode);
			for (int i = 0; i < arrayRootNode.length; i++) {
				System.out.println("***" + arrayRootNode[i].getChildCount());
			}
			
			
			JTree theTree = new JTree(arrayRootNode);
			
			this.add(theTree);
			
			

			
		//	convertArrayNodeToArrayListNode(tabNode).stream().forEach(System.out::println);
		//	System.out.println("00000000000000000000000000000000000000000");
		//	convertArrayNodeToArrayListNode(tableauNode).stream().forEach(System.out::println);

		}

//		arrayNodes = getNodesFromDir(rootFile);
//		arrayNodes.stream().map(e -> e.toString()).forEach(System.out::println);

	}
	
//	public DefaultMutableTreeNode createRootNode(ArrayList<DefaultMutableTreeNode> arrayNodes) {
//		
//		for (DefaultMutableTreeNode n : arrayNodes){
//			((DefaultMutableTreeNode) n.getParent()).add(n);
//		}
//		return arrayNodes.get(arrayNodes.size()-1);
//	}
	
	public DefaultMutableTreeNode addChildToParentNode(DefaultMutableTreeNode parent, DefaultMutableTreeNode child) {
		if (parent != null) {
			parent.add(child);
		}
		return parent;
	}
	
	public ArrayList<DefaultMutableTreeNode> getTheNode(ArrayList<ArrayList<File>> BigContent) {
		
		DefaultMutableTreeNode parentNode = null;
		
		for (ArrayList<File> arrayNode : BigContent) {
			// Creates the userObject parentNodeInfo that will be use to create the parent Node
			NodeInfo parentNodeInfo = new NodeInfo(
					arrayNode.get(0).getParentFile().getName(),  // get the parent's name
					arrayNode.get(0).getParentFile().getPath(),   // get the parent's url
					arrayNode.get(0).getParentFile().getParentFile().getName(), // get the granparent's name
					true, // is it a directory
					nbrFiles(arrayNode.get(0).getParentFile()), // get thet number of files in the parent, equals to the number of siblings of the element in arrayNode.get(0)
					isLast(arrayNode.get(0).getParentFile())  // check if the parent is at end (impossible as it is a parent here)
			);
			// Creates a parent node with the parentNodeInfo userObject
			parentNode = new DefaultMutableTreeNode(parentNodeInfo);
			System.out.println("parent Node Creation with parentNodeInfo = " + parentNode);
			// for each file in the ArrayList arrayNode,
			for (File f : arrayNode) {
				// Creates a userObject corresponding to the file
				NodeInfo infoNode = new NodeInfo(
						f.getName(),
						f.getPath(), 
						f.getParentFile().getName(),
						f.isDirectory(),
						nbrFiles(f),
						isLast(f)
				);
				// Creates a Node with the infoNode userObject
				DefaultMutableTreeNode Node = new DefaultMutableTreeNode(infoNode);
				System.out.println("Node Creation with NodeInfo = " + Node);
				// Adds the Node to the parent node
				parentNode.add(Node);
				System.out.println("NOW WE HAVE ADDED THE NODE TO THE PARENT NODE --> PARENT NODE  : " + parentNode);
				
			}
			
			System.out.println("tNode before : " + tNodes);
			// Adds the parent node to an array tNodes
			tNodes.add(parentNode);
			System.out.println("tNode after : " + tNodes);
		}
		
		return tNodes;
	}
	
	public static <T> void reverseList(List<T> list) {
		// Base condition when the list size is 0
		if (list.size() <= 1 || list == null) {
			return;
		}
		T value = list.remove(0);
		// Call the recursive function to reverse the list after removing the first element
		reverseList(list);
		// Now after the rest of the list has been reversed by the upper recursive call, add the first value to the end
		list.add(value);
	}
	
	
	public DefaultMutableTreeNode getNodes(ArrayList<File> contentArray) {
		// Get the first element to obtain access to the parent file in order to define the parent node.
		File fichier = contentArray.get(0);
		File parentFile = fichier.getParentFile(); 
		System.out.println(fichier);
		// Creates the parent node.
		DefaultMutableTreeNode theNode = createNode(parentFile);
	//	System.out.println("THE NODE : --> :" + theNode.toString());
		// For each file in the ArrayListy<File> we create a node with related params and then we add it to the parent node.
		for (File f : contentArray) {
			DefaultMutableTreeNode tempNode = createNode(f);
	//		System.out.println("TEMPNODE : --> :" + tempNode.toString());
			theNode.add(tempNode);
		}
		return theNode;
	}
	
	public String processAgain(File[] tabFile) {
		if (tabFile != null) {
			if (tabFile.length == 0) {System.out.println("THE RESULT IS UNDEFINED"); return "0";}
			if (tabFile.length == 1) {System.out.println("THE RESULT IS THE ONLY ELEMENT IN THE ARRAY"); return "1";}
			if (tabFile.length > 1) {System.out.println("WE GOT MULTIPLE RESULTS, NOW WE NEED TO SEND THEM TO PROCESS"); return "2";}
		}
		return "UNDEFINED";
	}
	
	public int nbrSiblings(File file) {
		return nbrFiles(file.getParentFile());
	}
	
	
	private JTree orderingNodes(ArrayList<File> listFiles) {
		int level = 0;
		JTree tree = new JTree();
		File[] tabFiles = listFiles.toArray(new File[listFiles.size()]);
		DefaultMutableTreeNode[] tabNodes;
		File[] otherTabFiles = new File[listFiles.size()];
		int j = 0;
		for (int i = tabFiles.length - 1; i >= 0 ; i--) {
			
			System.out.println("i = " + i + " -> " + tabFiles[i]);
			otherTabFiles[j] = tabFiles[i].getParentFile();
			System.out.println("j = " + j + " -> " + otherTabFiles[j]);
			j = j + 1;
			
			//  In case we found an empty directory
			if (tabFiles[i].isDirectory() == true && tabFiles[i].listFiles().length == 0) {
					System.out.println("***** EMPTY DIRECTORY -> " + tabFiles[i].getName());
			}
			if (tabFiles[i].isDirectory() == false) {
				System.out.println("***** FILE -> " + tabFiles[i].getName());
			}
			
			
			
		}
		
		
		
		
		
		
		return tree;
	}
	
	
	public void comparePath(ArrayList<File> fileList) {
		
	}
	
	// Returns true if the file and the node passed in param share the same name, path and parent name.
	public boolean compareFileToNode(File file, DefaultMutableTreeNode node) {
		NodeInfo info = (NodeInfo) node.getUserObject();
		if (file.getName() == info.getNodeName() && (String) file.getPath() == info.getNodePath() && file.getParentFile().getName() == info.parent()) {
			return true;
		}
		return false;
	}
	
	public DefaultMutableTreeNode processDirChildren(File theDirectory) {
		int nbrFiles = nbrFilesInDir(theDirectory);
		int nbrDir = nbrDir(theDirectory);
		
		// The case where the directory does not contain another directory.
		if (nbrDir == 0) {
			File[] tabFiles = theDirectory.listFiles();
			ArrayList<DefaultMutableTreeNode> arrayNode = new ArrayList<DefaultMutableTreeNode>();
			for (File f : tabFiles) { arrayNode.add(createNode(f)); }
			DefaultMutableTreeNode parentNode = createNode(theDirectory.getParentFile());
			for (DefaultMutableTreeNode node : arrayNode) { parentNode.add(node); }
			return parentNode;
		// The case where there is another directory inside theDirectory passed as param.
		} else if (nbrDir != 0) {
			
		}
		
		// for now
		return null;
	}
	
	public File exploreDirectory(File fichier, boolean recursivity) {
		
		File initialFile = fichier;
		boolean recurs = recursivity;
		
		int nbrChild = 0;
		
		File[] tabFiles = initialFile.listFiles();
		
		
		if (tabFiles != null) {
			for (File f : tabFiles) {
				// In case of a directory that has child :
				if (f.isDirectory() == true) {
					File[] tabFile = f.listFiles();
					for (File file : tabFile) { nbrChild += 1; }
					DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(new NodeInfo(f.getName(), (String) f.getPath(), f.getParentFile().getName(), true, nbrChild, false));
					arrayNodes.add(tempNode);
					
				// In case of an empty directory :
				} else if (f.isDirectory() == true && f.listFiles() == null) {
					DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(new NodeInfo(f.getName(), (String) f.getPath(), f.getParentFile().getName(), true, 0, true));
					arrayNodes.add(tempNode);
					
				// In case of a file (not a directory) : 
				} else {
					DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(new NodeInfo(f.getName(), (String) f.getPath(), f.getParentFile().getName(), false, 0, true));
					arrayNodes.add(tempNode);
				}
				
				// In case of a directory is not empty then we set the recusivity to true
				if (f.isDirectory() == true && f.listFiles() != null ) {
			//		this.recursivePath = true;
			//		if (f.isDirectory() == true && this.recursivePath == true) {
			//			CustomDirectory tempFile = new CustomDirectory(f, true);
			//			listD(tempFile);
			//		}
				}
			}
		}
		
		return null;
		
	}
	
	public boolean hasSeveralDir(File dir) {
		if (nbrDir(dir) > 1) {
			return true;
		}
		return false;
	}
	
	public File searchForDir(File file) {
		int nbrDirectory = nbrDir(file);
		System.out.println("nombre de dossiers dans " + file + " est de " + nbrDirectory);
		// We get the directories that has at least on child.
		File[] tabDir = getListDir(file);

	//	System.out.println("Taille du tableau de files : " + tabDir.length );
		System.out.println("we get the content from " + file.getName() + " and list an array with its elements");
		if (tabDir != null) {
			for (File f : tabDir) {
				searchForDir(f);
			}
		} else {
			System.out.println("we just found an empty array from " + file.getName() + " so this should be a file or empty directory at the end. Is it ?");
			System.out.println("RETURN -> " + file.getName());
			return file;
		}
	System.out.println("IF YOU SEE THIS IT RETURNS NULL");
		return null;
	}
	
	
	// Function that checks if the File given as param does have at least one child.
	public boolean hasChild(File file) {
		// In case of the File as param is not a directory, return false.
		if (!file.isDirectory()) {return false;}
		// Otherwise we need to check if the directory is empty or not.
		if (isEmptyDir(file)) {return false;}
		// In case we get to this point, it means that we are dealing with a directory that has, at least, one child.
		return true;
	}
	
	
	public DefaultMutableTreeNode processFileToNode(File child) {
		// Gets the parent of the file
		File parent = child.getParentFile();
		// Turns it into a node
		DefaultMutableTreeNode nodeParent = createNode(parent);
		// Turns the File into a node
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(new NodeInfo(child.getName(), (String) child.getPath(), parent.getName(), child.isDirectory(), nbrFilesInDir(child), isLast(child)));
		// Adds the child node to the parent node
		nodeParent.add(node);
		// Returns the parent as a node containing the child node.
		return nodeParent;
	}
	
	// Creates and returns a DefaultMutableTreeNode from a File given as param.
	public DefaultMutableTreeNode createNode(File fichier) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(new NodeInfo(fichier.getName(), (String) fichier.getPath(), fichier.getParentFile().getName(), fichier.isDirectory(), nbrFilesInDir(fichier), isLast(fichier)));
		return node;
	}
	
	
	public boolean isLast(File file) {
		if (getList(file) == null || isEmptyDir(file) == true) {
			return true;
		} else { return false; }
	}
	
	// Returns an array containing the siblings of the file given as param
	public File[] getSiblings(File file) {
		File parent = file.getParentFile();
		File[] siblings = getList(parent);
		return siblings;
		}
	
	// Returns an array of siblings filtered by elements that are not last element
	public File[] checkLastSiblings(File[] tabFile) {
		ArrayList<File> tableauDeFile = new ArrayList<File>();;
		for (File f : tabFile) { if (isLast(f)) { } else { tableauDeFile.add(f);} }
		ArrayList<Object> listObj = new ArrayList<Object>();
		for (File f : tableauDeFile) { listObj.add(f); }
		return ((File[]) convertArrayListObjectToArrayObject(listObj));
	}
	
	// Processing the siblings that are not last element, returns the file that is at last position in the hierarchy
	public File[] processSiblings(File[] tabSiblings) {
	//	ArrayList<Object> listObj = new ArrayList<Object>();
		File tempElement = new File("undefined");
		ArrayList<File> stepTable = new ArrayList<File>();
		// ArrayList that represents the files that are not last element.
		ArrayList<File> files = new ArrayList<File>();
		// For each File, from the array given as param,
		for (File f : tabSiblings) {
			// creates an array of File that lists the files present in it
			File[] tabFiles = getList(f);
			// and for each of thoses files, check if it's last element, 
			if (tabFiles != null) {
				for (File g : tabFiles) {
					// in case it isn't, we add it to the array "files" representing the files that are not last element.
					 if (isLast(g)) { tempElement = g; } else {files.add(g);}
				}
			}
		}
		// Checks if the array "files" is empty, has one, or has more elements
		if (files.size() == 0) {
			System.out.println("We did not get any element that is not last element, we should RETURN the last tempElement file");
			stepTable.add(tempElement);
			return convertArrayListFileToArrayFile(stepTable);
		} else if (files.size() == 1) {
			System.out.println("We got only one element that is last element, we should RETURN this element");
			return convertArrayListFileToArrayFile(files);
		} else {
			System.out.println("We got several elements that are not last element, we RETURN these elements, we need to process them by sending them to previous steps");
			return convertArrayListFileToArrayFile(files);
		}
	}
	
	
	
	public void gettingLevels(File directory) {
		// If the directory has children
		if (getList(directory) != null && getList(directory).length != 0){
			// We get the number of files in the directory and adds it to nbrFilesInLevel
			Integer nbrFiles = getList(directory).length;
			nbrFilesInLevel.add(nbrFiles);
			// We get the list from the directory as File[], we convert it into an ArrayList<File> and we add this ArrayList to the contentLevel.
			contentLevel.add(convertTabToArrayList(getList(directory)));
		}
		// Gets an array with only the directories (no files)
		File[] listDir = getListDir(directory);
		
		ArrayList<File> arrayListDeDir = new ArrayList<File>();
		for (File f : listDir) {
			arrayListDeDir.add(f);
		}
	//	arrayListDeDir.stream().map(e -> e.getName()).forEach(System.out::println);

			for (File f : listDir) {
				System.out.println(f.getName());				
				processLevel(f);
			}
		
//		for (ArrayList<File> e : contentLevel) {
//			e.stream().map(f -> f.getName()).forEach(System.out::println);
//		}
//		for (Integer i : nbrFilesInLevel) {
//			System.out.println(i);
//		}
		
	}
	
	public void processLevel(File parent) {
		File[] files = getList(parent);
		ArrayList<File> arrayFiles = new ArrayList<File>();
		arrayFiles = convertTabToArrayList(files);
		
		contentLevel.add(arrayFiles);
		

		File[] tableauDir = getListDir(parent);
		for (File f : tableauDir) {
		//	System.out.println("WE FOUND A DIRECTORY: " + f.getName() + ", WE SEND IT TO  -->  gettingLevels() to be processed");
			gettingLevels(f);
		}

		
	}
	
	public File[] getFilesOnly(File file) {
		ArrayList<File> tableauFiles = new ArrayList<File>();
		for (File f : file.listFiles()) {
			if (f.isDirectory() == true) {
				// -- Do nothing --
			} else {
				tableauFiles.add(f);
			}
		}
		return convertArrayListFileToArrayFile(tableauFiles);
	}
	
	// Convert an array of File into an ArrayList<File>
	public ArrayList<File> convertTabToArrayList(File[] tabFile){
		if (tabFile != null) {
		ArrayList<File> arrayFile = new ArrayList<File>();
		for (File f : tabFile) {
			arrayFile.add(f);
		}
		return arrayFile;
		} else {
			return null;
		}
	}
	
	// Convert an array of File into an ArrayList<String> representing only the name of the files.
	public ArrayList<String> convertTabToArrayListString(File[] tabFile){
		ArrayList<String> arrayFileString = new ArrayList<String>();
		for (File f : tabFile) {
			arrayFileString.add(f.getName());
		}
		return arrayFileString;
	}
	
	
	public ArrayList<DefaultMutableTreeNode> convertArrayNodeToArrayListNode(DefaultMutableTreeNode[] tabNode){
		ArrayList<DefaultMutableTreeNode> arrayListNode = new ArrayList<DefaultMutableTreeNode>();
		for (DefaultMutableTreeNode n : tabNode) {
			arrayListNode.add(n);
		}
		return arrayListNode;
	}
	
	// Convert an ArrayList<File> into an array File[]
		public File[] convertArrayListFileToArrayFile(ArrayList<File> arrayFile){
			return (arrayFile.toArray(new File[arrayFile.size()]));
		}
		
		public DefaultMutableTreeNode[] convertArrayListNodeToArrayNode(ArrayList<DefaultMutableTreeNode> arrayNodes){
			return (arrayNodes.toArray(new DefaultMutableTreeNode[arrayNodes.size()]));
		}
	
	// To get an array of File[] that represents the content of the File given as param.
	public File[] getList(File theDir) {
		if (theDir != null) {
			File[] lesFichiers = theDir.listFiles();
			return lesFichiers;
		} else {
			return null;
		}
	}
	
	// To check if the File given as param is an empty directory.
	public boolean isEmptyDir(File file) {
		if (getList(file) == null || nbrFiles(file) == 0) {
			return true;
		}
		return false;
	}
	
	// To get the number of files present in the File given as param.
	// CAUTION : if the File[] f got from the listFiles() method is null, we cannot use the f.length as it would raise a NullPointerException
	public int nbrFiles(File file) {
		int nbr = 0;
		File[] tabFile = getList(file);
		if (tabFile != null) {
			if (tabFile.length == 0){return 0;}
			for (File f : tabFile) {
				nbr = nbr +1;
			}
		}
		
		return nbr;
	}
	
	private ArrayList<DefaultMutableTreeNode> getListNodesFromDir(File file) {
    ArrayList<DefaultMutableTreeNode> nodeList = new ArrayList<DefaultMutableTreeNode>();
		if (!file.isDirectory()) {
			return null;
		} else {
			File[] listFiles = file.listFiles();
			for (File f : listFiles) {
				tempNode = convertFileToNode(f);
//				NodeInfo nodeObject = (NodeInfo) tempNode.getUserObject();
				nodeList.add(tempNode);
			}
			return nodeList;
		}
	}
	
	public int nbrDir(File file) {
		int nbr = 0;
		File[] tabFile = getList(file);
		if (tabFile != null) {
		for (File f : tabFile) {
			if (f.isDirectory()) {nbr += 1;}
		}
		return nbr;
		} else {
			return 0;
		}
	}
	
	public Object[] convertArrayListObjectToArrayObject(ArrayList<Object> arrayList) {
		return (arrayList.toArray(new Object[arrayList.size()]));
	}
	
	// Gets an array of File that represents the list of the directories that are present in the File given as param.
	public File[] getListDir(File directory) {
		if (directory == null) {return null;}
		// Creates an array of File with the list from the File given as param.
		File[] lesFichiers = getList(directory);
		// Creates an empty ArrayList for the directories
		ArrayList<File> lesDossiers = new ArrayList<File>();
			// In case we are dealing with a simple file or an empty directory.
			if (directory.isDirectory() == false || nbrDir(directory) == 0) {
				// nothing appends - intended -
			} else {
				
				// Adds each directory that is not empty to the ArrayList
				for (File f : lesFichiers) {
					if (f.isDirectory() && hasChild(f) == true) {
						lesDossiers.add(f);
					}
				}	
			}
		return convertArrayListFileToArrayFile(lesDossiers);
	}
	
	
	private int nbrFilesInDir(File dossier) {
		int nbrFile = 0;
		if (!dossier.isDirectory()) {return 0;} else {tabTempFiles = getList(dossier);
			for (File d : tabTempFiles) {nbrFile += 1;}
			return nbrFile;
		}
	}
	
	private int nbrDirInDir(File dossier) {
		int nbrDir = 0;
		// In case we are dealing with a file or an empty directory.
		if (!dossier.isDirectory() || isEmptyDir(dossier)) {return 0;} else {
			File[] tabDir = getList(dossier);
			for (File f : tabDir) {nbrDir += 1;}
			return nbrDir;
		}
	}
	
	private DefaultMutableTreeNode convertFileToNode(File file) {
		if (file.isDirectory()) {
			int nbrChild = nbrFilesInDir(file);
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(new NodeInfo(file.getName(), (String) file.getPath(), file.getParentFile().getName(), true, nbrChild, false));
			return node;
		} else {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(new NodeInfo(file.getName(), (String) file.getPath(), file.getParentFile().getName(), false, 0, false));
			return node;
		}
	}
	
	// Methodes
	// Resize
	private void checkSize() {
		if (isFullWindow) {
			setSize(screenSize);
		} else {
			setSize(new Dimension(w,h));
		}
	}
	// Window closing behaviour
	private void defineCloseOperation() {
		switch (closeOperation) {
		case "DISPOSE_ON_CLOSE", "dispose_on_close", "dispose" -> setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		case "DO_NOTHING_ON_CLOSE", "do_nothing_on_close", "nothing" -> setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		case "EXIT_ON_CLOSE", "exit_on_close", "exit" -> setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		case "HIDE_ON_CLOSE", "hide_on_close", "hide" -> setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		default -> setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}
	// MenuBar creation
	private JMenuBar createJMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(createMenuFile());
		menuBar.add(createMenuEdit());
		menuBar.add(createMenuTheme());
		return menuBar;
	}
	// File Menu
	private JMenu createMenuFile() {
		JMenu menuFile = new JMenu("File");
		// New
		icon = new ImageIcon("src/Images/Icons/icon-new.png");  
		JMenuItem menuNew = new JMenuItem("New", icon);
		menuNew.addActionListener(this::menuNewListener);
		menuFile.add(menuNew);
		// Open
		icon = new ImageIcon("src/Images/Icons/icon-open.png"); 
		JMenuItem menuOpen = new JMenuItem("Open", icon);
		menuOpen.addActionListener(this::menuOpenListener);
		menuFile.add(menuOpen);
		// Save
		icon = new ImageIcon("src/Images/Icons/icon-save.png");  
		JMenuItem menuSave = new JMenuItem("Save", icon);
		menuSave.addActionListener(this::menuSaveListener);
		menuFile.add(menuSave);
		// Close
		icon = new ImageIcon("src/Images/Icons/icon-close.png"); 
		JMenuItem menuClose = new JMenuItem("Close", icon);
		menuClose.addActionListener(this::menuCloseListener);
		menuFile.add(menuClose);
		return menuFile;
	}
	// Edit menu
	private JMenu createMenuEdit() {
		JMenu menuEdit = new JMenu("Edit");
		// Modify
		icon = new ImageIcon("src/Images/Icons/icon-edit.png");
		JMenuItem menuModif = new JMenuItem("Modifier", icon);
		menuModif.addActionListener(this::menuModifListener);
		menuEdit.add(menuModif);
		// Move
		icon = new ImageIcon("src/Images/Icons/icon-move.png");
		JMenuItem menuMove = new JMenuItem("Déplacer", icon);
		menuMove.addActionListener(this::menuMoveListener);
		menuEdit.add(menuMove);
		return menuEdit;
	}
	// Theme menu
	private JMenu createMenuTheme() {
		JMenu menuTheme = new JMenu("Theme");
		//Color
		icon = new ImageIcon("src/Images/Icons/icon-color.png"); 
		JMenuItem menuColor = new JMenuItem("Color", icon);
		menuColor.addActionListener(this::menuColorListener);
		menuTheme.add(menuColor);
		// Zoom
		icon = new ImageIcon("src/Images/Icons/icon-zoom.png"); 
		JMenuItem menuZoom = new JMenuItem("Zoom", icon);
		menuZoom.addActionListener(this::menuZoomListener);
		menuTheme.add(menuZoom);
		// Style
		icon = new ImageIcon("src/Images/Icons/icon-style-add.png"); 
		JMenuItem menuStyle = new JMenuItem("Style", icon);
		menuStyle.addActionListener(this::menuStyleListener);
		menuTheme.add(menuStyle);
		return menuTheme;
	}
	// Creation of JTree from UserFiles
	private JTree createJTreeMenu() {
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode();
		
		
		return new JTree();
	}
	
	//Menu listeners
	private void menuNewListener(ActionEvent e) {
		JOptionPane.showMessageDialog(this, "New document will be created");
		
	}
	private void menuOpenListener(ActionEvent e) {
		JOptionPane.showMessageDialog(this, "The user will choose to open a file from a list");
	}
	private void menuSaveListener(ActionEvent e) {
		JOptionPane.showMessageDialog(this, "Save the document");
	}
	private void menuCloseListener(ActionEvent e) {
		int reponse = JOptionPane.showConfirmDialog(this, "Quitter ?","Quitter l'application", 2);
		if (reponse == 0) {System.exit(0);}
	}
	private void menuModifListener(ActionEvent e) {
		JOptionPane.showMessageDialog(this, "The user will choose an element to modify from a list");
	}
	private void menuMoveListener(ActionEvent e) {
		JOptionPane.showMessageDialog(this, "The user will choose an element to move from a list");
	}
	private void menuZoomListener(ActionEvent e) {
		JOptionPane.showMessageDialog(this, "The user will be able to zoom in or out");
	}
	private void menuColorListener(ActionEvent e) {
		JOptionPane.showMessageDialog(this, "The user will be able to change the color of the theme");
	}
	private void menuStyleListener(ActionEvent e) {
		JOptionPane.showMessageDialog(this, "The user will be able to change the style of the theme");
	}
	// toolBar creation
	/*
	private JToolBar createToolBar(String nameToolBar, int hauteur, int largeur) {
		JToolBar toolBar = new JToolBar();
		toolBar.setName(nameToolBar);
		toolBar.setSize(largeur, hauteur);
		
		JButton btnNew = new JButton(new ImageIcon("icons/new.png"));
		toolBar.add(btnNew);
		
		return toolBar;
	}
	*/
	
	private boolean isFullWindow() {
		return isFullWindow;
	}

	private void setFullWindow(boolean isFullWindow) {
		this.isFullWindow = isFullWindow;
	}

	private boolean isUnDecorated() {
		return isUnDecorated;
	}

	private void setUnDecorated(boolean isUnDecorated) {
		this.isUnDecorated = isUnDecorated;
		setUndecorated(isUnDecorated);
	}
	
	private int getH() {
		return h;
	}

	private void setH(int h) {
		this.h = h;
	}

	private int getW() {
		return w;
	}

	private void setW(int w) {
		this.w = w;
	}

	private Color getBackgroundColor() {
		return backgroundColor;
	}

	private void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	private String getCloseOperation() {
		return closeOperation;
	}

	private void setCloseOperation(String closeOperation) {
		this.closeOperation = closeOperation;
	}	
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
	}	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
	}	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
	}	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
	}	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
	}	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void process(File parent) {
		
		ArrayList<File> tabTempDir = new ArrayList<File>();
		
		//  On liste les fichiers présents dans parent, et on ajoute chaque élément dans orderedListFiles
			File[] listWithFile = getList(parent);
			for (File f : listWithFile) {
				orderedListFiles.add(f);
			}
			
			//  Pour chaque dossier présent dans parent, on ajoute dans tabTempDir
			for (File f : listWithFile) {
				if (f.isDirectory()) {
					tabTempDir.add(f);
				}
			}
			
			//  Pour chaque élément (donc dossier) dans tabTempDir, si il n'est pas vide, on récupère la liste dans tempList
			for (File f : tabTempDir) {
				if (!isEmptyDir(f)) {
					File[] tempList = getList(f);
					for (File d : tempList) {
						process(f);
					}
				}
			}
		
		
		orderedListFiles.stream().map(element -> element.getParentFile().getName() + " -> " + element.getName()).forEach(System.out::println);
		
	}
	
	
}
