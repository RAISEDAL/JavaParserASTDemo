package ca.dal.cs.raise.astdemo.core;

public class B {
	public String name;
	public int roll;

	/***
	 * 
	 * @param name
	 * @param roll
	 */
	public B(String name, int roll) {
		this.name = name;
		this.roll = roll;
	}

	/***
	 * 
	 * @param fooName
	 */
	public B(String fooName) {
		// do something
		this.name = fooName;
	}

}
