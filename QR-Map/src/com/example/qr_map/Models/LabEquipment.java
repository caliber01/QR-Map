package com.example.qr_map.Models;


public class LabEquipment extends Equipment {
	
	//Private Fields
	private String Number;
	private String Electronic;
	private boolean HasProjector;
	private boolean HasWiFi;
	private String WiFiName;
	private int Tables;
	private int Chairs;
	
	//Constructors
	public LabEquipment()
	{
		
	}
	
	public LabEquipment(
			String _Number,
			String _Electronic,
			boolean _HasProjector,
			boolean _HasWiFi,
			String _WiFiName,
			int _Tables,
			int _Chairs
			)
	{
		Number = _Number;
		Electronic = _Electronic;
		HasProjector = _HasProjector;
		HasWiFi = _HasWiFi;
		WiFiName = _WiFiName;
		Tables = _Tables;
		Chairs = _Chairs;
	}
	
	//Accessors
	public String getNumber() {
		return Number;
	}
	public void setNumber(String number) {
		Number = number;
	}
	
	public String getElectronic() {
		return Electronic;
	}
	public void setElectronic(String electronic) {
		Electronic = electronic;
	}
	
	public boolean hasProjector() {
		return HasProjector;
	}
	public void setHasProjector(boolean hasProjector) {
		HasProjector = hasProjector;
	}
	
	public boolean hasWiFi() {
		return HasWiFi;
	}
	public void setHasWiFi(boolean hasWiFi) {
		HasWiFi = hasWiFi;
	}
	
	public String getWiFiName() {
		return WiFiName;
	}
	public void setWiFiName(String wiFiName) {
		WiFiName = wiFiName;
	}
	
	public int getTables() {
		return Tables;
	}
	public void setTables(int tables) {
		Tables = tables;
	}
	
	public int getChairs() {
		return Chairs;
	}
	public void setChairs(int chairs) {
		Chairs = chairs;
	}
}
