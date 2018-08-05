package TemplateMethod;

public abstract class AbstractDisplay {
	//可以写成protected使其他包无法调用
	public abstract void open();
	public abstract void print();
	public abstract void close();
	//final表示强硬要求不能重写display方法
	//在GoF明确写着不应该重写模板方法
	public final void display(){
		open();
		for(int i=0;i<5;i++){
			print();
		}
		close();
	}
}
