package com.first_crud.repository;

import com.first_crud.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {

    List<Board> findAllByOrderByModifiedAtDesc();
//    List<Board> findAllById(Long id);

}
