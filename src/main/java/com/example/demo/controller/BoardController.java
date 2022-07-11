package com.example.demo.controller;


import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import com.example.demo.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;

  /*  @GetMapping("/list")
    public String list(Model model, @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Board> list = boardRepository.findAll(pageable);
        List<Board> boards = boardRepository.findAll();
        int nowPage = pageable.getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int nextPage = Math.min(nowPage + 5, list.getTotalPages());
        int endPage = Math.max(nowPage, list.getTotalPages());

        model.addAttribute("list", boards);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("nextPage", nextPage);
        model.addAttribute("endPage", endPage);
        log.debug("boardlist{}" + list);
        return "/board/boardlist";

    }*/
    @GetMapping("/list")
    public String list(Model model,Pageable pageable) {
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        model.addAttribute("pageable",pageable);
        return "board/boardlist";
    }



        @PostMapping("/form")
        public String boardForm(){

            return "/board/boardlist";

    }

}
