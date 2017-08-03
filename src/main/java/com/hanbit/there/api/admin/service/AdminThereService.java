package com.hanbit.there.api.admin.service;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hanbit.there.api.admin.dao.AdminThereDAO;
import com.hanbit.there.api.admin.vo.AdminThereGroupVO;
import com.hanbit.there.api.admin.vo.AdminThereVO;
import com.hanbit.there.api.service.FileService;
import com.hanbit.there.api.service.MenuService;
import com.hanbit.there.api.vo.FileVO;
import com.hanbit.there.api.vo.ThereVO;

@Service
public class AdminThereService {

	@Autowired
	private MenuService menuService;

	@Autowired
	private AdminThereDAO adminThereDAO;

	@Autowired
	private FileService fileService;

	public List<AdminThereGroupVO> listThereGroups() {
		return adminThereDAO.selectThereGroups();
	}

	@Transactional
	public void modifyThereGroupOrder(String idUp, String idDown) {
		adminThereDAO.updateThereGroupOrder(idUp, -1);
		adminThereDAO.updateThereGroupOrder(idDown, 1);

		menuService.invalidateCache();
	}

	public AdminThereGroupVO getThereGroup(String id) {
		return adminThereDAO.selectThereGroup(id);
	}

	public void modifyThereGroup(AdminThereGroupVO groupVO) {
		adminThereDAO.updateThereGroup(groupVO);
		menuService.invalidateCache();
	}

	public void removeThereGroup(String id) {
		adminThereDAO.deleteThereGroup(id);
		menuService.invalidateCache();
	}

	public void addThereGroup(AdminThereGroupVO groupVO) {
		adminThereDAO.insertThereGroup(groupVO);
	}

	public boolean hasThereGroupId(String id) {
		return adminThereDAO.countThereGroupId(id) > 0;
	}

	public List<AdminThereVO> listTheres(String groupId) {
		return adminThereDAO.selectTheres(groupId);
	}

	public boolean hasThereId(String id) {
		return adminThereDAO.countThereId(id) > 0;
	}

	@Transactional
	public void modifyThere(ThereVO thereVO, MultipartFile background) {
		String backgoundFileId = "there-" + thereVO.getId();

		if (background != null) {
			thereVO.setBackground("/api/file/" + backgoundFileId);
		}

		adminThereDAO.updateThere(thereVO);
		adminThereDAO.updateLocation(thereVO);
		adminThereDAO.deleteInfos(thereVO.getId());
		adminThereDAO.insertInfos(thereVO);
		adminThereDAO.deleteTraffics(thereVO.getId());
		adminThereDAO.insertTraffics(thereVO);

		if (background != null) {
			FileVO fileVO = new FileVO();
			fileVO.setFileId(backgoundFileId);

			String fileExt = FilenameUtils.getExtension(background.getOriginalFilename());
			String fileName = thereVO.getId() + "." + fileExt;

			fileVO.setFilePath("/hanbit/webpack/hanbit-there/src/img/theres/" + fileName);
			fileVO.setFileName(fileName);
			fileVO.setContentType(background.getContentType());
			fileVO.setContentLength(background.getSize());

			try {
				fileService.modifyFile(fileVO, background.getInputStream());
			}
			catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

}












