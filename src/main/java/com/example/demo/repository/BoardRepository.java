package com.example.demo.repository;

import com.example.demo.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository <Board, Long>{
    Board findByGoeHelpContent(String goe_help_content);

    List<Board> findByGoeTitle(String goe_title);
    Page<Board> findAll(Pageable pageable);
    Optional<Board> findById(Integer id);
    Page<Board> findByGoeTitleContainingOrGoeUserNameContaining(String goe_title, String goe_user_name ,Pageable pageable );






}
