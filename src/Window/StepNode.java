package Window;

import javax.swing.tree.DefaultMutableTreeNode;

public class StepNode {

	private String nameFolder;
	private DefaultMutableTreeNode[] container;
	
	public StepNode(String nameFolder, DefaultMutableTreeNode[] container) {
		setName(nameFolder);
		setContainer(container);
	}
	
	private void setName(String name) {
		this.nameFolder = name;
	}
	
	private void setContainer(DefaultMutableTreeNode[] elements) {
		this.container = elements;
	}
	
	public String getName() {
		return this.nameFolder;
	}
	
	public DefaultMutableTreeNode[] getContainer() {
		return this.container;
	}
	
}
