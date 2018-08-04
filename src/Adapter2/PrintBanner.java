package Adapter2;

/**
 * @author CC
 * @Description  适配器   Adapter(适配)
 */
public class PrintBanner extends Print{
	//只能继承一个类,所以交给字段banner
	private Banner banner;
	
	public PrintBanner(String string) {
		this.banner=new Banner(string);
	}

	@Override
	public void printWeak() {
		banner.showWithParen();
	}

	@Override
	public void printStrong() {
		banner.showWithAster();
	}
	
}
