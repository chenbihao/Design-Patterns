package Facade.pagemaker;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

class Database {
	private Database() {
	}// 防止外部new出实例

	public static Properties getProperties(String dbname) {
		String filename = "C:\\Software\\JAVA\\work\\Design-Patterns\\src\\Facade\\" + dbname + ".txt";
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(filename));// 没写成
														// 绝对值这里会报错，因为是static，也没办法用getClass来获取
		} catch (IOException e) {
			System.out.println("错误：文件不存在");
		}
		return prop;
	}
}
