package file_system_creation;

import java.util.HashMap;
import java.util.Map;

class FileSystemNode {
	String name;
	Map<String,FileSystemNode> children;

	FileSystemNode(String name) {
		this.name = name;
		children = new HashMap<>();
	}

	public FileSystemNode decend(String childName) {
		if (children.keySet().contains(childName))
			return children.get(childName);
		else {
			FileSystemNode newChild = new FileSystemNode(childName);
			children.put(childName, newChild);
			return newChild;
		}
	}
	
	public boolean mkdirIsNecessary(String childName) {
		if (children.keySet().contains(childName))
			return false;
		else return true;  
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileSystemNode other = (FileSystemNode) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FileSystemNode [name=" + name + ", number of children=" + children.size() + "]";
	}


}
