package util.storage;

import java.io.Serializable;

/**
 * Used to store two objects
 * 
 * @author Oliver
 *
 * @param <A>
 *            First object
 * @param <B>
 *            Second object
 */
public class Pair<A, B> implements Serializable {

	private static final long serialVersionUID = 1L;

	private final A a;
	private final B b;

	/**
	 * Used to store two objects
	 * 
	 * @param a
	 *            First object
	 * @param b
	 *            Second object
	 */
	public Pair(A a, B b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public String toString() {
		return "Pair[" + a.toString() + "," + b.toString() + "]";
	}

	/**
	 * 
	 * @return First object
	 */
	public A getA() {
		return a;
	}

	/**
	 * 
	 * @return Second object
	 */
	public B getB() {
		return b;
	}
}
