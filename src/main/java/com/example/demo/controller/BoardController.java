package com.example.demo.controller;


import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import com.example.demo.service.BoardService;
import com.example.demo.validator.BoardValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;


@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;


    @Autowired
    private BoardValidator boardValidator;


    @Autowired
    private BoardService boardService;



    @RequestMapping("/list")
    public String list(Model model) {
        List<Board> boards = this.boardService.getList();
        model.addAttribute("boards", boards);

        log.debug("boards{}"+ boards );
        return "board/boardlist";
    }

    @PostMapping("/form")
    public String questionCreate(
            @RequestParam String goe_user_name,  String goe_user_phone,
            String goe_title, String goe_school_name, String goe_help_content) {
        this.boardService.list(goe_user_name,goe_user_phone,goe_title,goe_school_name,goe_help_content);


        return "redirect:/board/list"; // 질문 저장후 질문목록으로 이동
    }



    }



//    @PostMapping("/form")
//    public String postForm( Board board, BindingResult bindingResult, Authentication authentication) {
//        boardValidator.validate(board, bindingResult);
//        if(bindingResult.hasErrors()){
//            return "board/form";
//        }
//        // Authentication a = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();
//        boardService.save(username, board);
//        boardRepository.save(board);
//
//        return "redirect:/board/list";
//    }


