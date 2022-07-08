package com.example.demo.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

    @GetMapping("list")
    public String BoardList (Model model){
        return "/board/boardlist";
    }

        @GetMapping("/form")
        public String boardForm(){

            return "/board/boardform";

    }

}
