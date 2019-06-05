package util.storage;

import java.io.Serializable;

/**
 * Used to store three objects
 * 
 * @author Oliver
 *
 * @param <A>
 *            First object
 * @param <B>
 *            Second object
 * @param <C>
 *            Third object
 */
public class Triplet<A, B, C> implements Serializable {

	private static final long serialVersionUID = 1L;

	private final A a;
	private final B b;
	private final C c;

	/**
	 * Used to store three objects
	 * 
	 * @param a
	 *            First object
	 * @param b
	 *            Second object
	 * @param c
	 *            Third object
	 */
	public Triplet(A a, B b, C c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	@Override
	public String toString() {
		return "Triplet[" + a.toString() + "," + b.toString() + " , " + c.toString() + "]";
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

	/**
	 * 
	 * @return Third object
	 */
	public C getC() {
		return c;
	}
}
