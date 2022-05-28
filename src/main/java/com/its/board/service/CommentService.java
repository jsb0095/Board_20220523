package com.its.board.service;

import com.its.board.DTO.CommentDTO;
import com.its.board.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentService {
    @Autowired CommentRepository commentRepository;

    public void save(CommentDTO commentDTO) {
        commentRepository.save(commentDTO);

    }


    public List<CommentDTO> findAll(Long boardId) {
      return   commentRepository.findAll(boardId);
    }
}
