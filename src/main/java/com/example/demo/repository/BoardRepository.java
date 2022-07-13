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
    //Board findByGoeTitle(String goeTitle);

    //Page<Board> findAll(Pageable pageable);


}
