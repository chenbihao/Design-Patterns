package ChainOfResponsibility;

public abstract class Support {
	private String name;
	private Support next;

	public Support(String name) {
		this.name = name;
	}

	public Support setNext(Support next) {
		this.next = next;
		return next;
	}

	public final void support(Trouble trouble) {
		if (resolve(trouble)) {
			done(trouble);
		} else if (next != null) {
			next.support(trouble);
		} else {
			fail(trouble);
		}
	}

	@Override
	public String toString() {
		return "[" + name + "]";
	}

	protected abstract boolean resolve(Trouble trouble);// 解决问题的方法

	protected void done(Trouble trouble) {// 解决
		System.out.println(this + "解决了" + trouble + ".");
	}

	protected void fail(Trouble trouble) {// 未解决
		System.out.println("未解决" + trouble + ".");
	}
}
