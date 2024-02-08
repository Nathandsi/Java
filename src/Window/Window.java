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
import java.net.URL;
import java.util.ArrayList;

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
public class Window extends JFrame implements ActionListener, WindowListener{
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
	
	private DefaultMutableTreeNode tempNode;
	private ArrayList<DefaultMutableTreeNode> arrayNodes = new ArrayList<DefaultMutableTreeNode>();
	private ArrayList<DefaultMutableTreeNode> tempNodeList = new ArrayList<DefaultMutableTreeNode>();
	
	// private JComboBox<String> selectionList = new JComboBox<String>();
	
	
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
		
		
//		listDirectory(rootFile.listFiles());
//		arrayFile.stream().map(element -> element.getAbsolutePath()).forEach(System.out::println);
		
		if (rootFile.list() != null) {
			// CustomDirectory creation, from the source folder (rootFile), with recursivity set to "true"
			CustomDirectory customDir = new CustomDirectory(rootFile, true);
			// Invoking the "listD()" method from the "CustomDirectory" class that lists all the elements from the folder
			customDir.listD();
			// Invoking the "showFile()" method that return an ArrayList, streamed and mapped to only keep the name, then println each line (that represents an element)
			customDir.showFiles().stream().map(element -> element.getName()).forEach(System.out::println);
			
//			customDir.showFiles().stream().map(element -> element.getName()).forEach(DefaultMutableTreeNode::new);
			customDir.showFiles().stream().forEach((element) -> {
				if (element.isDirectory()) {
					
				}
				arrayNodes.add(convertFileToNode(element));
				});
			
			
		}

		

	

		
	}
	
	private ArrayList<DefaultMutableTreeNode> getNodesFromDir(File file) {
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
	
	private int checkNbrDir(File file) {
		int nbr = 0;
		File[] tabFile = file.listFiles();
		for (File f : tabFile) {
			if (f.isDirectory()) {nbr += 1;}
		}
		return nbr;
	}
	
	
	private DefaultMutableTreeNode convertFileToNode(File file) {
		if (file.isDirectory()) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(new NodeInfo(file.getName(), (String) file.getPath(), true));
			return node;
		} else {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(new NodeInfo(file.getName(), (String) file.getPath(), false));
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
}
