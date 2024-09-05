package com.study.board.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data //
@Entity // 테이블 의미
public class Board { // 테이블 이름이랑 일치하게

    @Id
    //기본 키(Primary Key) 설정:
    // @Id 어노테이션이 붙은 필드는 데이터베이스 테이블의 기본 키로 설정됩니다.
    // 기본 키는 각 레코드를 고유하게 식별하며, 중복될 수 없습니다.

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    // 어노테이션은 JPA에서 기본 키 값을 자동으로 생성하는 전략을 정의할 때 사용됩니다.
    // 이 어노테이션은 @Id 어노테이션과 함께 사용되며,
    // 데이터베이스에서 기본 키 값을 자동으로 생성하도록 지정합니다.
    private Integer id;
    private String title;
    private String content;

}
