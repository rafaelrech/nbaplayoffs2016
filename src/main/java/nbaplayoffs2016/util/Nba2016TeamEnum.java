package nbaplayoffs2016.util;

public enum Nba2016TeamEnum {
	TEXT("varchar(50)"), PWD("varchar(20)"), DATE("DATETIME"), NUMBER("int(7)");
	private final String dataType;

	Nba2016TeamEnum(String str) {
		this.dataType = str;
	}

	public String get() {
		return this.dataType;
	}
}
