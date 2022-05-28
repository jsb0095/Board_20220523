package com.its.board.repository;

import com.its.board.DTO.BoardDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BoardRepository {
    @Autowired
    private SqlSessionTemplate sql;
    public int save(BoardDTO boardDTO) {
      int result= sql.insert("board.save",boardDTO);
      return result;
    }

    public List<BoardDTO> findAll() {
        return  sql.selectList("board.findAll");

    }

    public BoardDTO hitAdd(Long id) {
        sql.update("board.hitAdd",id);
        BoardDTO boardDTO = sql.selectOne("board.update",id);
        return boardDTO;
    }

    public int dropPasswordCheck(BoardDTO boardDTO) {
       return sql.delete("board.dropPasswordCheck",boardDTO);

    }

    public void update(BoardDTO boardDTO) {
        sql.update("board.update1",boardDTO);
    }


    public BoardDTO updatePassword(Long id) {
       return sql.selectOne("board.updatePassword",id);
    }


    public void saveFile(BoardDTO boardDTO) {
        sql.selectOne("board.saveFile",boardDTO);
    }
    public int boardCount() {
        return sql.selectOne("board.count");
    }

    public List<BoardDTO> pagingList(Map<String, Integer> pagingParam) {
        return sql.selectList("board.pagingList", pagingParam);
    }


    public List<BoardDTO> search(Map<String, String> searchParam) {
        return sql.selectList("board.search",searchParam);
    }
}
