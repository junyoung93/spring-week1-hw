package com.first_crud.service;

import com.first_crud.DTO.BoardRequestDTO;
import com.first_crud.DTO.BoardResponseDTO;
import com.first_crud.entity.Board;
import com.first_crud.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    /*
    BoardService가 생성이 될 때 파라미터로 jdbcTemplate 받아오고
    boardRepository 만들어서 jdbcTemplate를 받아온다
     */
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public BoardResponseDTO createBoard(BoardRequestDTO requestDTO) {

        //RequestDTO -> Entity
        Board board = new Board(requestDTO);  //memo객체에 requestDTO 클라 데이터 넣기

        // DB 저장
        Board saveBoard = boardRepository.save(board);

        // Entity -> ResponseDto
        BoardResponseDTO boardResponseDTO = new BoardResponseDTO(board);

        return boardResponseDTO;

    }

    public List<BoardResponseDTO> getBoard() {
        return boardRepository.findAll();
    }

    public BoardResponseDTO detailListBoard(Long id) {
        return boardRepository.find(id);

    }

    public Long updateBoard(Long id, BoardRequestDTO boardRequestDTO) {
        Board board = boardRepository.findById(id);
        //.해당 게시물 가져오기
        if (board != null) {
            boardRepository.update(id, boardRequestDTO);
            return id;
        } else {
            throw new IllegalArgumentException("선택한 게시물은 존재하지 않습니다.");
        }
    }

    public Long deleteBoard(Long id, String pw) {
        //해당 게시물이 db에 있는지 확인
        Board board = boardRepository.findById(id);
        if (board != null) {
            boardRepository.delete(id, pw);

            return id;
        } else {
            throw new IllegalArgumentException("선택한 게시물은 존재하지 않습니다.");
        }
    }
}
