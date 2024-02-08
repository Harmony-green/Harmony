package com.nowon.cho.service;

import org.springframework.ui.Model;

public interface AdminService {
	
	void productlistProcess(Model model);

	void mainBannerListProcess(Model model);
	
	void mainBannerListDeleteProcess(long no);

	void updateProcess(long no, String title, long price, int stock, String category);

}
