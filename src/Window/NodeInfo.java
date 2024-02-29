package Window;

public class NodeInfo{
	private String nodeName;
	private String nodePath;
	private boolean isDir;
	private int nbrChild;
	private boolean isAtEnd;
	private String parent;
	
	public NodeInfo(String name, String url, String parent, boolean isDir, int nbrChild, boolean isAtEnd) {
		this.nodeName = name;
		this.isDir = isDir;
		this.nodePath =  url;
		this.nbrChild = nbrChild;
		this.isAtEnd = isAtEnd;
		this.parent = parent;
		if (nodePath == null) {
			System.out.println("Could not find the file : " + url);
		}
	}
	
	public String getNodeName() {
		return this.nodeName;
	}
	
	public String getNodePath() {
		return this.nodePath;
	}
	
	public boolean isAtEnd() {
		return this.isAtEnd;
	}
	
	public boolean isDir() {
		return this.isDir;
	}
	
	public String parent() {
		return this.parent;
	}
	
	public int getNbrChild() {
		return this.nbrChild;
	}
	
	public String toString() {
		String text =  nodeName +  " contient " + nbrChild + " éléments " + " et il est l'enfant de " + parent;
		return text;
	}
	
}
