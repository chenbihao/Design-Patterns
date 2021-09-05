package Composite;

/**
 * @author CC
 * @Description 用来实现File类和Directory类的一致性
 */
public abstract class Entry {
	public abstract String getName();

	public abstract int getSize();

	public Entry add(Entry entry) throws FileTreatementException {
		throw new FileTreatementException();
	}

	public void printList() {
		printList("");
	}

	protected abstract void printList(String prefix);

	@Override
	public String toString() {
		return getName() + "[" + getSize() + "]";
	}

}
