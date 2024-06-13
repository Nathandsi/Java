package Window;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;

public class CustomNode extends DefaultMutableTreeNode {

	private String nom;
	private boolean allowChildren;
	
	public CustomNode(String nom, Boolean allowChildren) {
		super(nom, allowChildren);
	}
	
	@Override
	public boolean isLeaf() {
		if (getChildCount() == 0 && getAllowsChildren() == true) {
			return false;
		}
		return (getChildCount() == 0);
	}

//	@Override
//	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
//			boolean leaf, int row, boolean hasFocus) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	
}
