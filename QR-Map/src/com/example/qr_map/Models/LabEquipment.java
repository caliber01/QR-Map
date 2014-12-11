package com.example.qr_map.Models;


public class LabEquipment extends Equipment {
	
	//Private Fields
	private String Number;
	private String Electronic;
	private boolean HasProjector;
	private boolean HasWiFi;
	private String WiFiName;
	private String Tables;
	private String Chairs;
	
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
			String _Tables,
			String _Chairs
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
	
	public String getTables() {
		return Tables;
	}
	public void setTables(String tables) {
		Tables = tables;
	}
	
	public String getChairs() {
		return Chairs;
	}
	public void setChairs(String chairs) {
		Chairs = chairs;
	}
}
