package Iterator;

import java.util.ArrayList;

/**
 * @author CC
 * @Description 具体的集合 ConcreteAggregate
 */
public class BookShelf implements Aggregate {

	//private Book[] books;
	//private int last = 0;
	private ArrayList books;
	
	//public BookShelf(int maxsize) {
	//	this.books = new Book[maxsize];
	//}
	public BookShelf(int initialsize) {
		this.books = new ArrayList(initialsize);
	}
	
	public Book getBookAt(int index) {
		//return books[index];
		return (Book)books.get(index);
	}

	public int getLength() {
		//return last;
		return books.size();
	}

	public void addpendBook(Book book) {
		//this.books[last] = book;
		//last++;
		books.add(book);
		
	}

	@Override
	public Iterator iterator() {
		return new BookShelfIterator(this);
	}

}
