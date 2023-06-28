package com.first_crud.DTO;

import lombok.Getter;

@Getter
public class  BoardRequestDTO {
    private Long id;
    private String title;
    private String username;
    private String contents;
    private String pw;
}
