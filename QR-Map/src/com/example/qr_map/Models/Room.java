package com.example.qr_map.Models;


public abstract class Room {
	
	//private Fields
	protected String Name;
	protected String Number;
	protected String Type;
	protected Equipment mEquipment;
	
	public Equipment getEquipment() {
		return mEquipment;
	}
	public void setEquipment(Equipment mEquipment) {
		this.mEquipment = mEquipment;
	}
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
