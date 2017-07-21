package com.hanbit.there.api.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hanbit.there.api.admin.service.AdminThereService;
import com.hanbit.there.api.admin.vo.AdminThereGroupVO;

@RestController
@RequestMapping("/api/admin/there")
public class AdminThereController {

	@Autowired
	private AdminThereService adminThereService;
	
	@RequestMapping("/groups")
	public List<AdminThereGroupVO> listThereGroups() {
		return adminThereService.listThereGroups();
	}
	
}
