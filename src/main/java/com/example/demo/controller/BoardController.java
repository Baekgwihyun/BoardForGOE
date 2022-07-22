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
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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



    @GetMapping("/list")
    public String list(Model model,  @RequestParam(value = "page", defaultValue = "0")int page,
                      @RequestParam(required = false,defaultValue = "") String searchKeyword,Pageable pageable) {

        Page<Board> paging = boardService.getList(page);
        List<Board> totalBoardsCount = boardRepository.findAll();
        Page<Board> boards= boardRepository.findByGoeTitleContainingOrGoeUserNameContaining(searchKeyword,searchKeyword, pageable);


        model.addAttribute("totalBoardsCount", totalBoardsCount);
        model.addAttribute("paging", paging);
        model.addAttribute("boards", boards);
       log.debug("boards{}"+ boards );
        log.debug("paging{}"+ paging );
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


//    @GetMapping("/searchKeywordList")
//    public String searchKeywordList(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
//                                    @RequestParam(value = "kw", defaultValue = "") String searchKeyword) {
//        Page<Board> paging = this.boardService.getList(page, searchKeyword);
//        model.addAttribute("paging", paging);
//        model.addAttribute("kw", searchKeyword);
//
//        return "board/boardlist";
//    }


}













