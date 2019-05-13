package pack;

public class ParsingModel {
	private String type;
	private Object value;
	
	public ParsingModel() {
		super();
	}

	public ParsingModel(String type, Object value) {
		super();
		this.type = type;
		this.value = value;
	}

	String getType() {
		return type;
	}

	void setType(String type) {
		this.type = type;
	}

	Object getValue() {
		return value;
	}

	void setValue(Object value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "\nParsingModel [type=" + type + ", value=" + value + "]";
	}
	
}
