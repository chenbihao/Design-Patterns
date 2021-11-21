package Prototype.framework;

//Cloneable本身没有方法，只是告诉程序可以使用clone()
public interface Product extends Cloneable {
	
	public abstract void use(String s);
	public abstract Product createClone();
	
}
