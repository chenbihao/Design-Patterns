package ChainOfResponsibility;

/**
 * @author CC
 * @Description 职责链 Chain of Responsibility
 */
public class Main {

	public static void main(String[] args) {
		Support no=new NoSupport("no");
		Support limit1=new LimitSupport("limit1",100);
		Support special=new SpecialSupport("special",429);
		Support limit2=new LimitSupport("limit2",200);
		Support odd=new OddSupport("odd");
		Support limit3=new LimitSupport("limit3",300);
		
		//no.setNext(limit1.setNext(special).setNext(limit2).setNext(odd.setNext(limit3)));
		no.setNext(limit1).setNext(special).setNext(limit2).setNext(odd).setNext(limit3);
		
		for (int i = 0; i < 500; i+=33) {
			no.support(new Trouble(i));
		}
		
	}

}
