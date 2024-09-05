package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // 시작할때 여기가 컨트롤러라는걸 알려준다
public class BoardController {

    @Autowired
    private BoardService boardService; // controller에서 사용하니까 여기서만든다

    @GetMapping("/board/write") //localhost:8080/board/write 로 접속하면 보여주겠다
    public String boardWriteForm() {

        return "boardWrite";
    }

    @PostMapping("/board/writepro")
    public String boardWritPro(Board board) {
//        System.out.println("제목: "+ title); // 이렇게 넘어오는것 까지 확인 후 // DB에 저장 위해 레파지토리 만들기
//        System.out.println("내용: "+ content); // 근데 이거 많아지면 관리어려워서 entity에서 보낸다 -> 원래 dto에서 보내는거아닌가?
        System.out.println(board.getTitle());
        boardService.write(board);
        return "";
    }

    @GetMapping("/board/list")
    public String boardList(Model model) { // 데이터를 담아서 우리가보는 페이지로보내주는게 Model
        model.addAttribute("list",boardService.boardList()); // list로 담아서 넘긴다
        return "boardList";
    }

    @GetMapping("/board/view") // localhost:8080/board/view?id=1
    public String boardView(Model model, Integer id) {

        model.addAttribute("board",boardService.boardView(id));

        return "boardView";
    }

    @GetMapping("/board/delete") //board
    public String boardDelete(Integer id) {
        boardService.boardDelete(id);

        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id, Model model) { // integer형태의 id로 들어온다
        // PathVariable 사용하면 ? 안붙이고 역슬래쉬 뒤에 깔끔하게 들어옴
        model.addAttribute("board",boardService.boardView(id));

        return "boardmodify";
    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, Board board) {

        Board boardTemp = boardService.boardView(id); //기존에 있던 내용가지고오고
        boardTemp.setTitle(board.getTitle()); // 새로입력한내용 기존에 있던곳에 덮어씌움
        boardTemp.setContent(board.getContent()); // 원래는 jpa수정할때 이렇게 덮어씌워버리면 안됨

        boardService.write(boardTemp);

        return "redirect:/board/list";
    }
}
