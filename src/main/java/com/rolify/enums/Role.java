package com.rolify.enums;

public enum Role {
	MJ(0), JOUEUR(1);
	
	private int numVal;
	
	Role(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }
}
