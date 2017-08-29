package com.hanbit.there.api.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hanbit.there.api.dao.MemberDAO;
import com.hanbit.there.api.vo.MemberVO;

@Service
public class MemberService {

	private static final char[] CHARS
		= "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public void signUp(MemberVO memberVO) {
		if (memberDAO.countMember(memberVO.getEmail()) > 0) {
			throw new RuntimeException("이미 가입된 이메일 입니다.");
		}

		memberVO.setUid(generateUid());

		// 패스워드 암호화
		String encodedPassword = passwordEncoder.encode(memberVO.getPassword());
		memberVO.setPassword(encodedPassword);

		memberDAO.insertMember(memberVO);
	}

	private String generateUid() {
		int length = 12;
		char[] uid = new char[length];

		Random random = new Random();

		for (int i=0; i<length; i++) {
			uid[i] = CHARS[random.nextInt(CHARS.length)];
		}

		return new String(uid);
	}

	public MemberVO signIn(String email, String password) {
		MemberVO memberVO = memberDAO.selectMember(email);

		if (memberVO == null) {
			throw new RuntimeException("가입되지 않은 이메일입니다.");
		}

		if (!passwordEncoder.matches(password, memberVO.getPassword())) {
			throw new RuntimeException("잘못된 비밀번호입니다.");
		}

		return memberVO;
	}

}








