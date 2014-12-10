package com.example.qr_map.Logic;

import com.example.qr_map.Models.LabEquipment;
import com.example.qr_map.Models.Laboratory;
import com.example.qr_map.Models.Sponsor;

public class Showing {
	public String[] getLabInfo(Laboratory lab){
		String[] arr = new String[10];
		arr[0] = lab.getCampus()+"/"+lab.getFloor() +" этаж/"+lab.getNumber();
		arr[1] = lab.getType();
		arr[2] = lab.getSponsor().toString();
		arr[3] = lab.getActivity();
		arr[4] = lab.getFaculty();
		arr[5] = lab.getCathedra();
		arr[6] = lab.getChiefFIO();
		arr[7] = lab.getNumber();
		arr[8] = lab.getWorkTime();
		String assists = "";
		int i;
		for(i = 0; i < lab.getLabAssistantsFIOs().size() - 1; i++){
			assists+=lab.getLabAssistantsFIOs().get(i)+", ";
		}
		assists += lab.getLabAssistantsFIOs().get(i) + ".";
		arr[9] = assists;
		return arr;
	}

	public String[] getSponsorInfo(Sponsor sponsor){
		String[] arr = new String[4];
		arr[0] = sponsor.getWebSite();
		arr[1] = sponsor.getAddress();
		arr[2] = sponsor.getTelephone();
		arr[3] = sponsor.getDescription();
		return arr;
	}
	
	public String[] getEquipmentInfo(LabEquipment equip){
		String[] arr = new String[5];
		arr[0] = equip.getElectronic();
		arr[1] = equip.hasProjector()? "Есть" : "Нет";
		arr[2] = equip.hasWiFi()? "Есть (" + equip.getWiFiName()+")":"Нет";
		arr[3] = equip.getChairs() + " столов";
		arr[4] = equip.getTables() + " посадочных мест";
		return arr;
	}
}
