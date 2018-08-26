package Proxy;

public class Main {

	public static void main(String[] args) {
		Printable p=new PrinterProxy("C");
		System.out.println("现在名字是："+p.getPrinterName());
		p.setPrinterName("Cc");
		System.out.println("现在名字是："+p.getPrinterName());
		p.print("hello world!");
	}

}
