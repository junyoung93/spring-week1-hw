package com.first_crud.DTO;

import com.first_crud.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponseDTO {
    private Long id;
    private String title;
    private String username;
    private String contents;
    private String pw;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public BoardResponseDTO(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.username = board.getUsername();
        this.contents = board.getContents();
        this.pw = board.getPw();
        this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
    }



}
