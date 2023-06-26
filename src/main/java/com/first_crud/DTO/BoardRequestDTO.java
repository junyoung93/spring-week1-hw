package com.first_crud.DTO;

import lombok.Data;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Getter
public class  BoardRequestDTO {
    private Long id;
    private String title;
    private String username;
    private String contents;
    private LocalDate regDate;
    private String pw;
}
