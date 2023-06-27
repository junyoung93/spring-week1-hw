package com.first_crud.entity;

import com.first_crud.DTO.BoardRequestDTO;
import com.first_crud.DTO.BoardResponseDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;


@Entity
@Table(name = "board")
@Getter
@Setter
@NoArgsConstructor
public class Board extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column
    private String title;
    @Column
    private String contents;
    @Column(nullable = false)
    private String pw;




    public Board(BoardRequestDTO requestDTO) {
        this.title = requestDTO.getTitle();
        this.username = requestDTO.getUsername();
        this.contents = requestDTO.getContents();
        this.pw = requestDTO.getPw();
    }

    public void update(BoardRequestDTO boardRequestDTO) {
        this.username = boardRequestDTO.getUsername();
        this.title = boardRequestDTO.getTitle();
        this.contents = boardRequestDTO.getContents();
        this.pw = boardRequestDTO.getPw();

    }


}
