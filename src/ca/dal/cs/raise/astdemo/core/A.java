package ca.dal.cs.raise.astdemo.core;

public class A {

	/** A great example of feature envy code smell! **/

	B b;
	String sampleField;

	public A() {
		b = new B("John", 125);
	}

	/***
	 * 
	 * @return name
	 */
	public String getName() {
		return b.name;
	}

	/***
	 * 
	 * @return roll
	 */
	public int getRoll() {
		return b.roll;
	}

	protected String getMyOwnField() {
		return this.sampleField;
	}
}
