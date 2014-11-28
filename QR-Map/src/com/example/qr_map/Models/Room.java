package com.example.qr_map.Models;

import java.util.List;

public abstract class Room {
	
	//private Fields
	private String Name;
	private String Number;
	private String Type;
	
	//Accessors
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getNumber() {
		return Number;
	}
	public void setNumber(String number) {
		Number = number;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	
}
