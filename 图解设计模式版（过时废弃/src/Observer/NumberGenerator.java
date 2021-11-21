package Observer;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class NumberGenerator {
	private ArrayList obs = new ArrayList<>();

	public void addObserver(Observer observer) {
		this.obs.add(observer);
	}

	public void deleteObserver(Observer observer) {
		this.obs.remove(observer);
	}

	public void notifyObservers() {
		Iterator it = obs.iterator();
		while (it.hasNext()) {
			Observer o = (Observer) it.next();
			o.update(this);
		}
	}

	public abstract int getNumber();

	public abstract void execute();
}
