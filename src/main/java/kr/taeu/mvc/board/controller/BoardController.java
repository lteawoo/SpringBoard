package kr.taeu.mvc.board.controller;

import kr.taeu.mvc.board.domain.BoardVO;
import kr.taeu.mvc.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@SessionAttributes("boardVO")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @RequestMapping(value="/board/list")
    public String list(Model model) {
        model.addAttribute("boardList", boardService.list());
        return "/board/list";
    }

    @RequestMapping(value="/board/read/{seq}")
    public String read(Model model, @PathVariable int seq) {
        model.addAttribute("boardVO", boardService.read(seq));
        return "/board/read";
    }

    @RequestMapping(value="/board/write", method=RequestMethod.GET)
    public String write(Model model) {
        model.addAttribute("boardVO", new BoardVO());
        return "/board/write";
    }

    @RequestMapping(value="/board/write", method=RequestMethod.POST)
    public String write(@Valid BoardVO boardVO, BindingResult bindingResult, SessionStatus sessionStatus) {
        if(bindingResult.hasErrors()) {
            return "/board/write";
        } else {
            boardService.write(boardVO);
            sessionStatus.setComplete();
            return "redirect:/board/list";
        }
    }

    @RequestMapping(value="/board/edit/{seq}", method=RequestMethod.GET)
    public String edit(@PathVariable int seq, Model model) {
        BoardVO boardVO = boardService.read(seq);
        model.addAttribute("boardVO", boardVO);
        return "/board/edit";
    }

    @RequestMapping(value="/board/edit/{seq}", method=RequestMethod.POST)
    public String edit(@Valid @ModelAttribute BoardVO boardVO, BindingResult result, int pwd
            , SessionStatus sessionStatus, Model model) {
        if(!result.hasErrors()) {
            if(boardVO.getPassword() == pwd) {
                boardService.edit(boardVO);
                sessionStatus.setComplete();
                return "redirect:/board/list";
            }

            model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
        }
        return "/board/edit";
    }

    @RequestMapping(value="/board/delete/{seq}", method=RequestMethod.GET)
    public String delete(@PathVariable int seq, Model model) {
        model.addAttribute("seq", seq);
        return "/board/delete";
    }

    @RequestMapping(value="/board/delete", method=RequestMethod.POST)
    public String delete(int seq, int pwd, Model model) {
        int rowCount;
        BoardVO boardVO = new BoardVO();
        boardVO.setSeq(seq);
        boardVO.setPassword(pwd);

        rowCount = boardService.delete(boardVO);

        if(rowCount == 0) {
            model.addAttribute("seq", seq);
            model.addAttribute("msg", "비밀 번호가 맞지 않습니다.");
            return "/board/delete";
        } else {
            return "redirect:/board/list";
        }
    }
}
