package evolutionary_algorithms.complement;

public class Probability implements Cloneable {
    private Object key;
    private Object value;
	private float probability;
	
	@Override
	public Probability clone() {
		try {
			return (Probability) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError(); // Can't happen
		}
	}
	
	public float getProbability() {
		return probability;
	}
	public void setProbability(float probability) {
		this.probability = probability;
	}
	public Object getKey() {
		return key;
	}
	public void setKey(Object key) {
		this.key = key;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
}
