package Window;
// Imports
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
	public int levelCounter = 0;
	
	private DefaultMutableTreeNode tempNode;
	private DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("UserFiles", true);
	private ArrayList<DefaultMutableTreeNode> arrayNodes = new ArrayList<DefaultMutableTreeNode>();
	private JTree tree = new JTree(rootNode, true);
	private File[] tabTempFiles;
	private ArrayList<File> orderedListFiles = new ArrayList<File>();
	private ArrayList<ArrayList<File>> contentLevel = new ArrayList<ArrayList<File>>();
	private ArrayList<Integer> nbrFilesInLevel = new ArrayList<Integer>();
	
	public ArrayList<DefaultMutableTreeNode> tabNodes = new ArrayList<DefaultMutableTreeNode>();
	
	public ArrayList<DefaultMutableTreeNode> tNodes = new ArrayList<DefaultMutableTreeNode>();
	
	// Only files that have been converted to a node then added to a parent node has been processed
	public ArrayList<String> nameFileProcessed = new ArrayList<String>();
	
	// This one is useful to see if the parent node has already been created, in order to avoid creating it again
	public ArrayList<String> nameDirProcessed = new ArrayList<String>();
	
	// Only nodes that have been processed
	public ArrayList<DefaultMutableTreeNode> nodeProcessed = new ArrayList<DefaultMutableTreeNode>();
	// Only files that have been processed
	public ArrayList<File> fileProcessed = new ArrayList<File>();
	// Only parent nodes that have been processed
	public ArrayList<DefaultMutableTreeNode> parentNodeProcessed = new ArrayList<DefaultMutableTreeNode>();
	
	
	
	// Represents the list of every directory inside userFiles
	public ArrayList<File> fullDir = new ArrayList<File>();
	public ArrayList<File> fullElement = new ArrayList<File>();
	
	public ArrayList<DefaultMutableTreeNode> fullNode = new ArrayList<DefaultMutableTreeNode>();
	
	public boolean multiRecurs = false;
	
	// These 3 nodes are there to be replaced by the process...
	public DefaultMutableTreeNode childNode = null;
	public DefaultMutableTreeNode actualNode = new DefaultMutableTreeNode();
	public DefaultMutableTreeNode parentNode = new DefaultMutableTreeNode();
	
	ArrayList<DefaultMutableTreeNode> parentArrayNode = new ArrayList<DefaultMutableTreeNode>();
	ArrayList<File> parentArrayFile = new ArrayList<File>();
	
	public int level = 0;
	public ArrayList<File> tabLevel = new ArrayList<File>();
	
	// ArrayList and Array of StepNode, each StepNode represent a folder node with a name and an array of nodes representing the children
	public ArrayList<StepNode> arrayStepNode = new ArrayList<StepNode>();
	public StepNode[] tabStepNode;
	
	// Constructor
	public Window(boolean isFullWindow, int w, int h, Color backColor, boolean isUnDecorated, String closeOperation) {
		//   -----   Try to apply Look and Feel   -----
		try {UIManager.setLookAndFeel(new NimbusLookAndFeel());} catch (UnsupportedLookAndFeelException e) {
			System.out.println("Unsupported Look and Feel");
			e.printStackTrace();
		}
		
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
		
		if (rootFile.listFiles() != null) {
			
			gettingLevels(rootFile);
			
			ArrayList<DefaultMutableTreeNode> TabNode = new ArrayList<DefaultMutableTreeNode>();
			
			reverseM(contentLevel);

			for (ArrayList<File> tabArrayFile : contentLevel) {
				// for each ArrayList<File>, we send it to processArrayListFile and get the node that contains the other nodes (converted files) from the ArrayList<File>
				DefaultMutableTreeNode node = processArrayListFile(tabArrayFile);
				// Adds this node to an ArrayList<DefaultMutableTreeNode>, so that ArrayList contains each nodes that contains their previous ArrayList<File> converted to nodes.
				TabNode.add(node);
			}
			DefaultMutableTreeNode[] TheTabNodes = convertArrayListNodeToArrayNode(TabNode);
			rootNode = TheTabNodes[0];

			/*
			
			System.out.println("RootFile : " + rootFile);
		//	firstStep(rootFile);
			fullListDir(rootFile);
			fullListElement(rootFile);
			
			System.out.println("BEFORE : " + fullDir);
			reverseM(fullDir);
			System.out.println("AFTER : " + fullDir);
			
			System.out.println(fullDir);
			
			// For each directory
			for (File f : fullDir) {
				
				File[] siblingsFiles = getSiblings(f);
				
				// Case where f has siblings, we need to handle those here
				if (siblingsFiles != null && siblingsFiles.length != 0) {
					// For each sibling
					for (File file : siblingsFiles) {
						// Check if the sibling is a child and a parent at the same time
						if (isChildAndParent(file)) {
							
						}
					}
				}
				
			}
				
				
			*/
				
				
				
				
				/*
				
				// Check if the node that represents the parent of f exists, if it does we instanciate into parentNode
				if (checkForParent(f) == true) {
					parentNode = getParentNode(f);
				} else {
					// We instanciate parentNode with the parent file parameters
					parentNode = new DefaultMutableTreeNode(f.getParentFile().getName(), true);
				}
				// We get the node that represents the directory with its children into actualNode
				actualNode = getNodeWithChildrenFromFile(f);
				// We add the actualNode to the parentNode
				parentNode.add(actualNode);
				
				// We put the parentNode into an array to be reused if already exists for next steps
				parentArrayNode.add(parentNode);
				
				// We put the parent of the file into an array of files to know if it exists in the array of nodes (both array should grow the same)
				parentArrayFile.add(f.getParentFile());
				
				*/
				
			
			
			/*
			// For each file we check if it has siblings and if so,
			for (File f : parentArrayFile) {
				if (nbrSiblingsOfDir(f) > 1) {
					// We get those into an array
					DefaultMutableTreeNode[] tabN = getSiblingsOfDir(f);
					// We get the node previously saved, that represents the parent of these siblings
					DefaultMutableTreeNode parentN = getParentNode(f.getParentFile());
					// For each sibling
					for (DefaultMutableTreeNode n : tabN) {
						// We add it to the parent
						parentN.add(n);
					}
				}
			}
			*/
			

			/*
			
			// Convertion of ArrayList to Array
			File[] tabFiles = convertArrayListFileToArrayFile(fullDir);
			
			convertArrayListNodeToArrayNode(fullNode);
			
			reverseM(fullElement);
			// All elements from UserFiles in this Array
			File[] allElements = convertArrayListFileToArrayFile(fullElement);
			// For each file inside "all the elements"
			for (File f : allElements) {	
				// We get the siblings of f in an array
				File[] recupSiblings = searchForSiblings(f, allElements);
				// Check if the parent exists in the "parent processed"
				if (getParentProcessed(f.getParentFile().getName()) != null) {
					// We found it in the "parent processed", we use it.
					parentNode = getParentProcessed(f.getParentFile().getName());
				} else {
					// It does not exist, we create it 
					parentNode = new DefaultMutableTreeNode(f.getParentFile().getName(), f.getParentFile().isDirectory());
				}
				
				// For each of those siblings we create a node
				for (File s : recupSiblings) {
					// instanciate "actualNode" with the file "s"
					actualNode = new DefaultMutableTreeNode(s.getName(), s.isDirectory());
					
					parentNode.add(actualNode);
					
					
					if (s.isDirectory() == false) {
						nodeProcessed.add(actualNode);
					} else {
						parentNodeProcessed.add(actualNode);
					}
					// Adds the "parentNode" to the "parentNodeProcessed" to keep track
					parentNodeProcessed.add(parentNode);
				}
			}
			
			*/
			
			// For each ArrayList<File> inside the contentLevel
			for (ArrayList<File> f : contentLevel) {
				// Creates an ArrayList of nodes that will be converted and used to create the stepNode
				ArrayList<DefaultMutableTreeNode> arrayNode = new ArrayList<DefaultMutableTreeNode>();
				// The name of the parent of each file from this ArrayList "f" that will be used to create the stepNode
				String nameParent = f.get(0).getParentFile().getName();
					// For each file present in the ArrayList "f"
					for (File file : f) {
						// We create a node that we put in the arrayNode
						arrayNode.add(new DefaultMutableTreeNode(file.getName(), file.isDirectory()));
					}
				// We create a stepNode with the name of the parent and an array of nodes representing the children
				StepNode stepNode = new StepNode(nameParent, convertArrayListNodeToArrayNode(arrayNode));
				// We add this stepNode to the ArrayList<StepNode>
				arrayStepNode.add(stepNode);
			}
			
			for (StepNode s : arrayStepNode) {
				System.out.println("Name : " + s.getName());
				actualNode = new DefaultMutableTreeNode(s.getName(), true);
				for (DefaultMutableTreeNode node : s.getContainer()) {
					actualNode.add(node);
				}
				
				if (childNode == null) {
					childNode = actualNode;
				} else {
				//	actualNode.add(childNode);
					childNode = actualNode;
				}
				
				
			}
			
			
			JTree secondTree = new JTree(actualNode);
			
			this.add(secondTree);

		}
	}
	
	public DefaultMutableTreeNode recursProcess(File file) {
		// Gets the list of element inside "file"
		File[] tabFile = getList(file);
		// If the list is not null and if it's length is not equal to 0
		if (tabFile != null && tabFile.length != 0) {
			//For each of these elements
			for (File f : tabFile) {
				//Check if there is a directory that contains at leat one element
				if (f.isDirectory() && getList(f) != null) {
					// We relaunch the function with this directory and we increment "level" to know we have passed a new directory to the function
					level = level + 1;
					recursProcess(f);
				}
			}
			// We got out the "for each" loop that was looping elements inside "file"
			// So we create the node corresponding to "file"
			actualNode = getNodeWithChildrenFromFile(file);
			parentNode = getNodeWithChildrenFromFile(file.getParentFile());

			for (int i = level; i >= 0; i--) {
				parentNode.add(actualNode);
				file = file.getParentFile();
				actualNode = parentNode;
			}
		}
	return actualNode;
	}
	
	// Returns an array of File[] that contains every files that share the same parent : parentFile.
	public File[] getFilesWithSameParent(File[] tabFiles, File parentFile) {
		ArrayList<File> returnFiles = new ArrayList<File>();
		for (File f : tabFiles) {
			if (f.getParentFile().getName().equals(parentFile.getName())){
				returnFiles.add(f);
			}
		}
		return convertArrayListFileToArrayFile(returnFiles);
	}
	
	
	public File[] getAllSiblings(File file) {
		File[] tabSiblings = file.getParentFile().listFiles();
		return tabSiblings;
	}
	
	
	// Checks if the file passed as param has already a parent node created for it.
	public boolean checkForParent(File file) {
		String name = file.getParentFile().getName();
		for (DefaultMutableTreeNode n : parentArrayNode) {
			if (n.toString().equals(name)){
				return true;
			}
		}
		return false;
	}
	
	
	
	public int nbrSiblingsOfDir(File file) {
		int nbr = 0;
		String nameParent = file.getParentFile().getName();
		for (File f : parentArrayFile) {
			if (f.getName().equals(nameParent)) {
				nbr += 1;
			}
		}
		return nbr;
	}
	
	// Give a file as param and get an array of nodes 
	public DefaultMutableTreeNode[] getSiblingsOfDir(File file) {
		ArrayList<DefaultMutableTreeNode> tabNodes = new ArrayList<DefaultMutableTreeNode>();
		String nameParent = file.getParentFile().getName();
		for (File f : parentArrayFile) {
			if (f.getName().equals(nameParent)) {
				tabNodes.add(getParentNode(f));
			}
		}
		return convertArrayListNodeToArrayNode(tabNodes);
	}
	
	
	
	
	// Takes a file in param, check if, inside the array of nodes, the name of the parent file exists also as a name of a node, if it does return it.
	public DefaultMutableTreeNode getParentNode(File file) {
		for (DefaultMutableTreeNode n : parentArrayNode) {
			if (file.getParentFile().getName().equals(n.toString())) {
				return n;
			}
		}
		return null;
	}
	
	
	public void test(File file) {
		new DefaultMutableTreeNode("UserFiles", true);
		getList(file);
		
		
	}
	
	
	
	
	
	// Checks if the file passed as param has at least one child and is a child of a folder different than "UserFiles"
	public boolean isChildAndParent(File file) {
		if (getList(file) != null) {
			if (file.getParentFile() != null && file.getParentFile().getName() != "UserFiles") {
				return true;
			}
		}
		return false;
	}
	
	
	public boolean checkFileForProcessed(File file) {
		for (File f : fileProcessed) {
			if (f.getName().equals(file.getName())) {return true;}
		}
		return false;
	}
	
	public boolean checkNodeForProcessed(File nodeFile) {
		for (DefaultMutableTreeNode n : nodeProcessed) {
			if (n.toString().equals(nodeFile.getName())) {return true;}
		}
		return false;
	}
	
	public boolean checkParentNodeForProcessed(File parentNodeFile) {
		for (DefaultMutableTreeNode n : parentNodeProcessed) {
			if (n.toString().equals(parentNodeFile.getName())) {return true;}
		}
		return false;
	}
	
	public DefaultMutableTreeNode getParentProcessed(String folderName) {
		for (DefaultMutableTreeNode n : parentNodeProcessed) {
			if (n.toString().equals(folderName)) {
				return n;
			}
		}
		return null;
	}
	
	public DefaultMutableTreeNode getNodeProcessed(String elementName) {
		for (DefaultMutableTreeNode n : nodeProcessed) {
			if (n.toString().equals(elementName)) {
				return n;
			}
		}
		return null;
	}
	
	
	// Give a File and an array File[] that contains every files,
	// Check each of the elements to see if their parent is the same as the parent of the File given as param
	// Returns the list of siblings (that has the same parent as File given as param).
	public File[] searchForSiblings(File file, File[] files) {
		// ArrayList creation that will contain the result
		ArrayList<File> arraySiblings = new ArrayList<File>();
		System.out.println("files length : " + files.length);
		// For each file, compare the name of the parent to the name of the parent file passed as param.
		for (int i = 0; i < files.length; i++) {
			if (files[i].getParentFile().getName().equals(file.getParentFile().getName())) {
				arraySiblings.add(files[i]);
			} else {
			//	System.out.println("f : " + f.getParentFile().getName());
			//	System.out.println("file : " + file.getParentFile().getName());
			}
			
		}
		// Convert the ArrayList into an Array
		File[] tabSiblings = convertArrayListFileToArrayFile(arraySiblings);
		for (int i = 0; i < tabSiblings.length; i++) {
			System.out.println(tabSiblings[i]);
		}
			
			return tabSiblings;
		
	}
	
	
	
	
	public void analizeFile(File[] files, DefaultMutableTreeNode[] nodes) {
		
	}
	

	
	public DefaultMutableTreeNode processArrayListFile(ArrayList<File> arrayFile) {
		String nomNode = arrayFile.get(0).getParentFile().getName();
		DefaultMutableTreeNode nodeParent = new DefaultMutableTreeNode(nomNode, true);
		for (File f : arrayFile) {
			DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(f.getName(), f.isDirectory());
			nodeParent.add(tempNode);
		}
		return nodeParent;
	}
	
	public DefaultMutableTreeNode exploreFilesToGetNode(File fichier) {
		// We create a the node, that will contain the other nodes created from files, that will be return at the end of the function
		DefaultMutableTreeNode returnNode = new DefaultMutableTreeNode(fichier.getName(), fichier.isDirectory());
		// parentNode
		DefaultMutableTreeNode parentNode = new DefaultMutableTreeNode("tempParent");
		// tempNode
		DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode("tempNode");
		// abstractNode
		DefaultMutableTreeNode abstractNode = new DefaultMutableTreeNode("tempNode");
		if (fichier != null) {
			// Get the list of the files from the initial file
			File[] lesFichiers = fichier.listFiles();
			// if the list is not null
			if (lesFichiers != null) {
				// We increment the counter as we go down 1 level in the hierarchy
				levelCounter++;
				// For each file from that previous list of files, we explore again recursively
				for (File f : lesFichiers) {
					exploreFilesToGetNode(f);
				}
			} else {
				
				// Checks if the file has siblings
				int nbrSib = nbrSiblings(fichier);
				if (nbrSib > 1) {
					for (File f : fichier.getParentFile().listFiles()) {
						if (f != null && f.listFiles() != null) {
							levelCounter++;
							// If we find a siblings that has children, we resend it to the function
							exploreFilesToGetNode(f);
						} else {
						parentNode = new DefaultMutableTreeNode(fichier.getParentFile().getName(), true);
						// Else we add each Node (converted files) to the parent node
						tempNode = new DefaultMutableTreeNode(f.getName(), f.isDirectory());
						parentNode.add(tempNode);
						System.out.println(tempNode + " Has been added to " + parentNode);
						}
					}
					
				}
				// If we get here, it means that the first file is not null, it has no children and we have processed his siblings
				abstractNode = new DefaultMutableTreeNode(fichier.getParentFile().getName(), fichier.getParentFile().isDirectory());
				abstractNode.add(parentNode);
				System.out.println(parentNode + " Has been added to " + abstractNode);
			}

			
		}
		return returnNode;
	}
	
	// Loop to get file without containing any folder then continue ->
	// Gets the directories from "fichier", check if it contains other directories and if so, do the search again until there isn't
	public void firstStep(File fichier) {
		System.out.println("First Step : fichier -->  " + fichier);
		File[] tabDir = getListDir(fichier);
		// If tabDir is not null, it means there is at least one directory in "fichier"
		if (tabDir != null) {
			// For each of these directories, we relaunch the first Step until tabDir is null, so no more directories in "fichier"
			for (File f : tabDir) {
				firstStep(f);
			}
		} else {

			// We get here, after searching inside the directories, when "fichier" does not contain any directory  -->  We send it to secondStep()
			secondStep(fichier);
		}
	}
	
	
	// We found a file without folder, we convert it and its children to a node, add the children to the parent then continue ->
	// We get here when we found that "fichier" is at last position in hierarchy. 
	// We need to create a node from "fichier" and from the list of files inside fichier, and the thirdStep should deal with the siblings of "fichier"
	public void secondStep(File fichier) {
		System.out.println("Second Step : fichier -->  " + fichier);
		// The node that will be send to thirdStep
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(fichier.getName(), fichier.isDirectory());
		// list the files from fichier
		File[] tabFiles = fichier.listFiles();
		// for each of those file we create a node that will be added to the node that will be send to thirdStep.
		if (tabFiles != null) {
			// Node creation for each element in "fichier"
			for (File f : tabFiles) {
				DefaultMutableTreeNode temp = new DefaultMutableTreeNode(f.getName(), f.isDirectory());
				// Adds to the ArrayList of names, the name of the file that just has been processed into a node, to keep track.
				nameFileProcessed.add(f.getName());
				// Once every element inside "fichier" has been converted to node, we add those to the node created from "fichier"
				node.add(temp);
			}
		}
		// Here we have a node that represent the file "fichier", with all its sub-elements, now we send it to thirdStep to process the siblings.
		thirdStep(fichier, node);
	}
	
	// Look for siblings, if any, send them to the firstStep so they can be equals to the node we have, and then continue ->
	// thirdStep is to process each siblings, the file and node passed as param are linked as the node is the representation of the file.
	// So here we have a node that contains other nodes, these other nodes are in the nameFileProcessed, but not the node.
	// ThirdStep should then check if the siblings have been processed and if not, process them
	public void thirdStep(File fichier, DefaultMutableTreeNode node) {
		System.out.println("Third Step : fichier -->  " + fichier);
		// Check if the parent node already exists
		if (checkForDirProcessed(fichier.getParentFile().getName()) == true) {
			// -- TBD --
		}
		// Parent node creation that will contain the node + the siblings
		DefaultMutableTreeNode parentNode = new DefaultMutableTreeNode(fichier.getParentFile().getName(), fichier.getParentFile().isDirectory());
		// We check if the file ("fichier") has siblings directories
		if (checkForSiblings(fichier) == true) {
			// If it does, we get those in an array
			File[] tempFiles = getSiblings(fichier);
				// for each element of this array
				for (File f : tempFiles) {
					// We check if f has already been processed
					if (checkForFileProcessed(f.getName())) {
						// Already processed at this point  -> nothing happens - intended -
						System.out.println(f.getName() + "  has already been processed -_-' ");
					} else {
						// Otherwise we check if f is either a file or an empty directory, if it is
						if (f.isFile() || isEmptyDir(f)) {
						// We process the name, create the Sibling Node and add it to the parent node.
						nameFileProcessed.add(f.getName());
						DefaultMutableTreeNode temp = new DefaultMutableTreeNode(f.getName(), f.isDirectory());
						parentNode.add(temp);
						} else {
							// Else if we get here we assume that we are dealing with a directory that is not empty
							// We send f to the firstStep to get to the last file of this directory then it passes to the secondStep
							firstStep(f);
						}
					}
				}
		// We get here if the "fichier" has no siblings
		} else {
				// In this case there are no siblings, we can create the parentNode and add the node (from "fichier") to it.
				nameFileProcessed.add(fichier.getName());
				parentNode.add(node);
		}
		if (fichier.getName().equals("UserFiles")) {System.out.println("DONE"); fourthStep(fichier, node);}
		thirdStep(fichier.getParentFile(), parentNode);
	}
			
		//	fourthStep(fichier.getParentFile(), parentNode);
	
	
	public DefaultMutableTreeNode fourthStep(File fichier, DefaultMutableTreeNode node) {
		System.out.println("Fourth Step : fichier -->  " + fichier);
		return node;
	}
	
	// Get all the directories from "fichier" and list them into the fullDir arrayList
	public void fullListDir(File fichier) {
		File[] tabDir = getListDir(fichier);
		// If tabDir is not null, it means there is at least one directory in "fichier"
		if (tabDir != null) {
			// For each of these directories, we add them into the fullDir arrayList
			for (File f : tabDir) {
				fullDir.add(f);
			}
			// Afterwards, for each of these directories, we relaunch the function to search for directories in each of them.
			for (File f : tabDir) {
				fullListDir(f);
			}
		} 
	}
	
	
	
	// Get all the elements from "fichier" and list them into the fullElement arrayList
		public void fullListElement(File fichier) {
			File[] tabElement = getList(fichier);
			// If tabElement is not null, it means there is at least one Element in "fichier"
			if (tabElement != null) {
				// For each of these Elements, we add them into the fullElement arrayList
				for (File f : tabElement) {
					fullElement.add(f);
				}
				// Afterwards, for each of these elements, we relaunch the function to search for elements in each of them.
				for (File f : tabElement) {
					fullListElement(f);
				}
			} 
		}
	
	// Returns the next directory that has not been processed, or null if there are no directories inside the array, or if all of the directories has been processed.
	public File getNextDirectory(File[] tabFile) {
		for (File f : tabFile) {
			if (f.isDirectory() && checkForDirProcessed(f.getName()) == false) {
				return f;
			}
		}
		return null;
	}
	
	public DefaultMutableTreeNode getNode(File dossier) {
		// Node creation from "dossier"
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(dossier.getName(), dossier.isDirectory());
		// get the list of element inside "dossier"
		File[] tabFiles = dossier.listFiles();
		// For each of these elements
		for (File f : tabFiles) {
			// We create a node that will be added to the first node created with "dossier"
			DefaultMutableTreeNode temp = new DefaultMutableTreeNode(f.getName(), f.isDirectory());
			node.add(temp);
		}
		return node;
	}
	
	
	public boolean checkForFileProcessed(String nameFichier) {
		for (String n : nameFileProcessed) {
			if (n.equals(nameFichier)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkForDirProcessed(String nameFichier) {
		for (String n : nameDirProcessed) {
			if (n.equals(nameFichier)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkForSiblings(File fichier) {
		File parentFile = fichier.getParentFile();
		File[] tabFile = parentFile.listFiles();
		if (tabFile == null || tabFile.length <= 1) {
			return false;
		} else {
			return true;
		}
	}
	
	public File[] getSiblings(File fichier) {
		File parentFile = fichier.getParentFile();
		String nomFichier = fichier.getName();
		File[] listElement = parentFile.listFiles();
		ArrayList<File> listSiblings =  new ArrayList<File>();
		
		for (File f : listElement) {
			if (!f.getName().equals(nomFichier)) {
				listSiblings.add(f);
			}
		}
		return convertArrayListFileToArrayFile(listSiblings);
	}
	
	
	public int getSiblingsCount(File fichier) {
		File parentFile = fichier.getParentFile();
		String nomFichier = fichier.getName();
		File[] listElement = parentFile.listFiles();
		int nbrElement = 0;
		for (File f : listElement) {
			if (!f.getName().equals(nomFichier)) {
				// -- nothing --
			} else {
				nbrElement += 1;
			}
		}
		return nbrElement;
	}
	
	
	public DefaultMutableTreeNode getNodeWithChildrenFromFile(File file) {
		if (file != null) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(file.getName(), file.isDirectory());
			File[] tabFiles = getList(file);
			for (File f : tabFiles) {
				node.add(new DefaultMutableTreeNode(f.getName(), f.isDirectory()));
			}
			return node;
		}
		return null;
	}
	
	
	// Return true if every siblings does not has children, false if at least one of them does
	public boolean checkLastSiblings(File f) {
		File[] tabFile = getSiblings(f);
		for (File e : tabFile) {
			if (hasChild(e)) {
				return false;
			}
		}
		return true;	
	}
	
	
   	public DefaultMutableTreeNode returnNode(DefaultMutableTreeNode[] tabNode) {
		DefaultMutableTreeNode theNode = new DefaultMutableTreeNode(tabNode[0].toString(), true);
		for (int i = 1; i < tabNode.length; i++) {
			theNode.add(tabNode[i]);
		}
		return theNode;
	}
	
	public DefaultMutableTreeNode addChildToParentNode(DefaultMutableTreeNode parent, DefaultMutableTreeNode child) {
		if (parent != null) {
			parent.add(child);
		}
		return parent;
	}
	
	public ArrayList<DefaultMutableTreeNode> getTheNode(ArrayList<ArrayList<File>> BigContent) {
		
		DefaultMutableTreeNode parentNode = null;
		
		for (ArrayList<File> arrayNode : BigContent) {
			// Creates the userObject parentNodeInfo that will be used to create the parent Node
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
		if (list.size() <= 1 || list == null) {return;}
		T value = list.remove(0);
		// Call the recursive function to reverse the list after removing the first element
		reverseList(list);
		// Now after the rest of the list has been reversed by the upper recursive call, add the first value to the end
		list.add(value);
	}
	
	// Same function that reverse a list but with only one loop with one line of code.
	public static <T> void reverseM(List<T> liste) {
		for (int i = 0; i <= liste.size()-1; i++) {
			liste.add(liste.remove(liste.size()-1-i));
		}
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
		JTree tree = new JTree();
		File[] tabFiles = listFiles.toArray(new File[listFiles.size()]);
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
	
	
	// Returns true if the file and the node passed in param share the same name, path and parent name.
	public boolean compareFileToNode(File file, DefaultMutableTreeNode node) {
		NodeInfo info = (NodeInfo) node.getUserObject();
		if (file.getName().equals(info.getNodeName()) && file.getPath().equals((String)info.getNodePath()) && file.getParentFile().getName().equals(info.parent())) {
			return true;
		}
		return false;
	}
	
	
	public DefaultMutableTreeNode processDirChildren(File theDirectory) {
		nbrFilesInDir(theDirectory);
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
	
	
	public DefaultMutableTreeNode getParentNodeWithNodeFromFile(File child) {
		// Gets the parent of the file
		File parent = child.getParentFile();
		// Turns it into a node
		DefaultMutableTreeNode nodeParent = createNode(parent);
		// Turns the File into a node
		DefaultMutableTreeNode node = createNode(child);
		// Adds the child node to the parent node
		nodeParent.add(node);
		// Returns the parent as a node containing the child node.
		return nodeParent;
	}
	
	// Creates and returns a DefaultMutableTreeNode from a File given as param.
	public DefaultMutableTreeNode createNode(File fichier) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(fichier.getName(), fichier.isDirectory());
		return node;
	}
	
	
	public boolean isLast(File file) {
		if (getList(file) == null || isEmptyDir(file) == true) {
			return true;
		} else { return false; }
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
		if (listDir != null) {
			for (File f : listDir) {
				arrayListDeDir.add(f);
			}
		}
		
	//	arrayListDeDir.stream().map(e -> e.getName()).forEach(System.out::println);
		if (listDir != null) {
			for (File f : listDir) {
			//	System.out.println(f.getName());				
				processLevel(f);
			}
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
			if (arrayFile == null) {return null;}
			int length = arrayFile.size();
			File[] tabFile = new File[length];
			for (int i = 0; i <= length - 1; i++) {
				tabFile[i] = arrayFile.get(i);
			}
		return tabFile;
		}
		
		public DefaultMutableTreeNode[] convertArrayListNodeToArrayNode(ArrayList<DefaultMutableTreeNode> arrayNodes){
			if (arrayNodes == null) {return null;}
			
			int length = arrayNodes.size();
			DefaultMutableTreeNode[] tabNodes = new DefaultMutableTreeNode[length];
			
			for (int i = 0; i < length; i++) {
				tabNodes[i] = arrayNodes.get(i);
			}
		return tabNodes;
		}
	
	// To get an array of File[] that represents the content of the File given as param.
	public File[] getList(File theDir) {
		if (theDir != null && theDir.listFiles() != null) {
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
		if (directory == null || getList(directory) == null) {
			return null;
		}
		// Creates an array of File with the list from the File given as param.
		File[] lesFichiers = getList(directory);
		// Creates an empty ArrayList for the directories
		ArrayList<File> lesDossiers = new ArrayList<File>();
			// In case we are dealing with a simple file or an empty directory.
			if (directory.isDirectory() == false) {
				// nothing appends - intended -
			} else if (directory.isDirectory() == true && nbrFilesInDir(directory) == 0) {
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
		if (dossier.isDirectory() == false) {return 0;} else {tabTempFiles = getList(dossier);
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
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(new NodeInfo(file.getName(), (String) file.getPath(), file.getParentFile().getName(), true, nbrChild, isLast(file)));
			return node;
		} else {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(new NodeInfo(file.getName(), (String) file.getPath(), file.getParentFile().getName(), false, 0, isLast(file)));
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
		new DefaultMutableTreeNode();
		
		
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
