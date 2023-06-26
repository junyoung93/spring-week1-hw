package com.first_crud.entity;

import com.first_crud.DTO.BoardRequestDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Getter
@Setter
@NoArgsConstructor
public class Board {
    private Long id;
    private String title;
    private String username;
    private String contents;
    private LocalDate regDate;
    private String pw;


    public Board(BoardRequestDTO requestDTO) {
        this.title = requestDTO.getTitle();
        this.username = requestDTO.getUsername();
        this.contents = requestDTO.getContents();
        this.regDate = LocalDate.now();
        this.pw = requestDTO.getPw();
    }

    public void update(BoardRequestDTO boardRequestDTO) {
        this.username = boardRequestDTO.getUsername();
        this.title = boardRequestDTO.getTitle();
        this.contents = boardRequestDTO.getContents();
    }

    public String getFormatCreatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return regDate.format(formatter);
    }


}
