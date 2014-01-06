package core;

public class Case {

	public static final int EMPTY = 1;
	public static final int FULL = 2;
	public static final int BORDER = 3;

	private int id;
	private int state;

	public Case(int id) {
		this.id = id;
	}

	public void setId(int i) {
		this.id = i;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return this.state;
	}

	public int getId() {
		return this.id;
	}

}
