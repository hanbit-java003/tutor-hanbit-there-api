package com.hanbit.there.api.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanbit.there.api.admin.vo.AdminThereGroupVO;

@Repository
public class AdminThereDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public List<AdminThereGroupVO> selectThereGroups() {
		return sqlSession.selectList("admin.there.selectThereGroups");
	}
	
}
