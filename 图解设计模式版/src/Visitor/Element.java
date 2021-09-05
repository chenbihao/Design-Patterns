package Visitor;

/**
 * @author CC
 * @Description 表示数据结构的接口，它接受访问者的访问
 */
public interface Element  {
	public abstract void accept(Visitor v);
}
