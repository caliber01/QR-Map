package com.example.qr_map.Models;

public class Sponsor {
	//Private Fields
	private String Name;
	private String WebSite;
	private String Address;
	private String Telephone;
	private String Description;
	//Constructors
	public Sponsor()
	{
		
	}
	public Sponsor(
			String _Name,
			String _WebSite,
			String _Address,
			String _Telephone,
			String _Description
			)
	{
		Name = _Name;
		WebSite = _WebSite;
		Address = _Address;
		Telephone = _Telephone;
		Description = _Description;
		
	}
	//Accessors
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	public String getWebSite() {
		return WebSite;
	}
	public void setWebSite(String webSite) {
		WebSite = webSite;
	}
	
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	
	public String getTelephone() {
		return Telephone;
	}
	public void setTelephone(String telephone) {
		Telephone = telephone;
	}
	
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
}
