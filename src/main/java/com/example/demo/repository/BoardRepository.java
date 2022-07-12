package com.example.demo.repository;

import com.example.demo.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Repository;

import java.sql.SQLOutput;
import java.util.List;

public interface BoardRepository extends JpaRepository <Board, Long>{
    //List<Board> findByGoe_title(String goe_title);
    //List<Board> findByGoe_titleOrOrGoe_help_content(String goe_title, String goe_help_content);

   // Page<Board> findByGoe_titleOrOrGoe_help_content(String goe_title, String goe_help_content, Pageable pageable);

}
