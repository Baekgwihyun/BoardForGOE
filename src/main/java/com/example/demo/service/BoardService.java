package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Board> getList() {
        return this.boardRepository.findAll();
    }

    public void list(String goe_user_name, String goe_user_phone ,String goe_title , String goe_school_name,String goe_help_content) {
        Board board = new Board();
        board.setGoe_user_name(goe_user_name);
        board.setGoe_user_phone(goe_user_phone);
        board.setGoe_title(goe_title);
        board.setGoe_school_name(goe_school_name);
        board.setGoe_help_content(goe_help_content);
        board.setGoe_help_date(LocalDateTime.now());
        log.debug("boards{}"+ board );
        this.boardRepository.save(board);
    }


}