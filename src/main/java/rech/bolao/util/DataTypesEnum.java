package rech.bolao.util;

public enum DataTypesEnum {
	TEXT("varchar(50)"), PWD("varchar(20)"), DATE("DATETIME"), NUMBER("int(7)"), BIGTEXT("varchar(1000)");
	private final String dataType;

	DataTypesEnum(String str) {
		this.dataType = str;
	}

	public String get() {
		return this.dataType;
	}
}
