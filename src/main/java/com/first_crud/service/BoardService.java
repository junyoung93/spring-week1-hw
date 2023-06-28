package com.first_crud.service;

import com.first_crud.DTO.BoardRequestDTO;
import com.first_crud.DTO.BoardResponseDTO;
import com.first_crud.entity.Board;
import com.first_crud.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class BoardService {

    private final BoardRepository boardRepository;


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
        return boardRepository.findAllByOrderByModifiedAtDesc().stream().map(BoardResponseDTO::new).toList();
    }

    public BoardResponseDTO detailListBoard(Long id) {
        return new BoardResponseDTO(boardRepository.findById(id).orElseThrow(()->new IllegalArgumentException("선택한 게시물이 존재하지 않습니다.")));
    }
    @Transactional
    public Long updateBoard(Long id, BoardRequestDTO boardRequestDTO) {
        Board board = findboard(id);
        board.update(boardRequestDTO);
        return id;
    }

    public Long deleteBoard(Long id, String pw) {
        Board board = findboard(id);
        boardRepository.delete(board);
        return id;
    }

    private Board findboard(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("선택한 게시물은 존재하지 않습니다."));
    }
}
