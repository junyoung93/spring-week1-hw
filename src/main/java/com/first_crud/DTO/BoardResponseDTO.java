package com.first_crud.DTO;

import com.first_crud.entity.Board;
import lombok.Getter;

@Getter
public class BoardResponseDTO {
    private Long id;
    private String title;
    private String username;
    private String contents;
    private String pw;

    public BoardResponseDTO(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.username = board.getUsername();
        this.contents = board.getContents();
        this.pw = board.getPw();
    }


    public BoardResponseDTO(Long id, String username, String contents, String pw, String title) {
        this.id= id;
        this.username=username;
        this.contents=contents;
        this.pw=pw;
        this.title=title;
    }
}
