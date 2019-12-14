package kr.taeu.mvc.board.service;

import kr.taeu.mvc.board.dao.BoardDao;
import kr.taeu.mvc.board.domain.BoardVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    @Resource
    private BoardDao boardDao;

    public BoardDao getBoardDao() {
        return boardDao;
    }

    public void setBoardDao(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @Override
    public List<BoardVO> list() {
        return boardDao.list();
    }

    @Override
    public int delete(BoardVO boardVO) {
        return boardDao.delete(boardVO);
    }

    @Override
    public int edit(BoardVO boardVO) {
        return boardDao.update(boardVO);
    }

    @Override
    public void write(BoardVO boardVO) {
        boardDao.insert(boardVO);
    }

    @Override
    public BoardVO read(int seq) {
        boardDao.updateReadCount(seq);
        return boardDao.select(seq);
    }
}
