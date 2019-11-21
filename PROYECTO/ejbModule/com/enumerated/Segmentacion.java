package com.enumerated;

public enum Segmentacion {

	SI("Si"),
	NO("No"); 
	
	private String label;
	
	Segmentacion(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
