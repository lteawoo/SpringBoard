package kr.taeu.mvc.board.service;

import kr.taeu.mvc.board.domain.BoardVO;

import java.util.List;

public interface BoardService {
    public abstract List<BoardVO> list();

    public abstract int delete(BoardVO boardVO);

    public abstract int edit(BoardVO boardVO);

    public abstract void write(BoardVO boadVO);

    public abstract BoardVO read(int seq);
}
