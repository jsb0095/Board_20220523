package com.its.board.service;

import com.its.board.DTO.BoardDTO;
import com.its.board.DTO.PageDTO;
import com.its.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;


    public boolean save(BoardDTO boardDTO) {
        System.out.println("boardDTO = " + boardDTO);
        int result = boardRepository.save(boardDTO);
        if (result > 0) {
            return true;

        } else {
            return false;
        }
    }

    public List<BoardDTO> findAll() {

        List<BoardDTO> boardDTOList = boardRepository.findAll();

        return boardDTOList;
    }

    public BoardDTO hitAdd(Long id) {
        return boardRepository.hitAdd(id);
    }

    public boolean dropPasswordCheck(BoardDTO boardDTO) {
        int result = boardRepository.dropPasswordCheck(boardDTO);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void update(BoardDTO boardDTO) {
        boardRepository.update(boardDTO);
    }


    public BoardDTO updatePassword(Long id) {
        return boardRepository.updatePassword(id);
    }

    public void saveFile(BoardDTO boardDTO) throws IOException {
//        1.DTO객체에 담긴 파일을 꺼냄
        MultipartFile boardFile = boardDTO.getBoardFile();
//         * 2.파일의 이름을 가져옴
        String boardFileName = boardFile.getOriginalFilename();
//         * 2.1 파일 이름 중복을 피하기 위한 조치  currentTimeMillis()1/1000초 을 이용해  파일 이름에 적용 ex)중복파일 방지
        boardFileName = System.currentTimeMillis() + "-" + boardFileName;
//         * 3.파일 이름을 DTO객체의 boardFileName에 저장
        boardDTO.setBoardFileName(boardFileName);
//        * 4.파일 저장위치 지정
        String savePath = "C:\\Spring_img\\" + boardFileName;
//          * 5.파일 저장처리
//        if(!boardFile.isEmpty()){
//           boardFile.transferTo(new File(savePath));
        //isEmpty: 문자열의 길이가 0인 경우에, true를 리턴합니다.
        if (!boardFile.isEmpty()) {
            boardFile.transferTo(new File(savePath));
        }
//             6.DTO객체 repository로 전달
        boardRepository.saveFile(boardDTO);
    }


    private static final int PAGE_LIMIT = 3;
    //page 글개수
    private static final int BLOCK_LIMIT = 3;

    //BLOCK [1] [2] [3] => [4][5][6]페이지 넘어가는 버튼
    public List<BoardDTO> pagingList(int page) {
        int pagingStart = (page - 1) * PAGE_LIMIT;
        //pagingStart  page=3 3-1=2 2*3=6인덱스(값:7) page=4 4-1=3 3*3=9인덱스(값:10)
        Map<String, Integer> pagingParam = new HashMap<>();
//                      Map<String, Integer>↓
        pagingParam.put("start", pagingStart);
        pagingParam.put("limit", PAGE_LIMIT);
        List<BoardDTO> pagingList = boardRepository.pagingList(pagingParam);
        return pagingList;
    }

    public PageDTO paging(int page) {
        int boardCount = boardRepository.boardCount();
//       Math(수학).ceil(소수점 아래 첫째 자리 올림(1~9))
        int maxPage = (int) (Math.ceil((double) boardCount / PAGE_LIMIT));
        int startPage = (((int) (Math.ceil((double) page / BLOCK_LIMIT))) - 1) * BLOCK_LIMIT + 1;
        //page=3 3/3=1.0 ceil로 올려도 1 => 1-1=0(int형변환)=0 0*3=0 0+1=(1)시작페이지
        int endPage = startPage + BLOCK_LIMIT - 1;
        //  마지막페이지 3+3=6-1=5(인덱스6)
        if (endPage > maxPage) {
            endPage = maxPage;
        }
        PageDTO paging = new PageDTO();
        paging.setPage(page);
        paging.setStartPage(startPage);
        paging.setEndPage(endPage);
        paging.setMaxPage(maxPage);
        return paging;
    }


    public List<BoardDTO> search(String searchType, String q) {
        Map<String, String> searchParam = new HashMap<>();
        searchParam.put("type", searchType);
        searchParam.put("q", q);
        List<BoardDTO> searchList = boardRepository.search(searchParam);
        return searchList;
    }


}
