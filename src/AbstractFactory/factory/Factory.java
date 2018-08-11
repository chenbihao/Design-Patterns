package AbstractFactory.factory;

public abstract class Factory {
	public static Factory getFactory(String classname) {
		Factory factory = null;
		try { // 通过Class类的forName方法来动态地读取类信息，接着使用newInstance方法生成该类的实例
			factory = (Factory) Class.forName(classname).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return factory;
	}

	public abstract Link createLink(String caption, String url);

	public abstract Tray createTray(String caption);

	public abstract Page createPage(String title, String author);

}
