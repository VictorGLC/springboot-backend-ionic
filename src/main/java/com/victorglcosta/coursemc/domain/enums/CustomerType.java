package com.victorglcosta.coursemc.domain.enums;

public enum CustomerType {
	
	NATURALPERSON(1, "Natural Person"),
	LEGALPERSON(2, "Legal Person");
	
	private int cod;
	private String description;
	
	private CustomerType(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}
	
	public static CustomerType toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(CustomerType x : CustomerType.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
			
		}
		
		throw new IllegalArgumentException("Invalid id: " + cod);
	}
}
