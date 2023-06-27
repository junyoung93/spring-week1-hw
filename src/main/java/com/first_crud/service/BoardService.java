package com.first_crud.service;

import com.first_crud.DTO.BoardRequestDTO;
import com.first_crud.DTO.BoardResponseDTO;
import com.first_crud.entity.Board;
import com.first_crud.repository.BoardRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;


public class BoardService {
    private final JdbcTemplate jdbcTemplate;
    // final은 반드시 초기화
    public BoardService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public BoardResponseDTO createBoard(BoardRequestDTO requestDTO) {

        //RequestDTO -> Entity
        Board board = new Board(requestDTO);  //memo객체에 requestDTO 클라 데이터 넣기

        // DB 저장
        BoardRepository boardRepository = new BoardRepository(jdbcTemplate);
        Board saveBoard = boardRepository.save(board);

        // Entity -> ResponseDto
        BoardResponseDTO boardResponseDTO = new BoardResponseDTO(board);

        return boardResponseDTO;

    }

    public List<BoardResponseDTO> getBoard() {
        BoardRepository boardRepository = new BoardRepository(jdbcTemplate);
        return boardRepository.findAll();
    }

    public BoardResponseDTO detailListBoard(Long id) {
        BoardRepository boardRepository = new BoardRepository(jdbcTemplate);
        return boardRepository.find(id);

    }

    public Long updateBoard(Long id, BoardRequestDTO boardRequestDTO) {
        BoardRepository boardRepository = new BoardRepository(jdbcTemplate);
        Board board = boardRepository.findById(id);
        //.해당 게시물 가져오기
        if(board != null) {
            boardRepository.update(id,boardRequestDTO);
            return id;
        } else {
            throw new IllegalArgumentException("선택한 게시물은 존재하지 않습니다.");
        }
    }

    public Long deleteBoard(Long id, String pw) {
        BoardRepository boardRepository = new BoardRepository(jdbcTemplate);
        //해당 게시물이 db에 있는지 확인
        Board board = boardRepository.findById(id);
        if(board != null) {
            boardRepository.delete(id, pw);

            return id;
        } else {
            throw new IllegalArgumentException("선택한 게시물은 존재하지 않습니다.");
        }
    }
}
