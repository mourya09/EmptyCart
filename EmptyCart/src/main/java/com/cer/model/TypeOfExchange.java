package com.cer.model;

public enum TypeOfExchange {
	NSE(0),
	BSE(1);
	
	
	private final int value;
	TypeOfExchange(final int newValue)
	{
		value = newValue;
	}
	public int getValue() { return value; }
}
