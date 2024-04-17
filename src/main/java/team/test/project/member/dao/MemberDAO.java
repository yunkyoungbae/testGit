package team.test.project.member.dao;

import java.util.Map;

import team.test.project.member.dto.MemberDTO;

public interface MemberDAO {

	int join(Map<String, String> param);

	int overlay(String id);

	MemberDTO login(String id, String pw);


}
