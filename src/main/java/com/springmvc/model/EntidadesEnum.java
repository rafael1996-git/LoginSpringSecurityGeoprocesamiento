package com.springmvc.model;

public enum EntidadesEnum {
	
	ENTIDAD01("01"),ENTIDAD02("02"),ENTIDAD03("03"),ENTIDAD04("04"),ENTIDAD05("05"),ENTIDAD06("06"),
	ENTIDAD07("07"),ENTIDAD08("08"),ENTIDAD09("09"),ENTIDAD10("10"),ENTIDAD11("11"),ENTIDAD12("12"),ENTIDAD13("13"),
	ENTIDAD14("14"),ENTIDAD15("15"),ENTIDAD16("16"),ENTIDAD17("17"),ENTIDAD18("18"),ENTIDAD19("19"),ENTIDAD20("20"),
	ENTIDAD21("21"),ENTIDAD22("22"),ENTIDAD23("23"),ENTIDAD24("24"),ENTIDAD25("25"),ENTIDAD26("26"),ENTIDAD27("27"),
	ENTIDAD28("28"),ENTIDAD29("29"),ENTIDAD30("30"),ENTIDAD31("31"),ENTIDAD32("32");
	
	private String entidadId;

	EntidadesEnum(String entidadId) {
		this.entidadId=entidadId;
	}

	public String getEntidadId() {
		return entidadId;
	}


}
