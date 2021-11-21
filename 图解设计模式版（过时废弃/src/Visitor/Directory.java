package Visitor;

import java.util.ArrayList;
import java.util.Iterator;

public class Directory extends Entry {
	private String name;
	private ArrayList directory = new ArrayList();
	public Directory(String name) {
		this.name = name;
	}
	@Override
	public String getName() {return name;}

	@Override
	public int getSize() {
		int size = 0;
		Iterator it = directory.iterator();
		// 递归
		while (it.hasNext()) {
			Entry entry = (Entry) it.next();
			size += entry.getSize();
		}
		return size;
	}

	public Entry add(Entry entry) {
		directory.add(entry);
		return this;
	}
	
	public Iterator iterator(){
		return directory.iterator();
	}
	
//	@Override
//	protected void printList(String prefix) {
//		System.out.println(prefix + "/" + this);
//		Iterator it = directory.iterator();
//		while (it.hasNext()) {
//			Entry entry = (Entry) it.next();
//			entry.printList(prefix + "/" + name);
//		}
//	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);//通过调用visit方法，可以告诉Visitor正在访问的对象是Directory类的实例this
	}

}
