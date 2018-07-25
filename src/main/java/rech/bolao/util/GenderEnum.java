package rech.bolao.util;

public enum GenderEnum {
	MALE(1, "male", "Masculino"), FEMALE(2, "female", "Feminino"), MIXED(3, "mix", "Misto");
	private final int id;
	private final String nameKey;
	private final String defaultName;

	
	GenderEnum(int id, String nameKey, String defaultName) {
		this.id = id;
		this.nameKey = nameKey;
		this.defaultName = defaultName;
	}

	public int getId() {
		return this.id;
	}

	public String getKey() {
		return nameKey;
	}

	public String getDefaultName() {
		return defaultName;
	}

	
}
