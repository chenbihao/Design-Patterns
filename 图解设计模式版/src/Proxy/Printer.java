package Proxy;

public class Printer implements Printable {
	private String name;
	public Printer() {
		super();
		
	}
	public Printer(String name) {
		super();
		this.name = name;
		heavyJob("生成Printer实例("+name+")中");
	}

	@Override
	public void setPrinterName(String name) {
		this.name = name;
	}

	@Override
	public String getPrinterName() {
		return name;
	}

	@Override
	public void print(String str) {
		System.out.println("=== "+name+" ===");
		System.out.println(str);
	}
	
	public void heavyJob(String msg){
		System.out.print(msg);
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.print(".");
		}
		System.out.println("完成：");
	}
	
}
