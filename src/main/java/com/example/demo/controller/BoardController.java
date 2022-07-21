package com.example.demo.controller;


import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import com.example.demo.service.BoardService;
import com.example.demo.service.ExcelFileService;
import com.example.demo.validator.BoardValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;


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

    @Autowired
    protected ExcelFileService excelFileService;



   /* @RequestMapping("/list")
    public String list(Model model) {
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);

        log.debug("boards{}"+ boards );
        return "board/boardlist";
    }*/


    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Board> paging = this.boardService.getList(page);



        model.addAttribute("paging", paging);
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);

        System.out.println("paging" + paging);
        log.debug("boards{}" + paging);
        return "board/boardlist";


    }


    // 게시글 작성
    @PostMapping("/form")
    public String questionCreate(
            @RequestParam String goe_user_name, String goe_user_phone,
            String goe_title, String goe_school_name, String goe_help_content) {
        this.boardService.list(goe_user_name, goe_user_phone, goe_title, goe_school_name, goe_help_content);

        log.debug("boardService.list{}" + boardService);
        return "redirect:/board/list"; // 질문 저장후 질문목록으로 이동
    }


    // 게시판 상세
    //@RequestMapping(value = "/board/boardview/{id}")
    /*@GetMapping(value = "/board/boardview/{id}")
    public String boardView(Model model, @RequestParam Long id) {
        log.debug(id.toString());
        //Board board = this.boardService.getBoard(id);
        //model.addAttribute("board", board);
        return "board/boardview";
    }*/

    @RequestMapping(value = "/boardview/{id}")
    public String boardView(Model model, @PathVariable("id") Integer id) {
        // log.debug(id.toString());
        Board board = boardService.getBoard(id);
        //log.debug("[Board] Title : "+board.getGoeTitle());
        model.addAttribute("board", board);
        return "board/boardview";
    }


    //Excel다운로드
    @GetMapping("/excel/download")
    public void downloadExcelFile(HttpServletResponse response) throws IOException {
        List<Board> boardExcel = (List<Board>)boardRepository.findAll();
        ByteArrayInputStream byteArrayInputStream = excelFileService.export(boardExcel);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=contacts.xlsx");
        IOUtils.copy(byteArrayInputStream, response.getOutputStream());
    }
}













