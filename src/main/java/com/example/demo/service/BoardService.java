package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        log.debug("boards{}" + board.toString());
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
//    private Specification<Board> search(String searchKeyword) {
//        return new Specification<>() {
//            private static final long serialVersionUID = 1L;
//            @Override
//            public Predicate toPredicate(Root<Board> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
//                query.distinct(true);  // 중복을 제거
//                Join<Board, SiteUser> u1 = q.join("author", JoinType.LEFT);
//                Join<Board, Answer> a = q.join("answerList", JoinType.LEFT);
//                Join<Answer, SiteUser> u2 = a.join("author", JoinType.LEFT);
//                return cb.or(cb.like(q.get("subject"), "%" + searchKeyword + "%"), // 제목
//                        cb.like(q.get("content"), "%" + searchKeyword + "%"),      // 내용
//                        cb.like(u1.get("username"), "%" + searchKeyword + "%"),    // 질문 작성자
//                        cb.like(a.get("content"), "%" + searchKeyword + "%"),      // 답변 내용
//                        cb.like(u2.get("username"), "%" + searchKeyword + "%"));   // 답변 작성자
//            }
//        };
    }




//    public Page<Board> boardSearchList(String searchKeyword,Pageable pageable){
//        return boardRepository.findByGoeTitleContaining(searchKeyword,pageable);
//    }
//
//
//    public Page<Board> boardList(Pageable pageable){
//        return boardRepository.findAll(pageable);
//    }










