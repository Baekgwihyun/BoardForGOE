package com.example.demo.repository;

import com.example.demo.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BoardRepository extends JpaRepository <Board, Long>{
    List<Board> findByTitle(String goe_title);
    Page<Board> findByTitleContaining(String searchKeyword, Pageable pageable);
}
