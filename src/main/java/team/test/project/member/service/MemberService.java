package team.test.project.member.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.test.project.member.dao.MemberDAO;
import team.test.project.member.dto.MemberDTO;

@Service
public class MemberService {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired MemberDAO memberDao;
	
	public int join(Map<String, String> param) {
		int row = memberDao.join(param);
		return row;
	}

	public int overlay(String id) {
		return memberDao.overlay(id);
	}

	public MemberDTO login(String id, String pw) {
		
		return memberDao.login(id, pw);
	}

}
