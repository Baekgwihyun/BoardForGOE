package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public Page<Board> getList(int page ) {
        Pageable pageable = PageRequest.of(page, 5);

        log.debug("pageable{}"+ pageable);
        log.debug("page{}"+ page);
        return boardRepository.findAll(pageable);

    }

    public void list(String goe_user_name, String goe_user_phone, String goe_title, String goe_school_name, String goe_help_content) {
        Board board = new Board();
        board.setGoeUserName(goe_user_name);
        board.setGoeUserPhone(goe_user_phone);
        board.setGoeTitle(goe_title);
        board.setGoeSchoolName(goe_school_name);
        board.setGoeHelpContent(goe_help_content);
        board.setGoeHelpDate(LocalDateTime.now());
        log.debug("boards{}" + board);
        this.boardRepository.save(board);
    }

    public Board getBoard(Integer id) {
        Optional <Board> board = this.boardRepository.findById(id);
        if (board.isPresent()) {
            return board.get();
        } else {
            throw new DataNotFoundException("board not found");
        }
    }

//    public Page<Board> boardSearchList(String searchKeyword,Pageable pageable){
//        return boardRepository.findByGoeTitleContaining(searchKeyword,pageable);
//    }

}





