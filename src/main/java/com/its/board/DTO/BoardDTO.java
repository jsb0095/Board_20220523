package com.its.board.DTO;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

@Data
public class BoardDTO {
    Long id;
    String boardTitle;
    String boardWriter;
    String boardPassword;
    String boardContents;
    int boardHits;
    Timestamp boardCreatedDate;
    private MultipartFile boardFile;//saveFile.jsp에서 컨트롤러로 넘오올 때 파일을 담아오는 용도
    private String boardFileName;//상세조회 등을 할때 파일 이름을 담을 용도
}
