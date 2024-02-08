package Window;

public class NodeInfo{
	private String nodeName;
	private String nodePath;
	private boolean isDir;
	public NodeInfo(String name, String url, boolean isDir) {
		this.nodeName = name;
		this.isDir = isDir;
		this.nodePath =  url;
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
