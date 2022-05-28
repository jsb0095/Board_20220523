package com.its.board.controller;

import com.its.board.DTO.BoardDTO;
import com.its.board.DTO.CommentDTO;
import com.its.board.DTO.PageDTO;
import com.its.board.service.BoardService;
import com.its.board.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
public class BoardController {
    @Autowired private BoardService boardService;
    @Autowired private CommentService commentService;
    @GetMapping ("/board/save")
    public String save(){
        return "board/save";
    }

    @PostMapping ("/board/save")
    public String save(BoardDTO boardDTO){

     boardService.save(boardDTO);
        return "board/findAll";
    }
    @GetMapping("/board/findAll")
    public String findAll(Model model){
        List<BoardDTO> boardDTOList=  boardService.findAll();
        model.addAttribute("boardDTOList",boardDTOList);
        return "board/findAll";
    }
    @GetMapping("/detail")
    public String hitAdd(@RequestParam("id")Long id,Model model ,
                         @RequestParam(value="page" ,required = false,defaultValue = "1") int page){
//
       BoardDTO boardDTO = boardService.hitAdd(id);
       model.addAttribute("page",page);
        model.addAttribute("result",boardDTO);
        // 댓글 목록도 가져가야함
        List<CommentDTO>commentDTOList= commentService.findAll(id);
        model.addAttribute("commentList",commentDTOList);

        return "/board/detail";
    }
    @GetMapping("/password")
    public String dropPassword(@RequestParam("id")Long id, Model model){
        model.addAttribute("id",id);
        return "/board/dropPasswordCheck";
    }
    @PostMapping("/password")
    public String dropPasswordCheck(@ModelAttribute BoardDTO boardDTO){
       boolean result = boardService.dropPasswordCheck(boardDTO);
       if(result){
           return "redirect:/board/findAll";
       }else{
           return "board/dropPasswordCheck";
       }

    }

    @GetMapping("/update")
    public String updatePassword(@RequestParam("id")Long id,Model model){
       BoardDTO boardDTO= boardService.updatePassword(id);
        model.addAttribute("boardDTO",boardDTO);

        return "/board/update";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO){
        boardService.update(boardDTO);
        return "redirect:/detail?id="+boardDTO.getId();
    }

    @GetMapping("/board/saveFile")
    public String saveFileForm(){
        return "board/saveFile";



    }
    @PostMapping("/saveFile")
    public String saveFile(@ModelAttribute BoardDTO boardDTO) throws IOException {
        boardService.saveFile(boardDTO);
        return "redirect:/board/findAll";
    }
    @GetMapping("/board/paging")  //required 매개변수가 false 실행 => defaultValue 페이지 실행시 매인화면 기본(페이지)값이 1로 설정
    public String paging(@RequestParam(value="page", required=false, defaultValue="1") int page, Model model) {
                                      //value ="page"라는 단어
        List<BoardDTO> boardList = boardService.pagingList(page);

        PageDTO paging = boardService.paging(page);
        model.addAttribute("boardList", boardList);
        model.addAttribute("paging", paging);
        return "board/pagingList";
    }
    //검색처리
    @GetMapping("/board/search")
    public String search(@RequestParam("searchType")String searchType ,@RequestParam ("q") String q,Model model){
        List<BoardDTO> boardDTOList =boardService.search(searchType,q);

        model.addAttribute("boardList",boardDTOList);
        return "board/pagingList";
    }

}
