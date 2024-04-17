package team.test.project.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import team.test.project.member.dto.MemberDTO;
import team.test.project.member.service.MemberService;

@Controller
public class MemberController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired MemberService memberService;
	
	@RequestMapping(value = "/")
	public String main() {
		logger.info("메인요청이다");
		return "login";
	}
	
	//회원가입 페이지로 이동
	@RequestMapping(value="/join.go")
	public String joinForm() {
		logger.info("회원가입 페이지로 이동이다");
		return "joinForm";
	}
	
	//회원가입
	@RequestMapping(value = "/join.do", method = RequestMethod.POST)
	public String join(Model model, @RequestParam Map<String, String> param) {
		String page = "joinForm";
		String msg = "회원가입에 실패이다.";
		logger.info("param:"+ param);
		int row = memberService.join(param);
		logger.info("insert count : " + row);
		if (row == 1) {
			page = "login";
			msg = "회원가입 성공이다";
		}
		model.addAttribute("msg", msg);
		return page;
	}
	
	//중복체크
	@RequestMapping(value = "/overlay.do")
	@ResponseBody
	public Map<String, Object> overlay(String id){
		logger.info("id=" + id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("use", memberService.overlay(id));
		return map;
	}
	
	//로그인
	@RequestMapping(value = "/login.do")
	public String login(Model model, HttpSession session, String id, String pw) {
		String page = "login";
		logger.info("id : {} / pw : {} " , id, pw);
		MemberDTO info = memberService.login(id, pw);
		logger.info("loginID: " + info);
		
		if(info != null) {
			page = "redirect:/list";
			session.setAttribute("loginInfo", info);
		}else {
			model.addAttribute("msg", "ID or PW check"	);
		}
		logger.info("page:" + page);
		return page;
	}
	
	
}
