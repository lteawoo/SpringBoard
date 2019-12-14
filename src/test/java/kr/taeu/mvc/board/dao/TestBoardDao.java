package kr.taeu.mvc.board.dao;

import kr.taeu.mvc.board.domain.BoardVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/BoardDaoTest-context.xml")
//@Transactional
public class TestBoardDao{
    @Autowired
    private BoardDao boardDao;

    private List<BoardVO> boardList;

    @Before
    public void setUp() {
        boardDao.deleteAll();

        boardDao.insert(new BoardVO("제목1", "내용1", "작성자1", 1000));
        boardDao.insert(new BoardVO("제목2", "내용2", "작성자2", 1111));
        boardDao.insert(new BoardVO("제목3", "내용3", "작성자3", 2222));

        boardList = boardDao.list();
    }

    @Test
    public void list() {
        assertThat(boardList.size(), is(3));

        BoardVO dbVO;

        for(int index = 0; index < boardList.size(); index++) {
            dbVO = boardDao.select(boardList.get(index).getSeq());
            compareVO(dbVO, boardList.get(index));
        }
    }

    private void compareVO(BoardVO vo1, BoardVO vo2) {
        assertThat(vo1.getSeq(), is(vo2.getSeq()));
        assertThat(vo1.getTitle(), is(vo2.getTitle()));
        assertThat(vo1.getContent(), is(vo2.getContent()));
        assertThat(vo1.getWriter(), is(vo2.getWriter()));
        assertThat(vo1.getRegDate(), is(vo2.getRegDate()));
        assertThat(vo1.getPassword(), is(vo2.getPassword()));
        assertThat(vo1.getCnt(), is(vo2.getCnt()));
    }
}
