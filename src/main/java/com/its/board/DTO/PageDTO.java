package com.its.board.DTO;

import lombok.Data;




    @Data
    public class PageDTO {
        private int page;
        private int maxPage;
        private int startPage;
        private int endPage;
//        page에 현재 페이지 값
//        max페이지에 최대 페이지값
//        strart페이지에 처음나오는 페이지 값(3개씩 나온다고 하면 1,4,7,10~~)
//        end페이지에 마지막 페이지
    }
