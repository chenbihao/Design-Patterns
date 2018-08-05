package Adapter2;

/**
 * @author CC
 * @Description Client(请求者) 委托 适配器
 */
public class Main {

/**
 * 使用现有的类时，可以使用适配模式来把这些现有的类当做组件使用
 * 当出现bug时我们可以很明确的知道bug不是由现有的类产生的
 * 可以不改变现有代码的前提下适配新的接口（API）
 * 可以解决新旧版本兼容性问题
 */

	public static void main(String[] args) {
		Print p = new PrintBanner("test");
		p.printWeak();
		p.printStrong();
		
	}

}
