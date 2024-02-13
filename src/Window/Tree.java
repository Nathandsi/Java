package Window;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class Tree extends DefaultTreeModel implements TreeModel {
	
	private static final long serialVersionUID = 1L;
	private DefaultMutableTreeNode root;
	private boolean isDir;
	
	
	public Tree(DefaultMutableTreeNode root, boolean isDir) {
		super(root, isDir);
		this.root = root;
		this.isDir = isDir;
	}
	
	public void addChild(DefaultMutableTreeNode parent, DefaultMutableTreeNode child) {
		parent.add(child);
	}

	public DefaultMutableTreeNode getRoot() {
		return this.root;
	}

	public DefaultMutableTreeNode getChildAt(DefaultMutableTreeNode parent, int index) {
		return (DefaultMutableTreeNode) parent.getChildAt(index);
	}

	public int getChildCount(DefaultMutableTreeNode parent) {
		return parent.getChildCount();
	}

	public boolean isLeaf(DefaultMutableTreeNode node) {
		return node.isLeaf();
	}

	public void valueForPathChanged(TreePath path, Object newValue) {
		
	}

	public int getIndexOfChild(DefaultMutableTreeNode parent, DefaultMutableTreeNode child) {
		return parent.getIndex(child);
	}

	@Override
	public void addTreeModelListener(TreeModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {
		// TODO Auto-generated method stub
		
	}

}
