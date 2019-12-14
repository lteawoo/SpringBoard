package kr.taeu.mvc.board.controller;

import kr.taeu.mvc.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @RequestMapping(value="/board/list")
    @ResponseBody
    public String list() {
        return boardService.list().toString();
    }
}
