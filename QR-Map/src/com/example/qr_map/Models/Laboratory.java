package com.example.qr_map.Models;

import java.util.List;

import com.example.qr_map.R;


public class Laboratory extends Room {
	
	//Private Fields
	private String PhoneNumber;
	private String Activity;
	private double AverageRating;
	private String ChiefFIO;
	private List<String> LabAssistantsFIOs;
	private String WorkTime;
	private Sponsor Sponsor;
	private String Faculty;
	private String Cathedra;
	
	//Constructors
	public Laboratory()
	{
		
	}
	public Laboratory(
			String _Name,
			Sponsor _Sponsor,
			Equipment _Equipment,
			String _Number,
			String _Type,
			String _Faculty,
			String _Cathedra,
			String _ChiefFIO,
			List<String> _LabAssistantsFIOs,
			String _WorkTime,
			String _Activity,
			double _AverageRating
			)
	{
		Name = _Name;
		Sponsor = _Sponsor;
		mEquipment = _Equipment;
		Number = _Number;
		Type = _Type;
		Faculty = _Faculty;
		Cathedra = _Cathedra;
		ChiefFIO = _ChiefFIO;
		LabAssistantsFIOs = _LabAssistantsFIOs;
		WorkTime = _WorkTime;
		Activity = _Activity;
		AverageRating = _AverageRating;		
	}

	//Accessors
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	
	public String getActivity() {
		return Activity;
	}
	public void setActivity(String activity) {
		Activity = activity;
	}
	
	public double getAverageRating() {
		return AverageRating;
	}
	public void setAverageRating(double averageRating) {
		AverageRating = averageRating;
	}

	public String getChiefFIO() {
		return ChiefFIO;
	}
	public void setChiefFIO(String chiefFIO) {
		ChiefFIO = chiefFIO;
	}
	
	public List<String> getLabAssistantsFIOs() {
		return LabAssistantsFIOs;
	}
	
	public void setLabAssistantsFIOs(List<String> labAssistantsFIOs) {
		LabAssistantsFIOs = labAssistantsFIOs;
	}
	
	public String getWorkTime() {
		return WorkTime;
	}
	public void setWorkTime(String workTime) {
		WorkTime = workTime;
	}
	
	public Sponsor getSponsor() {
		return Sponsor;
	}
	public void setSponsor(Sponsor sponsor) {
		Sponsor = sponsor;
	}
	
	public String getFaculty() {
		return Faculty;
	}
	public void setFaculty(String faculty) {
		Faculty = faculty;
	}
	
	public String getCathedra() {
		return Cathedra;
	}
	public void setCathedra(String cathedra) {
		Cathedra = cathedra;
	}
	@Override
	public String toString() {
		return "Laboratory [PhoneNumber=" + PhoneNumber + ", Activity="
				+ Activity + ", AverageRating=" + AverageRating + ", ChiefFIO="
				+ ChiefFIO + ", LabAssistantsFIOs=" + LabAssistantsFIOs
				+ ", WorkTime=" + WorkTime + ", Sponsor=" + Sponsor
				+ ", Faculty=" + Faculty + ", Cathedra=" + Cathedra + ", Name="
				+ Name + ", Number=" + Number + ", Type=" + Type + "]";
	}
	
	
	//Methods
	public String getCampus(){
		String campus;
		switch(Number.charAt(Number.length()-1)){
		case '�':
			campus = "�";
			break;
		case '�':
			campus = "�";
			break;
		default:
			campus = "�������";
			break;
		}
		return campus;
	}
	public char getFloor(){
		return Number.charAt(0);
	}
}
