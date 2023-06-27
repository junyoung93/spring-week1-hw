package com.first_crud.entity;

import com.first_crud.DTO.BoardRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "board")
@Getter
@Setter
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "contents", nullable = false,length = 500)
    private String contents;
    @Column(name = "pw", columnDefinition = "VARCHAR(255) DEFAULT 'default_password'")
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
