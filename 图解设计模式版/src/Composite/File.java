package Composite;

public class File extends Entry {
	private String Name;
	private int Size;

	public File(String name, int size) {
		super();
		Name = name;
		Size = size;
	}

	@Override
	public String getName() {
		return Name;
	}

	@Override
	public int getSize() {
		return Size;
	}

	@Override
	protected void printList(String prefix) {
		System.out.println(prefix + "/" + this);// 等价+this.toString()/+toString()
	}

}
