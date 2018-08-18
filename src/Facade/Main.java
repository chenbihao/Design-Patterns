package Facade;

import Facade.pagemaker.PageMaker;

/**
 * @author CC
 * @Description 外观 Facade
 */
public class Main {

	public static void main(String[] args) {
		PageMaker.makeWelcomePage("aa@aa.com","welcome.html");
	}

}
