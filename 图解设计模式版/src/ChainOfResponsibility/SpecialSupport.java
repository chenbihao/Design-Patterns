package ChainOfResponsibility;

/**
 * @author CC
 * @Description 解决指定编号
 */
public class SpecialSupport extends Support {
	private int number;
	public SpecialSupport(String name,int number) {
		super(name);
		this.number=number;
	}

	@Override
	protected boolean resolve(Trouble trouble) {
		if(trouble.getNumber()==number){
			return true;
		}else{
			return false;
		}
	}

}
