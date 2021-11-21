package TemplateMethod;
/**
 * @author CC
 * @Description 模板方法模式
 */ 
public class Main {
	/**
	 * 模板方法模式的优点是可以在抽象父类中编写算法
	 * 要求父类子类之间的协作与一致性
	 * 处理的流程被定义在父类中，而具体的处理则交给了子类
	 */ 
	public static void main(String[] args) {
		AbstractDisplay d1=new CharDisplay('H');
		AbstractDisplay d2=new StringDisplay("aaaaaaaaaa");
		AbstractDisplay d3=new StringDisplay("bbbbbbbbbb");
		d1.display();
		d2.display();
		d3.display();
	}
}
