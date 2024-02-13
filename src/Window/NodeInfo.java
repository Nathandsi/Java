package Window;

public class NodeInfo{
	private String nodeName;
	private String nodePath;
	private boolean isDir;
	private int nbrChild;
	
	public NodeInfo(String name, String url, boolean isDir, int nbrChild) {
		this.nodeName = name;
		this.isDir = isDir;
		this.nodePath =  url;
		this.nbrChild = nbrChild;
		if (nodePath == null) {
			System.out.println("Could not find the file : " + url);
		}
		
	}
	public boolean isDir() {
		return this.isDir;
	}
	public String toString() {
		return nodeName;
	}
}
