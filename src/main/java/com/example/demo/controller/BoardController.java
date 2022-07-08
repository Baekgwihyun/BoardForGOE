package com.example.demo.controller;


import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import com.example.demo.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;



    @GetMapping("/list")
    public String BoardList (Model model, @PageableDefault(size = 5) Pageable pageable,
                             @RequestParam(required = false, defaultValue = "") String searchText){
 Page<Board> boards = boardRepository.findByTitleContaining(searchText,pageable);

        int startPage = Math.max(1,boards.getPageable().getPageNumber() -4);
        int endPage = Math.min(boards.getTotalPages(),boards.getPageable().getPageNumber() +4);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
        System.out.println(boards);
        System.out.println(startPage);
        System.out.println(endPage);


        return "/board/boardlist";
    }

        @GetMapping("/form")
        public String boardForm(){

            return "/board/boardform";

    }

}
