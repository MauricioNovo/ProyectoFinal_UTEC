package com.enumerated;

public enum TipoEstiba {
	
	SI("Si"),
	NO("No"); 
	
	private String label;
	
	TipoEstiba(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
