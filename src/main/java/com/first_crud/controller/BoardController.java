package com.first_crud.controller;

import com.first_crud.DTO.BoardRequestDTO;
import com.first_crud.DTO.BoardResponseDTO;
import com.first_crud.entity.Board;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController //html을 따로 반환하지 않기 때문에
@RequestMapping("/api/board")
public class BoardController {

    private final Map<Long, Board> boardlist = new HashMap<>();

    @PostMapping("/create")
    public BoardResponseDTO createBoard(@RequestBody BoardRequestDTO requestDTO) { // requestDTO클라이언트에서 보내준 데이터
        //RequestDTO -> Entity
        Board board = new Board(requestDTO);  //memo객체에 requestDTO 클라 데이터 넣기


        //Id값으로 중복체크해서 메모 구분
        Long maxId = boardlist.size() > 0 ? Collections.max(boardlist.keySet()) + 1: 1;
        //boardlist.size()는 메모개수 체크 , 있으면 Collections.max()써써 boardlist.keySet() = Long값에서 가장 큰 값을 가져온다
        board.setId(maxId);


        //DB에 저장
        boardlist.put(board.getId(), board);


        //Entity -> ResponseDTO
        BoardResponseDTO boardResponseDTO = new BoardResponseDTO(board);
        return boardResponseDTO;

    }

    @GetMapping("/list")
    public List<BoardResponseDTO> getBoard(){
        List<BoardResponseDTO> responseList = boardlist.values().stream().map(BoardResponseDTO::new).toList();
        // boardlist.values() = 여러개 들어있는 보드리스트들
        // stream() = for문 처럼 하나하나씩 돌려줌
        // .toList () = list타입으로 변환
        return responseList;
    }

    @GetMapping("/detailList/{id}")
    public BoardResponseDTO detailListBoard(@PathVariable Long id) {
        if (boardlist.containsKey(id)) {
            Board board = boardlist.get(id);
            return new BoardResponseDTO(board);
        } else {
            throw new IllegalArgumentException("선택한 게시물은 존재하지 않습니다.");
        }
    }

    @PutMapping("/update/{id}")
    //Long으로 한 이유는 id 타입이 Long이니까
    public Long updateBoard(@PathVariable Long id,@RequestBody BoardRequestDTO boardRequestDTO){
        // 클라이언트에서 body부분에서 넘어온다 . 그래서 body의 json형식으로 넘어온다. body의 json형식 = RequestDTO를 생각하자
        // 해당 메모가 db에 존재하는 지 확인
        if(boardlist.containsKey(id)){
            //.해당 게시물 가져오기
            Board board = boardlist.get(id);

            if(board.getPw().equals(boardRequestDTO.getPw())){
                //게시물 수정
                board.update(boardRequestDTO);
                return board.getId();
            } else {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
        }else {
            throw new IllegalArgumentException("선택한 게시물은 존재하지 않습니다.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBoard(@PathVariable Long id, @RequestParam String pw) {
        if (boardlist.containsKey(id)) {
            Board board = boardlist.get(id);

            // 비밀번호 일치 여부 확인
            if (board.getPw().equals(pw)) {
                // 게시물 삭제
                boardlist.remove(id);

                // boardList 업데이트
                return "게시글이 삭제되었습니다.";
            } else {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
        } else {
            throw new IllegalArgumentException("선택한 게시물은 존재하지 않습니다.");
        }
    }
}
