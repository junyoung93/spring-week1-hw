package com.first_crud.controller;

import com.first_crud.DTO.BoardRequestDTO;
import com.first_crud.DTO.BoardResponseDTO;
import com.first_crud.service.BoardService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController //html을 따로 반환하지 않기 때문에
@RequestMapping("/api/board")
public class BoardController {

    private final JdbcTemplate jdbcTemplate;
    // final은 반드시 초기화
    public BoardController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/create")
    public BoardResponseDTO createBoard(@RequestBody BoardRequestDTO requestDTO) { // requestDTO클라이언트에서 보내준 데이터
        //다른 class의 method를 사용하기 위해서는 인스턴스화를 해서 사용
        BoardService boardService = new BoardService(jdbcTemplate);
        return boardService.createBoard(requestDTO);
    }

    @GetMapping("/list")
    public List<BoardResponseDTO> getBoard(){
        BoardService boardService = new BoardService(jdbcTemplate);
        return boardService.getBoard();
    }

    @GetMapping("/detailList/{id}")
    public BoardResponseDTO detailListBoard(@PathVariable Long id) {
        BoardService boardService = new BoardService(jdbcTemplate);
        return boardService.detailListBoard(id);
    }



    @PutMapping("/update/{id}")
    //Long으로 한 이유는 id 타입이 Long이니까
    public Long updateBoard(@PathVariable Long id,@RequestBody BoardRequestDTO boardRequestDTO){
        BoardService boardService = new BoardService(jdbcTemplate);
        return boardService.updateBoard(id, boardRequestDTO );
        // 클라이언트에서 body부분에서 넘어온다 . 그래서 body의 json형식으로 넘어온다. body의 json형식 = RequestBody를 생각하자
        // 해당 메모가 db에 존재하는 지 확인
    }

    @DeleteMapping("/delete/{id}")
    public Long deleteBoard(@PathVariable Long id, @RequestParam String pw) {
        BoardService boardService = new BoardService(jdbcTemplate);
        return boardService.deleteBoard(id, pw);
    }
}
