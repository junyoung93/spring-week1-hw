package com.first_crud.DTO;

import com.first_crud.entity.Board;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

import static org.apache.tomcat.util.http.FastHttpDateFormat.getCurrentDate;

@Getter
public class BoardResponseDTO {
    private Long id;
    private String title;
    private String username;
    private String contents;
    private LocalDate regDate;
    private String pw;

    public BoardResponseDTO(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.username = board.getUsername();
        this.contents = board.getContents();
        this.regDate = LocalDate.now();
        this.pw = board.getPw();
    }


}
