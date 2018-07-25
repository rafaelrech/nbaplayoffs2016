package rio2016.util;

public enum MedalEnum {
	GOLD(1, "gold", "Ouro"), SILVER(2, "silver", "Prata"), BRONZE(3, "bronze", "Bronze"), NO_MEDAL(99, "none", "Sem Medalha");
	private final int id;
	private final String nameKey;
	private final String defaultName;

	MedalEnum(int id, String nameKey, String defaultName) {
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
