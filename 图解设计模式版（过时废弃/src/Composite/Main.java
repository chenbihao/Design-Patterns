package Composite;

public class Main {

	public static void main(String[] args) throws FileTreatementException {
		System.out.println("Making root entries...");
		Directory rootdir = new Directory("root");
		Directory bindir = new Directory("bin");
		Directory tmpdir = new Directory("tmp");
		Directory usrdir = new Directory("usr");
		rootdir.add(bindir);
		rootdir.add(tmpdir);
		rootdir.add(usrdir);
		bindir.add(new File("vi", 1000));
		bindir.add(new File("latex", 2000));
		rootdir.printList();

		System.out.println("");
		System.out.println("Making user entries...");
		Directory da = new Directory("da");
		Directory db = new Directory("db");
		Directory dc = new Directory("dc");
		usrdir.add(da);
		usrdir.add(db);
		usrdir.add(dc);
		da.add(new File("da.html", 230));
		da.add(new File("da.doc", 210));
		db.add(new File("db.java", 130));
		db.add(new File("db.class", 130));
		dc.add(new File("dc.txt", 230));
		rootdir.printList();
		
	}

}
