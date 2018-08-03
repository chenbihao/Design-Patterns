package Iterator;

/**
 * @author CC
 * @Description 迭代器 Iterator
 */
public interface Iterator {

	public abstract boolean hasNext();
	//next=returnCurrentElementAndAdvanceToNextPosition
	public abstract Object next();

}
