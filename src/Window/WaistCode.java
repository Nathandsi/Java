package Window;

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

	
}
