package Visitor;

public class File extends Entry {
	private String Name;
	private int Size;
	public File(String name, int size) {
		super();
		Name = name;
		Size = size;
	}
	@Override
	public String getName() {return Name;}
	@Override
	public int getSize() {return Size;}

//	@Override
//	protected void printList(String prefix) {
//		System.out.println(prefix + "/" + this);// 等价+this.toString()/+toString()
//	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);//通过调用visit方法，可以告诉Visitor正在访问的对象是File类的实例this
	}

}
