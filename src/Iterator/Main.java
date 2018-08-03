package Iterator;

public class Main {

	public static void main(String[] args) {
		BookShelf bookShelf = new BookShelf(4);
		bookShelf.addpendBook(new Book("设计模式A"));
		bookShelf.addpendBook(new Book("设计模式B"));
		bookShelf.addpendBook(new Book("设计模式C"));
		bookShelf.addpendBook(new Book("设计模式D"));
		bookShelf.addpendBook(new Book("设计模式E"));
		bookShelf.addpendBook(new Book("设计模式F"));
		bookShelf.addpendBook(new Book("设计模式G"));
		Iterator it = bookShelf.iterator();

		while (it.hasNext()) {
			Book book = (Book) it.next();
			System.out.println(book.getName());
		}
		
		/** 
		 * 	为什么要引入迭代器（Iterator）呢？
		 *	这里只使用了Iterator的hasNext和next方法
		 *	也就是说 不依赖于BookShelf的实现
		 *	所以不管BookShelf怎么改变，只要BookShelf的iterator()方法有效
		 *	即使不对while循环进行修改，代码也能正常运行
		 *	帮助我们编写“可复用”的代码
		 */
		
		/**
		 * 	将遍历功能置于Aggregate角色外是Iterator模式的一个特征
		 * 	根据这个特征可以针对一个ConcreteAggregate角色编写多个ConcreteIterator角色
		 */
		
		/**
		 * 	遍历方法可以多种多样
		 * 	不需要deleteIterator 
		 */
		
	}
}
