package com.study.board.controller;

import com.study.board.service.BoardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
// static은 자동 import 안됨


class BoardControllerTest { // 1. 왜 public 안붙이나

  private MockMvc mockMvc; // MockMvc는 스프링 MVC의 동작을 테스트할 수 있게 해주는 유틸리티 클래스 // 2.Mvc 설명

  @Mock
  private BoardService boardService; // BoardService를 Mock 객체로 생성.
  // @Mock 어노테이션:
  //BoardService 객체를 Mock으로 생성합니다. Mock 객체는 실제 서비스의 동작을 모방하되, 실제 구현과는 독립적으로 동작합니다.

  @InjectMocks   // 테스트할 대상인 BoardController를 InjectMocks로 주입받습니다. // 3. MockMvc @Mock @InjectMocks 는 테스트에서 필수인가
  private BoardController boardController;
  // @InjectMocks 어노테이션:
  //BoardController 객체를 생성하고, @Mock으로 생성된 BoardService를 이 BoardController에 주입합니다.

  @BeforeEach // 각 테스트가 실행되기 전에 Mock 객체들을 초기화하고 MockMvc를 설정합니다.
  void setUp() {
      MockitoAnnotations.openMocks(this);
      mockMvc = MockMvcBuilders.standaloneSetup(boardController).build();
      //@BeforeEach 어노테이션:
      //각 테스트 메소드가 실행되기 전에 실행되는 메소드입니다.
      // MockitoAnnotations.openMocks(this)를 호출하여 Mock 객체들을 초기화하고, MockMvc를 설정합니다.
      //MockMvcBuilders.standaloneSetup(boardController).build()를 사용하여
      // BoardController만을 대상으로 하는 MockMvc 인스턴스를 생성합니다.
      // 이는 MockMvc를 이용해 HTTP 요청을 시뮬레이션하고 응답을 검증할 수 있도록 합니다.
  }
  //----------------------------------------------------------여기까지가 controller test 준비 과정--------------------------

  @Test
  void testBoardWriteFrom() throws Exception {
      // /board/write 요청을 보냈을 때, boardWrite 뷰를 반환하는지 테스트
      mockMvc.perform(get("/board/write"))
              .andExpect(status().isOk()) // HTTP 상태 코드 200을 기대합니다.
              .andExpect(view().name("boardWrite"));  // 반환된 뷰 이름이 "boardWrite"인지 확인합니다.
  }
}
