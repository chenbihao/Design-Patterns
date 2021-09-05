package Facade.pagemaker;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PageMaker {
	private PageMaker() {
	}

	public static void makeWelcomePage(String mailaddr, String filename) {
		try {
			Properties mailprop = Database.getProperties("maildata");
			String username = mailprop.getProperty(mailaddr);
			HtmlWriter writer = new HtmlWriter(new FileWriter(filename));
			writer.title("Welcome to " + username + "'s Page!");
			writer.paragraph("欢迎来到" + username + "的页面");
			writer.mailto(mailaddr, username);
			writer.close();
			System.out.println(filename + "创建于" + mailaddr + ":" + username);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
