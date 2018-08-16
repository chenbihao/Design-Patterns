package Visitor;

import java.util.Iterator;

/**
 * @author CC
 * @Description 用来实现File类和Directory类的一致性
 */										
public abstract class Entry implements Element {	//implements Element
	public abstract String getName();
	public abstract int getSize();
	public Entry add(Entry entry) throws FileTreatementException {
		throw new FileTreatementException();
	}
	public Iterator iterator() throws FileTreatementException{
		throw new FileTreatementException();
	}
	
//	protected abstract void printList(String prefix);
//	public void printList() {
//		printList("");
//	}
	
	@Override
	public String toString() {
		return getName() + "[" + getSize() + "]";
	}

}
