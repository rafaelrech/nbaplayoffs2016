package rio2016.util;

public enum SportTypeEnum {
	INDIVIDUAL(1, "individual", "Individual"), DOUBLE(2, "double", "Duplas"), TEAM(9, "team", "Equipe");
	private final int id;
	private final String nameKey;
	private final String defaultName;

	
	SportTypeEnum(int id, String nameKey, String defaultName) {
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
