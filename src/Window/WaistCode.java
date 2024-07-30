package Window;

import java.io.File;
import java.util.ArrayList;

public class WaistCode {
	  
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

	
	/*
	
	// For each file 
	for (File f : fileList) {
		ArrayList<String> sameParent = new ArrayList<String>();
		ArrayList<File> sameParentFile = new ArrayList<File>();
	//	ArrayList<DefaultMutableTreeNode> childNodes = new ArrayList<DefaultMutableTreeNode>();
		String fName = f.getName();
	//	DefaultMutableTreeNode nodeParent = new DefaultMutableTreeNode(f.getName(), true);
		
		for (File file : fileList) {
			if (file.getName().equals(fName)) {
				// -- We compare 2 identical files --
			} else if (file.getParentFile().getName().equals(fName)) {
				sameParent.add(file.getName());
				sameParentFile.add(file);
			}
		}

//		System.out.println("Parent Name : " + fName + " |  And its children : " + sameParent);
//		System.out.println("Parent Name : " + nodeParent + " |  And its children : " + childNodes);
		parentWithChildren.put(fName, sameParent);
		parentFileMap.put(f, sameParentFile);
		
	}

	for (File n : fileList) {
		ArrayList<String> childNodes = new ArrayList<String>();
		ArrayList<DefaultMutableTreeNode> realNode = new ArrayList<DefaultMutableTreeNode>();
		String nodeName = n.getName();

		
		for (File node : fileList) {
			if (node.getName().equals(nodeName)) {
				// -- We compare 2 identical files --
			} else if (node.getParentFile().getName().equals(nodeName)) {
				childNodes.add(node.getName());
				realNode.add(new DefaultMutableTreeNode(node.getName(), node.isDirectory()));
			}
		}

		
//		System.out.println("Parent Name : " + fName + " |  And its children : " + sameParent);
//		System.out.println("Parent Name : " + nodeParent + " |  And its children : " + childNodes);
		
		realNodes.put(nodeName, realNode);
		nodesWithChildren.put(nodeName, childNodes);
	}
	
	
*/
	
//	What I Need to do for the next time : delete the node part that is alike the file part (wich works already) then create the node part from the file part result.
//		System.out.println("fullElement : ");
//		fullElement.stream().map(e->e.toString()).forEach(System.out::println);
//		parentWithChildren.forEach((key, value) -> {System.out.println("FILE : KEY : " + key + " ||  VALUE : " + value);});
//		nodesWithChildren.forEach((key, value) -> {System.out.println("NODE : KEY : " + key + " ||  VALUE : " + value);});
//		realNodes.forEach((key, value) -> {System.out.println("REAL NODE : KEY : " + key + " ||  VALUE : " + value);});
	
/*		
	// For each file 
	for (File f : fullDir) {
		// If it has children
		if (f.listFiles() != null) {
			// We get those in an array
			File[] tabFiles = f.listFiles();

			// For each of those children
			for (File file : tabFiles) {
				// We create a node that represent the child
				DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(file.getName(), file.isDirectory());
				// We create an entry in the "stringNodeMap", with the "file" name and a node that represents the same "file"
				stringNodeMap.put(file.getName(),  childNode);
				// We create an entry in the "stringFileMap", with the "file" name and the "file" itself
				stringFileMap.put(file.getName(), file);
			}
			
			// We add an new entry in the bigNodeMap with the name of the "f" (as String) and "stringNodeMap" that has the name of the child ("file") and a node representing "file", so the child.
			// So for the String, String, Node --> first is the name of the parent, second is the name of the child, Node is the childNode
			bigNodeMap.put(f.getName(), stringNodeMap);
			// We add an entry in the bigFileMap with the name of "f" (as String) and the "stringFileMap" that has the name of "file" (as String) and the "file" (as File)
			// So for the String, String, File --> first is the name of the parent, second is the name of the child, File is the child file.
			bigFileMap.put(f.getName(), stringFileMap);
			
		// Else it means that "f" does not have any children
		} else {
			// So we create a tempNode from "f"
			DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(f.getName(), f.isDirectory());
			// We add a new entry in the "bigNodeMap" with the name of "f" (as String) and "null" as HashMap parameter because the HashMap represents the children (none existent here).
			bigNodeMap.put(f.getName(), null);
			// We add a new entry in the "bigFileMap" with the name of "f" (as String) and "null" as HashMap parameter because the HashMap represents the children (none existent here).
			bigFileMap.put(f.getName(),  null);
		}
		
	}

*/	
	
	/*
	
	// Represents the parent with an ArrayList of its children
	HashMap<File,ArrayList<File>> parentFileWithChildren = new HashMap<>();
	// Represents the temp parent with an ArrayList of its children
	HashMap<File,ArrayList<File>> tempParentFileWithChildren = new HashMap<>();
	
	// Represents the parent with an ArrayList of its children
	HashMap<File,HashSet<File>> parentFileWithHChildren = new HashMap<>();
	// Represents the temp parent with an ArrayList of its children
	HashMap<File,ArrayList<File>> tempParentFileWithHChildren = new HashMap<>();
	
	HashSet<File> tempSet = new HashSet<>();
	
	// To get the files that are only at last position.
	ArrayList<File> lastFiles = new ArrayList<File>();
	// For each file in fullElement, saves the "last" files in the ArrayList "lastFiles"
	for (File f : fullElement) {
		if (hasChild(f) == false) {
			lastFiles.add(f);
		}
	}
	
	int nbrLastFiles = nbrFiles(convertArrayListFileToArrayFile(lastFiles));
	
//	fullElement.stream().map(e -> e.getName()).forEach(System.out::println);
//	System.out.println(nbrFiles(convertArrayListFileToArrayFile(lastFiles)));
	System.out.println("**********************************");
	
//	fullElement.stream().map(e->e.getName()).forEach(System.out::println);
	
	// First Parent, Second Children
	HashMap<File, ArrayList<File>> mapAll = new HashMap<>();
	
	// First the name of the actual element, Second is the number of element inside the first file
	HashMap<File, Integer> mapAllNbr = new HashMap<>();
	
	// First Parent, Second children
	HashMap<File, File[]> mapParentChild = new HashMap<>();
	
	HashMap<String,ArrayList<File>> nameNodes = new HashMap<>();
	
	// test
	HashMap<File, ArrayList<File>> aaF = new HashMap<>();
	
	for (File f : fullElement) {
		File parent = f.getParentFile();
		File[] tabChildren = f.getParentFile().listFiles();
		mapAll.put(parent,convertTabToArrayList(tabChildren));
		
		
		
		mapAllNbr.put(f,  nbrFiles(f));
		mapParentChild.put(f.getParentFile(), f.getParentFile().listFiles());
	}
	
	//System.out.println(mapAll);
	mapAll.forEach((key,value) -> {
	//	System.out.println("KEY : " + key.getName() + "  VALUE : " + value);
	});
	mapAllNbr.forEach((key,value) -> {
		System.out.println("Name of the Element : " + key.getName() + "  Number of elements inside the element : " + value);
	});
	mapParentChild.forEach((key,value) -> {
	//	System.out.println("Name of the Parent : " + key.getName() + "  Array of Children : " + value);
		ArrayList<File> aF = convertTabToArrayList(value);
		aaF.put(key, aF);
	});
	
	
	aaF.forEach((key,value) -> {
		System.out.print("Name of the Parent : " + key.getName() + "  Array of Children : ");
		value.stream().map(e->e.getName() + " | ").forEach(System.out::print);
		System.out.println(" ");
	});
	
	aaF.forEach((key,value) -> {
		nameNodes.put(key.getName(), value);
	});
	
	// To save all elements into nodes in an arrayList
	ArrayList<DefaultMutableTreeNode> allNodes = new ArrayList<DefaultMutableTreeNode>();
	ArrayList<DefaultMutableTreeNode> processedNodes = new ArrayList<DefaultMutableTreeNode>();

	
	
	// Creation of all nodes
	for (File f : fullElement) {
		allNodes.add(new DefaultMutableTreeNode(f.getName(),f.isDirectory()));
	}
	
	

	// For each nodes (that we got from converting each elements)
	for (DefaultMutableTreeNode n : allNodes) {
		// Temp ArrayList<File> creation that will get the files representing the children from the HashMap nameNodes (String - Children Files)
		ArrayList<File> tempFiles = nameNodes.get(n.toString());
		// If tempFiles is null it means that, the String representation of the file that has the same name as the node, does not represents a file that has children.
		if (tempFiles != null) {
			for (File f : tempFiles) {
				DefaultMutableTreeNode temps = new DefaultMutableTreeNode(f.getName(),f.isDirectory());
				// Adds children representing files, to the node n
				n.add(temps);
				processedNodes.add(n);
				
			}
			// removes the String representation of n inside nameNodes, wich removes the arrayList<File> that contains the children, as n should already has those children nodes into it.
		//	nameNodes.remove(n.toString());
		}
	}
	
	System.out.println("!!!!!" + nameNodes.toString());
	
	DefaultMutableTreeNode RootN = new DefaultMutableTreeNode("UserFiles", true);
	DefaultMutableTreeNode tempN;

	
	ArrayList<File> newArrayListFile = nameNodes.get("UserFiles");
	ArrayList<File> tempArrayListFile;
	int tempNbr = 0;
	
	// for each file inside "UserFiles", so for each children of "UserFiles"
	for (File f : newArrayListFile) {
		RootN.add(new DefaultMutableTreeNode(f.getName(),f.isDirectory()));
		tempNbr += 1;
	}
	
	// This is something to work for...
	for (int i = 0; i == tempNbr; i++) {
		if (RootN.getChildAt(i).getAllowsChildren() == true) {
			newArrayListFile = nameNodes.get(RootN.getChildAt(i).toString());
			tempN = (DefaultMutableTreeNode) RootN.getChildAt(i);
			for (File fil : newArrayListFile) {
				tempN.add(new DefaultMutableTreeNode(fil.getName(),fil.isDirectory()));
			}
			RootN.insert(tempN, i);
		}
	}
	
	for (File f : newArrayListFile) {
		tempArrayListFile = nameNodes.get(f.getName());
		if (tempArrayListFile != null) {
			for (File fi : tempArrayListFile) {
	//			RootN.getC
			}
		}
	}
	
	for (File f : newArrayListFile) {
		for (DefaultMutableTreeNode n : allNodes) {
			if (f.getName().equals(n.toString())) {
				RootN.add(n);
			}
		}
	}
	
	
	
	nameNodes.forEach((key,value)->{
		for (DefaultMutableTreeNode n : allNodes) {
			if (key.equals(n.toString())) {
			//	n.add()
			}
		}
	});
	
	for (DefaultMutableTreeNode n : allNodes) {
		System.out.println("NAME : " + n.toString() + "  NOMBRE ELEMENTS : " + n.getChildCount());
	}
	
	ArrayList<File> tempH = new ArrayList<>();
	
	for (File f : lastFiles) {
		File[] tempTabFile = getAllSiblings(f);
		for (File fi : tempTabFile) {
		//	System.out.println("*******" + fi.getName() + "***********");
			tempH.add(fi);
		}
	//	System.out.println("tempH : " + tempH);
		tempParentFileWithHChildren.put(f.getParentFile(), tempH);
	//	System.out.println(tempParentFileWithHChildren);
		tempH.clear();
		
		tempParentFileWithHChildren.forEach((key,value) -> {
			tempSet.addAll(value);
			parentFileWithHChildren.put(key, tempSet);
		});
	}
	
	parentFileWithHChildren.forEach((key,value) -> {
	//	System.out.println("KEY : " + key.getName());
	//	value.stream().map(e->e.getName()).forEach(System.out::println);
	});
	
	ArrayList<File> tempHierarchyFile = new ArrayList<File>();
	HashMap<File, ArrayList<File>> lastToHierarchyFile = new HashMap<File, ArrayList<File>>();
	
//	lastFiles.stream().map(e -> e.getName()).forEach(System.out::println);
	
	// First the parent, Second the child
	HashMap<File, File> parentChildMap = new HashMap<File, File>();
	
	for (File f : lastFiles) {
		parentChildMap.put(f.getParentFile(), f);
	}
	
	parentChildMap.forEach((key, value)->{
		//	System.out.println("PARENT : " + key.getName() + " ENFANT : " + value.getName());
		});
	
	
	
	// For each file that is "Last"
	for (File f : lastFiles) {
		// Temp file creation
		File tempFile = f;
		// While the parent of tempFile is not equal to "UserFiles"
		while (!tempFile.getParentFile().getName().equals("UserFiles")) {
			// If the great parent of tempFile is equal to "UserFiles"
			if (tempFile.getParentFile().getParentFile().getName().equals("UserFiles")) {
				// Adds the tempFile to the tempHierarchyFile ArrayList
				tempHierarchyFile.add(tempFile);
				// Adds the tempFile's parent to the tempHierarchyFile ArrayList
				tempHierarchyFile.add(tempFile.getParentFile());
			}
			// Adds the tempFile to the tempHierarchyFile ArrayList
			tempHierarchyFile.add(tempFile);
			// Updates the tempFile to its parent.
			tempFile = tempFile.getParentFile();
		}
	}
	
	*/
	
	/*
	if (f.getParentFile().getName().equals("UserFiles")) {
		lastToHierarchyFile.put(f, tempHierarchyFile);
	} else {
		tempHierarchyFile.add(f);
	}
	*/
	/*
	 * 
	 * while (isAtTop == false) {
			if (f.getParentFile().getName().equals("UserFiles")) {
				isAtTop = true;
			}
			tempHierarchyFile.add(f);
		}
	
	*/
	
	
	/*
	 *  boolean isChildAndParent(File file) -> return "true" if the file passed as param is a child and a parent at the same time.
	 *  ArrayList<File> getChildrenFromList(File parent, ArrayList<File> listFiles) -> Give a file and a list of files, get the children of the file, from the list of files, in an ArrayList
	 *  ArrayList<File> getChildrenOfFile(File file) -> Give a File and gets an ArrayList of its children (or null if there aren't any)
	 *  ArrayList<File> getFilesOfParent(File parent, ArrayList<File> checkList) -> Give a File parent and an ArrayList<File> to get an ArrayList<File> that contains every File that has the same parent.
	 *  File[] getFilesWithSameParent(File[] tabFiles, File parentFile) -> Returns an array of File[] that contains every files that share the same parent : parentFile.
	 *  File[] getAllSiblings(File file) -> Give a File and get an array of files sharing the same parent
	 *  boolean checkForSiblings(File fichier) -> Give a File and returns true if the file as at least one sibling, false otherwise.
	 *  File[] getSiblings(File fichier) -> Give a File and returns its siblings as an array (returns the list of children from the parent minus the file itself as an array).
	 *  int getSiblingsCount(File fichier) -> int getSiblingsCount(File fichier) 
	 *  boolean hasChild(File file) -> returns True if and only if the file has at least one child. False otherwise.
	 *  
	 *  
	 *  
	 *  
	 */
	
	
	/*
	 * 
	 * 	// Give a file and a list of files, get the children of the file, from the list of files, in an ArrayList
	public ArrayList<File> getChildrenFromList(File parent, ArrayList<File> listFiles) {
		ArrayList<File> children = new ArrayList<File>();
		for (File f : listFiles) {
			if (f.getParentFile().getName().equals(parent.getName())) {
				children.add(f);
			}
		}
		return children;
	}
	
	 */
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/*
	
	public HashSet<File> processList(HashMap<File, ArrayList<File>> parentWithChildren, HashMap<String, ArrayList<DefaultMutableTreeNode>> nodeWithChildren){
		
		HashMap<File, ArrayList<File>> tempParentWithChildren = parentWithChildren;
		HashMap<String, ArrayList<DefaultMutableTreeNode>> tempRealNodes = nodeWithChildren;
		
		ArrayList<File> tempFiles = new ArrayList<File>();
		HashSet<File> finalFiles = new HashSet<File>();
		
		parentWithChildren.forEach((key, value) -> {
			for (File f : value) {
				if (f.isDirectory()) {
					
				} else {
					tempFiles.add(f);
				}
			}
		});
		
		for (File file : tempFiles) {
			File tFile = file;
			String parentName = file.getParentFile().getName();
			for (File fi : tempFiles) {
				if (fi.getParentFile().getName().equals(parentName)) {
					finalFiles.add(fi);
				}
			}
		}
		
		return null;
	}
	
	*/
}
