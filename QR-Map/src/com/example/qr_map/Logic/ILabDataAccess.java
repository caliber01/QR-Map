package com.example.qr_map.Logic;

import java.util.List;

import com.example.qr_map.Models.Laboratory;
import com.example.qr_map.Models.Sponsor;

public interface ILabDataAccess extends IDataAccess{

	public List<Laboratory> FindBySponsor(Sponsor _Sponsor);
}
