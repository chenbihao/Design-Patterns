package Builder;

/**
 * @author CC
 * @Description 构建者 Builder
 */
public class Main {

	public static void main(String[] args) {
		if(args.length!=1){
			usage();
			System.exit(0);
		}
		if(args[0].equals("plain")){
			TextBuilder textbuilder =new TextBuilder();
			Director director=new Director(textbuilder);
			director.construct();
			String result=textbuilder.getResult();
			System.out.println(result);
		}else if(args[0].equals("html")){
			HTMLBuilder htmlBuilder=new HTMLBuilder();
			Director director=new Director(htmlBuilder);
			director.construct();
			String filename=htmlBuilder.getResult();
			System.out.println(filename+"文档编写完成");
		}else{
			usage();
			System.exit(0);
		}
	}

	private static void usage() {
		System.out.println("Usage: java Main plain    编写纯文本文档");
		System.out.println("Usage: java Main html     编写html文档");
	}

}
