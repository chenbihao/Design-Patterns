package Builder;

public class Director {
	private Builder builder;
	public Director (Builder builder){
		this.builder=builder;
	}
	public void construct(){	//编写文档
		builder.makeTitle("Greeting");
		builder.makeString("白天");
		builder.makeItems(new String[]{"早上好","下午好"});
		builder.makeString("夜晚");
		builder.makeItems(new String[]{"晚上好","晚安","再见"});
		builder.close();
	}
}
