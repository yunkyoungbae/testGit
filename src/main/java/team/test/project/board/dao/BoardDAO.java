package team.test.project.board.dao;

import java.util.List;

import team.test.project.board.dto.BoardDTO;

public interface BoardDAO {

	int allCount(int pagePerCnt);

	List<BoardDTO> list(int pagePerCnt, int start);

}
