package com.first_crud.repository;

import com.first_crud.DTO.BoardRequestDTO;
import com.first_crud.DTO.BoardResponseDTO;
import com.first_crud.entity.Board;
import jakarta.persistence.EntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class BoardRepository {

    private final JdbcTemplate jdbcTemplate;

    public BoardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Board save(Board board) {
        // DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체

        String sql = "INSERT INTO board (username, contents, pw, title) VALUES (?, ?, ?, ?)";


        jdbcTemplate.update(con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, board.getUsername());
                    preparedStatement.setString(2, board.getContents());
                    preparedStatement.setString(3, board.getPw());
                    preparedStatement.setString(4, board.getTitle());

                    return preparedStatement;
                },
                keyHolder);

        // DB Insert 후 받아온 기본키 확인
        Long id = keyHolder.getKey().longValue();
        board.setId(id);

        return board;
    }

    public List<BoardResponseDTO> findAll() {
        String sql = "SELECT * FROM board";

        return jdbcTemplate.query(sql, new RowMapper<BoardResponseDTO>() {
            public BoardResponseDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long id = rs.getLong("id");
                String username = rs.getString("username");
                String contents = rs.getString("contents");
                String title = rs.getString("title");
                String pw = rs.getString("pw");
                return new BoardResponseDTO(id, username, contents, title, pw);

            }
        });
    }

    public BoardResponseDTO find(Long id) {
        String sql = "SELECT * FROM board WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<BoardResponseDTO>() {
            public BoardResponseDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long id = rs.getLong("id");
                String username = rs.getString("username");
                String contents = rs.getString("contents");
                String pw = rs.getString("pw");
                String title = rs.getString("title");
                return new BoardResponseDTO(id, username, contents, title, pw);
            }
        });
    }

    public void update(Long id, BoardRequestDTO boardRequestDTO) {
        String sql = "UPDATE board SET username = ?, contents = ?, pw = ?, title = ? WHERE id = ?";

        jdbcTemplate.update(sql, boardRequestDTO.getUsername(), boardRequestDTO.getContents(),
                boardRequestDTO.getPw(), boardRequestDTO.getTitle(), id);
    }

    public void delete(Long id, String pw) {
        String sql = "DELETE FROM board WHERE id = ? AND pw = ?";
        jdbcTemplate.update(sql, id, pw);
    }


    public Board findById(Long id) {
        // DB 조회
        String sql = "SELECT * FROM board WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                Board board = new Board();
                board.setUsername(resultSet.getString("username"));
                board.setContents(resultSet.getString("contents"));
                board.setPw(resultSet.getString("pw"));
                board.setTitle(resultSet.getString("title"));
                return board;
            } else {
                return null;
            }
        }, id);
    }

    @Transactional
    public Board createBoard(EntityManager em) {
        Board memo = em.find(Board.class, 1);
        memo.setUsername("이름테스트");
        memo.setContents("@Transactional 전파 테스트 중!");
        memo.setTitle("@Transactional 타이틀");
        memo.setPw("@Transactional 123");

        System.out.println("createBoard 메서드 종료");
        return memo;
    }
}
