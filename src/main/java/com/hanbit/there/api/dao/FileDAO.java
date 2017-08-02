package com.hanbit.there.api.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.hanbit.there.api.vo.FileVO;

public class FileDAO {

	@Autowired
	private SqlSession sqlSession;

	public FileVO selectFile(String fileId) {
		return sqlSession.selectOne("file.selectFile", fileId);
	}

	public int insertFile(FileVO fileVO) {
		return sqlSession.insert("file.insertFile", fileVO);
	}

	public int replaceFile(FileVO fileVO) {
		return sqlSession.insert("file.replaceFile", fileVO);
	}

}










