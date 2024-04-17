package team.test.project.board.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.RequestScope;

import team.test.project.board.dao.BoardDAO;
import team.test.project.board.service.BoardService;

@Controller
public class BoardController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired BoardService boardService;
	
	@RequestMapping(value="/list")
	public String list(Model model) {
		return "list";
	}
	
	@RequestMapping(value = "/list.ajax")
	@ResponseBody
	public Map<String, Object> listCall(String page, String cnt){
		logger.info("페이지당 보여줄 갯수 : " + cnt);
		logger.info("요청 페이지 : " + page);
		
		int currPage = Integer.parseInt(page);
		int pagePerCnt = Integer.parseInt(cnt);
		
		//*중요* list(currPage, pagePerCnt)는 sql문 limit을 쓰냐 offset에 따라 매개변수 순서가 다름
		//나는 여기서 offset을 사용했으니 currPage, pagePerCnt 순
		Map<String, Object> map = boardService.list(currPage, pagePerCnt);
		
		return map;
	}
	
	
	
}
