/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui.SV_CORE;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Pair<A, B> {
	private A first;
	private B second;

	public Pair(A first, B second) {
		this.first = first;
		this.second = second;
	}

	public int hashCode() {
		int hashFirst = this.first != null ? this.first.hashCode() : 0;
		int hashSecond = this.second != null ? this.second.hashCode() : 0;
		return (hashFirst + hashSecond) * hashSecond + hashFirst;
	}

	public boolean equals(Object other) {
		if (other instanceof Pair) {
			Pair otherPair = (Pair) other;
			return (this.first == otherPair.first || this.first != null
					&& otherPair.first != null
					&& this.first.equals(otherPair.first))
					&& (this.second == otherPair.second || this.second != null
							&& otherPair.second != null
							&& this.second.equals(otherPair.second));
		}
		return false;
	}

	public String toString() {
		return "(" + this.first + ", " + this.second + ")";
	}

	public A getFirst() {
		return this.first;
	}

	public B getSecond() {
		return this.second;
	}

	public void setFirst(A first) {
		this.first = first;
	}

	public void setSecond(B second) {
		this.second = second;
	}
}
